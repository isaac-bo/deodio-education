
/**
 * @Title: DConstants.java
 * @Package com.deodio.elearning.constants
 * @author isaac
 * @date 2015-1-28
 * @version V1.0
*/
	
package com.deodio.elearning.constants;

import com.deodio.core.constants.Constants;


/**
 * @ClassName: DConstants
 * @author isaac
 * @date 2015-1-28
 */

public interface DConstants extends Constants{
	
	//accout审核状态
	public static final  String ACCOUNT_STATUS = "ACCSTA";//account审核未通过
	public static final  String ACCOUNT_STATUS_APPROVING_STRING = "0";//account审核中
	public static final  String ACCOUNT_STATUS_APPROVED_STRING = "1";//account审核通过
	public static final  Short ACCOUNT_STATUS_APPROVING_SHORT = 0;//account审核中
	public static final  Short ACCOUNT_STATUS_APPROVED_SHORT = 1;//account审核通过
	
	public static final  String CELL_DATA_DELIMITER = "_#_";
	public static final  String ERROR_DATA_DELIMITER = "_:::_";

	public static final Integer USER_STATUS_ACTIVATE = 1;
	public static final Integer USER_STATUS_UNACTIVATE = 0;
	
	public static final Integer USER_TYPE_IS_ACCOUNT = 1;
	public static final Integer USER_TYPE_IS_NORMAL = 0;
	
	public static final Integer ACCOUNT_IS_PERSONAL = 1;
	public static final Integer ACCOUNT_IS_COMPANY = 0;
	
	public static final Integer BILL_STATUS_ACTVATE = 1;
	public static final Integer BILL_STATUS_UNACTIVATE = 0;
	
	public static final Integer BUSINESS_LICENSE_INIT = 0;
	public static final Integer BUSINESS_LICENSE_APPROVING = 1;
	public static final Integer BUSINESS_LICENSE_APPROVED = 2;
	public static final Integer BUSINESS_LICENSE_REJECT = 3;
	
	public static final String USER_ACCOUNT_LIST_SIZE = "ACCOUNT_SIZE";
	public static final String USER_CURRENT_GROUP_ID = "CURRENT_GROUP";
	public static final String USER_SELF_ACCOUNT_ID="SELF_ACCOUNT_ID";
	
	public static final String COOKIE_USER_ID="CUID";
	public static final String COOKIE_ACCOUNT_ID="CAID";
	public static final String COOKIE_USER_NAME="CUNAME";
	public static final String COOKIE_NICK_NAME="N_NAME";
	public static final String COOKIE_USER_PASS="CUKEY";
	public static final String COOKIE_USER_HEADER_PIC="CUHP";
	public static final String COOKIE_USER_REGISTER_TYPE="CURT";
	public static final String COOKIE_USER_TYPE="CUT";
	public static final String COOKIE_JSESSION_ID = "JSESSIONID";
	public static final String COOKIE_GROUP_ROLE_ID = "CGRID";
	public static final String COOKIE_GROUP_ID = "CGID";
	public static final String COOKIE_COURSE_ID = "CCID";
	
	public static final String COOKIE_HEADER_IMG = "CHI";
	public static final String COOKIE_LOGO_IMG ="CLI";
	public static final String COOKIE_FOOTER_IMG = "CFI";
	public static final String COOKIE_BANNER_IMG = "CBI";
	
	public static final String COOKIE_HEADER_IMG_LINK_URL = "CHILU";
	public static final String COOKIE_LOGO_IMG_LINK_URL ="CLILU";
	public static final String COOKIE_FOOTER_IMG_LINK_URL = "CFILU";
	public static final String COOKIE_BANNER_IMG_LINK_URL = "CBILU";
	
	
	public static final String COOKIE_MOBILE_VALIDATION_CODE="MV_CODE";
	
	public static final String COOKIE_PICTURE_VALIDATION_CODE="P_CODE";
	
	public static final short CLASSIFICATION_ITEMS_TYPE_PRESENTATION = 1;
    public static final short CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE = 21;
    public static final short CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE = 22;
    public static final short CLASSIFICATION_ITEMS_TYPE_COURSE_ONLIVE = 23;
    public static final short CLASSIFICATION_ITEMS_TYPE_KNOWLEDGEPOINT = 3;
    public static final short CLASSIFICATION_ITEMS_TYPE_COURSEPACKAGE = 4;
    
    public static final short TAGS_ITEMS_TYPE_PRESENTATION = 1;
    public static final short TAGS_ITEMS_TYPE_COURSE_ONLINE = 21;
    public static final short TAGS_ITEMS_TYPE_COURSE_OFFLINE = 22;
    public static final short TAGS_ITEMS_TYPE_COURSE_ONLIVE = 23;
    public static final short TAGS_ITEMS_TYPE_KNOWLEDGEPOINT = 3;
    public static final short TAGS_ITEMS_TYPE_COURSEPACKAGE = 4;
    public static final short TAGS_ITEMS_TYPE_EXAMINATION = 5;
    
