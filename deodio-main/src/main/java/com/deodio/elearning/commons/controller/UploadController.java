package com.deodio.elearning.commons.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deodio.core.constants.Constants;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.CookieUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.JsonUtils;
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.commons.mediaUpload.MediaUpload;
import com.deodio.elearning.commons.mediaUpload.MediaUploadInfo;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.MediaService;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.account.service.AccountService;
import com.deodio.elearning.modules.presentation.service.PresentationScromService;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.modules.presentation.service.SlidesService;
import com.deodio.elearning.modules.quiz.service.impl.QuizSubjectServiceImpl;
import com.deodio.elearning.modules.user.service.UserService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.AttachmentModelMediaMapper;
import com.deodio.elearning.persistence.mapper.PresentationFilesMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentExample;
import com.deodio.elearning.persistence.model.AttachmentMediaItem;
import com.deodio.elearning.persistence.model.AttachmentModelMedia;
import com.deodio.elearning.persistence.model.Media;
import com.deodio.elearning.persistence.model.PresentationFiles;
import com.deodio.elearning.persistence.model.PresentationFilesExample;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncSlides;
import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.Slides;
import com.deodio.elearning.persistence.model.customize.FFmpegBo;
import com.deodio.elearning.utils.CommonUtils;
import com.deodio.elearning.utils.HttpUtils;
import com.deodio.elearning.utils.ZipUtils;
import com.google.gson.Gson;

@Controller
public class UploadController {

	private static final Logger LOGGER = Logger.getLogger(UploadController.class);

	private static final int MAX_POST_SIZE = 1024 * 1024 * 1024;

	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private PresentationScromService presentationScromService;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private MediaService mediaService;

	@Autowired
	private SlidesService slidesService;
	
	@Autowired
	private PresentationFilesMapper presentationFilesMapper;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private AttachmentModelMediaMapper attachmentModelMediaMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private QuizSubjectServiceImpl quizSubjectServiceImpl;
	
	
	@RequestMapping("/commons/uploadAttach" + Constants.URL_SUFFIX)
	public void importProfilePic(final HttpServletRequest request, final HttpServletResponse response,
			final String accountId, final String userId, final Integer attachRelType) throws IOException {

		AjaxResultModel arm = new AjaxResultModel();
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		Attachment attach = null;
		try {
			attach = uploadService.addAttachement(request);
		} catch (Exception e) {
			e.printStackTrace();

		}

		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(attach);
		JsonUtils.objToJson(arm, response);
	}

