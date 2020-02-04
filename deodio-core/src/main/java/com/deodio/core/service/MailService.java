package com.deodio.core.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.deodio.core.utils.AppUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service
public class MailService extends BaseService {

    public JavaMailSender mailSender;
    public FreeMarkerConfigurer freeMarker;
    public SimpleMailMessage simpleMailMessage;
    
    private String from;
    private String to;
    private String subject;
    private String content;
    private boolean validate = Boolean.FALSE;
    
    public static final String CONTENT_TYPE_TEXT = "text";
    public static final String CONTENT_TYPE_IMG = "img";
    public static final String CONTNET_TYPE_ATTACH = "attach";
    
    public static final String CONTENT_MAP_KEY = "key";
    public static final String CONTENT_MAP_VAL = "value";

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setFreeMarker(FreeMarkerConfigurer freeMarker) {
        this.freeMarker = freeMarker;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
    
    private String getMailText(String templateName, Map<?, ?> map) {
        try {
            Configuration configuration = freeMarker.getConfiguration();
            Template t = configuration.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, map);
        } catch (TemplateException e) {
            log.error("Error while processing FreeMarker template ", e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Error while open template file ", e);
        } catch (IOException e) {
            log.error("Error while generate Email Content ", e);
        }
		return null;
    }

    /**
     * Send email base on template.
     * 
     * @param model
     */
    public void send(Map<?,?> model, String template) {

        this.simpleMailMessage.setTo(this.getTo());
        this.simpleMailMessage.setFrom(this.simpleMailMessage.getFrom());
        this.simpleMailMessage.setSubject(this.getSubject());
        String result = getMailText(template, model);
        this.simpleMailMessage.setText(result);
        this.mailSender.send(this.simpleMailMessage);

    }

    /**
     * Send normal email.
     */
    public void sendText() {
        this.simpleMailMessage.setTo(this.getTo());
        this.simpleMailMessage.setFrom(this.simpleMailMessage.getFrom());
        this.simpleMailMessage.setSubject(this.getSubject());
        this.simpleMailMessage.setText(this.getContent());
        this.mailSender.send(this.simpleMailMessage);
    }

    /**
     * Send rich text email.
     */
    public void sendHtml(String content,String subjectTitle,String toUser) {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
        	
        	if(toUser.contains(";")){
        		   messageHelper.setTo(toUser.split(";"));
        	}else{
        		   messageHelper.setTo(toUser);
        	}
         
            messageHelper.setFrom(this.getFrom());
            messageHelper.setSubject(subjectTitle);
            messageHelper.setText(content, true);
        } catch (MessagingException e) {
        	System.out.println("发送失败-------------------------");
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
        System.out.println("发送成功-------------------------");
    }

    /**
     * Send html email with attachments.
     * 
     */
    public void sendHtmlWithAttachment(String content,String toUser,String subjectTitle,String filePath) {

        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toUser);
            messageHelper.setFrom(this.getFrom());
            messageHelper.setSubject(subjectTitle);
            messageHelper.setText(content, true);
            if (StringUtils.isNotBlank(filePath)) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                // System.out.println("file.getFilename==="+file.getFilename());
                messageHelper.addAttachment(file.getFilename(), file);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
    }
    
    /**
     * @param toUser
     * @param subjectTitle
     * @param contents   邮件发送内容按顺序排列   如  文字  + 图片 +文字；list[0] = 文字   list[1] = 图片  list[2] = 文字
     */
    public void sendHtmlWithImg(String toUser,String subjectTitle,List<Map<String,String>> contents){
    	
    	MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(toUser);
            messageHelper.setFrom(this.getFrom());
            messageHelper.setSubject(subjectTitle);
//            messageHelper.setText(content, true);
            
            MimeMultipart multipart = messageHelper.getMimeMultipart();
            
            for(Map<String,String> content : contents){
            	String contentType = content.get(MailService.CONTENT_MAP_KEY);
            	String contentValue = content.get(MailService.CONTENT_MAP_VAL);
            	if(StringUtils.isNotBlank(contentValue)){
            		if(MailService.CONTENT_TYPE_IMG.equals(contentType)){
                		this.addImage(multipart, contentValue);
                	}else{
                		this.addHtml(multipart, contentValue);
                	}
        		}
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);

    }

    /** 
     * 在邮件内容中增加附件（邮件中添加需要在邮件中显示的图片时使用） 
     * @param attach File 附件 
     * @param header String Content-ID 
     */  
    private void addAttach(MimeMultipart multipart,File attach, String header) {  
        try {  
            BodyPart bodyPart = new MimeBodyPart();  
            DataSource dataSource = new FileDataSource(attach);  
            bodyPart.setDataHandler(new DataHandler(dataSource));  
            bodyPart.setFileName(attach.getName());  
            if(header != null){  
                bodyPart.setHeader("Content-ID", "<" + header + ">");  
            }  
            multipart.addBodyPart(bodyPart);  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 在邮件中添加 html 代码 
     * @param html String 
     */  
    private void addHtml(MimeMultipart multipart,String html) {  
        try {  
            BodyPart bodyPart = new MimeBodyPart();  
            bodyPart.setContent(html, "text/html;charset=utf8");  
            multipart.addBodyPart(bodyPart);  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }  
    }  
    
    /** 
     * 在邮件中添加可以显示的图片 
     * @param image File 图片 
     */  
    private void addImage(MimeMultipart multipart,String imgUrl){  
        try {  
        	File image = new File(imgUrl);
            String header = AppUtils.uuid();
            String img = "<img src=\"cid:" + header + "\">";  
            addHtml(multipart,img);  
            addAttach(multipart,image, header);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public String getFrom() {
        return AppUtils.MAIL_SMTP_FROM;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }
}
