package com.deodio.components.pagination.dialect.db;

import com.deodio.components.pagination.dialect.Dialect;

/**
 * PostgreSQL
 * @author botao
 *
 */
public class PostgreSQLDialect implements Dialect {
	private static final String LIMIT_KEYWORD = " limit ";
	private static final String OFFSET_KEYWORD = " offset ";
	

    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset),
                Integer.toString(limit));
    }

    /**
     * The the sql becomes paging sql statement, provided the offset and limit use to replace the placeholder (placeholder).
     * <pre>
     * like mysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") will return
     * select * from user limit :offset,:limit
     * </pre>
     *
     * @param sql               
     * @param offset            
     * @param offsetPlaceholder 
     * @param limitPlaceholder  
     * @return sql
     */
    public String getLimitString(String sql, int offset,
                                 String offsetPlaceholder, String limitPlaceholder) {
        StringBuilder pageSql = new StringBuilder().append(sql);
        pageSql = offset <= 0
                ? pageSql.append(LIMIT_KEYWORD).append(limitPlaceholder) :
                pageSql.append(LIMIT_KEYWORD).append(limitPlaceholder).append(OFFSET_KEYWORD).append(offsetPlaceholder);
        return pageSql.toString();
    }
}
