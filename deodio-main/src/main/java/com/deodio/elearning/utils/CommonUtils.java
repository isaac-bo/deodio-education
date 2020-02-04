package com.deodio.elearning.utils;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.IOUtils;
import com.deodio.core.utils.PropUtils;

public class CommonUtils extends AppUtils {

	private String appPropsPath;

	public String getAppPropsPath() {
		return appPropsPath;
	}

	public void setAppPropsPath(String appPropsPath) {
		this.appPropsPath = appPropsPath;
	}

	
	public static String FMS_PROTOCAL_RMTP = "";
	public static String FMS_PROTOCAL_HTTP = "";
	public static String FMS_SERVER_IS_RED5 = "";
	public static String FMS_SERVER_NAME = "";
	public static String FMS_SERVER_APP = "";
	public static String FILE_LOCAL_DIR = "";

	public static String CONVERT_SERVER_URL = "";
	public static String CONVERT_SERVER_PORT = "";

	public static String PPT_BASE_DIR = "";
	public static String PNG_BASE_DIR = "";
	public static String SMB_BASE_DIR = "";
	public static String MEDIA_BASE_DIR = "";
	public static String IMGS_LOCAL_DIR = "";
	public static String SCROM_FILE_DIR = "";
	public static String FILE_BASE_FOLDER = "";
	public static String IMGS_SERVER_DIR = "";

	public static String HTTP_SERVER_URL = "";
	public static String HTTP_SERVER_URL_ACTIVE = "";
	public static String HTTP_SERVER_URL_SETPWD = "";
	public static String HTTP_SERVER_URL_STUDENT="";
	public static String APPLICATION_URL = "";
	public static String APPLICATION_NAME = "";
	public static String APPLICATION_PATH = "";

	public static String HOT_TAGS_SWITCH = "";
	public static String OPENOFFICE_SWFTOOLS_PATH = "";
	public static String OPENOFFICE_ENVIRONMENT = "";
	
	//阿里云视频点播
	public static  String ACCESS_KEY_ID = "";
	public static  String ACCESS_KEY_SECRET = "";
	public static  String MNS_ENDPOINT = "";
	public static  String MEDIA_WORKFLOW_NAME = "";
	public static  String OSS_REGION = "";
	public static  String OSS_ENDPOINT = "";
	public static  String VOD_REGION = "";
	public static  String VOD_PRODUCT = "";
	public static  String VOD_ENDPOINT = "";
	public static  String OPEN_ALIYUN_VOD = "";
	
	public static  String USER_GROUP_ROLE_GROUP_LEADER_ID = "";
	
	public static String APPLICATION_AUTO_GET_SYS_ACTION = "";
	public static String APPLICATION_DOMAIN_SECOND_OPEN = "";
	public static String DOMAIN_POSTFIX = "";
	public static String COOKIE_DOMAIN = "";
	
	public static String MEDIA_LOCAL_CONVERT_FLAG = "";
	
	public static String PDF2HTML_TOOL_LOCATION_WINDOWS = "";
	
	public static String CHECK_MAIL_SERVER_URL = "";
	
	public static String HTTP_CLIENT_URL = "";

