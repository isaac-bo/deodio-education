package com.deodio.core.constants;

public interface Constants {
	public static final String EXCEPTION_KEY = "EXCEPTION_KEY";
	public static final String LOGIN_SESSION_NAME = "SESSION_USER";
    public static final int FALSE_STATUS = 0;
    public static final int TRUE_STATUS = 1;
    public static final int FAILURE_STATUS = 0;
    public static final int SUCCESS_STATUS = 1;

    public static final String STR_TRUE_STATUS = "true";
    public static final String STR_FALSE_STATUS = "false";

    public static final String STR_TITLE="title";
    public static final String STR_PATH="path";
    
    public static final int STATUS_ZERO = 0;
    public static final int STATUS_ONE = 1;
    public static final int STATUS_TWO = 2;

    public static final int NUMBER_ZERO = 0;
    public static final int NUMBER_ONE = 1;
    public static final int NUMBER_TWO = 2;
    public static final int NUMBER_THREE = 3;
    public static final int NUMBER_FOUR = 4;
    public static final int NUMBER_FIVE = 5;
    public static final int NUMBER_SIX = 6;
    public static final int NUMBER_SEVEN = 7;
    public static final int NUMBER_EIGHT = 8;
    public static final int NUMBER_NINE = 9;
    public static final int NUMBER_THIRTY = 30;

    public static final String STRING_ZERO = "0";
    public static final String STRING_ONE = "1";
    public static final String STRING_TWO = "2";
    
    public static final long LONG_ZERO = 0L;
    public static final long LONG_ONE = 1L;
    public static final long LONG_TWOE = 2L;
    public static final long LONG_THREE = 3L;
    
    public static final int PAGE_SIZE = 25;
    public static final int PAGE_SIZE_TEN = 10;
    public static final int PAGE_SIZE_FIVE = 5;
    public static final int PAGE_SIZE_SIX = 6;
    public static final int PAGE_SIZE_SEVEN = 7;
    public static final int PAGE_CURRENT_PAGE = 1;
    public static final int PAGE_TOTAL_PAGES = 0;
    public static final int PAGE_TOTAL_ROWS = 0;
    public static final int PAGE_START_ROW = 0;
    public static final int PAGE_END_ROW = 0;
    public static final int PAGE_DEFAULT_NO_PAGINATION = 99999999;

    public static final String PAGING_INFO_DATA_LIST = "dataList";
    public static final String PAGING_INFO_CURREN_PAGE = "currePage";
    public static final String PAGING_INFO_TOTAL_PAGE = "totalPage";
    public static final String PAGING_INFO_TOTAL_ROW = "totalRow";

    public static final int DEFAULT_CURRENT_PAGE_NUMBER = 1;
    public static final int DEFAULT_TOTAL_ROWS_NUMBER = 0;
    
    
    public static final int T_BASE_WEIGHT_AUTHORITIES = 2;
    public static final int T_BASE_WEIGHT_TRIPS_AND_OTHERS = 3;
    public static final int T_BASE_WEIGHT_CAR_SERVICE = 4;
    public static final int T_BASE_WEIGHT_VGC_LABOR = 5;
    
    public static final int T_BASE_PRICE_AUTHORITIES = 2;
    public static final int T_BASE_PRICE_TRIPS_AND_OTHERS = 3;
    public static final int T_BASE_PRICE_CAR_SERVICE = 4;
    public static final int T_BASE_PRICE_VGC_LABOR = 5;
    
    public static final int T_PAYMENT_RECORD_PREVIOUS_QUARTER_TYPE = 1;
    public static final int T_PAYMENT_RECORD_CURRENT_QUARTER_TYPE = 2;
    
    public static final int T_PAYMENT_RECORD_UPDATE_FAX_TYPE = 0;
    public static final int T_PAYMENT_RECORD_UPDATE_PFV_TYPE = 1;
    public static final int T_PAYMENT_RECORD_UPDATE_AMOUNT_TYPE = 2;
    public static final int T_PAYMENT_RECORD_UPDATE_REMARK_TYPE = 3;
    
