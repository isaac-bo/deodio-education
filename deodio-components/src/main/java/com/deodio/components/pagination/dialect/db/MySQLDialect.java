package com.deodio.components.pagination.dialect.db;

import com.deodio.components.pagination.dialect.Dialect;

/**
 * MySql
 * @author botao
 *
 */
public class MySQLDialect implements Dialect {


    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset),
                Integer.toString(limit));
    }

    public boolean supportsLimit() {
        return true;
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
    public String getLimitString(String sql, int offset, String offsetPlaceholder, String limitPlaceholder) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if (offset > 0) {
            stringBuilder.append(offsetPlaceholder).append(",").append(limitPlaceholder);
        } else {
            stringBuilder.append(limitPlaceholder);
        }
        return stringBuilder.toString();
    }

}
