
package com.deodio.components.pagination.interceptor;

import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.deodio.components.pagination.page.Page;
import com.deodio.core.utils.ReflectionUtils;

import java.sql.Connection;
import java.util.Properties;

@SuppressWarnings("UnusedDeclaration")
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class PreparePaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = -6075937069117597841L;

    public PreparePaginationInterceptor() {
        super();
    }

    @Override
    public Object intercept(Invocation ivk) throws Exception {
        if (ivk.getTarget().getClass().isAssignableFrom(RoutingStatementHandler.class)) {
            final RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
            final BaseStatementHandler delegate = (BaseStatementHandler) ReflectionUtils.getFieldValue(statementHandler, DELEGATE);
            final MappedStatement mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(delegate, MAPPED_STATEMENT);

            if (mappedStatement.getId().matches(sqlPattern)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();
                if (parameterObject == null) {
                    throw new NullPointerException("parameterObject can't be instance.");
                } else {
                    final Connection connection = (Connection) ivk.getArgs()[0];
                    final String sql = boundSql.getSql();
                    final int count = SQLHelp.getCount(sql, connection,
                            mappedStatement, parameterObject, boundSql);
                    Page page = null;
                    page = convertParameter(parameterObject, page);
                    page.init(count, page.getPageSize(), page.getCurrentPage());
                    String pagingSql = SQLHelp.generatePageSql(sql, page, dialect);
                    if (log.isDebugEnabled()) {
                        log.debug("SQL:" + pagingSql);
                    }
                    ReflectionUtils.setFieldValue(boundSql, "sql", pagingSql);
                }
            }
        }
        return ivk.proceed();
    }


    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        initProperties(properties);
    }
}
