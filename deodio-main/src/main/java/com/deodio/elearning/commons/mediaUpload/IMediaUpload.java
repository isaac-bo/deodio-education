package com.deodio.elearning.commons.mediaUpload;

import org.apache.commons.fileupload.FileItem;

public interface IMediaUpload {
	MediaUploadInfo excute(FileItem item,String slideDir) throws Exception;
	
	MediaUploadInfo queryProcess(String fileUrl)throws Exception;
	
	void deleteMessage(String fileUrl)throws Exception;
}
