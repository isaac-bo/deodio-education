package com.deodio.elearning.modules.convert.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.vod.client.MediaInfo;
import com.aliyun.vod.util.StringUtil;
import com.deodio.convert.socket.model.MRResult;
import com.deodio.convert.socket.model.MediaResult;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.elearning.commons.mediaUpload.MediaUpload;
import com.deodio.elearning.commons.mediaUpload.MediaUploadInfo;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.MediaService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.convert.service.ConvertService;
import com.deodio.elearning.modules.presentation.service.PresentationService;
import com.deodio.elearning.persistence.mapper.AttachmentMediaItemMapper;
import com.deodio.elearning.persistence.mapper.AttachmentModelMediaMapper;
import com.deodio.elearning.persistence.mapper.customize.PresentationCustomizeMapper;
import com.deodio.elearning.persistence.model.AjaxResultModel;
import com.deodio.elearning.persistence.model.AttachmentMediaItem;
import com.deodio.elearning.persistence.model.AttachmentMediaItemExample;
import com.deodio.elearning.persistence.model.AttachmentModelMedia;
import com.deodio.elearning.persistence.model.AttachmentModelMediaExample;
import com.deodio.elearning.persistence.model.AttachmentSlidesItem;
import com.deodio.elearning.persistence.model.Media;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncPoints;
import com.deodio.elearning.persistence.model.PresentationSyncSlides;
import com.deodio.elearning.persistence.model.PresentationSyncSlidesExample;
import com.deodio.elearning.utils.CommonUtils;
import com.deodio.elearning.utils.PPTUtils;

/**
 * 文件转换
 * @author P0076724
 *
 */
@Controller
//@RequestMapping("/convert")
public class ConvertController extends BaseController{
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ConvertService convertService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private PresentationCustomizeMapper presentationCustomizeMapper;
	
	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private AttachmentMediaItemMapper attachmentMediaItemMapper;
	
	@Autowired
	private AttachmentModelMediaMapper attachmentModelMediaMapper;
	
