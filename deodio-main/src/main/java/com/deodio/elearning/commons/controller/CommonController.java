package com.deodio.elearning.commons.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deodio.components.sms.service.SmsSendService;
import com.deodio.components.thread.EmailThread;
import com.deodio.components.thread.SmsThread;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.service.MailService;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.EmailUtil;
import com.deodio.elearning.commons.service.GroupRoleService;
import com.deodio.elearning.commons.service.RegionService;
import com.deodio.elearning.commons.service.VelocityService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.presentation.service.PresentationPackageService;
import com.deodio.elearning.modules.user.service.UserService;

import com.deodio.elearning.persistence.model.PresentationPackageItem;
import com.deodio.elearning.utils.CommonUtils;

@Controller
public class CommonController extends BaseController{
	
	@Autowired
	private VelocityService velocityService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private MailService mailService;
	@Autowired 
	private RegionService regionService;
	@Autowired 
	private GroupRoleService groupRoleService;
	
	@Autowired 
	private PresentationPackageService presentationPackageService;
	@Autowired 
	private UserService userService;
	/**
	 * 获取手机验证码
	 * @Title: getSmsCode
	 * @param mobileNumber
	 * @param response
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getSmsCode"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getSmsCode(@RequestParam String mobileNumber,HttpServletResponse response){
		AjaxResultModel arm = new AjaxResultModel();
		try {		
			int code = (int)((Math.random()*9+1)*100000);			
			Map<String,Object> codeMap = new HashMap<String,Object>();
			codeMap.put("validateCode", code);
			CookieUtils.setCookie(DConstants.COOKIE_MOBILE_VALIDATION_CODE, String.valueOf(code), 80, response,CommonUtils.COOKIE_DOMAIN);
			CookieUtils.setCookie(DConstants.COOKIE_PICTURE_VALIDATION_CODE, String.valueOf(code), 80, response,CommonUtils.COOKIE_DOMAIN);
			String content = velocityService.getVelocityTemplate(10000, codeMap);
			
			SmsThread smsThread = new SmsThread(mobileNumber,content,smsSendService);
			taskExecutor.execute(smsThread);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", code);
			arm.setData(map);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		System.err.println(getClass().getName()+"\tarm:\t"+arm.toString());
		return arm;
	}
	/**
	 * 获取邮件验证码
	 * @Title: getEmailValidateCode
	 * @param emailUrl
	 * @param response
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getEmailCode"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getEmailValidateCode(@RequestParam String emailUrl,HttpServletResponse response){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			int code = (int)((Math.random()*9+1)*100000);		
			Map<String,Object> codeMap = new HashMap<String,Object>();
			codeMap.put("validateCode", code);
			CookieUtils.setCookie(DConstants.COOKIE_PICTURE_VALIDATION_CODE, String.valueOf(code), 80, response,CommonUtils.COOKIE_DOMAIN);
			String content = velocityService.getVelocityTemplate(10001, codeMap);
			System.err.println(getClass().getName()+"\tcontent:\t"+content+"\temailUrl:\t"+emailUrl);
			StringBuffer sb=new StringBuffer();
			sb.append("这是一份自动发出的通知,系统检测到你正在通过邮箱找回密码,如果不是你本人进行的操作,请忽略该邮件。");
			sb.append("<br>");
			sb.append("如果是你本人的操作,请在找回密码页面输入验证码:"+code);
			sb.append(",进行本次密码找回操作。");
			EmailUtil.SendHtmlEmailWithImg(emailUrl, "密码找回", sb.toString());
//			EmailThread emailThread = new EmailThread(emailUrl, content, mailService,"[deodio-验证码邮件]",null);
//			taskExecutor.execute(emailThread);
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (Exception e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	/**
	 * 获取省数据信息
	 * @Title: getProvinceByCountry
	 * @param countryId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getProvinceByCountry"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getProvinceByCountry(@RequestParam Integer countryId){
		AjaxResultModel arm = new AjaxResultModel();
	
		try {
//			if(countryId == null){
//				throw new DeodioException("countryId为空！");
//			}
			arm.setData(regionService.getDicProvinceModelList(countryId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		
		return arm;
	}
	
	/**
	 * 获取城市
	 * @Title: getProvinceByCountry
	 * @param countryId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getCityByProvince"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getCityByProvince(@RequestParam Integer provinceId){
		AjaxResultModel arm = new AjaxResultModel();
	
		try {
			arm.setData(regionService.getDicCityModelList(provinceId));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	/**
	 * 获取城市
	 * @Title: getProvinceByCountry
	 * @param countryId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getGroupRoleList"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getGroupRoleList(HttpServletRequest request){
		AjaxResultModel arm = new AjaxResultModel();
		try {
			String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
			String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId",userId);
			params.put("accountId",accountId);
			//获取角色类别
			arm.setData(groupRoleService.getGroupRoleModelList(params));
			arm.setStatus(AjaxResultModel.SUCCESS);
		} catch (DeodioException e) {
			arm.setStatus(AjaxResultModel.FAIL);
			e.printStackTrace();
		}
		return arm;
	}
	
	
	/**
	 * 获取本地html
	 * @Title: getProvinceByCountry
	 * @param countryId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getLocalHtml"+Constants.URL_SUFFIX)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String htmlUrl) throws Exception {  
  
		try {
			String htmlPath = CommonUtils.FILE_LOCAL_DIR + Constants.STRING_SLASH + htmlUrl;
			File htmlFile = new File(htmlPath);
			String htmlContent = FileUtils.readFileToString(htmlFile);
			response.setDateHeader("Expires", 0);  
	        // Set standard HTTP/1.1 no-cache headers.  
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
	        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
	        // Set standard HTTP/1.0 no-cache header.  
	        response.setHeader("Pragma", "no-cache");  
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(htmlContent);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;  
    }  
	
	/**
	 * 获取本地html
	 * @Title: getProvinceByCountry
	 * @param countryId
	 * @return
	 * @return AjaxResultModel
	 */
	@RequestMapping("/commons/getLocalHtmlForId"+Constants.URL_SUFFIX)
    public ModelAndView getLocalHtmlForId(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String itemId) throws Exception {  
  
		try {
			PresentationPackageItem objItem = presentationPackageService.queryPresentationPackageItemById(itemId);
		
			String folder = com.deodio.core.utils.FileUtils.getName(objItem.getPackageGenerateName(), false);
			
			String htmlUrl = objItem.getPackageDir()+folder+File.separator+folder+".html";
			
			
			String htmlPath = CommonUtils.FILE_LOCAL_DIR + File.separator + htmlUrl;
			File htmlFile = new File(htmlPath);
			String htmlContent = FileUtils.readFileToString(htmlFile);
			response.setDateHeader("Expires", 0);  
	        // Set standard HTTP/1.1 no-cache headers.  
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
	        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
	        // Set standard HTTP/1.0 no-cache header.  
	        response.setHeader("Pragma", "no-cache");  
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(htmlContent);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;  
    } 
	/**
	 * 判断用户更改手机号码时 手机号码是否已经被注册
	 * @param userId
	 * @param mobilePhone
	 * @return
	 * @throws DeodioException
	 */
	@RequestMapping("/commons/validate/mobile_exist"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getMobileExist(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String mobilePhone)throws Exception{
		String userId =String.valueOf(CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID));
		boolean flag = userService.getMobileExist(userId, mobilePhone);
		return super.getAjaxResult(!flag, AjaxResultModel.SUCCESS);
	}
	
}
