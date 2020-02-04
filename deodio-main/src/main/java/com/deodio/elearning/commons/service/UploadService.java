package com.deodio.elearning.commons.service;


/**
 * @Title: UploadService.java
 * @Package com.deodio.elearning.service
 * @author isaac
 * @date 2015-2-5
 * @version V1.0
*/
	


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.PresentationFilesMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentExample;
import com.deodio.elearning.persistence.model.PresentationFilesExample;
import com.deodio.elearning.utils.CommonUtils;

import net.coobird.thumbnailator.Thumbnails;


/**
 * @ClassName: UploadService
 * @author isaac
 * @date 2015-2-5
 */
@Service
public class UploadService {
	

	
	@Autowired
	private AttachmentMapper attachmentMapper;


	@Autowired
	private PresentationFilesMapper presentationFilesMapper;
	
	 public Attachment addAttachement(HttpServletRequest request) throws Exception{
		 
		  /*DB add Attachment */
	      Attachment atta = null;
	        
        String moduleName = "";
        String attachRelType = "";
        String attachName = "";
        String accountId = "";
	    String isModel = "";  
	    String isTheumb = "";   
	        
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        List items = upload.parseRequest(request);
	        Iterator it = items.iterator();
	        while(it.hasNext()){
	        	FileItem fileItem = (FileItem) it.next();
	        	 if(fileItem.isFormField()){
	        		 if(fileItem.getFieldName().equalsIgnoreCase("name")) {
	        			 attachName = encodingString(fileItem);
	        		 }else if(fileItem.getFieldName().equalsIgnoreCase("Filename")){
	        			 attachName = encodingString(fileItem);
	        		 }else if(fileItem.getFieldName().equalsIgnoreCase("attachRelType")){
	        			 attachRelType = encodingString(fileItem);
	        			 moduleName =  attachRelType;
	        		 }else if(fileItem.getFieldName().equalsIgnoreCase("accountId")){
	        			 accountId = encodingString(fileItem);
	        		 }else if(fileItem.getFieldName().equalsIgnoreCase("isModel")){
	        			 isModel = encodingString(fileItem);
	        		 }else if(fileItem.getFieldName().equalsIgnoreCase("isTheumb")){
	        			 isTheumb = encodingString(fileItem);
	        		 }
	        	
	        	 }else{
	        		
	        		 if(StringUtils.isBlank(attachName)) {
	        			 attachName =  fileItem.getName();
	        		 }
	        		 
	 	            String[]  generateName = this.generateFileName(attachName);
	 	            String absDir = generateDir(moduleName);
	 	            String absUrlStr  = StringUtils.replace(absDir.toString(),DConstants.STRING_BACKSLASH,DConstants.STRING_SLASH);	 	          
		            String serverDir = CommonUtils.IMGS_LOCAL_DIR;
		            String toFileFolder = serverDir + File.separator + absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH);
		            String finalFile = writeFileItems(fileItem, generateName,
							toFileFolder);
		            if("true".equals(isModel)){
		                atta = insertAttament(attachRelType, attachName, accountId,
		                		generateName[1], generateName[0], absDir, absUrlStr,"",0);
		            }else{
		            	atta = new Attachment();
		            	atta.setAttachDir(absDir+ generateName[0].toString());
		            	atta.setAttachUrl(absUrlStr+ generateName[0].toString());
		            }
		           if("true".equals(isTheumb)){
		            	Thumbnails.of(finalFile)   
		                .size(360, 360)  
		                .toFile(finalFile);  
		            }
	        	 }
	        }
	        