	public static final String DEFAULT_PASS_WORD="123456";
	
	public static final Integer PRESENTATION_FILES_TYPE_SCORM = 0;
	public static final Integer PRESENTATION_FILES_TYPE_PACKAGE = 1;
	
	public static final Integer TYPE_NO_PUBLISH = 0;
	public static final Integer TYPE_IS_PUBLISH = 1;
	
	public static final Integer NUMBER_TEN=10;
	
	public static final Integer NUMBER_FIVE=5;
	
	
	public static final String MAIL_HEADER_DATA = ";"; 
	
	/**
	 * The suffix of Excel 2003.
	 */
	public static final String XLS = "xls";
	/**
	 * The suffix of Excel 2007.
	 */
	public static final String XLSX = "xlsx";
	public static final String DELIMITER_CELL_DATA = "_&_";
	/**
	 * Separator by different characters for | 
	 */
	public static final String SEPARATOR = "|";
	/**
	 * Separator by different characters for _@_
	 */
	public static final String DELIMITER_ROW = "_@_";
	/**
	 * Separator by different characters for _#_
	 */
	public static final String DELIMITER_DATA = "_#_";
	/**
	 * Separator by different characters for ]=[
	 */
	public static final String DELIMITER_ATTR = "]=[";
	/**
	 * Separator by different characters for _$_
	 */
	public static final String DELIMITER_DOLLER = "_$_";
	/**
	 * Separator by different characters for ]@[
	 */
	public static final String DELIMITER_CELL_STYLE = "]@[";
	/**
	 * Separator by different characters for comma
	 */
	public static final String DELIMITER_COMMA = ",";
	
	public static final String DELIMITER_HR = "_";
	
	public static final String DELIMITER_SEMICOLON = ";";
	
	public static final String DELIMITER_SEPARATOR = "_|_";
	
	public static final String FOLDER_MEDIA = "vod";
	
	public static final String FOLDER_SCROM = "scrom";
	
	public static final String FOLDER_IMAGE = "img";
	
	public static final String FOLDER_PACKAGE = "package";
	
	public static final String[] IMAGE_TYPE = {"jpg","JPG","gif","GIF","jpeg","JPEG","png","PNG","swf","SWF"};
	
	public static final String[] PPT_FORMAT = {"ppt","PPT","pptx","PPTX"};
	
	public static final String[] MEDIA_MP3 = {"mp3","MP3"};
	
	public static final String[] OFFICE_FILE_TYPES = {"ppt","PPT","pptx","PPTX","xls","XLS","xlsx","XLSX","doc","DOC","docx","DOCX","txt","TXT","pdf","PDF"};
	
	
	public static final Integer COURSE_TYPE_ONLINE = 1;
	public static final Integer COURSE_TYPE_OFFLINE = 2;
	public static final Integer COURSE_TYPE_ONLIVE = 3;
	
	public static final int QUEUE_WAIT_SECONDS = 30;
	public static final String DELIMITER_SLASH = "/";
	public static final String DELIMITER_FULL = ".";
	
	public static final String MEDIA_COVERT_STATUS_MRSTART_SUCCESS = "SUCCEEDED";
	public static final String MEDIA_COVERT_STATUS_MRSTART_PROCESSING = "RUNNING";
	
	public static final Integer SERVICE_PLAN_TRIAL = 1;
	public static final Integer SERVICE_PLAN_TWO = 2;
	public static final Integer SERVICE_PLAN_THREE = 3;
	public static final Integer SERVICE_PLAN_FOUR = 4;
	
	public static final String SERVICE_PLAN_CMOBO_TYPE_MAX_CLASSIFICATION_COUNT = "maxClassificationCount";
	
	public static final Integer ACCOUNT_ITEMS_REL_ITEMS_TYPE_PLAN = 1;
	public static final Integer ACCOUNT_ITEMS_REL_ITEMS_TYPE_CLASSIFICATION = 2;
	
	public static final Integer ACCOUNT_ITEMS_REL_ACCOUNT_TYPE_STANDARD = 1;
	public static final Integer ACCOUNT_ITEMS_REL_ACCOUNT_TYPE_PREFERENTIAL = 2;
	
