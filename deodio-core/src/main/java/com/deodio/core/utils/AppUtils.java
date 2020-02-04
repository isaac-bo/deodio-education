package com.deodio.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtils extends Utils{

    // defautl application properties
    protected static final String APPLICATION_PROPERTIES = "properties/application.properties";
    protected static final String EMAIL_PROPERTIES = "properties/mail.properties";

    /**
     * Logger for this class
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AppUtils.class);
    protected static PropUtils PROPERTIES_UTILS = null;

    public static  String THREAD_POOL_CORE_SIZE = "";
	public static  String THREAD_POOL_MAX_SIZE = "";
	public static  String THREAD_POOL_KEEP_ALIVE_TIME = "";
	public static  String THREAD_POOL_CAPACITY = "";

	public static  String SMS_SERVER_URL = "";
	public static  String SMS_SERVER_USERNAME = "";
	public static  String SMS_SERVER_PASSWORD = "";

	//邮件地址
	public static  String MAIL_SMTP_FROM = "";
	public static  String MAIL_HOST = "";
	public static  String MAIL_PORT  = "";
	public static  String MAIL_USERNAME  = "";
	public static  String MAIL_PASSWORD  = "";
	public static  String MAIL_TRANSPORT_PROTOCOL  = "";
	public static  String MAIL_SMTP_STARTTLS_ENABLE  = "";
	public static  String MAIL_SMTP_AUTH  = "";
	public static  String MAIL_SMTP_TIMEOUT = "";
	public static  String MAIL_SMTP_SSL_TRUST  = "";
	
	public static  String API_BAIDU_FANYI_APP_ID = "";
	public static  String API_BAIDU_FANYI_SECURITY_KEY = "";
    
    public void init(){
    	THREAD_POOL_CORE_SIZE = PROPERTIES_UTILS.getValue("thread.pool.core.size");
	    THREAD_POOL_MAX_SIZE = PROPERTIES_UTILS.getValue("thread.pool.max.size");
	    THREAD_POOL_KEEP_ALIVE_TIME = PROPERTIES_UTILS.getValue("thread.pool.keep.alive.time");
	    THREAD_POOL_CAPACITY = PROPERTIES_UTILS.getValue("thread.pool.capacity");
	    
	    SMS_SERVER_URL = PROPERTIES_UTILS.getValue("sms.server.url");
	    SMS_SERVER_USERNAME = PROPERTIES_UTILS.getValue("sms.server.username");
	    SMS_SERVER_PASSWORD = PROPERTIES_UTILS.getValue("sms.server.password");
	     
	    MAIL_SMTP_FROM= PROPERTIES_UTILS.getValue("mail.smtp.from");
	    MAIL_HOST = PROPERTIES_UTILS.getValue("mail.host");
	    MAIL_PORT  = PROPERTIES_UTILS.getValue("mail.port");
	    MAIL_USERNAME  = PROPERTIES_UTILS.getValue("mail.username");
        MAIL_PASSWORD  = PROPERTIES_UTILS.getValue("mail.password");
        MAIL_TRANSPORT_PROTOCOL  = PROPERTIES_UTILS.getValue("mail.transport.protocol");
        MAIL_SMTP_STARTTLS_ENABLE  = PROPERTIES_UTILS.getValue("mail.smtp.starttls.enable");
        MAIL_SMTP_AUTH  = PROPERTIES_UTILS.getValue("mail.smtp.auth");
        MAIL_SMTP_TIMEOUT = PROPERTIES_UTILS.getValue("mail.smtp.timeout");
        MAIL_SMTP_SSL_TRUST  = PROPERTIES_UTILS.getValue("mail.smtp.ssl.trust");
        
        API_BAIDU_FANYI_APP_ID  = PROPERTIES_UTILS.getValue("api.baidu.fanyi.app.id");
        API_BAIDU_FANYI_SECURITY_KEY  = PROPERTIES_UTILS.getValue("api.baidu.fanyi.security.key");
    }
}