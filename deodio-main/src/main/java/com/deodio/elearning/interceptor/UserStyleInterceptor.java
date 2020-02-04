package com.deodio.elearning.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.UrlUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.persistence.model.Account;
import com.deodio.elearning.persistence.model.AccountAttachment;
import com.deodio.elearning.utils.CommonUtils;
import com.deodio.elearning.modules.login.service.LoginService;

public class UserStyleInterceptor implements HandlerInterceptor {
	@Autowired
	private AccountService accountService;  
	@Autowired
	private LoginService loginService;
//	private static final String DOMAIN_SESSION_ACCOUNT_ID = DConstants.DOMAIN_SESSION_ACCOUNT_ID;
//	public static final String COOKIE_HEADER_IMG = DConstants.COOKIE_HEADER_IMG;
//	public static final String COOLIE_LOGO_IMG = DConstants.COOLIE_LOGO_IMG;
//	public static final String COOKIE_FOOTER_IMG = DConstants.COOKIE_FOOTER_IMG;
//	public static final String COOKIE_BANNER_IMG = DConstants.COOKIE_BANNER_IMG;
	/**
	 * 用户登录拦截器，如果未登录/清除cookie后，统一跳转登陆页重新登录
	 * @Title: preHandle
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @return boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
			 return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//拦截更新cookie中roleId
		String queryStr = request.getQueryString();
		Object roleId = null;
		
		if(accountService == null){
			BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext()); 
			accountService = (AccountService) factory.getBean("accountService"); 
		}
		
		//查询字符串中存在grouproleId，则获取该不为空的值存入cookie并且写入cgrid中；该值为空是，从cookie中获取
		if(StringUtils.isNotEmpty(queryStr)){
			String groupRoleId = UrlUtils.parseQueryString(queryStr, "groupRoleId");
			if(StringUtils.isNotEmpty(groupRoleId)){
				CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, groupRoleId, 604800, response,CommonUtils.COOKIE_DOMAIN);
				roleId = groupRoleId;
			}
		}
								
		
		Object domainSessionAccountId = request.getSession().getAttribute(DConstants.DOMAIN_SESSION_ACCOUNT_ID);
		Object cookieAuid = CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		Boolean isLoginPage = request.getServletPath().contains("login"+DConstants.URL_SUFFIX);
		
		String userId=(String) CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
		String accountId=(String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		 if(((cookieAuid != null || domainSessionAccountId != null) && null != modelAndView ) || isLoginPage){
			 
			//获取用户是否具有切换账户的功能
			if (com.deodio.core.utils.StringUtils.isNotEmpty(userId)) {
				Account account=accountService.getAccountInfoByOwnerId(userId);
				Integer accountNum=loginService.getAccountNum(userId);
				if (account!=null) {
					if (accountNum>=1) {
						request.getSession().setAttribute("hidden_change_account", DConstants.CHANGE_ACCOUNT_SHOW);
					}else {
						request.getSession().setAttribute("hidden_change_account", DConstants.CHANGE_ACCOUNT_HIDDEN);
					}
				}else {
					if (accountNum>1) {
						request.getSession().setAttribute("hidden_change_account", DConstants.CHANGE_ACCOUNT_SHOW);
					}else {
						request.getSession().setAttribute("hidden_change_account", DConstants.CHANGE_ACCOUNT_HIDDEN);
					}
				}
				//获取用户groupRoleId
				if (com.deodio.core.utils.StringUtils.isNotEmpty(accountId)) {
					Map<String,Object> paramsMap = new HashMap<String,Object>();
					paramsMap.put("userId",userId );
					paramsMap.put("accountId", accountId);

					String userType = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_TYPE);
					//获取小组角色列表
					List<Map<String, Object>> groupRoleRelList = accountService.queryUserGroupRoleRel(paramsMap);
					List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
					Set<String> roleIdSet=new HashSet<>();
					for (Map<String, Object> map : groupRoleRelList) {
						roleIdSet.add((String) map.get("group_role_id"));
					}
					if (roleIdSet.contains(DConstants.GROUP_ROLE_CONTENT_CREATOR_ID)) {
						if (roleIdSet.contains(DConstants.GROUP_ROLE_VIEWER_ID)) {
							request.getSession().setAttribute("hidden_group_role_id", DConstants.GROUP_COURSE_MANAGER_AND_STUDY_SHOW);
						}else {
							request.getSession().setAttribute("hidden_group_role_id", DConstants.GROUP_COURSE_MANAGER_SHOW);
						}
					}else if (roleIdSet.contains(DConstants.GROUP_ROLE_VIEWER_ID)) {
						request.getSession().setAttribute("hidden_group_role_id", DConstants.GROUP_COURSE_STUDY_SHOW);
					}else if(roleIdSet.contains(DConstants.GROUP_ROLE_GROUP_LEADER_ID)) {
						request.getSession().setAttribute("hidden_group_role_id", DConstants.GROUP_COURSE_MANAGER_AND_STUDY_HIDDEN);
					}

					//已加入小组
					if(groupRoleRelList != null && !groupRoleRelList.isEmpty()){
						Set<Map<String, Object>> roleSet = new HashSet<Map<String, Object>>(groupRoleRelList);
						roleList.addAll(roleSet);
						if(groupRoleRelList.size() > DConstants.NUMBER_ONE){
						}else{
							String groupRoleId =(String) roleList.get(0).get("group_role_id");
							CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, groupRoleId, 604800, response,CommonUtils.COOKIE_DOMAIN);
							roleId=groupRoleId;
							if(null != modelAndView){
								modelAndView.addObject("cgrid", roleId);
							}
						}					
					}else{
						//未加入小组并且用户为leader时，调整到小组列表页面
						if(StringUtils.isNotEmpty(userType)&&userType.equals(String.valueOf(DConstants.USER_TYPE_COMPANY)) && StringUtils.isNotEmpty((String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID))){
							CookieUtils.setCookie(DConstants.COOKIE_GROUP_ROLE_ID, DConstants.ROLE_CONTENT_ACCOUNT_ID, 604800, response,CommonUtils.COOKIE_DOMAIN);
							roleId=DConstants.ROLE_CONTENT_ACCOUNT_ID;
						}else {
						}
					}
					if(null == roleId){
						roleId = CookieUtils.getCookie(request, DConstants.COOKIE_GROUP_ROLE_ID);
					}
					if(roleId!=null) {
						if(null != modelAndView){
							modelAndView.addObject("cgrid", roleId);
						}
					}

//					Map<String, Object> accountMap = accountService.getLoginAccountList(userId);
					if (accountService.checkAddInOrCreateGroup(userId)&&accountService.checkCompleteSelfInfo(userId)) {
						CookieUtils.setCookie(DConstants.USER_CHANGE_ACCOUNT_TO, DConstants.USER_CHANGE_ACCOUNT_TO_OTHER, 604800, response,CommonUtils.COOKIE_DOMAIN);
						System.err.println(getClass().getName()+"\t "+DConstants.USER_CHANGE_ACCOUNT_TO_OTHER);
					}else {
						CookieUtils.setCookie(DConstants.USER_CHANGE_ACCOUNT_TO, DConstants.USER_CHANGE_ACCOUNT_TO_SELF, 604800, response,CommonUtils.COOKIE_DOMAIN);
						System.err.println(getClass().getName()+"\t "+DConstants.USER_CHANGE_ACCOUNT_TO_SELF);
					}
					System.err.println(getClass().getName()+"\tuserId:\t"+userId
							+"\t accountId :\t"+accountId
							+"\t accountNum :\t"+accountNum
							+"\t userType :\t"+userType
							+"\t roleId :\t"+roleId);
				}
			}


			 Object cookieHeaderImg = CookieUtils.getCookie(request, DConstants.COOKIE_HEADER_IMG);
			 Object cookieLogoImg = CookieUtils.getCookie(request, DConstants.COOKIE_LOGO_IMG);
			 Object cookieFooterImg = CookieUtils.getCookie(request, DConstants.COOKIE_FOOTER_IMG);
			 Object cookieBannerImg = CookieUtils.getCookie(request, DConstants.COOKIE_BANNER_IMG);
			 
			 Object cookieHeaderImgLinkUrl = CookieUtils.getCookie(request, DConstants.COOKIE_HEADER_IMG_LINK_URL);
			 Object cookieLogoImgLinkUrl = CookieUtils.getCookie(request, DConstants.COOKIE_LOGO_IMG_LINK_URL);
			 Object cookieFooterImgLinkUrl = CookieUtils.getCookie(request, DConstants.COOKIE_FOOTER_IMG_LINK_URL);
			 Object cookieBannerImgLinkUrl = CookieUtils.getCookie(request, DConstants.COOKIE_BANNER_IMG_LINK_URL);
			 
			 Object refreshImg = request.getSession().getAttribute(DConstants.REFRESH_IMG_LOGO);
			 
			 Boolean isRefreshImg = (Boolean) (refreshImg != null ? refreshImg : false);
			 Boolean isLogin = request.getServletPath().contains("login");
			
			 cookieAuid = isLoginPage?"":cookieAuid;
			 
			 String headerImgUrl = StringUtils.EMPTY;
			 String footerImgUrl = StringUtils.EMPTY;
			 String bannerImgUrl = StringUtils.EMPTY;
			 String logoImgUrl = StringUtils.EMPTY;
			 
			 String headerImgLinkUrl = StringUtils.EMPTY;
			 String footerImgLinkUrl = StringUtils.EMPTY;
			 String bannerImgLinkUrl = StringUtils.EMPTY;
			 String logoImgLinkUrl = StringUtils.EMPTY;
			 
			 //查询，更新数据
			 if(isLogin || isRefreshImg){
				 
				 request.getSession().removeAttribute(DConstants.REFRESH_IMG_LOGO);
				 
				 Object queryId = domainSessionAccountId ==null ? cookieAuid : domainSessionAccountId;
				 
				 Map<String,Object> resMap= accountService.queryAccountUrlsByAccountId(String.valueOf(queryId));
				 AccountAttachment accountAttachment = (AccountAttachment) resMap.get("accountAttachment");
				 if(null != accountAttachment){
					 headerImgUrl = accountAttachment.getHeaderImgUrl();
					 footerImgUrl = accountAttachment.getFooterImgUrl();
					 bannerImgUrl = accountAttachment.getBannerImgUrl();
					 logoImgUrl = accountAttachment.getLogoImgUrl();
				 }
				 
				 Account account = accountService.queryAccountModelById(String.valueOf(queryId));
				 if(null != account){
					 headerImgLinkUrl = account.getHeaderLinkUrl();
					 footerImgLinkUrl = account.getFooterLinkUrl();
					 bannerImgLinkUrl = account.getBannerLinkUrl();
				     logoImgLinkUrl =	account.getLogoLinkUrl();
				 }
					 
				 CookieUtils.setCookie(DConstants.COOKIE_HEADER_IMG, StringUtils.isNotBlank(headerImgUrl)?headerImgUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 CookieUtils.setCookie(DConstants.COOKIE_LOGO_IMG, StringUtils.isNotBlank(logoImgUrl)?logoImgUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 CookieUtils.setCookie(DConstants.COOKIE_FOOTER_IMG, StringUtils.isNotBlank(footerImgUrl)?footerImgUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 CookieUtils.setCookie(DConstants.COOKIE_BANNER_IMG, StringUtils.isNotBlank(bannerImgUrl)?bannerImgUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 
				 CookieUtils.setCookie(DConstants.COOKIE_HEADER_IMG_LINK_URL, StringUtils.isNotBlank(headerImgLinkUrl)?headerImgLinkUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 CookieUtils.setCookie(DConstants.COOKIE_LOGO_IMG_LINK_URL, StringUtils.isNotBlank(logoImgLinkUrl)?logoImgLinkUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 CookieUtils.setCookie(DConstants.COOKIE_FOOTER_IMG_LINK_URL, StringUtils.isNotBlank(footerImgLinkUrl)?footerImgLinkUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 CookieUtils.setCookie(DConstants.COOKIE_BANNER_IMG_LINK_URL, StringUtils.isNotBlank(bannerImgLinkUrl)?bannerImgLinkUrl:StringUtils.EMPTY, 604800, response,CommonUtils.COOKIE_DOMAIN);
				 
			 }else{
			    headerImgUrl = (String) cookieHeaderImg;
			    footerImgUrl =(String) cookieFooterImg;
			    bannerImgUrl =(String) cookieBannerImg;
		        logoImgUrl = (String) cookieLogoImg;
		        
		        headerImgLinkUrl =  (String)cookieHeaderImgLinkUrl;
			    footerImgLinkUrl = (String) cookieFooterImgLinkUrl;
			    bannerImgLinkUrl = (String) cookieBannerImgLinkUrl;
		        logoImgLinkUrl =  (String)  cookieLogoImgLinkUrl;
			 } 
			 
			 Map<String,String> accountAttachment = new HashMap<String,String>();
			 accountAttachment.put(DConstants.COOKIE_LOGO_IMG, logoImgUrl);
			 accountAttachment.put(DConstants.COOKIE_BANNER_IMG, bannerImgUrl);
			 accountAttachment.put(DConstants.COOKIE_FOOTER_IMG, footerImgUrl);
			 accountAttachment.put(DConstants.COOKIE_HEADER_IMG, headerImgUrl);
			 System.err.println(getClass().getName()+"\t accountImgMap :\t"+accountAttachment.toString());
			 modelAndView.addObject("accountImgMap", accountAttachment);
			 
			 Map<String,String> accountLinkUrl = new HashMap<String,String>();
			 accountLinkUrl.put(DConstants.COOKIE_LOGO_IMG_LINK_URL, logoImgLinkUrl);
			 accountLinkUrl.put(DConstants.COOKIE_BANNER_IMG_LINK_URL, bannerImgLinkUrl);
			 accountLinkUrl.put(DConstants.COOKIE_FOOTER_IMG_LINK_URL, footerImgLinkUrl);
			 accountLinkUrl.put(DConstants.COOKIE_HEADER_IMG_LINK_URL, headerImgLinkUrl);
			 modelAndView.addObject("accountLinkUrl", accountLinkUrl);
			 
		 }
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		}

}