	@PostConstruct
	public void init() {
		PROPERTIES_UTILS = new PropUtils(IOUtils.getResource(appPropsPath));
		
		super.init();
		
		FMS_PROTOCAL_RMTP = PROPERTIES_UTILS.getValue("fms.protocol.rtmp");
		FMS_PROTOCAL_HTTP = PROPERTIES_UTILS.getValue("fms.protocol.http");
		FMS_SERVER_IS_RED5 = PROPERTIES_UTILS.getValue("fms.server.is.red5");
		FMS_SERVER_NAME = StringUtils.split(PROPERTIES_UTILS.getValue("fms.server.list"), ',')[random(StringUtils.split(PROPERTIES_UTILS.getValue("fms.server.list"), ',').length - 1)];
		FMS_SERVER_APP = PROPERTIES_UTILS.getValue("fms.server.app");
		FILE_LOCAL_DIR = PROPERTIES_UTILS.getValue("file.local.dir");

		CONVERT_SERVER_URL = PROPERTIES_UTILS.getValue("convert.server.url");
		CONVERT_SERVER_PORT = PROPERTIES_UTILS.getValue("convert.server.port");

		PPT_BASE_DIR = PROPERTIES_UTILS.getValue("ppt.base.dir");
		PNG_BASE_DIR = PROPERTIES_UTILS.getValue("png.base.dir");
		SMB_BASE_DIR = PROPERTIES_UTILS.getValue("smb.base.dir");
		MEDIA_BASE_DIR = PROPERTIES_UTILS.getValue("media.base.dir");

		IMGS_LOCAL_DIR = PROPERTIES_UTILS.getValue("imgs.local.dir");
		SCROM_FILE_DIR = PROPERTIES_UTILS.getValue("scrom.local.dir");
		FILE_BASE_FOLDER = PROPERTIES_UTILS.getValue("file.base.folder");
		IMGS_SERVER_DIR = PROPERTIES_UTILS.getValue("imgs.server.dir");

		HTTP_SERVER_URL = PROPERTIES_UTILS.getValue("http.server.url");
		HTTP_SERVER_URL_ACTIVE= PROPERTIES_UTILS.getValue("http.server.url.active");
		HTTP_SERVER_URL_SETPWD= PROPERTIES_UTILS.getValue("http.server.url.setPwd");
		HTTP_SERVER_URL_STUDENT= PROPERTIES_UTILS.getValue("http.server.url.student");
		APPLICATION_URL = PROPERTIES_UTILS.getValue("application.url");
		APPLICATION_NAME = PROPERTIES_UTILS.getValue("application.name");
		APPLICATION_PATH = PROPERTIES_UTILS.getValue("application.path");

		HOT_TAGS_SWITCH = PROPERTIES_UTILS.getValue("hot.tags.switch");
		
	    OPENOFFICE_SWFTOOLS_PATH = PROPERTIES_UTILS.getValue("openoffice.swftools.path");
		OPENOFFICE_ENVIRONMENT = PROPERTIES_UTILS.getValue("openoffice.environment");
		
		//阿里云视频点播
		 ACCESS_KEY_ID = PROPERTIES_UTILS.getValue("aliyun.access.key.id");
		 ACCESS_KEY_SECRET = PROPERTIES_UTILS.getValue("aliyun.access.key.secret");
		 MNS_ENDPOINT = PROPERTIES_UTILS.getValue("aliyun.mns.endpoint");
		 MEDIA_WORKFLOW_NAME = PROPERTIES_UTILS.getValue("aliyun.media.workflow.name");
		 OSS_REGION = PROPERTIES_UTILS.getValue("aliyun.oss.region");
		 OSS_ENDPOINT = PROPERTIES_UTILS.getValue("aliyun.oss.endpoint");
		 VOD_REGION = PROPERTIES_UTILS.getValue("aliyun.vod.region");
		 VOD_PRODUCT = PROPERTIES_UTILS.getValue("aliyun.vod.product");
		 VOD_ENDPOINT = PROPERTIES_UTILS.getValue("aliyun.vod.endpoint");
		 OPEN_ALIYUN_VOD = PROPERTIES_UTILS.getValue("aliyun.vod.open");
		 
		 //初始化用户小组角色-groupLeader
		 USER_GROUP_ROLE_GROUP_LEADER_ID = PROPERTIES_UTILS.getValue("user.group.role.group.leader.id");
		 
		 APPLICATION_AUTO_GET_SYS_ACTION = PROPERTIES_UTILS.getValue("application.auto.get.sys.action");
		 APPLICATION_DOMAIN_SECOND_OPEN = PROPERTIES_UTILS.getValue("application.domain.second.open");
		 DOMAIN_POSTFIX = PROPERTIES_UTILS.getValue("domain.postfix");
		 COOKIE_DOMAIN = getCookieDomain();
		 
		 //视频是否需要转换
		 MEDIA_LOCAL_CONVERT_FLAG = PROPERTIES_UTILS.getValue("media.local.convert.flag");
		 //windows 系统下pdf转Html第三方工具磁盘位置
		 PDF2HTML_TOOL_LOCATION_WINDOWS = PROPERTIES_UTILS.getValue("pdf2html.tool.location.windows");
		 
		 //邮箱认证链接
		 CHECK_MAIL_SERVER_URL =  PROPERTIES_UTILS.getValue("check.mail.server.url");
		 
		 //公网访问路径配置
		 HTTP_CLIENT_URL =  PROPERTIES_UTILS.getValue("http.client.url");
				 
	}
	
	
	public static String getCookieDomain(){
		String result = null;
		if(Boolean.TRUE.toString().equals(APPLICATION_DOMAIN_SECOND_OPEN)){
			result = DOMAIN_POSTFIX;
		}
		return result;
	}
	//add by cheng.wang start
	//判断字符串是否全是数字组成
	public static boolean isInteger(String str) {  
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		return pattern.matcher(str).matches();  
	}
	//add by cheng.wang end
}
