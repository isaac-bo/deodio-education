package com.deodio.components.pagination.page;

import java.io.Serializable;
import java.util.List;


public interface Page<T> extends Serializable {
	
	List<T> getResult();
	
    void setResult(List<T> result);
    
    int getCurrentPage();

    boolean isNext();

    boolean isPrevious();

    int getPageEndRow();

    int getPageSize();

    int getPageStartRow();

    int getTotalPages();

    int getTotalRows();
    
    int getPrePage();
    
    int getNextPage();

    void setTotalPages(int i);

    void setCurrentPage(int i);

    void setNext(boolean b);

    void setPrevious(boolean b);

    void setPageEndRow(int i);

    void setPageSize(int i);

    void setPageStartRow(int i);

    void setTotalRows(int i);

    void init(int rows, int pageSize, int currentPage);
}