	/**
	 * 多媒体转换
	 * @param filePath
	 * @param fileId
	 * @param request
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("/convert/transcoding"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel transcoding(@RequestParam String filePath,@RequestParam String fileId,
			@RequestParam String presentationId,HttpServletRequest request,HttpServletResponse response)  throws NumberFormatException, Exception{
		AjaxResultModel arm = new AjaxResultModel();
		
		String userId = this.getCookieUserId(request);
		
		if(!CommonUtils.OPEN_ALIYUN_VOD.equals(Boolean.TRUE.toString())){
			String serverDir = CommonUtils.FILE_LOCAL_DIR ;
			//String temp[] = filePath.split("Z:");
			String tempFilePath = "/mnt/medies"+CommonUtils.FILE_BASE_FOLDER+filePath;
			String targetFile = StringUtils.replace(tempFilePath, CommonUtils.FILE_LOCAL_DIR, CommonUtils.MEDIA_BASE_DIR);
			com.deodio.core.utils.FileUtils.createDir(new File(targetFile).getParent());
			//查询多媒体参数
			MediaResult mediaResult = convertService.queryParams(targetFile,FilenameUtils.getBaseName(new File(filePath).getName()));
			Media media = new Media();
			//转换文件夹
			String fileName = StringUtils.substringBefore((StringUtils.substringAfterLast(filePath, Constants.STRING_SLASH)), DConstants.STRING_DOT)+DConstants.STRING_DOT+mediaResult.getConvertFormat();
			String fileFolder = StringUtils.substringBeforeLast(filePath, DConstants.STRING_DOT);
			media.setId(fileId);
			media.setMediaHeight(mediaResult.getMediaHeight());
			media.setMediaWidth(mediaResult.getMediaWidth());
			media.setMediaLength(mediaResult.getMediaLength());
			media.setMediaEnd(mediaResult.getMediaLength());
			media.setMediaExt(mediaResult.getConvertFormat());
			media.setMediaDir(fileFolder+DConstants.STRING_SLASH+fileName);
			media.setMediaUrl(fileFolder+DConstants.STRING_SLASH+fileName);
			media.setMediaName(fileName);
			media.setUpdateId(userId);
			media.setUpdateTime(new Date());
			//视频参数存储(t_media)
			int num = mediaService.updateMediaById(media);
			
			//判断是否需要视频转换
			boolean resultFlag = false;
			if(Boolean.TRUE.toString().equals(CommonUtils.MEDIA_LOCAL_CONVERT_FLAG)){
				//视频转换
				resultFlag = convertService.mediaTranscoding(targetFile);
			}else{
				//复制不需要转换的指定目录
				String inputPath = StringUtils.replace(serverDir + filePath,DConstants.STRING_SLASH,File.separator);
				String outPath = StringUtils.replace(serverDir + fileFolder+DConstants.STRING_SLASH+fileName,DConstants.STRING_SLASH,File.separator).trim();
				com.deodio.core.utils.FileUtils.copy(inputPath,outPath);
			}
			//视频转换
//			boolean resultFlag = convertService.mediaTranscoding(targetFile);
			//转换进度查询
			MRResult result = new MRResult();
			PresentationSyncMedia presentationSyncMedia = new PresentationSyncMedia();
			if(resultFlag){
				result = convertService.queryProcess(FilenameUtils.getBaseName(new File(filePath).getName()),null);
			}else{
				//FileUtils.copy(CommonUtils.FILE_LOCAL_DIR+filePath, CommonUtils.FILE_LOCAL_DIR+fileFolder+DConstants.STRING_SLASH+fileName);
				presentationSyncMedia.setMediaConvertStatus(1);
				result.setmProcess("1");
				result.setrProcess("0");
			}
			if(num>0){
				presentationSyncMedia.setPresentationId(presentationId);
				presentationSyncMedia.setMediaId(fileId);
				presentationSyncMedia.setMediaHeight(mediaResult.getMediaHeight());
				presentationSyncMedia.setMediaWidth(mediaResult.getMediaWidth());
				presentationSyncMedia.setMediaLength(mediaResult.getMediaLength());
				presentationSyncMedia.setMediaEnd(mediaResult.getMediaLength());
				presentationSyncMedia.setMediaJobId(result.getJobId());
				presentationSyncMedia.setMediaExt(mediaResult.getConvertFormat());
				presentationSyncMedia.setMediaDir(fileFolder+DConstants.STRING_SLASH+fileName);
				presentationSyncMedia.setMediaUrl(fileFolder+DConstants.STRING_SLASH+fileName);
				presentationSyncMedia.setMediaName(fileName);
				presentationSyncMedia.setUpdateId(userId);
				presentationSyncMedia.setUpdateTime(new Date());
				//视频转换参数存储()
				presentationCustomizeMapper.updatePresentationSyncMediaById(presentationSyncMedia);
				
				saveAttachmentMedaiInfo(fileId,presentationSyncMedia,userId);
			}
			arm.setData(result);
		}else{
			//使用阿里云视频点播  上传成功后自动转码，默认设置为成功50%
			//转换进度查询
			MRResult result = new MRResult();
			result.setmProcess("0.5");
			result.setrProcess("0");
			arm.setData(result);
		}
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	/**
	 * 转换进度查询
	 * @param filePath
	 * @param jobId
	 * @param request
	 * @param response
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("/media/queryProcess"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel queryProcess(@RequestParam String filePath,@RequestParam String jobId,@RequestParam(required=false) String fileId,
			@RequestParam(required=false) String presentationId,HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		AjaxResultModel arm = new AjaxResultModel();
		MRResult res = new MRResult();
		
		String userId = this.getCookieAccount(request);
		if(!CommonUtils.OPEN_ALIYUN_VOD.equals(Boolean.TRUE.toString())){
			if(Boolean.FALSE.toString().equals(CommonUtils.MEDIA_LOCAL_CONVERT_FLAG)){
				res.setMrStat(DConstants.MEDIA_COVERT_STATUS_MRSTART_SUCCESS);
			}else{
				res = convertService.queryProcess(FilenameUtils.getBaseName(new File(filePath).getName()),jobId);
			}
		}else{
			MediaUploadInfo result = MediaUpload.ALIYUN.queryProcess(filePath);
			String status = result.getStatus();
			//阿里云视频点播 成功时直接刷新数据
			if(StringUtils.isNotBlank(status) && status.equals("1")){
				res.setMrStat(DConstants.MEDIA_COVERT_STATUS_MRSTART_SUCCESS);
				MediaInfo info = result.getMediaInfo();
				Media media = new Media();
				media.setId(fileId);
				media.setMediaHeight(info.height());
				media.setMediaWidth(info.width());
				media.setMediaLength(info.fileSize());
				media.setMediaEnd(info.fileSize());
				media.setMediaDir(result.getTranscodeOutputUrl());
				media.setMediaUrl(result.getTranscodeOutputUrl());
				media.setMediaCover(result.getSnapshotOutputUrl());
				//视频参数存储(t_media)
				int num = mediaService.updateMediaById(media);
				if(num>0){
					PresentationSyncMedia presentationSyncMedia = new PresentationSyncMedia();
					presentationSyncMedia.setPresentationId(presentationId);
					presentationSyncMedia.setMediaId(fileId);
					presentationSyncMedia.setMediaHeight(info.height());
					presentationSyncMedia.setMediaWidth(info.width());
					presentationSyncMedia.setMediaLength(info.fileSize());
					presentationSyncMedia.setMediaEnd(info.fileSize());
					presentationSyncMedia.setMediaDir(result.getTranscodeOutputUrl());
					presentationSyncMedia.setMediaUrl(result.getTranscodeOutputUrl());
					presentationSyncMedia.setMediaCover(result.getSnapshotOutputUrl());
					//视频转换参数存储()
					presentationCustomizeMapper.updatePresentationSyncMediaById(presentationSyncMedia);
					
					saveAttachmentMedaiInfo(fileId,presentationSyncMedia,userId);
				}
				MediaUpload.ALIYUN.deleteMessage(filePath);
			}else{
				res.setMrStat(DConstants.MEDIA_COVERT_STATUS_MRSTART_PROCESSING);
			}
		}
		arm.setData(res);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	
	private void saveAttachmentMedaiInfo(String fileId,PresentationSyncMedia presentationSyncMedia,String userId){
		AttachmentMediaItemExample attachmentMediaItemExample =  new AttachmentMediaItemExample();
		attachmentMediaItemExample.createCriteria().andMediaIdEqualTo(fileId);
		//更新附件
		AttachmentMediaItem mediaItem = new AttachmentMediaItem();
		mediaItem.setMediaId(fileId);
		mediaItem.setItemHeight(presentationSyncMedia.getMediaHeight());
		mediaItem.setItemWidth(presentationSyncMedia.getMediaWidth());
		mediaItem.setItemLength(presentationSyncMedia.getMediaLength());
		mediaItem.setItemEnd(presentationSyncMedia.getMediaEnd());
		mediaItem.setItemExt(presentationSyncMedia.getMediaExt());
		mediaItem.setItemName(presentationSyncMedia.getMediaName());
		mediaItem.setItemUrl(presentationSyncMedia.getMediaUrl());
		mediaItem.setItemDir(presentationSyncMedia.getMediaDir());
		mediaItem.setUpdateId(userId);
		mediaItem.setUpdateTime(new Date());
		mediaItem.setItemOriginalName(presentationSyncMedia.getMediaOriginalName());
		attachmentMediaItemMapper.updateByExampleSelective(mediaItem, attachmentMediaItemExample);
		
		AttachmentModelMediaExample attachmentModelMediaExample =  new AttachmentModelMediaExample();
		attachmentModelMediaExample.createCriteria().andMediaIdEqualTo(fileId);
		//更新附件
		AttachmentModelMedia attachmentModelMedia = new AttachmentModelMedia();
		attachmentModelMedia.setMediaId(fileId);
		attachmentModelMedia.setMediaHeight(presentationSyncMedia.getMediaHeight());
		attachmentModelMedia.setMediaWidth(presentationSyncMedia.getMediaWidth());
		attachmentModelMedia.setMediaLength(presentationSyncMedia.getMediaLength());
		attachmentModelMedia.setMediaEnd(presentationSyncMedia.getMediaEnd());
		attachmentModelMedia.setUpdateId(userId);
		attachmentModelMedia.setUpdateTime(new Date());
		attachmentModelMediaMapper.updateByExampleSelective(attachmentModelMedia, attachmentModelMediaExample);
		//更新附件转换状态
		attachmentService.uploadAttachmentConvertStatusByPk(fileId, DConstants.ATTACHMENT_IS_CONVERT_YES, userId);
	}
	
	/**
	 * ppt转换
	 * @param filePath
	 * @param request
	 * @param response
	 * @param presentationId
	 * @param slideId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/convert/convert2png"+Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel convert2png(@RequestParam String filePath,HttpServletRequest request,HttpServletResponse response,
			@RequestParam String presentationId,@RequestParam String slideId,@RequestParam String userId,
			@RequestParam Integer isSyncPoint,@RequestParam Integer isSlideShow,@RequestParam String interval) throws Exception{
		AjaxResultModel arm = new AjaxResultModel();
		
		String pptFileStr = StringUtils.replace(filePath, CommonUtils.FILE_LOCAL_DIR, CommonUtils.PPT_BASE_DIR);
		String pngFolderStr =StringUtils.replace(StringUtils.substringBefore(pptFileStr, "."), CommonUtils.PPT_BASE_DIR, CommonUtils.PNG_BASE_DIR);
		
		/*com.deodio.core.utils.FileUtils.createDir(new File(pptFileStr).getParent());
		com.deodio.core.utils.FileUtils.createDir(pngFolderStr);*/
		
