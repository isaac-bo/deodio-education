package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.AttachmentFileItemRel;

public interface AttachmentCustomizeMapper {
	
	public<AttachmentSlidesItem> void insertSlidesBatch(List<AttachmentSlidesItem> items);
	
	public<AttachmentScromItem> void insertScromBatch(List<AttachmentScromItem> items);
	
	public<AttachmentPackageItem> void insertPackageBatch(List<AttachmentPackageItem> items);
	
	public<AttachmentMediaItem> void insertMediaBatch(List<AttachmentMediaItem> items);
	
	public void insertAttachmentItemRel(Map<String,Object> params);
	
	public Integer selectAttachmentItemRelByAttachmentAndItem(Map<String,Object> params);
	
	public List<Map<String,Object>> selectAttachmentByItem(Map<String,Object> params);
	
	public Integer deleteAttachmentItemRel(Map<String,Object> params);
	
	public List<Map<String,Object>> findAttachmentNotSelected(Map<String,Object> params);
	
	public Integer insertAttachmentItemRelBatch(Map<String,Object> params);
	
	public Integer insertAttachmentItemSettingBatch(List<AttachmentFileItemRel> fileItemRel);
	
	public List<Map<String,Object>> queryAttachmentItemSetting(Map<String,Object> params);
	
	public Integer deleteAttachmentItemSetting(Map<String,Object> params);
	
	public List<Map<String,Object>> queryAttachmentItems(Map<String,Object> params);
}
