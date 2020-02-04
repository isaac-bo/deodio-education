package com.deodio.components.pagination.dialect.db;

import com.deodio.components.pagination.dialect.Dialect;

/**
 * Oracle
 * 
 */
public class OracleDialect implements Dialect {
	
	private static final String FOR_UPDATE = " for update";
	private static final int DEFAULT_SIZE = 100;
	
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
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
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(FOR_UPDATE)) {
            sql = sql.substring(0, sql.length() - FOR_UPDATE.length());
            isForUpdate = true;
        }
        StringBuilder pagingSelect = new StringBuilder(sql.length() + DEFAULT_SIZE);
        if (offset > 0) {
            pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
        } else {
            pagingSelect.append("select * from ( ");
        }
        pagingSelect.append(sql);
        if (offset > 0) {
            String endString = offsetPlaceholder + "+" + limitPlaceholder;
            pagingSelect.append(" ) row_ ) where rownum_ <= ")
                    .append(endString).append(" and rownum_ > ").append(offsetPlaceholder);
        } else {
            pagingSelect.append(" ) where rownum <= ").append(limitPlaceholder);
        }

        if (isForUpdate) {
            pagingSelect.append(FOR_UPDATE);
        }

        return pagingSelect.toString();
    }

}