	public static final short USE_REGISTER_TYPE_PHONE = 1;
	public static final short USE_REGISTER_TYPE_MAIL = 2;
	public static final short USE_REGISTER_TYPE_COMPANY = 3;
	public static final short USE_REGISTER_TYPE_THREE_PART = 4;
	public static final short USE_REGISTER_TYPE_OTHERS= 5;
	public static final String USE_REGISTER_TYPE_PHONE_String = "1";
	public static final String USE_REGISTER_TYPE_MAIL_String = "2";
	public static final String USE_REGISTER_TYPE_COMPANY_String = "3";
	public static final String USE_REGISTER_TYPE_THREE_PART_String = "4";
	
//	public static final String DOMAIN_POSTIFX = ".deodio.com.cn";
	public static final String DOMAIN_WWW = "www";
	public static final String DOMIAN_CONTENT_ROOT = "/deodio-main";
	public static final String DOMAIN_SESSION_ACCOUNT_ID="DSAID";
	
	public static final String GROUP_ROLE_GROUP_LEADER_ID = "10000";
	public static final String GROUP_ROLE_CONTENT_CREATOR_ID = "10001";
	public static final String GROUP_ROLE_VIEWER_ID = "10002";
	public static final String ROLE_CONTENT_ACCOUNT_ID = "ACCOUNT";
	
	public static final short USER_TYPE_PERSONAL = 1;
	public static final short USER_TYPE_COMPANY = 2;
	
	public static final short HAS_LEADER_PRIVILEDGE_YES = 1;
	public static final short HAS_LEADER_PRIVILEDGE_NO = 0;
	
	public static final String REFRESH_IMG_LOGO = "refresh";
	
	/*1:presentation, 2:quiz,3:survey, 4:course*/
	public static final short GROUP_ITEMS_TYPE_PRESENTATION = 1;
	public static final short GROUP_ITEMS_TYPE_QUIZ = 2;
	public static final short GROUP_ITEMS_TYPE_SURVEY = 3;
	public static final	short GROUP_ITEMS_TYPE_COURSE = 4;
	public static final	short GROUP_ITEMS_TYPE_PACKAGE = 5;
	
	public static final boolean INSERT_SYS_CLASSIFICATION_FLAG_PRE_DEL_DO = true;
	public static final boolean INSERT_SYS_CLASSIFICATION_FLAG_PRE_DEL_UNDO = false;
	
	public static final short GROUP_TYPE_PERMISSION = 1;
	public static final short GROUP_TYPE_MODEL_FUNCTION = 2;
	
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_SUBJECT = 10005;
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_CONTENT = 10006;
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_TEMPLATE = 10002;
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_REINVITE = 10003;
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_TEXT_TEMPLATE = 10007;
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_TEXT_TEMPLATE_COMMON_MAIL = 10008;
	public static final int SYSTEM_TEMPLATE_INVITE_MAIL_TEXT_TEMPLATE_BATCH_IMPORT_MAIL = 10010;
	
	public static final Integer PRESENTATION_IS_COUNT_DOWN_FLAG_YES = 1;
	public static final Integer PRESENTATION_IS_COUNT_DOWN_FLAG_NO = 0;
	
	public static final Short PRESENTATION_PUBLISH_IS_COURSE_YES = 1;
	public static final Short PRESENTATION_PUBLISH_IS_COURSE_NO = 0;
	
	public static final Integer ATTACHMENT_TYPE_PRESENTATION = 5;
	public static final Integer ATTACHMENT_TYPE_SCROM = 50;
	public static final Integer ATTACHMENT_TYPE_PACKAGE = 51;
	public static final Integer ATTACHMENT_TYPE_SLIDES = 53;
	public static final Integer ATTACHMENT_TYPE_MEDIA = 54;
	
	public static final String SCROM_MANIFEST_FILE_NAME = "imsmanifest.xml";
	
	public static final Integer PACKAGE_IS_CONVERT_YES = 1;
	public static final Integer PACKAGE_IS_CONVERT_NO = 0;
	
	public static final Integer ATTACHMENT_IS_CONVERT_YES = 1;
	public static final Integer ATTACHMENT_IS_CONVERT_NO = 0;
	
	//站内信状态 0:保存 1:已发布
	public static final String MESSAGE_TEXT_STATUS_SAVE="0";
	public static final String MESSAGE_TEXT_STATUS_PUBLISH="1";
	
	//站内信消息类型
	public static final String MESSAGE_TEXT_TYPE_JOIN="1";
	
	//站内信发送对象类型
	public static final Integer  MESSAGE_TEXT_REC_TYPE_TEACHER=1;
	
	//站内信内容查看状态 0:未查看 1:查看
	public static final String MESSAGE_VIEW_STATUS_ZERO="0";
	public static final String MESSAGE_VIEW_STATUS_ONE="1";
	
	public static final String SLIDE_EXT_PNG = "png";
	public static final String MEDIA_EXT_MP4 = "mp4";
	
