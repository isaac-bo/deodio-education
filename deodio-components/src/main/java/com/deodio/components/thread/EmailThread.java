package com.deodio.components.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.deodio.core.service.MailService;
import com.deodio.core.utils.EmailUtil;

public class EmailThread implements Runnable {
	
	private String toEmailAddress;
	private String mailContent;
	private MailService mailService;
	private String subjectTitle;
	private String filePath;
	private List<String> contentMap = new ArrayList<String>();
	
	private boolean multiple = false;
	
	private boolean sendWithImg = false;
	private List<List<Map<String,String>>> contentMaps = new ArrayList<List<Map<String,String>>>();
	
	
	public EmailThread(String toEmailAddress,String mailContent,MailService mailService,String subjectTitle,String filePath) {
		this.mailContent = mailContent;
		this.toEmailAddress = toEmailAddress;
		this.mailService = mailService;
		this.filePath = filePath;
		this.subjectTitle = subjectTitle;
	}
	
	public EmailThread(String toEmailAddress,List<String> contentMap,MailService mailService,String subjectTitle,String filePath,boolean multiple) {
		this.toEmailAddress = toEmailAddress;
		this.mailService = mailService;
		this.filePath = filePath;
		this.subjectTitle = subjectTitle;
		this.contentMap = contentMap;
		this.multiple = multiple;
	}
	
	public EmailThread(String toEmailAddress,List<List<Map<String,String>>> contentMaps,MailService mailService,String subjectTitle,boolean sendWithImg) {
		this.toEmailAddress = toEmailAddress;
		this.mailService = mailService;
		this.subjectTitle = subjectTitle;
		this.contentMaps = contentMaps;
		this.sendWithImg = sendWithImg;
	}
	
	public EmailThread(String toEmailAddress,List<String> contentMap,String subjectTitle,boolean sendWithImg) {
		this.toEmailAddress = toEmailAddress;
		this.subjectTitle = subjectTitle;
		this.contentMap = contentMap;
		this.sendWithImg = sendWithImg;
	}
	
	
	
	@Override
	public void run() {
		try {
			if(StringUtils.isNotBlank(filePath)){
				mailService.sendHtmlWithAttachment(mailContent, toEmailAddress, subjectTitle, filePath);
			}else if(multiple){
				String[] userMails = toEmailAddress.split(";");
				for(int i=0,z=userMails.length;i<z;i++){
					mailService.sendHtml(contentMap.get(i), subjectTitle,userMails[i]);	
				}
				
			}else if(sendWithImg){//add by xuxiangdong   邮件发送图片
			
				String[] userMails = toEmailAddress.split(";");
				if(null != mailService){
					for(int i=0,z=userMails.length;i<z;i++){
						String userMail =  userMails[i];
						if(StringUtils.isNotBlank(userMail)){
							mailService.sendHtmlWithImg(userMail, subjectTitle, contentMaps.get(i));
						}
					}
				}else{
					for(int i=0,z=userMails.length;i<z;i++){
						String userMail =  userMails[i];
						if(StringUtils.isNotBlank(userMail)){
							EmailUtil.SendHtmlEmailWithImg(userMail, subjectTitle, contentMap.get(i));
						}
					}
				}
			}else{
				mailService.sendHtml(mailContent, subjectTitle,toEmailAddress);	
			}
		} catch (Exception e) {
		System.out.println("发送失败-------------------------");
			e.printStackTrace();
		}

	}

}