    public static final int T_FINANCE_RECORD_UPDATE_TYPE_4 = 4;
    public static final int T_FINANCE_RECORD_UPDATE_TYPE_5 = 5;
    public static final int T_FINANCE_RECORD_UPDATE_TYPE_6 = 6;
    public static final int T_FINANCE_RECORD_UPDATE_TYPE_7 = 7;
    public static final int T_FINANCE_RECORD_UPDATE_TYPE_8 = 8;
    
    
    public static final int PAYMENT_REPORT_DELETE_TYPE_PAYMENT_RECORD = 1;
    public static final int PAYMENT_REPORT_DELETE_TYPE_FINANCE_RECORD = 2;
    
    public static final String T_REMINDER_TYPE_BUDGET = "budget";
    public static final String T_REMINDER_TYPE_PAYMENT = "payment";
    public static final String T_REMINDER_TYPE_EXPENSE_RP4 = "expense_rp4";
    public static final String T_REMINDER_TYPE_INVOICE = "invoice";
    
    /**
     */
    public static final String CPRO_COUNTRY_CODE_CN = "CN";

    public static final int EXPORT_PER_NUM = 1000;
    public static final int EXPORT_PAGE_SIZE = 10000;
    public static final int EXPORT_PAGE_SIZE_4_ITEM = 8000;

    public static final String STRING_BRACKET = "()";
    public static final String STRING_DOT = ".";
    public static final String STRING_COMMA = ",";
    public static final String STRING_ROW = "_#_";
    public static final String STRING_DATA = "_#_";
    public static final String STRING_SLASH = "/";
    public static final String STRING_UNDER_LINE = "_";
    public static final String STRING_HORIZONTAL_LINE = "-";
    public static final String STRING_WHITESPACE = "";
    public static final String STRING_BACKSLASH = "\\";

    public static final String STRING_Y = "Y";
    public static final String STRING_N = "N";
    public static final String STRING_X = "X";
    public static final String STRING_L_X = "x";

    public static final String XML_SUFFIX = ".xml";
    public static final String EXCEL2003_SUFFIX = ".xls";
    public static final String EXCEL2007_SUFFIX = ".xlsx";
    public static final String WORD2007_SUFFIX = ".docx";
    public static final String WORD2003_SUFFIX = ".doc";
    public static final String PDF_SUFFIX = ".pdf";
    public static final String PNG_SUFFIX = ".png";
    
    public static final String URL_SUFFIX = ".html";

    public static final String USER_AGENT = "User-Agent";
    public static final String CHARSET_ISO_8859_1 = "ISO8859-1";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String RES_CHARSET_UTF_8="text/html;charset=UTF-8";

    public static final String BROWSER_IE = "IE";
    public static final String BROWSER_MISE = "MSIE";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_OPERA = "opera";

    public static final String PROXY_HTTP = "http://";
    
    public static final Integer COURSE_FINISH_PROFILES_RATE=10;
    public static final Integer COURSE_FINISH_BASE_RATE=20;
    public static final Integer COURSE_FINISH_GROUP_RATE=35;
    public static final String ERROR_PAGE_500 = "error/500";
    
    public static final Integer COURSE_FINISH_CAPABILITY_RATE=60;
    
    public static final Integer COURSE_FINISH_CERTIFICATE_RATE=80;
    
    public static final Integer COURSE_FINISH_PUBLISH_RATE=100;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_PB = 1;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_PT = 2;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_DS = 3;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_EVALUATION = 4;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_REMARK = 5;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_SOP = 6;
    public static final int EMISSION_PROJECT_EXTRA_UPDATE_MI = 7;
    
