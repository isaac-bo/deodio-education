package com.deodio.components.pagination.page;

import com.deodio.components.pagination.annotation.Paging;

@Paging(field = "pagination")
public class PageRequest {
		protected int pageNo = 1;

	    protected int pageSize = 10;
	    
	    protected int count = 0;
	    
	    protected int pageTotal = 0;
	    
	    protected int totalRow = 0;
	    
	    private Page pagination;
	    
	    public Page getPagination() {
			return pagination;
		}

		public int getTotalRow() {
			return this.pagination.getTotalRows();
		}

		public void setTotalRow(int totalRow) {
			this.totalRow = totalRow;
		}
	    
		public void setPagination(Page pagination) {
			this.pagination = pagination;
		}


		public PageRequest(int pageNo){
	    	 this.pageNo=pageNo;
			 Page paginationSupport = new Pagination();
			 paginationSupport.setCurrentPage(this.getPageNo());
			 paginationSupport.setPageSize(this.getPageSize());
			 this.setPagination(paginationSupport);
	    }
	    
	    
	    public PageRequest(int pageNo,int totalCount) {
	    	 this.pageNo=pageNo;
	    	 Page paginationSupport = new Pagination();
			 paginationSupport.setCurrentPage(this.getPageNo());
			 paginationSupport.setPageSize(this.getPageSize());
			 this.setPagination(paginationSupport);
	    	 this.setCount(totalCount);
		}
	    
	    public PageRequest(int pageNo,int totalCount,int pageSize) {
	    	 this.pageNo=pageNo;
	    	 Page paginationSupport = new Pagination();
			 paginationSupport.setCurrentPage(this.getPageNo());
			 paginationSupport.setPageSize(this.getPageSize());
			 this.setPagination(paginationSupport);
	    	 this.pageSize = pageSize;
	    	 this.setCount(totalCount);
		}
	    
	    public int getOffset() {
	        return (this.getPageNo() - 1) * this.pageSize;
	    }
	    
		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			   this.pageNo = pageNo;

		        if (pageNo < 1) {
		            this.pageNo = 1;
		        }
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;

	        if (pageSize < 1) {
	            this.pageSize = 1;
	        }
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
			if (this.pageSize <= 0) {
				this.pageSize = 1;
	        }
			this.pageTotal = count%this.pageSize==0?count/this.pageSize:count/this.pageSize+1;
			
			if (this.pageNo > this.pageTotal) {
	        	this.pageNo = this.pageTotal;
	        }
			if (this.pageNo <= 0) {
				this.pageNo = 1;
	        }
		}

		public int getPageTotal() {
			//return this.pagination.getTotalPages();
			return this.pageTotal==0?this.pagination.getTotalPages():this.pageTotal;
		}

		public void setPageTotal(int pageTotal) {
			this.pageTotal = pageTotal;
		}
	    
	    
	    
	    
}
