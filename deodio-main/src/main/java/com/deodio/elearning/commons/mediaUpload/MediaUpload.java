package com.deodio.elearning.commons.mediaUpload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.deodio.core.utils.FileUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.utils.CommonUtils;

public enum MediaUpload {
	LOCAL {
		@Override
		public MediaUploadInfo upload(FileItem item,String slideDir) throws Exception {
			
			String logicFileName = "";
			String deletPath = "";
			String fileNameTemp = "";
			
			String serverDir = CommonUtils.IMGS_LOCAL_DIR;
			String filePath = serverDir + slideDir;
			
			fileNameTemp = RandomStringUtils.randomAlphanumeric(32);
			String fileTypeStr = FileUtils.getExt(item.getName()).toLowerCase();
			
			logicFileName = fileNameTemp + "." + fileTypeStr;
			String logicFilePath = filePath + logicFileName;
			String mediaUrl = slideDir  + "/" + logicFileName;
			String mediaCover = slideDir + "/" + fileNameTemp+".jpg";
			deletPath = slideDir + logicFileName;
			
			IMediaUpload upload = new LocalMediaUpload();
			MediaUploadInfo info = upload.excute(item,logicFilePath);
			
			if(info == null){
				info = new MediaUploadInfo();
			}
			
			
			info.setDeletPath(deletPath);
			info.setFileNameTemp(fileNameTemp);
			info.setLogicFileName(logicFileName);
			info.setTranscodeOutputUrl(mediaUrl);
			info.setSnapshotOutputUrl(mediaCover);
			
			return info;
		}

		@Override
		public void deleteMessage(String fileUrl) throws Exception {
			// TODO Auto-generated method stub
			
		}
	},ALIYUN {
		@Override
		public MediaUploadInfo upload(FileItem item,String slideDir) throws Exception{
			IMediaUpload upload = new AliyunMediaUpload();
			MediaUploadInfo info =  upload.excute(item,null);
			String logicFileName = StringUtils.substringAfterLast(info.getTranscodeOutputUrl(),DConstants.DELIMITER_SLASH);
			String fileNameTemp = StringUtils.substringBefore(logicFileName, DConstants.DELIMITER_FULL);
			info.setLogicFileName(logicFileName);
			info.setFileNameTemp(fileNameTemp);
			info.setOpenAliyun("1");
			return info;
		}
		@Override
		public MediaUploadInfo queryProcess(String fileUrl) throws Exception {
			IMediaUpload upload = new AliyunMediaUpload();
			MediaUploadInfo info = upload.queryProcess(fileUrl);
			String logicFileName = StringUtils.substringAfterLast(info.getTranscodeOutputUrl(),DConstants.DELIMITER_SLASH);
			String fileNameTemp = StringUtils.substringAfter(logicFileName, DConstants.DELIMITER_FULL);
			info.setLogicFileName(logicFileName);
			info.setFileNameTemp(fileNameTemp);
			info.setOpenAliyun("1");
			 return info;
		}
		@Override
		public void deleteMessage(String fileUrl) throws Exception {
			IMediaUpload upload = new AliyunMediaUpload();
			upload.deleteMessage(fileUrl);
		}
	};
	
	public abstract MediaUploadInfo upload(FileItem item,String slideDir)throws Exception;
	public abstract void deleteMessage(String fileUrl)throws Exception;
	public MediaUploadInfo queryProcess(String fileUrl)throws Exception{
		return null;
	}
	
}