		String absPPTFile = StringUtils.remove(pptFileStr, CommonUtils.SMB_BASE_DIR);
		String absPNGFolder = StringUtils.remove(pngFolderStr, CommonUtils.SMB_BASE_DIR);
		
	
		
		System.out.println("filePath="+absPPTFile);
		System.out.println("pngFolder="+absPNGFolder);
		/*String absPNGFolderTemp = (CommonUtils.IMGS_LOCAL_DIR+absPNGFolder).replace("Z:", "Z:\\");
		String absPPTFileTemp = (CommonUtils.IMGS_LOCAL_DIR+absPPTFile).replace("Z:", "Z:\\");*/
		String absPNGFolderTemp = (CommonUtils.FILE_LOCAL_DIR+absPNGFolder);
//		String absPPTFileTemp = (CommonUtils.FILE_LOCAL_DIR+absPPTFile);
		com.deodio.core.utils.FileUtils.createDir(absPNGFolderTemp);
		boolean ifConvert = false,isImgFlag=false;
		if(!FileUtils.getExt(absPPTFile).contains("pptx") || !FileUtils.getExt(absPPTFile).contains("ppt")) {
			absPNGFolderTemp = CommonUtils.FILE_LOCAL_DIR+absPPTFile;
			isImgFlag = true;
			ifConvert = true;
		}else {
			ifConvert = PPTUtils.ppt2png(absPPTFile, absPNGFolder);
		}

		
		List<PresentationSyncSlides> syncSlidesList = new ArrayList<PresentationSyncSlides> ();
		List<AttachmentSlidesItem> attachmentSlidesItems = new ArrayList<AttachmentSlidesItem> ();
		if(ifConvert){
			final File dir = new File(absPNGFolderTemp);
			//System.out.println(dir.listFiles().length);
			
			Integer maxOrder = presentationService.getSyncSlideMaxOrder(presentationId);
		
			Integer nextOrder = maxOrder==null?0:maxOrder+1;
			int dirLength=0;
			if(isImgFlag) {
				dirLength = 1;
			}else {
				dirLength = dir.listFiles().length;
			}
			
			
			
			for(int i=0;i<dirLength;i++){
			//	System.out.println(dir.length());
				String slideName = isImgFlag?dir.getName():dir.listFiles()[i].getName();
				long slideSize = FileUtils.getSize(isImgFlag?dir:dir.listFiles()[i]);
				String slideUrl = isImgFlag==true?absPPTFile:absPNGFolder+DConstants.STRING_SLASH+slideName;
				String slideDir = isImgFlag==true?absPPTFile:DConstants.STRING_SLASH+slideName;
				String slideOriginalName = slideName;
				//非单个图片不保存
			//if(!isImgFlag) {
				PresentationSyncSlides presentationSyncSlides = new PresentationSyncSlides();
				presentationSyncSlides.setId(AppUtils.uuid());
				presentationSyncSlides.setPresentationId(presentationId);
				presentationSyncSlides.setSlideId(slideId);
				presentationSyncSlides.setSlideName(slideName);
				presentationSyncSlides.setSlideSize(slideSize);
				presentationSyncSlides.setSlideExt(DConstants.SLIDE_EXT_PNG);
				presentationSyncSlides.setSlideUrl(slideUrl);
				presentationSyncSlides.setSlideDir(slideDir);
				presentationSyncSlides.setSlideOrder(nextOrder==0?i:nextOrder+i);
				presentationSyncSlides.setCreateId(userId);
				presentationSyncSlides.setCreateTime(new Date());
				presentationSyncSlides.setSlideOriginalName(slideOriginalName);
				syncSlidesList.add(presentationSyncSlides);
				
			//}
//			else {
//				PresentationSyncSlidesExample example = new PresentationSyncSlidesExample();
//				example.createCriteria().andPresentationIdEqualTo(presentationId);
//				syncSlidesList = presentationService.selectSyncSlidesByExample(example);
//			}
				
				
				
				//获取attachmentSlidesItem 数据
				AttachmentSlidesItem attachmentSlidesItem = new AttachmentSlidesItem();
				attachmentSlidesItem.setCreateId(userId);
				attachmentSlidesItem.setCreateTime(new Date());
				attachmentSlidesItem.setId(AppUtils.uuid());
				attachmentSlidesItem.setSlideUrl(slideUrl);
				attachmentSlidesItem.setSlideDir(slideDir);
				attachmentSlidesItem.setSlideExt(DConstants.SLIDE_EXT_PNG);
				attachmentSlidesItem.setSlideId(slideId);
				attachmentSlidesItem.setSlideName(slideName);
				attachmentSlidesItem.setSlideOrder(i);
				attachmentSlidesItem.setSlideOriginalName(slideOriginalName);
				attachmentSlidesItem.setSlideSize(slideSize);
				attachmentSlidesItems.add(attachmentSlidesItem);
			}
			presentationService.insertSyncSlidesList(syncSlidesList, isSyncPoint, isSlideShow,interval,presentationId,isImgFlag);
			
			//保存信息值附件处理表中
			if(attachmentSlidesItems != null && attachmentSlidesItems.size() > 0){
				attachmentService.insertAttachmentItem(attachmentSlidesItems,null);
				//更新附件转换状态
				attachmentService.uploadAttachmentConvertStatusByPk(slideId, DConstants.ATTACHMENT_IS_CONVERT_YES, userId);
			}
			
			arm.setStatus(AjaxResultModel.SUCCESS);
		}else{
			arm.setStatus(AjaxResultModel.FAIL);
			presentationService.delSlide(slideId,filePath);
		}
		return arm;
	}
}