    public static final String CACHE_REOCRD_SPLIT_BUDGET = "#%&!";
    public static final String CACHE_REOCRD_NAME_BUDGET = "COOKIE_BUDGET";
    public static final String CACHE_REOCRD_NAME_YEAR = "COOKIE_YEAR";
    public static final String CACHE_REOCRD_NAME_BRAND = "COOKIE_BRAND";
    public static final String CACHE_REOCRD_TYPE_CCC = "1";
    public static final String CACHE_REOCRD_TYPE_EMISSION = "2";
    public static final int CACHE_RECORD_MAXAGE = 3600*24*30;
    public static final String CACHE_RECORD_CLEAR_COOKIE_STR = "0";
    
    public static final Integer  QUIZ_FINISH_PUBLISH_RATE=100;
    
    public static final Integer  QUIZ_CREATE_TYPE_MANUAL=1;//手动创建
    public static final Integer  QUIZ_CREATE_TYPE_AUTO=2;//自动创建试卷试卷
    public static final Integer  QUIZ_EXAMINATION_SCORE=0;//指定题型分数
    public static final Integer  QUIZ_BANK_SCORE=1;//题库分数
    
    public static final String SOCKET_MODEL_PROCESSTYPE_MEDIA_CONVERT = "1";//转换
    
    public static final String  SOCKET_MODEL_PROCESSTYPE_PROGRESS_QUERY = "2";//转换进度查询
    
    public static final String  SOCKET_MODEL_PROCESSTYPE_PARAMS_QUERY = "3";//多媒体参数查询
    
    public static final String  SOCKET_MODEL_PROCESSTYPE_MEDIA_DELETE = "4";//删除
    
    public static final String COOKIE_USER_ID="CUID";
	public static final String COOKIE_ACCOUNT_ID="CAID";
	public static final String COOKIE_USER_NAME="CUNAME";
	public static final String COOKIE_NICK_NAME="N_NAME";
	public static final String COOKIE_USER_PASS="CUKEY";
	public static final String COOKIE_GROUP_ID = "CGID";
	
	//add by xu.xiangdong  start
	public static final int  COURSE_OPERATE_TYPE_INSERT = 0;
	public static final int COURSE_OPERATE_TYPE_UPDATE = 1;
	public static final int COURSE_OPERATE_TYPE_DEL = 2;
	
	public static final short COURSE_TRAINEE_STATUS_ACTIVE = 0;//通过
	public static final short COURSE_TRAINEE_STATUS_SUSPEND = 1;//拒绝
	public static final short COURSE_TRAINEE_STATUS_SUBSTITUTE = 2;//替补
	public static final short COURSE_TRAINEE_STATUS_TEMP = 3;//待审核
	
	
	//add by xu.xiangdong end
	//add by jian.zhang  start
	public static final int  SURVEY_OPERATE_TYPE_SAVE  = 0;
	public static final int SURVEY_OPERATE_TYPE_SUMBIT  = 1;
	//add by jian.zhang  end
	//add by cheng.wang start
	public static final int COURSE_MODULE_PAGESIZE=5;
	
	public static final String ATTACH_TYPE_LOG="1";
	public static final String ATTACH_TYPE_FOOTER="2";
	public static final String ATTACH_TYPE_HEADER="3";
	public static final String ATTACH_TYPE_GROUP="4";
	public static final String ATTACH_TYPE_PRESENTATION="5";
	public static final String ATTACH_TYPE_COURSE="6";
	public static final String ATTACH_TYPE_ID_CARD_IMG="7";
	public static final String ATTACH_TYPE_BUSINESS_LICENCE_IMG="8";
	public static final String ATTACH_TYPE_COURSE_MANAGER_FILES="9";
	public static final String ATTACH_TYPE_COURSE_MANAGER_HOMEWORK="10";
	public static final String ATTACH_TYPE_QUIZ_IMG_QUISTION="11";
	public static final String ATTACH_TYPE_NO_STANDARD_PACKAGE="51";
	//add by cheng.wang end
	

	public static final short SHORT_STATUS_ZERO = 0;//通过
	public static final short SHORT_STATUS_ONE = 1;//通过
}
