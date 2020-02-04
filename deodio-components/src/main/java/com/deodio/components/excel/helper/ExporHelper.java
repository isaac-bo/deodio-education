package com.deodio.components.excel.helper;

import java.text.SimpleDateFormat;

public class ExporHelper {
    
    
    private ExporHelper() {
    }

    public static String rtnDate(Object o){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(o);
    }
    
    public static String rtnDateTime(Object o){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(o);
    }

}
