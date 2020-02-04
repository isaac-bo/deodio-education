package com.deodio.components.pagination.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.deodio.components.pagination.page.Page;
import com.deodio.components.pagination.page.PageContext;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 3576678797374122941L;

    @Override
    public Object intercept(Invocation invocation) throws Exception {

        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        if (mappedStatement.getId().matches(sqlPattern)) { 
            Object parameter = invocation.getArgs()[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String originalSql = boundSql.getSql().trim();
            Object parameterObject = boundSql.getParameterObject();
            if (null == boundSql.getSql()|| "".equals(boundSql.getSql()))
            {
                return null;
            }
            Page page = null;
            PageContext context = PageContext.getPageContext();

            if (parameterObject instanceof HashMap){
            	if(((HashMap)parameterObject).get("pagination")!= null ) {
            		 page = convertParameter(((HashMap)parameterObject).get("pagination"), page);
            	}
            }else{
            	Field f = parameterObject.getClass().getDeclaredField("pagination");
            	f.setAccessible(true);
                page = convertParameter(f.get(parameterObject), page);
            }


            if (page == null) {
                page = context;
            }
            if (page != null) {
                int totPage = page.getTotalRows();
                if (totPage == 0) {
                    Connection connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
                    totPage = SQLHelp.getCount(originalSql, connection, mappedStatement, parameterObject, boundSql);
                }

                page.init(totPage, page.getPageSize(), page.getCurrentPage());

                String pageSql = SQLHelp.generatePageSql(originalSql, page, dialect);
                if (log.isDebugEnabled()) {
                    log.debug("SQL:" + pageSql);
                }
                invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
                BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(),
                									pageSql, boundSql.getParameterMappings(), 
                									boundSql.getParameterObject());
                
                Field metaParamsField = ReflectUtil.getFieldByFieldName(boundSql, "metaParameters");
                
                if (metaParamsField != null) {
                    MetaObject mo = (MetaObject) ReflectUtil.getValueByFieldName(boundSql, "metaParameters");
                    ReflectUtil.setValueByFieldName(newBoundSql, "metaParameters", mo);
                }
                
                MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

                invocation.getArgs()[0] = newMs;
            }
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    } 
    
    @Override
    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                    SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
