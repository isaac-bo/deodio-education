package com.deodio.core.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * object 转Json帮助类
 * 
 * @author Viken
 * 
 */
public class JsonUtils extends Utils {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    public static void objToJson(Object obj, HttpServletResponse response) {
        try {
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(obj).toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            LOGGER.error("object to json  error", e.getMessage());
            e.printStackTrace();
        }
    }

    public static String objToJosn(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj).toString();
    }
    
    
    public static <T> T  jsonToObject(String str,Class<T> clazz){
    	Gson gson = new Gson();
    	T o = gson.fromJson(str, clazz);
    	return o;
    }
    
    
    public static <T> List<T> jsonToList(String str,Class<T> clazz){
    	Gson gson = new Gson();
    	List<T> list = gson.fromJson(str, new TypeToken<List<T>>(){}.getType());
    	return list;
    }
    
}
