package com.deodio.elearning.commons.mediaUpload;

import com.aliyun.vod.client.MediaInfo;

public class MediaUploadInfo {
	
	private MediaInfo mediaInfo;
	
	private String logicFileName;
	//云存储为  
	private String deletPath;
	private String fileNameTemp;
	
	private String queueName;
	private String storageUrl;
	
	private String transcodeOutputUrl;
	private String snapshotOutputUrl;
	
	private String status;
	private String openAliyun;
	
	public String getLogicFileName() {
		return logicFileName;
	}
	public void setLogicFileName(String logicFileName) {
		this.logicFileName = logicFileName;
	}
	public String getDeletPath() {
		return deletPath;
	}
	public void setDeletPath(String deletPath) {
		this.deletPath = deletPath;
	}
	public String getFileNameTemp() {
		return fileNameTemp;
	}
	public void setFileNameTemp(String fileNameTemp) {
		this.fileNameTemp = fileNameTemp;
	}
	public MediaInfo getMediaInfo() {
		return mediaInfo;
	}
	public void setMediaInfo(MediaInfo mediaInfo) {
		this.mediaInfo = mediaInfo;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getStorageUrl() {
		return storageUrl;
	}
	public void setStorageUrl(String storageUrl) {
		this.storageUrl = storageUrl;
	}
	public String getTranscodeOutputUrl() {
		return transcodeOutputUrl;
	}
	public void setTranscodeOutputUrl(String transcodeOutputUrl) {
		this.transcodeOutputUrl = transcodeOutputUrl;
	}
	public String getSnapshotOutputUrl() {
		return snapshotOutputUrl;
	}
	public void setSnapshotOutputUrl(String snapshotOutputUrl) {
		this.snapshotOutputUrl = snapshotOutputUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOpenAliyun() {
		return openAliyun;
	}
	public void setOpenAliyun(String openAliyun) {
		this.openAliyun = openAliyun;
	}
}