	public static final int PRESENTATION_MODEL_SCORM = 0;
	public static final int PRESENTATION_MODEL_PACKAGE = 1;
	public static final int PRESENTATION_MODEL_SYNC = 2;
	public static final int PRESENTATION_MODEL_EXTERNAL = 3;
	
	public static final Integer COURSE_RECORD_IS_PASS = 1;
	public static final Integer COURSE_RECORD_IS_CONTINUE = 0;
	
	public static final Integer TRAINEE_COURSE_REL_COURSE = 1;
	public static final Integer TRAINEE_COURSE_REL_PACKAGE = 2;
	
	/*课后作业60；考试61；调查问卷62  附件63*/
	public static final String TRAINEE_COURSE_HOMEWORK = "60";
	public static final String TRAINEE_COURSE_QUIZ = "61";
	public static final String TRAINEE_COURSE_SURVEY = "62";
	public static final String TRAINEE_COURSE_ATTACH = "63";
	public static final String TRAINEE_COURSE_APPRAISE = "64";
	
	//内容类型 1：考试 2：调查问卷
	public static final Integer TRAINEE_EXAM_TYPE_QUIZ = 1;
	public static final Integer TRAINEE_EXAM_TYPE_SURVEY = 2;
	//状态 1：新提交 2：已阅
	public static final Integer TRAINEE_EXAM_STATUS_NEWSUBMIT = 1;
	public static final Integer TRAINEE_EXAM_STATUS_READ = 2;
	//登录有效性
	public static final Short LOGIN_IS_NEED_CHECK_YES = 1;
	public static final Short LOGIN_IS_NEED_CHECK_NO = 0;
	public static final Short LOGIN_IS_VALID_NO = 0;
	//编辑状态 0: 未编辑 1： 正在编辑
	public static final Integer ISNOT_EDIT = 0;
	public static final Integer IS_EDIT = 1;
	//手机号码是否已验证: 0 未验证   1 已验证 
	public static final Short PHONE_NUMBER_NOT_CHECKED = 0;
	public static final Short PHONE_NUMBER_IS_CHECKED = 1;
	//邮箱是否已验证: 0 未验证   1 已验证 
	public static final Short EMAIL_NOT_CHECKED = 0;
	public static final Short EMAIL_IS_CHECKED = 1;
	
	//默认题库ID
	public static final String DEFAULT_BANK_ID = "999999";
	//试题来源 0：题库 1：手动试卷
	public static final Integer QUIZ_SUBJECT_FROM_MANUAL = 1;
	public static final Integer QUIZ_SUBJECT_FROM_BANK = 0;
	//0: 随机题 1：必考题
	public static final Integer QUIZ_SUBJECT_ISNOT_REQUIRED = 0;
	public static final Integer QUIZ_SUBJECT_IS_REQUIRED = 1;
	//用户注册方式
	public static final String REGISTER_TYPE_PHONE = "1";
	public static final String REGISTER_TYPE_MAIL = "2";
	public static final String REGISTER_TYPE_COMPANY = "3";
	//用户详细注册校验内容
	public static final String REGISTER_CHECK_OGCODE="organizationCode";
	public static final String REGISTER_CHECK_LSCODE="licenseCode";
	public static final String REGISTER_CHECK_IDCODE="IDCode";	
	//小组内课程管理和快速学习
	public static final String GROUP_COURSE_MANAGER_SHOW="GCMS";
	public static final String GROUP_COURSE_STUDY_SHOW="GCSS";
	public static final String GROUP_COURSE_MANAGER_AND_STUDY_SHOW="GCMASS";
	public static final String GROUP_COURSE_MANAGER_AND_STUDY_HIDDEN="GCMASH";
	
	//切换账户
	public static final String CHANGE_ACCOUNT_SHOW="CACCS";
	public static final String CHANGE_ACCOUNT_HIDDEN="CACCH";
	
	//标题栏显示
	public static final String GROUP_USER_ROLE_ID="userRoleId";
	//用户切换账户后跳转个人中心
	public static final String USER_CHANGE_ACCOUNT_TO="CHANGE_TO";
	public static final String USER_CHANGE_ACCOUNT_TO_SELF="SELF";
	public static final String USER_CHANGE_ACCOUNT_TO_OTHER="OTHER";

	//未登录不调转登录页面的链接
	public static final String USER_JOIN_GROUP="/deodio-main/group/active_join_group.html";
	public static final String USER_GROUP_REGISTER="/deodio-main/group/toGroupFormRegister.html";
	public static final String USER_GROUP_SETPWD="/deodio-main/group/setPwd.html";
	
/*	//小組课程列表排序方式
	public static final String GROUP_COURSE_ORDER_BY_STUDY_TIME="studyTime";
	public static final String GROUP_COURSE_ORDER_BY_ADD_TIME="addTime";*/
}
