package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.Media;
import com.deodio.elearning.persistence.model.PresentationFiles;
import com.deodio.elearning.persistence.model.PresentationModelSync;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncSlides;
import com.deodio.elearning.persistence.model.Slides;
import com.deodio.elearning.persistence.model.customize.PresentationBo;

/**
 * 设置Presentation信息 - 获取详细信息（关联查询图片路径）
 * @author P0076724
 *
 */
public interface PresentationCustomizeMapper {
	
	public Map<String,Object> getPresentationInfo(Map<String,Object> params);
	
	public Map<String,Object> getPresentationPackageInfo(Map<String,Object> params);

	public int updatePresentationSyncMediaById(PresentationSyncMedia presentationSyncMedia);
	
	public List<Map<String,Object>> findMediaInfoList(Map<String,Object> map);
	
	public Integer getMediaCount(Map<String,Object> map);
	
	public List<Media> getMediaList(List<Map<String,Object>> presentationSyncMediaList);
	
	public List<PresentationSyncMedia> getSyncMediaListFromAttachment(List<Map<String,Object>> presentationSyncMediaList);
	
	public Integer saveSyncMediaList(List<PresentationSyncMedia> syncMediaList);
	
	public Integer getSlideCount(Map<String,Object> map);
	
	public List<Map<String,Object>> findSlideInfoList(Map<String,Object> map);
	
	public Integer insertSyncSlidesList(List<PresentationSyncSlides> syncSlidesList);
	
	public List<Slides> selectSyncSlidesList(String presentationId);
	
	public Integer delSyncSlide(Map<String,Object> map);
	
	public List<Map<String,Object>> getSyncList(List<Map<String,Object>> syncMediaList);
	
	public Integer saveSlideList(List<Map<String,Object>> slideList);
	
	public int getEditSlidesCount(Map<String,Object> map);
	
	public List<Map<String,Object>> getEditSlidesList(Map<String,Object> map);
	
	public List<Map<String,Object>> getEditMediasList(Map<String,Object> map);
	
	public Map<String,Object> getConvertMediasInfo(Map<String,Object> params);
	
	public List<Map<String,Object>> queryClassificationList(Map<String,Object> params);
	
	public List<Map<String,Object>> isExistName(Map<String,Object> params);
	
	public List<Map<String,Object>>  findPresentationList(Map<String,Object> params);
	
	public Integer delPresentationFiles(Map<String,Object> map);
	
	public Integer delPresentationPackageItem(Map<String,Object> map);
	
	public List<PresentationFiles> findPresentationPackageList(Map<String,Object> map);
	
	public List<PresentationFiles> findAttachmentPresentationPackageList(Map<String,Object> map);
	
	public List<Map<String,Object>> getSyncPackageList(List<Map<String,Object>> syncPackageList);
	
	public List<Map<String,Object>> selectFileList(Map<String,Object> map);
	
	public List<Map<String,Object>> selectPresenetionList(Map<String,Object> map);
	
	public List<Map<String,Object>>  findPresentationListByKeyword(Map<String,Object> params);
	
	public List<Map<String,Object>>  getPresentationPackgesProfile(Map<String,Object> params);
	
	public Integer insertCourseInfoFromPresentation(Map<String,Object> params);
	
	public Integer insertCourseClassificationFromPresetation(Map<String,Object> params);
	
	public Integer insertCourseTagFromPresetation(Map<String,Object> params);
	
	public Integer insertCourseItemFromPresetation(Map<String,Object> params);

	public PresentationBo getPresentationInforById(String presentationId);
	
	public List<Map<String,Object>> findPresentationQuoteList(Map<String,Object> params);
	
	public List<Map<String,Object>> findQuoteForCourse(Map<String,Object> params);
	
	public Integer getSyncSlideMaxOrder(String presentationId);
	
	public int updatePresentationModelSync(PresentationModelSync record);
	
	public Integer getSyncSlideMaxPointTime(String presentationId);


	
}
