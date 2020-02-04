package com.deodio.elearning.modules.decompression.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.POIUtils;
import com.deodio.core.utils.Pdf2htmlEXUtil.Pdf2htmlEXUtil;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.mapper.PresentationPackageItemMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.AttachmentPackageItem;
import com.deodio.elearning.persistence.model.PresentationPackageItem;
import com.deodio.elearning.utils.CommonUtils;
import com.deodio.elearning.utils.HttpUtils;
import com.deodio.elearning.utils.ZipUtils;

/**
 * 解压缩文件  Controller
 * @author P0076724
 *
 */
@Controller
public class DecompressionController extends BaseController{
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PresentationPackageItemMapper presentationPackageItemMapper;
	
	@Autowired
	private AttachmentService attachmentService;
	
	
	/**
	 * 解压缩
	 * @param packagePath
	 * @param fileId
	 * @param userId
	 * @param presentationId
	 * @param packageId
	 * @param request
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("/decompression/package"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel decompression(@RequestParam String packagePath,@RequestParam String userId,@RequestParam(required=false)String packageName,
			@RequestParam String presentationId,@RequestParam String packageId,HttpServletRequest request,HttpServletResponse response)  throws NumberFormatException, Exception{
		AjaxResultModel arm = new AjaxResultModel();
		
		//解压缩类
		//ZipUtils zipUtils = new ZipUtils();
		String accountId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
		String fileType = StringUtils.substringAfterLast(packagePath, DConstants.STRING_DOT);
		File file = null;
		File[] filelist = null;
		Integer isZip = 1;
		if(fileType.equals("rar")||fileType.equals("RAR")||fileType.equals("ZIP")||fileType.equals("zip")){
			String filepackagePath = CommonUtils.FILE_LOCAL_DIR+packagePath; 
			//执行解压缩
			ZipUtils.unPackage(filepackagePath,StringUtils.substringBeforeLast(filepackagePath, "."),packageId);
			//获取转换文件夹内的所有文件
			file =new File(StringUtils.substringBeforeLast(filepackagePath, DConstants.STRING_DOT));
			
			//filelist = file.listFiles();
			filelist = FileUtils.getFilesIn(file);
			
		}else{
			//处理普通文件
//			convertDocumentToHtml(packagePath,fileType,StringUtils.EMPTY);
			file = new File(packagePath);
		}
		//swf转换类
//		if(null!=filelist){
//			zipOrUn = 0;
			//执行转换
//			for (int i = 0; i < filelist.length; i++) {
//				convertAndInsertSwf(filelist[i],presentationId,userId,packageId,accountId,0);
//	        }
//		}else{
		if(null == filelist){
			filelist = new File[1];
			filelist[0] = file;
			isZip = 0;
		}
		convertAndInsertSwf(filelist,presentationId,userId,packageId,accountId,isZip,packageName);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	private void convertDocumentToHtml(String filePath,String fileType,String htmlName) throws ParseException, IOException{
		if(("doc").equals(fileType)||("docx").equals(fileType)){
      	  	try {
				final String imageDirectory = "image";
				POIUtils.WordToHtml(filePath, imageDirectory, htmlName,CommonUtils.IMGS_SERVER_DIR);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}else if(("pdf").equals(fileType)){
			//Pdf2htmlEXUtil.pdf2Html(CommonUtils.PDF2HTML_TOOL_LOCATION_WINDOWS,filePath,"",htmlName);
			
			
			
			String paramsFile = StringUtils.substringAfterLast(filePath, "package");
			
			paramsFile=File.separator+"package"+paramsFile;
			
			paramsFile = StringUtils.replaceChars(paramsFile, File.separator, "_");
			System.out.println("路径----"+paramsFile);
			
			HttpUtils.pdfForHtml(CommonUtils.PDF2HTML_TOOL_LOCATION_WINDOWS, paramsFile, htmlName);
		}
		
	}
	
	public static void main(String[] args) {
		String s1 = "ssss\\xxx\\xx";
		System.out.println(StringUtils.replaceChars(s1, File.separator, "_"));
	}
	
	/**
	 * PDF转SWF，并将信息插入数据库
	 * @param file
	 * @param convertSwfUtils
	 * @param presentationId
	 * @param userId
	 * @param packageId
	 * @throws Exception
	 */
	private void convertAndInsertSwf(File[] filelist,String presentationId,String userId,String packageId,String accountId,Integer isZip,String packageOriginalName) throws Exception{
		
		List<AttachmentPackageItem> packageItems = new ArrayList<AttachmentPackageItem>();
		for(int i = 0 ;i< filelist.length;i++){
			String swfFileName=null;
			File pdfFile = filelist[i];
			//String pdfFileName = pdfFile.getPath().replace("Z:", "Z:\\");
			String pdfFileName = pdfFile.getPath();
			String fileTypeStr = FileUtils.getExt(pdfFile.getName()).toLowerCase();
			String  packageGenerateName = RandomStringUtils.randomAlphanumeric(32) ;
			
			String packageName =pdfFile.getName();
			if(isZip==0){
				packageName = StringUtils.isBlank(packageOriginalName)?pdfFile.getName():packageOriginalName;
				packageGenerateName = FileUtils.getName(pdfFileName, false);
				swfFileName = StringUtils.substringBeforeLast(pdfFileName, pdfFile.getName());
			}else if(isZip==1){
				
			
				swfFileName = File.separator+"package"+StringUtils.substringBetween(pdfFileName, "package", pdfFile.getName());
				pdfFileName = swfFileName+pdfFile.getName();
			}
			
			String packageGenerateNameWithEx = packageGenerateName + "." + fileTypeStr;
	        //swf输出创建夹
//			FileUtils.createDir(StringUtils.substringBeforeLast(pdfFileName, DConstants.STRING_DOT));
//			ConvertSwfUtils.getInstance().convertSwf(pdfFileName,swfFileName);
			//插入数据库(item)
			PresentationPackageItem presentationPackageItem = new PresentationPackageItem();
			presentationPackageItem.setId(AppUtils.uuid());
			presentationPackageItem.setPresentationId(presentationId);
			presentationPackageItem.setCreateId(userId);
			presentationPackageItem.setCreateTime(new Date());
			presentationPackageItem.setPackageGenerateName(packageGenerateNameWithEx);
			presentationPackageItem.setPackageName(packageName);
			presentationPackageItem.setPackageId(packageId);
			presentationPackageItem.setPackageSize(pdfFile.length());
			
			presentationPackageItem.setPackageExt(fileTypeStr);
		//	String tempPath = CommonUtils.FILE_LOCAL_DIR.replace(Constants.STRING_SLASH, Constants.STRING_BACKSLASH);
			presentationPackageItem.setPackageUrl(swfFileName);
			presentationPackageItem.setPackageDir(swfFileName);
			presentationPackageItem.setAccountId(accountId);
			presentationPackageItemMapper.insertSelective(presentationPackageItem);
			
			//获取附件解压后文件信息
			AttachmentPackageItem packageItem = new AttachmentPackageItem();
			packageItem.setId(AppUtils.uuid());
			packageItem.setCreateId(userId);
			packageItem.setCreateTime(new Date());
			packageItem.setPackageGenerateName(packageGenerateNameWithEx);
			packageItem.setPackageName(packageName);
			packageItem.setPackageId(packageId);
			packageItem.setPackageSize(pdfFile.length());
			packageItem.setPackageExt(fileTypeStr);
			packageItem.setPackageUrl(swfFileName);
			packageItem.setPackageDir(swfFileName);
			packageItems.add(packageItem);
			
			//转换为html
			String htmlName = packageGenerateName + Constants.URL_SUFFIX;
			convertDocumentToHtml(CommonUtils.FILE_LOCAL_DIR + pdfFileName,fileTypeStr,htmlName);
		}
		
		//保存信息值附件处理表中
		if(packageItems != null && packageItems.size() > 0){
			attachmentService.insertAttachmentItem(packageItems,null);
			//更新附件转换状态
			attachmentService.uploadAttachmentConvertStatusByPk(packageId, DConstants.ATTACHMENT_IS_CONVERT_YES, userId);
		}
	}
}
