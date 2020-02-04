package com.deodio.elearning.commons.mediaUpload;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

public class LocalMediaUpload implements IMediaUpload{

	@Override
	public MediaUploadInfo excute(FileItem item,String logicFilePath) throws Exception {
		File uploadedFile = new File(logicFilePath);
		item.write(uploadedFile);
		return null;
	}

	@Override
	public MediaUploadInfo queryProcess(String fileUrl) {
		return null;
	}

	@Override
	public void deleteMessage(String fileUrl) throws Exception {
	}
	
}
