package com.deodio.components.pagination.page;

import java.util.List;

import com.deodio.core.constants.Constants;


public class Pagination<T> implements Page<T> {
    
    private static final long serialVersionUID = 8919076199499894558L;
    
    protected List<T> result;
    protected int pageSize = Constants.PAGE_SIZE;
    protected int currentPage = Constants.PAGE_CURRENT_PAGE;
    protected int totalPages = Constants.PAGE_TOTAL_PAGES;
    protected int totalRows = Constants.PAGE_TOTAL_ROWS;
    protected int pageStartRow = Constants.PAGE_START_ROW;
    protected int pageEndRow = Constants.PAGE_END_ROW;
    boolean next = Boolean.FALSE;
    boolean previous = Boolean.FALSE;

    public Pagination(int rows, int pageSize) {
        this.init(rows, pageSize);
    }

    public Pagination() {

    }

    public void init(int rows, int pageSize) {

        this.pageSize = pageSize;

        this.totalRows = rows;

        if ((totalRows % pageSize) == 0) {
            totalPages = totalRows / pageSize;
        } else {
            totalPages = totalRows / pageSize + 1;
        }

    }

    @Override
    public void init(int rows, int pageSize, int currentPage) {

        this.pageSize = pageSize;

        this.totalRows = rows;

        if(totalRows/pageSize == 0){
        	totalPages = totalRows / pageSize + 1;
        }else if ((totalRows % pageSize) == 0) {
            totalPages = totalRows / pageSize;
        } else {
            totalPages = totalRows / pageSize + 1;
        }
        if (currentPage != 0){
            gotoPage(currentPage);
        }
    }

    private void calculatePage() {
        previous = (currentPage - 1) > 0;

        next = currentPage < totalPages;

        if (currentPage * pageSize < totalRows) {
            pageEndRow = currentPage * pageSize;
            pageStartRow = pageEndRow - pageSize;
        } else {
            pageEndRow = totalRows;
            pageStartRow = pageSize * (totalPages - 1);
        }

    }

    public void gotoPage(int page) {

        currentPage = page;

        calculatePage();

        // debug1();
    }

    public String debugString() {

        return "Total Rows:" + totalRows + "Total Pages:" + totalPages + "Current Page:"
                + currentPage + "Previous:" + previous + "Next:"
                + next + "Start Row:" + pageStartRow + "End Row:" + pageEndRow;

    }


    @Override
    public int getCurrentPage() {
        return currentPage;
    }


    @Override
    public boolean isNext() {
        return next;
    }


    @Override
    public boolean isPrevious() {
        return previous;
    }


    @Override
    public int getPageEndRow() {
        return pageEndRow;
    }


    @Override
    public int getPageSize() {
        return pageSize;
    }


    @Override
    public int getPageStartRow() {
        return pageStartRow;
    }


    @Override
    public int getTotalPages() {
        return totalPages;
    }


    @Override
    public int getTotalRows() {
        return totalRows;
    }


    @Override
    public void setTotalPages(int i) {
        totalPages = i;
    }


    @Override
    public void setCurrentPage(int i) {
        currentPage = i;
    }


    @Override
    public void setNext(boolean b) {
        next = b;
    }


    @Override
    public void setPrevious(boolean b) {
        previous = b;
    }


    @Override
    public void setPageEndRow(int i) {
        pageEndRow = i;
    }


    @Override
    public void setPageSize(int i) {
        pageSize = i;
    }


    @Override
    public void setPageStartRow(int i) {
        pageStartRow = i;
    }


    @Override
    public void setTotalRows(int i) {
        totalRows = i;
    }
    
    
    @Override
	public List<T> getResult() {
		return result;
	}
    
    @Override
	public void setResult(List<T> result) {
		this.result = result;
	}

	@Override
	public int getPrePage() {
		if (isPrevious()) {
			return getCurrentPage() - 1;
		} else {
			return getCurrentPage();
		}
	}

	@Override
	public int getNextPage() {
		if (isNext()) {
			return getCurrentPage() + 1;
		} else {
			return getCurrentPage();
		}
	}

	
    
	
}
