package com.deodio.components.pagination.page;


public class PageContext extends Pagination {
   
    private static final long serialVersionUID = -3294902812084550562L;

    
    private static final ThreadLocal<PageContext> PAGE_CONTEXT_THREAD_LOCAL = new ThreadLocal<PageContext>();

    public static PageContext getPageContext() {
        PageContext ci = PAGE_CONTEXT_THREAD_LOCAL.get();
        if (ci == null) {
            ci = new PageContext();
            PAGE_CONTEXT_THREAD_LOCAL.set(ci);
        }
        return ci;
    }

    public static void removeContext() {
        PAGE_CONTEXT_THREAD_LOCAL.remove();
    }

}