	        return atta;
	    }
	 /**
	  * 文件创建
	  * @Title: writeFileItems
	  * @param fileItem
	  * @param generateName
	  * @param toFileFolder
	  * @return
	  * @throws Exception
	  * @return String
	  */
	public String writeFileItems(FileItem fileItem, String[] generateName,
			String toFileFolder) throws Exception {
		System.out.println("文件路径====="+toFileFolder+"=====");
		FileUtils.createDir(toFileFolder.trim());
		
		String finalFile = toFileFolder+generateName[0];
		System.out.println("文件路径名称---"+finalFile);
		File targetFile = new File(finalFile);
		/*if file exists,delete it*/
		if(targetFile.exists()){
		    org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
		}
		fileItem.write(targetFile);
		
		return finalFile;
	}
	 /**
	  * 根据模块生成物理路径
	  * @Title: generateDir
	  * @param moduleName
	  * @return
	  * @return String
	  */
	 public String generateDir(String moduleName){
		  Calendar now = Calendar.getInstance();  
           
           StringBuilder absDir = new StringBuilder();
          absDir.append(File.separator).append(now.get(Calendar.YEAR)).append(File.separator)
          		.append((now.get(Calendar.MONTH) + 1)).append(File.separator).append(now.get(Calendar.DAY_OF_MONTH))
          			.append(File.separator).append(moduleName).append(File.separator);
          
          return absDir.toString();
	 }
	 
	 
	 /**
	  * 返回文件名和文件类型
	  * @Title: generateFileName
	  * @param attachName
	  * @return
	  * @return String[]
	  */
	 public String[] generateFileName(String attachName){
		 StringBuilder fileName = new StringBuilder();
          String fileExt = StringUtils.substringAfterLast(attachName, ".");
          String generateName = DateTimeUtils.formatDate(new Date(), "yyyyMMdd-HHmmss-")+RandomUtils.nextInt(100);
          fileName.append(generateName);
          fileName.append(".");
          fileName.append(fileExt);
          String[] arrays = new String[2];
          arrays[0]=fileName.toString();
          arrays[1]=fileExt;
          return arrays;
	 }
	 
	 
	 /**
	  * 字符串转码
	  * @Title: encodingString
	  * @param fileItem
	  * @return
	  * @throws UnsupportedEncodingException
	  * @return String
	  */
	private String encodingString(FileItem fileItem)
			throws UnsupportedEncodingException {
		return new String(fileItem.getString().getBytes("iso8859-1"), "UTF-8");
	}
	

	public Attachment insertAttament(String attachRelType, String attachName,
			String accountId, String fileExt, String generateName,
			String absDirStr, String absUrlStr,String relId,Integer attachSize,String attachId,Integer convertStatus) {
		Attachment atta;
		atta = new Attachment();
		atta.setCreateId(accountId);
		atta.setCreateTime(new Date());
		atta.setAttachDir(absDirStr);
		atta.setAttachExt(fileExt);
		atta.setGenerateName(generateName);
		atta.setId(attachId);
		atta.setAttachName(attachName);
		atta.setRelId(relId);
		atta.setAttachType(attachRelType);
		atta.setAttachUrl(absUrlStr);
		atta.setAttachSize(attachSize);
		atta.setIsConvert(convertStatus.shortValue());
		attachmentMapper.insertSelective(atta);
		return atta;
	}

	public Attachment insertAttament(String attachRelType, String attachName,
			String accountId, String fileExt, String generateName,
			String absDirStr, String absUrlStr,String relId,Integer attachSize) {
		String attachId = AppUtils.uuid();
		return insertAttament(attachRelType,attachName,accountId,fileExt,generateName,absDirStr,absUrlStr,relId,attachSize,attachId,DConstants.ATTACHMENT_IS_CONVERT_NO);
	}
	    
	   
	
	/**
	 * 根据路径删除文件
	 * @Title: deleteAttachByAttachDir
	 * @param attachDir
	 * @throws DeodioException
	 * @return void
	 */
	public void deleteAttachByAttachDir(String attachDir)throws DeodioException{
		 String serverPath = CommonUtils.FILE_LOCAL_DIR;
         org.apache.commons.io.FileUtils.deleteQuietly(new File(serverPath +attachDir));
	}
	
	
	    public void deleteAttach(String attachId) {
	        Attachment atta = attachmentMapper.selectByPrimaryKey(attachId);
	        if(null!=atta){
	            String serverPath = CommonUtils.FILE_LOCAL_DIR;
	            String fileDir = atta.getAttachDir()+atta.getGenerateName();
	            attachmentMapper.deleteByPrimaryKey(attachId);
	            org.apache.commons.io.FileUtils.deleteQuietly(new File(serverPath +fileDir));
	        }
	    }
	    

	    
	    public Attachment getAttachmentByPkId(String attachId)throws DeodioException{
	    	return attachmentMapper.selectByPrimaryKey(attachId);
	    }
	    
	    public void updateAttachRelId(String relId,Attachment atta){
	    	atta.setRelId(relId);
	    	attachmentMapper.updateByPrimaryKeySelective(atta);
	    }
	    
	    public void updateAttachRelId(String attachId,String relId){
	    	Attachment atta =  new Attachment();
	    	atta.setRelId(relId);
	    	atta.setId(attachId);
	    	attachmentMapper.updateByPrimaryKeySelective(atta);
	    }
	    
	    public Attachment deleteAndUpdateAttach(String presentationId,String attachmentId,HttpServletRequest request) throws Exception{
	    	deleteAttach(attachmentId);
	    	Attachment atta = addAttachement(request);
	    	return atta;
	    }
	    
	    public void deleteAttachmentRelId(String realId) {
	    	AttachmentExample example =  new AttachmentExample();
	    	example.createCriteria().andRelIdEqualTo(realId).andAttachTypeEqualTo(DConstants.ATTACHMENT_TYPE_SCROM.toString());
	    	attachmentMapper.deleteByExample(example);
	    	PresentationFilesExample example2 = new PresentationFilesExample();
	    	example2.createCriteria().andPresentationIdEqualTo(realId).andFileTypeEqualTo(DConstants.PRESENTATION_FILES_TYPE_SCORM);
	    	presentationFilesMapper.deleteByExample(example2);
	    	
	    }
	    
}
