package com.deodio.core.utils;

import java.net.URL;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceCompositeResolver;
import org.apache.commons.mail.resolver.DataSourceFileResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

public final class EmailUtil extends Utils {
//    private final static Logger LOGGER= LoggerFactory.getLogger(MathUtils.class);
    
    private EmailUtil() {
    	
    }
    
    public static ImageHtmlEmail getEmail() throws Exception{
    	ImageHtmlEmail email = new ImageHtmlEmail();
    	email.setHostName(AppUtils.MAIL_HOST);
        email.setSmtpPort(convertStrToInt(AppUtils.MAIL_PORT));
        email.setFrom(AppUtils.MAIL_SMTP_FROM, AppUtils.MAIL_SMTP_FROM);
        if(Boolean.TRUE.toString().equals(AppUtils.MAIL_SMTP_AUTH)){
        	email.setAuthenticator(new DefaultAuthenticator(AppUtils.MAIL_USERNAME, AppUtils.MAIL_PASSWORD));  
        }
        email.setDebug(true);//可以看到执行过程的debug信息  
        email.setCharset("UTF-8");//防止乱码  
        email.setSocketTimeout(convertStrToInt(AppUtils.MAIL_SMTP_TIMEOUT));
        email.setStartTLSEnabled(Boolean.valueOf(AppUtils.MAIL_SMTP_STARTTLS_ENABLE));
//        if(Boolean.TRUE.toString().equals(AppUtils.MAIL_SMTP_SSL_TRUST)){
//        	email.setSSLCheckServerIdentity(true);  
//        	email.setss
//            email.setSslSmtpPort(AppUtils.MAIL_HOST); // 设定SSL端口
//        }
          
        DataSourceResolver[] dataSourceResolvers =   
                new DataSourceResolver[]{
                		new DataSourceFileResolver(),//添加DataSourceFileResolver用于解析本地图片  
                		new DataSourceUrlResolver(new URL("http://")),
                		new DataSourceUrlResolver(new URL("https://"))};//添加DataSourceUrlResolver用于解析网络图片，注意：new URL("http://")  
        email.setDataSourceResolver(new DataSourceCompositeResolver(dataSourceResolvers,true));  
    	
    	return email;
    }
    
    
    public static void SendHtmlEmailWithImg(String toUser,String subjectTitle,String contents) throws Exception {  
    	
    	ImageHtmlEmail email = EmailUtil.getEmail();
     
        email.addTo(toUser, toUser);    
        email.setSubject(subjectTitle);  
          
        email.setHtmlMsg(contents);  
        //如果客户端不去持HTML格式会显示这句话，不过应该很少有不支持HTML格式的客户端了吧  
        email.setTextMsg("你的邮箱客户端不支持HTML格式邮件");  
        email.send();  
    }  
    
    
    
    public static int convertStrToInt(String value) throws Exception{
    	int result = 0;
    	value = StringUtils.trim(value);
    	if(StringUtils.isNumeric(value)){
    		result = StringUtils.string2Integer(value.trim());
    	}else{
    		throw new Exception("字符串(" + value + ")不能转换为Int");
    	}
    	
    	return result;
    }
    
}
