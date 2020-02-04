package com.deodio.components.pagination.dialect;

/**
 * This is similar with Hibernate's dialect, I just generate the pagination part.
 * @author botao
 *
 */
public interface Dialect {

	/**
	 * Database itself supports paging paging query mode
     * If the database does not support, no database paging
	 *
	 * @return true: support paging query mode
	 */
    public boolean supportsLimit();

    /**
     * Sql converted to a paging SQL, call paging sql
     *
     * @param sql    SQL
     * @param offset start
     * @param limit  number
     * @return return paging sql
     */
    public String getLimitString(String sql, int offset, int limit);

}
