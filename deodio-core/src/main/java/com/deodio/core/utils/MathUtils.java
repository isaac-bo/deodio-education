package com.deodio.core.utils;


import java.text.DecimalFormat;
import java.util.regex.Pattern;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is utils for math, it extends utils class
 * @ClassName: MathUtils
 * @author isaac
 * @date 2014-8-2
 */
public class MathUtils extends Utils {
    private final static Logger LOGGER= LoggerFactory.getLogger(MathUtils.class);
    
    private static Evaluator eval = new Evaluator();  
    
    /**
     * Calc String Expr.
     * @param str   expr String
     * @param len   
     * @return String result of calc expr
     */
    public static String calcString(String str,int len){
       String result="";
       try {
           if(len==-1){
               return eval.evaluate(str);
           }else{
               result =  String.format("%."+len+"f",Double.parseDouble(eval.evaluate(str)));
           }
        } catch (EvaluationException e) {
            LOGGER.error(e.getMessage(),e);
        }
       return result;
    }
    
    /**
     * Format the string to number with pattern
     * 	such as 100000000 to 100,000,000.00
     * @Title: getDecimalFormat
     * @param str
     * @return String
     */
    public static String getDecimalFormat(String str){   
        DecimalFormat   fmt   =   new   DecimalFormat("##,###,###,###,##0.00");     
        String outStr = null;   
        double d;   
        try {
            d = Double.parseDouble(str);   
            outStr = fmt.format(d);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(),e);
        }   
        return outStr;   
    }   
    
    /**
     * check string is double
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {  
        Pattern pattern = Pattern.compile("^[-//+]?//d+(//.//d*)?|//.//d+$");  
        return pattern.matcher(str).matches();  
      } 
}
