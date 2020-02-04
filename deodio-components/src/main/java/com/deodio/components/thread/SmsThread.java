package com.deodio.components.thread;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.deodio.components.sms.service.SmsSendService;


public class SmsThread  implements Runnable{
	protected Log log = LogFactory.getLog(SmsThread.class);
	
	private String mobileNumber;
	private String codeContent;
	

	private SmsSendService smsSendService;
	
	@SuppressWarnings("unused")
	private SmsThread(){
	}
	
	
	public SmsThread(String mobileNumber,String codeContent,SmsSendService smsSendService){
		this.mobileNumber = mobileNumber;
		this.codeContent = codeContent;
		this.smsSendService = smsSendService;
	}
	
	@Override
	public void run() {
		try {
			smsSendService.sendSms(codeContent, mobileNumber);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
