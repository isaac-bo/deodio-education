package com.deodio.core.utils.TransTxtUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import com.deodio.core.utils.AppUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TransTxtUtil {
    
    @SuppressWarnings("unchecked")
	public static String translate(String appId,String securityKey,String query,String targetLanguage){
    	
    	TransApi api = new TransApi(appId, securityKey);
        String resJson = api.getTransResult(query, "auto", targetLanguage);
        System.out.println(resJson);
        //获取翻译结果
        Gson gson = new Gson();
        Map<String,Object> resMap = gson.fromJson(resJson, new TypeToken<Map<String,Object>>(){}.getType());
        ArrayList<Map<String,String>> transResults = (ArrayList<Map<String, String>>) resMap.get("trans_result");
        
        String dst = transResults.get(0).get("dst");
//        System.out.println(convertUnicodeToChinese(dst));
        return convertUnicodeToUTF8(dst);
    }
    
    public static String translate(String query,String targetLanguage){
    	return translate(AppUtils.API_BAIDU_FANYI_APP_ID,AppUtils.API_BAIDU_FANYI_SECURITY_KEY,query,targetLanguage);
    }

    public static void main(String[] args) {
        String query = "初生牛犊不怕虎！";
//        String query = "Failure is probably the fortification in your pole. It is like a peek your wallet as the thief, when you are thinking how to spend several hard-won lepta, when you are wondering whether new money, it has laid background. Because of you, then at the heart of the most lax, alert, and most low awareness, and left it godsend failed.";
//        String query = "apple";
        String resJson = translate(APP_ID,SECURITY_KEY,query,LANGUAGE_EN);
        System.out.println(resJson);
    }
    
    private static String convertUnicodeToUTF8(String unicode){
    	String result = "";
    	if(unicode != null && unicode.length() > 0 ){
    		
    		try {
				result = new String(unicode.getBytes("UTF-8"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	return result;
    }
    
//	http://api.fanyi.baidu.com/api/trans/product/apidoc（百度翻译官方网站）
// 	在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20170320000042643";
    private static final String SECURITY_KEY = "oT0y7ncsbwjoDWJ5ZGJo";
//    auto	自动检测
    public static final String LANGUAGE_AUTO = "auto";
//	  zh	中文
    public static final String LANGUAGE_ZH = "zh";
//    en	英语
    public static final String LANGUAGE_EN = "en";
//    yue	粤语
    public static final String LANGUAGE_YUE = "yue";
//    wyw	文言文
    public static final String LANGUAGE_WYW = "wyw";
//    jp	日语
    public static final String LANGUAGE_JP = "jp";
//    kor	韩语
    public static final String LANGUAGE_KOR = "kor";
//    fra	法语
    public static final String LANGUAGE_KRA = "fra";
//    spa	西班牙语
    public static final String LANGUAGE_SPA = "spa";
//    th	泰语
    public static final String LANGUAGE_TH = "th";
//    ara	阿拉伯语
    public static final String LANGUAGE_ARA = "ara";
//    ru	俄语
    public static final String LANGUAGE_RU = "ru";
//    pt	葡萄牙语
    public static final String LANGUAGE_PT = "pt";
//    de	德语
    public static final String LANGUAGE_DE = "de";
//    it	意大利语
    public static final String LANGUAGE_IT = "it";
//    el	希腊语
    public static final String LANGUAGE_EL = "el";
//    nl	荷兰语
    public static final String LANGUAGE_NL = "nl";
//    pl	波兰语
    public static final String LANGUAGE_PL = "pl";
//    bul	保加利亚语
    public static final String LANGUAGE_BUL = "bul";
//    est	爱沙尼亚语
    public static final String LANGUAGE_EST = "est";
//    dan	丹麦语
    public static final String LANGUAGE_DAN = "dan";
//    fin	芬兰语
    public static final String LANGUAGE_FIN = "fin";
//    cs	捷克语
    public static final String LANGUAGE_CS = "cs";
//    rom	罗马尼亚语
    public static final String LANGUAGE_ROM = "rom";
//    slo	斯洛文尼亚语
    public static final String LANGUAGE_SLO = "slo";
//    swe	瑞典语
    public static final String LANGUAGE_SWE = "swe";
//    hu	匈牙利语
    public static final String LANGUAGE_HU = "hu";
//    cht	繁体中文
    public static final String LANGUAGE_CHT = "cht";
//    vie	越南语
    public static final String LANGUAGE_VIE = "vie";

}
