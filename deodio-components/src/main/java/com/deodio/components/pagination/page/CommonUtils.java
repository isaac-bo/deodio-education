package com.deodio.components.pagination.page;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static <T> Map<String, Object> outPrintJsonMapForPage(PageData<T> pageData){
    	Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", pageData.getList());
		jsonMap.put("currePage", pageData.getPageRequest().getPageNo());
		jsonMap.put("totalRow", pageData.getPageRequest().getTotalRow());
		jsonMap.put("totalPage", pageData.getPageRequest().getPageTotal());
		
		return jsonMap;
    }
}