	/**
	 * 设置Presentation信息--上传图片（如果已经上传过，再次上传执行修改）
	 * 
	 * @param request
	 * @param response
	 * @param presentationId
	 * @throws IOException
	 */
	@RequestMapping("/commons/uploadAndUpdateAttach" + Constants.URL_SUFFIX)
	public void importAndUpdateProfilePic(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="presentationId",required=false) String presentationId) throws IOException {
		AjaxResultModel arm = new AjaxResultModel();
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		Attachment attach = null;
		try {
			if (null == presentationId || "".equals(presentationId)) {
				attach = uploadService.addAttachement(request);
			} else {
				AttachmentExample example = new AttachmentExample();
				example.createCriteria().andRelIdEqualTo(presentationId);
				List<Attachment> list = attachmentMapper.selectByExample(example);
				if (list.size() < 1) {
					attach = uploadService.addAttachement(request);
				} else {
					attach = uploadService.deleteAndUpdateAttach(presentationId, list.get(0).getId(), request);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(attach);
		JsonUtils.objToJson(arm, response);
	}

	/**
	 * 上传图片/PPT/多媒体文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/commons/uploadFiles" + Constants.URL_SUFFIX)
	public ModelAndView uploadFiles(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		String fileId = StringUtils.isBlank(request.getParameter("fileId"))?AppUtils.uuid():request.getParameter("fileId");
		String fileType = request.getParameter("fileType");
		String userId = request.getParameter("userId");
		String accountId = request.getParameter("accountId");
		String presentationId = request.getParameter("presentationId");
		PrintWriter writer = response.getWriter();
//		String logicFilePath = "";
		String serverDir = CommonUtils.FILE_LOCAL_DIR;
		StringBuilder absDir = new StringBuilder();
		Calendar now = Calendar.getInstance();
		absDir.append(DConstants.STRING_SLASH).append(now.get(Calendar.YEAR)).append(DConstants.STRING_SLASH)
				.append((now.get(Calendar.MONTH) + 1)).append(DConstants.STRING_SLASH)
				.append(now.get(Calendar.DAY_OF_MONTH)).append(DConstants.STRING_SLASH);
		String slideDir = "";
		if (fileType.equals("media")) {
			slideDir = DConstants.STRING_SLASH + DConstants.FOLDER_MEDIA + DConstants.STRING_SLASH + accountId
					+ DConstants.STRING_SLASH + presentationId + DConstants.STRING_SLASH + userId
					+ absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH);
		} else if (fileType.equals("slides")) {
			slideDir = DConstants.STRING_SLASH + DConstants.FOLDER_IMAGE + DConstants.STRING_SLASH + accountId
					+ DConstants.STRING_SLASH + presentationId + DConstants.STRING_SLASH + userId
					+ absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH);
		}
		String filePath = serverDir + slideDir;
		FileUtils.create(filePath);
		int uploadType = 1;
		String syncMediaId = "";
		String deletPath = "";
		String returnStr = "";
		String attachmentType = DConstants.ATTACHMENT_TYPE_MEDIA.toString();
		Integer attachmentConvertStatus = DConstants.ATTACHMENT_IS_CONVERT_NO;
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(filePath));
			factory.setSizeThreshold(MAX_POST_SIZE);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(MAX_POST_SIZE);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (!item.isFormField()) {
					
					MediaUploadInfo info = new MediaUploadInfo();
					if(Boolean.TRUE.toString().equals(CommonUtils.OPEN_ALIYUN_VOD) && fileType.equals("media")){
						//阿里云音视频点播
						info = MediaUpload.ALIYUN.upload(item, slideDir);
					}else{
						//本地转换
						info = MediaUpload.LOCAL.upload(item, slideDir);
					}
//					String mediaUrl = info.getTranscodeOutputUrl();
					slideDir = info.getTranscodeOutputUrl();
				//	String mediaCover = info.getSnapshotOutputUrl();
					String logicFileName = info.getLogicFileName();
					String fileNameTemp = info.getFileNameTemp();
					deletPath = info.getDeletPath();
					String fileTypeStr = FileUtils.getExt(item.getName()).toLowerCase();
					
					String[] array = logicFileName.split("\\.");
					if (fileType.equals("media")) {
						
						String mediaCover = info.getSnapshotOutputUrl();
						//获取视频信息并且截取视频图片
						String result = HttpUtils.videoInfo(CommonUtils.PDF2HTML_TOOL_LOCATION_WINDOWS, slideDir, mediaCover);
						Gson gson = new Gson();
						FFmpegBo ffmpegBo = gson.fromJson(result, FFmpegBo.class);
						
						
						
						
						// save media
						Media media = new Media();
						media.setId(fileId);
						media.setCreateTime(new Date());
						media.setCreateId(userId);
						media.setMediaCover(mediaCover);
						media.setMediaDesc("");
						media.setMediaDir(slideDir);
						media.setMediaEnd(new Long(ffmpegBo.getTime()));
						media.setMediaExt("mp4");
						media.setMediaHeight(352);
						media.setMediaLength(new Long(ffmpegBo.getTime()));
						media.setMediaName(fileNameTemp + ".mp4");
						media.setMediaSize(item.getSize());
						media.setMediaStart(0);
						// if("mp3".equals(StringUtils.substringAfter(
						// logicFileName, "."))){
						if (StringUtils.contain(DConstants.MEDIA_MP3, StringUtils.substringAfter(logicFileName, "."))) {
							media.setMediaType(3);
						} else {
							media.setMediaType(1);
						}
						media.setMediaUrl(slideDir);
						media.setMediaWidth(640);
						media.setUpdateId(userId);
						media.setUpdateTime(new Date());
						media.setAccountId(accountId);
						media.setMediaOriginalName(item.getName());
						int num = mediaService.saveMedia(media);
						// save t_presentation_sync_media
						if (num > 0) {
							PresentationSyncMedia presentationSyncMedia = new PresentationSyncMedia();
							syncMediaId = AppUtils.uuid();
							presentationSyncMedia.setId(syncMediaId);
							presentationSyncMedia.setPresentationId(presentationId);
							presentationSyncMedia.setMediaId(fileId);
							presentationSyncMedia.setMediaName(fileNameTemp + ".mp4");
							presentationSyncMedia.setMediaSize(item.getSize());
							presentationSyncMedia.setMediaExt("mp4");
							presentationSyncMedia.setMediaUrl(slideDir);
							presentationSyncMedia.setMediaDir(slideDir);
							presentationSyncMedia.setMediaLength(new Long(ffmpegBo.getTime()));
							if ("mp3".equals(StringUtils.substringAfter(logicFileName, "."))) {
								presentationSyncMedia.setMediaType(3);
							} else {
								presentationSyncMedia.setMediaType(1);
							}
							presentationSyncMedia.setMediaOrder(0);
							presentationSyncMedia.setMediaCover(mediaCover);
							presentationSyncMedia.setMediaStart(new Long(0));
							presentationSyncMedia.setMediaEnd(new Long(45));
							presentationSyncMedia.setMediaWidth(640);
							presentationSyncMedia.setMediaHeight(352);
							presentationSyncMedia.setCreateTime(new Date());
							presentationSyncMedia.setCreateId(userId);
							presentationSyncMedia.setMediaOriginalName(item.getName());
							presentationSyncMedia.setMediaConvertStatus(1);
							mediaService.savePresentationSyncMedia(presentationSyncMedia);
							
							AttachmentMediaItem mediaItem = new AttachmentMediaItem();
							mediaItem.setId(AppUtils.uuid());
							mediaItem.setMediaId(fileId);
							mediaItem.setItemName(presentationSyncMedia.getMediaName());
							mediaItem.setItemSize(item.getSize());
							mediaItem.setItemExt(presentationSyncMedia.getMediaExt());
							mediaItem.setItemUrl(slideDir);
							mediaItem.setItemDir(slideDir);
							mediaItem.setItemLength(presentationSyncMedia.getMediaLength());
							mediaItem.setItemType(presentationSyncMedia.getMediaType());
							mediaItem.setItemOrder(presentationSyncMedia.getMediaOrder());
							mediaItem.setItemCover(presentationSyncMedia.getMediaCover());
							mediaItem.setItemStart(presentationSyncMedia.getMediaStart());
							mediaItem.setItemEnd(presentationSyncMedia.getMediaEnd());
							mediaItem.setItemWidth(presentationSyncMedia.getMediaWidth());
							mediaItem.setItemHeight(presentationSyncMedia.getMediaHeight());
							mediaItem.setCreateId(userId);
							mediaItem.setCreateTime(new Date());
							mediaItem.setItemOriginalName(presentationSyncMedia.getMediaOriginalName());
							List<AttachmentMediaItem> attachmentMediaItems = new ArrayList<AttachmentMediaItem>();
							attachmentMediaItems.add(mediaItem);
							attachmentService.insertAttachmentItem(attachmentMediaItems,null);
							
							AttachmentModelMedia attachmentModelMedia = new AttachmentModelMedia();
							attachmentModelMedia.setCreateId(userId);
							attachmentModelMedia.setCreateTime(new Date());
							attachmentModelMedia.setId(AppUtils.uuid());
							attachmentModelMedia.setMediaCover(mediaCover);
							attachmentModelMedia.setMediaEnd(presentationSyncMedia.getMediaEnd());
							attachmentModelMedia.setMediaHeight(presentationSyncMedia.getMediaHeight());
							attachmentModelMedia.setMediaId(fileId);
							attachmentModelMedia.setMediaLength(presentationSyncMedia.getMediaLength());
							attachmentModelMedia.setMediaOrder(presentationSyncMedia.getMediaOrder());
							attachmentModelMedia.setMediaStart(presentationSyncMedia.getMediaStart());
							attachmentModelMedia.setMediaType(presentationSyncMedia.getMediaType());
							attachmentModelMedia.setMediaType(presentationSyncMedia.getMediaType());
							attachmentModelMedia.setMediaWidth(presentationSyncMedia.getMediaWidth());
							attachmentModelMediaMapper.insertSelective(attachmentModelMedia);
							
						}
						uploadType = 2;
						returnStr = "{\"status\":\"1\",\"fileId\":\""+fileId+"\",\"uploadType\":\"" + uploadType + "\",\"presentationId\":\""
								+ presentationId + "\",\"id\":\"" + syncMediaId + "\",\"filePath\":\"" + deletPath
								+ "\"}";
					} else if (fileType.equals("slides")) {
						attachmentType = DConstants.ATTACHMENT_TYPE_SLIDES.toString();
						// String slideId = AppUtils.uuid();
						String slideId = fileId;
						String syncSlideId = AppUtils.uuid();
						Slides slides = new Slides();
						slides.setId(slideId);
						slides.setSlideName(logicFileName);
						slides.setSlideSize(item.getSize());
						slides.setSlideExt(array[array.length - 1]);
						slides.setSlideUrl(deletPath);
						slides.setSlideDir(deletPath);
						slides.setSlideOriginalName(item.getName());
						slides.setCreateTime(new Date());
						slides.setCreateId(userId);
						slides.setAccountId(accountId);
						slides.setPresentationId(presentationId);
						int result = slidesService.insertSlides(slides);
						boolean ifImage = false;
						// if(!fileTypeStr.equals("jpg")&&!fileTypeStr.equals("gif")&&!fileTypeStr.equals("jpeg")&&!fileTypeStr.equals("png")&&!fileTypeStr.equals("swf")){
						if (!StringUtils.contain(DConstants.IMAGE_TYPE, fileTypeStr)) {

						} else {
							ifImage = true;
							if (result > 0) {
								
								
								Integer maxOrder = presentationService.getSyncSlideMaxOrder(presentationId);
								
								Integer nextOrder = maxOrder==null?0:maxOrder+1;
								
								
								
								PresentationSyncSlides syncSlides = new PresentationSyncSlides();
								syncSlides.setId(syncSlideId);
								syncSlides.setPresentationId(presentationId);
								syncSlides.setSlideId(slideId);
								syncSlides.setSlideName(logicFileName);
								syncSlides.setSlideSize(item.getSize());
								syncSlides.setSlideExt(array[array.length - 1]);
								syncSlides.setSlideUrl(deletPath);
								syncSlides.setSlideDir(deletPath);
								syncSlides.setSlideOrder(nextOrder);
								syncSlides.setCreateTime(new Date());
								syncSlides.setCreateId(userId);
								syncSlides.setSlideOriginalName(item.getName());
								slidesService.insertSyncSlides(syncSlides);
							}
							attachmentConvertStatus = DConstants.ATTACHMENT_IS_CONVERT_YES;
						}
						uploadType = 1;
						returnStr = "{\"status\":\"1\",\"uploadType\":\"" + uploadType + "\",\"logicFilePath\":\""
								+ deletPath + "\",\"presentationId\":\"" + presentationId
								+ "\",\"userId\":\"" + userId + "\",\"slideId\":\"" + slideId + "\",\"ifImage\":\""
								+ ifImage + "\"}";
					}
					
					//将上传的文件插入attachment
					uploadService.insertAttament(attachmentType, item.getName(), userId,fileTypeStr, logicFileName, slideDir, slideDir,presentationId,(int) item.getSize(),fileId,attachmentConvertStatus);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("File upload failure or cancel  - " + e.getMessage());
		}
		writer.append(returnStr);
		writer.flush();
		writer.close();
		return null;
	}

	/**
	 * 上传Scrom课件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/commons/uploadScromFiles" + Constants.URL_SUFFIX)
	public void uploadScromFiles(@RequestParam String presentationId, @RequestParam String userId,@RequestParam String accountId,final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		try{
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		String sessionId = DateTimeUtils.date2String(new Date(), "yyyyMMddHHmmss");  //request.getSession().getId();
		String uploadDir = "";
		String zipFileName ="";
		AjaxResultModel arm = new AjaxResultModel();
		String xmlName = DConstants.SCROM_MANIFEST_FILE_NAME;
		String courseId = presentationId;
//		String ctx = request.getContextPath().substring(1);
		String nameOfExtractedFile = "";
		String serverDir = CommonUtils.IMGS_LOCAL_DIR;
		StringBuilder absDir = new StringBuilder();
		Calendar now = Calendar.getInstance();
		absDir.append(DConstants.STRING_SLASH).append(now.get(Calendar.YEAR)).append(DConstants.STRING_SLASH)
				.append((now.get(Calendar.MONTH) + 1)).append(DConstants.STRING_SLASH)
				.append(now.get(Calendar.DAY_OF_MONTH)).append(DConstants.STRING_SLASH);
		String scromDir = "";
			scromDir = DConstants.STRING_SLASH + DConstants.FOLDER_SCROM + DConstants.STRING_SLASH + accountId
					+ DConstants.STRING_SLASH + presentationId + DConstants.STRING_SLASH
					+ absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH)+sessionId;
		uploadDir = serverDir+scromDir+DConstants.STRING_SLASH;
		// 创建文件夹
		FileUtils.createDir(uploadDir);
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(uploadDir));
			factory.setSizeThreshold(MAX_POST_SIZE);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(MAX_POST_SIZE);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			String attachmentId = AppUtils.uuid();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (!item.isFormField()) {
					zipFileName = item.getName();
					File uploadedFile = new File(uploadDir+DConstants.STRING_SLASH +zipFileName);
					item.write(uploadedFile);
					
					String fileNameTemp = RandomStringUtils.randomAlphanumeric(32);
					String fileTypeStr = FileUtils.getExt(item.getName()).toLowerCase();
					String logicFileName = fileNameTemp + "." + fileTypeStr;
					
					//将上传的文件插入attachment
					uploadService.insertAttament(DConstants.ATTACHMENT_TYPE_SCROM.toString(), item.getName(), userId,fileTypeStr, logicFileName, scromDir, scromDir,presentationId,(int) item.getSize(),attachmentId,DConstants.ATTACHMENT_IS_CONVERT_NO);
					
					PresentationFilesExample presentationFilesExample = new PresentationFilesExample();
					presentationFilesExample.createCriteria().andPresentationIdEqualTo(presentationId);
					presentationFilesMapper.deleteByExample(presentationFilesExample);
					
					PresentationFiles presentationFiles = new PresentationFiles();
					presentationFiles.setId(attachmentId);
					presentationFiles.setFileName(logicFileName);
					presentationFiles.setFileSize(item.getSize());
					presentationFiles.setFileExt(fileTypeStr);
					presentationFiles.setFileUrl(scromDir);
					presentationFiles.setFileDir(scromDir);
					presentationFiles.setCreateId(userId);
					presentationFiles.setCreateTime(new Date());
					presentationFiles.setAccountId(accountId);
					presentationFiles.setPresentationId(presentationId);
					presentationFiles.setFileOriginalName(item.getName());
					presentationFiles.setFileType(DConstants.PRESENTATION_FILES_TYPE_SCORM);
					presentationFilesMapper.insertSelective(presentationFiles);
				}
			}
			//scrom路径
			String  packagePath = uploadDir + DConstants.STRING_SLASH  + zipFileName;
			int indexOfFileBeginning = xmlName.lastIndexOf("/") + 1;
			nameOfExtractedFile = xmlName.substring(indexOfFileBeginning);
			//scrom中xml文件路径
			String newXmlPath = uploadDir + DConstants.STRING_SLASH  + nameOfExtractedFile;
			System.out.print(newXmlPath);
			//提取scrom中xml文件\
			boolean resultFlg = ZipUtils.createFileFromPackage(packagePath, newXmlPath, nameOfExtractedFile);
			
			if (!resultFlg) {
				arm.setStatus(AjaxResultModel.FAIL);
				arm.setExceptionMessage("提示: 上传的文件不是Scrom标准课件包！");
			} else {
				presentationScromService.saveScromItemInfo(uploadDir + DConstants.STRING_SLASH  + xmlName,zipFileName,courseId,userId,uploadDir,attachmentId);
				Attachment attach = new Attachment();
				arm.setStatus(AjaxResultModel.SUCCESS);
				attach.setAttachUrl(uploadDir);
				attach.setAttachName(zipFileName);
				arm.setData(attach);
			}
			JsonUtils.objToJson(arm, response);
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("File upload failure or cancel  - " + e.getMessage());
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传压缩包解压压缩包
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/commons/uploadPackage" + Constants.URL_SUFFIX)
	public void uploadPackage(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		uploadPackFilesMethod(request, response);

	}
	
	@RequestMapping("/commons/uploadPackageLoacl" + Constants.URL_SUFFIX)
	public void uploadPackageLoacl(final HttpServletRequest request, final HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		uploadPackFilesMethod(request, response);
	}

	private void uploadPackFilesMethod(final HttpServletRequest request, final HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		String fileId =StringUtils.isBlank(request.getParameter("fileId"))?AppUtils.uuid():request.getParameter("fileId");
		//String fileType = request.getParameter("fileType");
		String userId = request.getParameter("userId");
		String accountId = request.getParameter("accountId");
		String presentationId = request.getParameter("presentationId");
		//当为空  为 拖拽， 0 为 本地上传
		String drapType = request.getParameter("drapType");
		if(StringUtils.isBlank(drapType) || "0".equals(drapType)) {
			String packageName = "";
			PrintWriter writer = response.getWriter();
			String logicFilePath = "";
			String serverDir = CommonUtils.FILE_LOCAL_DIR;
			StringBuilder absDir = new StringBuilder();
			Calendar now = Calendar.getInstance();
			absDir.append(DConstants.STRING_SLASH).append(now.get(Calendar.YEAR)).append(DConstants.STRING_SLASH)
					.append((now.get(Calendar.MONTH) + 1)).append(DConstants.STRING_SLASH)
					.append(now.get(Calendar.DAY_OF_MONTH)).append(DConstants.STRING_SLASH);
			String packageDir = DConstants.STRING_SLASH + DConstants.FOLDER_PACKAGE + DConstants.STRING_SLASH + accountId
					+ DConstants.STRING_SLASH + presentationId + DConstants.STRING_SLASH + userId
					+ absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH);
			String filePath = serverDir + packageDir;
			FileUtils.create(filePath);
			String returnStr = "";
			String returnFileDir ="";
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setRepository(new File(filePath));
				factory.setSizeThreshold(MAX_POST_SIZE);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(MAX_POST_SIZE);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					if (!item.isFormField()) {
						String fileNameTemp = RandomStringUtils.randomAlphanumeric(32);
						String fileTypeStr = FileUtils.getExt(item.getName()).toLowerCase();
						String logicFileName = fileNameTemp + "." + fileTypeStr;
						logicFilePath = filePath + logicFileName;
						//返回不带根路径路径文件
						returnFileDir =  packageDir + logicFileName;
						File uploadedFile = new File(logicFilePath);
						item.write(uploadedFile);
						
						//将上传的文件插入attachment
						uploadService.insertAttament(DConstants.ATTACHMENT_TYPE_PACKAGE.toString(), item.getName(), userId,fileTypeStr, logicFileName, packageDir, packageDir,presentationId,(int) item.getSize(),fileId,DConstants.ATTACHMENT_IS_CONVERT_NO);
						
						//上传文件存储
						PresentationFiles presentationFiles = new PresentationFiles();
						presentationFiles.setId(fileId);
						presentationFiles.setFileName(logicFileName);
						presentationFiles.setFileSize(item.getSize());
						presentationFiles.setFileExt(fileTypeStr);
						presentationFiles.setFileUrl(packageDir);
						presentationFiles.setFileDir(packageDir);
						presentationFiles.setCreateId(userId);
						presentationFiles.setCreateTime(new Date());
						presentationFiles.setAccountId(accountId);
						presentationFiles.setFileOriginalName(item.getName());
						presentationFiles.setFileType(DConstants.PRESENTATION_FILES_TYPE_PACKAGE);
						presentationFiles.setPresentationId(presentationId);
						presentationFilesMapper.insertSelective(presentationFiles);
						
						//设置非标准包名称
						packageName = item.getName();
					}
				}
				returnStr = "{\"status\":\"1\",\"packagePath\":\""+ returnFileDir + "\",\"presentationId\":\"" + presentationId + "\",\"packageName\":\"" + packageName
						+ "\",\"userId\":\"" + userId+"\",\"packageId\":\"" + fileId+"\"}";
			} catch (final Exception e) {
				e.printStackTrace();
				LOGGER.error("File upload failure or cancel  - " + e.getMessage());
			}
			writer.append(returnStr);
			writer.flush();
			writer.close();
		}
	}
	
	
	
	
	
	/**
	 * 多媒体 上传至阿里云点播  测试
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/commons/uploadFilesAliyun" + Constants.URL_SUFFIX)
	public ModelAndView uploadFilesAliyun(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		String fileId = StringUtils.isBlank(request.getParameter("fileId"))?AppUtils.uuid():request.getParameter("fileId");
		String fileType = request.getParameter("fileType");
		String userId = request.getParameter("userId");
		String accountId = request.getParameter("accountId");
		String presentationId = request.getParameter("presentationId");
		PrintWriter writer = response.getWriter();
//		String logicFilePath = "";
		String serverDir = CommonUtils.IMGS_LOCAL_DIR;
		StringBuilder absDir = new StringBuilder();
		Calendar now = Calendar.getInstance();
		absDir.append(DConstants.STRING_SLASH).append(now.get(Calendar.YEAR)).append(DConstants.STRING_SLASH)
				.append((now.get(Calendar.MONTH) + 1)).append(DConstants.STRING_SLASH)
				.append(now.get(Calendar.DAY_OF_MONTH)).append(DConstants.STRING_SLASH);
		String slideDir = "";
		if (fileType.equals("media")) {
			slideDir = DConstants.STRING_SLASH + DConstants.FOLDER_MEDIA + DConstants.STRING_SLASH + accountId
					+ DConstants.STRING_SLASH + presentationId + DConstants.STRING_SLASH + userId
					+ absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH);
		} 
		String filePath = serverDir + slideDir;
		FileUtils.create(filePath);
		int uploadType = 1;
		String syncMediaId = "";
		String deletPath = "";
		String returnStr = "";
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(filePath));
			factory.setSizeThreshold(MAX_POST_SIZE);
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(MAX_POST_SIZE);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (!item.isFormField()) {
					
					MediaUploadInfo info = new MediaUploadInfo();
					
					if(Boolean.TRUE.toString().equals(CommonUtils.OPEN_ALIYUN_VOD) && fileType.equals("media")){
						//阿里云音视频点播
						info = MediaUpload.ALIYUN.upload(item, slideDir);
					}else{
						//本地转换
						info = MediaUpload.LOCAL.upload(item, slideDir);
					}
					String mediaUrl = info.getTranscodeOutputUrl();
					
					String loggicFileName = info.getLogicFileName();
					String fileNameTemp = info.getFileNameTemp();
					deletPath = info.getDeletPath();
					
					if (fileType.equals("media")) {
						
						String mediaCover = filePath+fileNameTemp+".jpg";
						//获取视频信息并且截取视频图片
						String result = HttpUtils.videoInfo(CommonUtils.HTTP_CLIENT_URL, filePath, mediaCover);
						Gson gson = new Gson();
						FFmpegBo ffmpegBo = gson.fromJson(result, FFmpegBo.class);
				
						
						// save media
						Media media = new Media();
						media.setId(fileId);
						media.setCreateTime(new Date());
						media.setCreateId(userId);
						media.setMediaCover(mediaCover);
						media.setMediaDesc("");
						media.setMediaDir(mediaUrl);
						media.setMediaEnd(new Long(ffmpegBo.getTime()));
						media.setMediaExt("mp4");
						media.setMediaHeight(352);
						media.setMediaLength(new Long(ffmpegBo.getTime()));
						media.setMediaName(fileNameTemp + ".mp4");
						media.setMediaSize(item.getSize());
						media.setMediaStart(0);
						// if("mp3".equals(StringUtils.substringAfter(
						// logicFileName, "."))){
						if (StringUtils.contain(DConstants.MEDIA_MP3, StringUtils.substringAfter(loggicFileName, "."))) {
							media.setMediaType(3);
						} else {
							media.setMediaType(1);
						}
						media.setMediaUrl(info.getTranscodeOutputUrl());
						media.setMediaWidth(640);
						media.setUpdateId(userId);
						media.setUpdateTime(new Date());
						media.setAccountId(accountId);
						media.setMediaOriginalName(item.getName());
						;
						int num = mediaService.saveMedia(media);
						// save t_presentation_sync_media
						if (num > 0) {
							PresentationSyncMedia presentationSyncMedia = new PresentationSyncMedia();
							syncMediaId = AppUtils.uuid();
							presentationSyncMedia.setId(syncMediaId);
							presentationSyncMedia.setPresentationId(presentationId);
							presentationSyncMedia.setMediaId(fileId);
							presentationSyncMedia.setMediaName(fileNameTemp + ".mp4");
							presentationSyncMedia.setMediaSize(item.getSize());
							presentationSyncMedia.setMediaExt("mp4");
							presentationSyncMedia.setMediaUrl(mediaUrl);
							presentationSyncMedia.setMediaDir(mediaUrl);
							presentationSyncMedia.setMediaLength(new Long(ffmpegBo.getTime()));
							if ("mp3".equals(StringUtils.substringAfter(loggicFileName, "."))) {
								presentationSyncMedia.setMediaType(3);
							} else {
								presentationSyncMedia.setMediaType(1);
							}
							presentationSyncMedia.setMediaOrder(0);
							presentationSyncMedia.setMediaCover(mediaCover);
							presentationSyncMedia.setMediaStart(new Long(0));
							presentationSyncMedia.setMediaEnd(new Long(ffmpegBo.getTime()));
							presentationSyncMedia.setMediaWidth(640);
							presentationSyncMedia.setMediaHeight(352);
							presentationSyncMedia.setCreateTime(new Date());
							presentationSyncMedia.setCreateId(userId);
							presentationSyncMedia.setMediaOriginalName(item.getName());
							mediaService.savePresentationSyncMedia(presentationSyncMedia);
						}
						uploadType = 2;
						returnStr = "{\"status\":\"1\",\"uploadType\":\"" + uploadType + "\",\"presentationId\":\""
								+ presentationId + "\",\"id\":\"" + syncMediaId + "\",\"filePath\":\"" + deletPath
								+ "\"}";
					} 
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			LOGGER.error("File upload failure or cancel  - " + e.getMessage());
		}
		writer.append(returnStr);
		writer.flush();
		writer.close();
		return null;
	}
	
	public static void main(String[] args) {
		
	}
	
	
	/**
	 * 上传图片（如果已经上传过，再次上传执行修改）
	 * 
	 * @param request
	 * @param response
	 * @param presentationId
	 * @param attachRelType
	 * @throws IOException
	 */
	@RequestMapping("/commons/uploadAndUpdateAttachment" + Constants.URL_SUFFIX)
	public void uploadAndUpdateAttachment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String presentationId,@RequestParam String attachRelType) throws IOException {
		AjaxResultModel arm = new AjaxResultModel();
		System.err.println(getClass().getName()+"\tpresentationId\t"+presentationId+
				"\tattachRelType\t"+attachRelType);
		request.setCharacterEncoding(Constants.CHARSET_UTF_8);
		response.setContentType(Constants.RES_CHARSET_UTF_8);
		Attachment attach = null;
		try {
			//当关联id为空时，或者附件为课程管理的课程管理相关文件或者课后作业时,不删除原来的文件
			if (null == presentationId || "".equals(presentationId)||attachRelType.equals(DConstants.ATTACH_TYPE_COURSE_MANAGER_FILES)||
					attachRelType.equals(DConstants.ATTACH_TYPE_COURSE_MANAGER_HOMEWORK)) {
				attach = uploadService.addAttachement(request);
			} else {
				//检测是否已经存在相关联的附件
				AttachmentExample example = new AttachmentExample();
				example.createCriteria().andRelIdEqualTo(presentationId).andAttachTypeEqualTo(attachRelType);
				List<Attachment> list = attachmentMapper.selectByExample(example);				
				if (list.size() < 1) {
					//新增附件
					attach = uploadService.addAttachement(request);
				} else {
					//删除后新增附件
					attach = uploadService.deleteAndUpdateAttach(presentationId, list.get(0).getId(), request);
				}				
				System.err.println(getClass().getName()+":\t attachUrl:\t"+attach.getAttachUrl()
				+"\t attachDir :\t"+attach.getAttachDir()
				+"\t attachType :\t"+attachRelType
				+"\t presentationId :\t"+presentationId);
				attachmentService.updateAttachement(attach.getId(), presentationId, (String)CookieUtils.getCookie(request, Constants.COOKIE_USER_ID));
			}
			//更新附件关联信息
			if(attachRelType.equals(DConstants.ATTACH_TYPE_HEADER)) {
				userService.updateUserIconInfo(attach.getGenerateName(),presentationId);
			}else if(attachRelType.equals(DConstants.ATTACH_TYPE_ID_CARD_IMG)) {
				userService.updateUserIdCardImgInfo(attach.getGenerateName(),presentationId);
			}else if(attachRelType.equals(DConstants.ATTACH_TYPE_BUSINESS_LICENCE_IMG)) {
				accountService.updateBussinceImgInfo(attach.getGenerateName(),presentationId);
			}else if (attachRelType.equals(DConstants.ATTACH_TYPE_QUIZ_IMG_QUISTION)) {
				quizSubjectServiceImpl.updateQuizItemImg(attach.getAttachUrl(),attach.getGenerateName(),presentationId);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		arm.setStatus(AjaxResultModel.SUCCESS);
		arm.setData(attach);
		JsonUtils.objToJson(arm, response);
	}
}
