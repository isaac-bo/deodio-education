package com.deodio.components.sms.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.deodio.core.constants.Constants;
import com.deodio.core.utils.AppUtils;
@Service
public class SmsSendService {
	private static String Url = AppUtils.SMS_SERVER_URL;
	private static String account = AppUtils.SMS_SERVER_USERNAME;
	private static String password = AppUtils.SMS_SERVER_PASSWORD;
	
	
	public boolean sendSms(String codeContent,String mobileNumber) throws HttpException, IOException, DocumentException{
		HttpClient client = new HttpClient(); 
		PostMethod method = smsPostMethod(client,codeContent,mobileNumber);		
		
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			
			System.out.println(code+"--------------------");
			System.out.println(msg+"--------------------");
			System.out.println(smsid+"--------------------");
						
			 if(Constants.STRING_TWO.equals(code)){
				return true;
			}else{
				System.out.println(code);
				return false;
			}
	}



	private PostMethod smsPostMethod(HttpClient client,String content,String mobileNumber) {
		PostMethod method = new PostMethod(Url); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		
		//int mobile_code = (int)((Math.random()*9+1)*100000);

		//System.out.println(mobile);
		
	   // String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", account), 
			    new NameValuePair("password", password), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
			    new NameValuePair("mobile", mobileNumber), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);
		return method;
	}
	
	
}
