package com.deodio.elearning.modules.presentation.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.comparator.SyncMediasComparator;
import com.deodio.elearning.comparator.SyncPointsComparator;
import com.deodio.elearning.comparator.SyncQuizsComparator;
import com.deodio.elearning.comparator.SyncSurveyComparator;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.AttachmentModelMediaMapper;
import com.deodio.elearning.persistence.mapper.AttachmentPackageItemMapper;
import com.deodio.elearning.persistence.mapper.AttachmentSlidesItemMapper;
import com.deodio.elearning.persistence.mapper.ClassificationItemsRelMapper;
import com.deodio.elearning.persistence.mapper.CourseOnlineItemMapper;
import com.deodio.elearning.persistence.mapper.MediaMapper;
import com.deodio.elearning.persistence.mapper.PresentationDiscussionMapper;
import com.deodio.elearning.persistence.mapper.PresentationDiscussionReplyMapper;
import com.deodio.elearning.persistence.mapper.PresentationExternalItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationFilesMapper;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelExternalMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelPackageMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelScromMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelSyncMapper;
import com.deodio.elearning.persistence.mapper.PresentationPackageItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationScromItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncMediaMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncPointsMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncQuizsMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncSlidesMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncSurveyMapper;
import com.deodio.elearning.persistence.mapper.SlidesMapper;
import com.deodio.elearning.persistence.mapper.TagsItemsRelMapper;
import com.deodio.elearning.persistence.mapper.customize.PresentationCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.PresentationDiscussionCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.PresentationSyncCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentExample;
import com.deodio.elearning.persistence.model.AttachmentModelMediaExample;
import com.deodio.elearning.persistence.model.AttachmentPackageItem;
import com.deodio.elearning.persistence.model.AttachmentPackageItemExample;
import com.deodio.elearning.persistence.model.AttachmentSlidesItem;
import com.deodio.elearning.persistence.model.AttachmentSlidesItemExample;
import com.deodio.elearning.persistence.model.ClassificationItemsRel;
import com.deodio.elearning.persistence.model.ClassificationItemsRelExample;
import com.deodio.elearning.persistence.model.CourseOnlineItem;
import com.deodio.elearning.persistence.model.CourseOnlineItemExample;
import com.deodio.elearning.persistence.model.Media;
import com.deodio.elearning.persistence.model.MediaExample;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationDiscussionReply;
import com.deodio.elearning.persistence.model.PresentationDiscussionReplyExample;
import com.deodio.elearning.persistence.model.PresentationExample;
import com.deodio.elearning.persistence.model.PresentationExternalItem;
import com.deodio.elearning.persistence.model.PresentationExternalItemExample;
import com.deodio.elearning.persistence.model.PresentationFiles;
import com.deodio.elearning.persistence.model.PresentationModelExternal;
import com.deodio.elearning.persistence.model.PresentationModelExternalExample;
import com.deodio.elearning.persistence.model.PresentationModelPackage;
import com.deodio.elearning.persistence.model.PresentationModelPackageExample;
import com.deodio.elearning.persistence.model.PresentationModelScrom;
import com.deodio.elearning.persistence.model.PresentationModelScromExample;
import com.deodio.elearning.persistence.model.PresentationModelSync;
import com.deodio.elearning.persistence.model.PresentationModelSyncExample;
import com.deodio.elearning.persistence.model.PresentationPackageItem;
import com.deodio.elearning.persistence.model.PresentationPackageItemExample;
import com.deodio.elearning.persistence.model.PresentationScromItem;
import com.deodio.elearning.persistence.model.PresentationScromItemExample;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncMediaExample;
import com.deodio.elearning.persistence.model.PresentationSyncPoints;
import com.deodio.elearning.persistence.model.PresentationSyncPointsExample;
import com.deodio.elearning.persistence.model.PresentationSyncQuizs;
import com.deodio.elearning.persistence.model.PresentationSyncQuizsExample;
import com.deodio.elearning.persistence.model.PresentationSyncSlides;
import com.deodio.elearning.persistence.model.PresentationSyncSlidesExample;
import com.deodio.elearning.persistence.model.PresentationSyncSurvey;
import com.deodio.elearning.persistence.model.PresentationSyncSurveyExample;
import com.deodio.elearning.persistence.model.Slides;
import com.deodio.elearning.persistence.model.SlidesExample;
import com.deodio.elearning.persistence.model.TagsItemsRel;
import com.deodio.elearning.persistence.model.TagsItemsRelExample;
import com.deodio.elearning.persistence.model.customize.PresentationBo;
import com.deodio.elearning.persistence.model.customize.PresentationDiscussionDto;
import com.deodio.elearning.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * 新建课程presentation相关设置Service
 * @author P0076724
 *
 */
@Service
public class PresentationService {

	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private PresentationSyncMediaMapper presentationSyncMediaMapper;
	
	@Autowired
	private PresentationSyncPointsMapper presentationSyncPointsMapper;
	
	@Autowired
	private PresentationSyncSlidesMapper presentationSyncSlidesMapper;
	
	@Autowired
	private PresentationSyncQuizsMapper presentationSyncQuizsMapper;
	
	@Autowired
	private PresentationModelSyncMapper presentationModelSyncMapper;
	
	@Autowired
	private PresentationModelScromMapper presentationModelScromMapper;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private PresentationCustomizeMapper presentationCustomizeMapper;
	
	@Autowired
	private MediaMapper mediaMapper;
	
	@Autowired
	private SlidesMapper slidesMapper;
	
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private PresentationScromItemMapper presentationScromItemMapper;
	
	@Autowired
	AttachmentSlidesItemMapper  attachmentSlidesItemMapper;
	
	
	@Autowired
	private PresentationDiscussionCustomizeMapper  presentationDiscussionCustomizeMapper;
	
	@Autowired
	private PresentationDiscussionReplyMapper  presentationDiscussionReplyMapper;
	
	@Autowired
	private PresentationDiscussionMapper presentationDiscussionMapper;
	
	
	@Autowired
	private TagsItemsRelMapper tagsItemsRelMapper;
	@Autowired
	private ClassificationItemsRelMapper classificationItemsRelMapper;
	
	@Autowired
	private PresentationFilesMapper presentationFilesMapper;
	
	@Autowired
	private PresentationPackageItemMapper presentationPackageItemMapper;
	
	@Autowired
	private PresentationModelPackageMapper presentationModelPackageMapper;
	
	
	@Autowired
	private PresentationModelExternalMapper presentationModelExternalMapper;
	
	@Autowired
	private PresentationExternalItemMapper presentationExternalItemMapper;
	
	@Autowired
	private AttachmentPackageItemMapper attachmentPackageItemMapper;
	@Autowired
	private PresentationSyncSurveyMapper presentationSyncSurveyMapper;
	@Autowired
	private CourseOnlineItemMapper courseOnlineItemMapper;
	
	@Autowired
	private AttachmentModelMediaMapper attachmentModelMediaMapper;
	
	
	@Autowired
	private PresentationSyncCustomizeMapper presentationSyncCustomizeMapper;
	
	
	/**
	 * 删除指定章节(只有章节创建者才能删除)
	 * @param presetatioinId
	 * @param createId
	 * @return
	 */
	public int deletePresetation(String presentationId,String createId) throws DeodioException{
		PresentationExample example = new PresentationExample();
		example.createCriteria().andIdEqualTo(presentationId).andCreateIdEqualTo(createId);
		return presentationMapper.deleteByExample(example);
	}
	
	public PresentationBo getPresentation(String presentationId,String type){
		PresentationBo presentationBo = new PresentationBo();
		Presentation presentation =  presentationMapper.selectByPrimaryKey(presentationId);
		BeanUtils.copyProperties(presentation, presentationBo);
		if(StringUtils.isBlank(type)) {
			presentationBo.setMedias(getMediasByPresentationId(presentationId));
		}else {
			presentationBo.setModelSync(getSildesByPresentationId(presentationId));
		}
			
		presentationBo.setPoints(getPointsByPresentationId(presentationId));
		presentationBo.setSlides(getSlidesByPresentationId(presentationId));
		presentationBo.setQuizs(getQuizsByPresentationId(presentationId));
		presentationBo.setSurvey(getSurveyByPresentationId(presentationId));
		return presentationBo;
	}
	
	
	public void savePresentation(String presentationId,String currentUserId, String pointsJson,String mediasJson){
		Gson _pointsJson = new Gson(); 
		List<Map<String,Object>> pointList = _pointsJson.fromJson(pointsJson, new TypeToken<List<Map<String,Object>>>(){}.getType());
		
		Gson _mediasJson = new Gson();
		List<Map<String,Object>> mediaList = _mediasJson.fromJson(mediasJson, new TypeToken<List<Map<String,Object>>>(){}.getType());
		
		PresentationBo presentationBo = new PresentationBo();
		presentationBo.setId(presentationId);
		
		
		List<PresentationSyncPoints> syncPointList = new ArrayList<PresentationSyncPoints>();
		List<PresentationSyncQuizs> syncQuizList = new ArrayList<PresentationSyncQuizs>();
		
		List<PresentationSyncMedia> syncMediaList = new ArrayList<PresentationSyncMedia>();
		
		List<PresentationSyncSurvey> syncSurveyList = new ArrayList<PresentationSyncSurvey>();
		
		
		
		for(int index = 0; index < pointList.size(); index++){
			Map<String,Object> pointMap = pointList.get(index);
			if(pointMap.get("type").equals("sync")){
				setSyncPoints(presentationId, currentUserId, syncPointList, pointMap);
			}else if(pointMap.get("type").equals("quiz")){
				setSyncQuizs(presentationId, currentUserId, syncQuizList, pointMap);
			}else if(pointMap.get("type").equals("survey")) {
				setSyncSurvey(presentationId, currentUserId, syncSurveyList, pointMap);
			}
		}
		
		for(int index = 0; index < mediaList.size(); index++){
			Map<String,Object> mediaMap = mediaList.get(index);
			setSyncMedias(presentationId, currentUserId, syncMediaList,mediaMap);
		}
		
		Collections.sort(syncPointList,new SyncPointsComparator());
		Collections.sort(syncQuizList,new SyncQuizsComparator());
		Collections.sort(syncMediaList,new SyncMediasComparator());
		Collections.sort(syncSurveyList,new SyncSurveyComparator());
		
		
		presentationBo.setPoints(syncPointList);
		presentationBo.setQuizs(syncQuizList);
		presentationBo.setMedias(syncMediaList);
		presentationBo.setSurvey(syncSurveyList);
		savePresentation(presentationBo);
	}

	private void setSyncMedias(String presentationId, String currentUserId,
			List<PresentationSyncMedia> syncMediaList,
			Map<String, Object> mediaMap) {
		PresentationSyncMedia presentationSyncMedia = new PresentationSyncMedia();
		presentationSyncMedia.setId((String)mediaMap.get("id"));
		presentationSyncMedia.setMediaId((String)mediaMap.get("mediaId"));
		presentationSyncMedia.setMediaCover((String)mediaMap.get("mediaCover"));
//		presentationSyncMedia.setMediaConvertStatus((BigDecimal.valueOf((Double)mediaMap.get("mediaCoverStatus"))).intValue());
		presentationSyncMedia.setMediaDir((String)mediaMap.get("mediaDir"));
		presentationSyncMedia.setMediaUrl((String)mediaMap.get("mediaUrl"));
		presentationSyncMedia.setMediaExt((String)mediaMap.get("mediaExt"));
		presentationSyncMedia.setMediaName((String)mediaMap.get("mediaName"));
		presentationSyncMedia.setMediaLength((BigDecimal.valueOf((Double)mediaMap.get("mediaLength"))).longValue());
		presentationSyncMedia.setMediaEnd((BigDecimal.valueOf((Double)mediaMap.get("mediaEnd"))).longValue());
		presentationSyncMedia.setMediaStart((BigDecimal.valueOf((Double)mediaMap.get("mediaStart"))).longValue());
		presentationSyncMedia.setMediaHeight((BigDecimal.valueOf((Double)mediaMap.get("mediaHeight"))).intValue());
		presentationSyncMedia.setMediaWidth((BigDecimal.valueOf((Double)mediaMap.get("mediaWidth"))).intValue());
		presentationSyncMedia.setMediaSize((BigDecimal.valueOf((Double)mediaMap.get("mediaSize"))).longValue());
		presentationSyncMedia.setMediaOriginalName((String)mediaMap.get("mediaOriginalName"));
		presentationSyncMedia.setMediaSize((BigDecimal.valueOf((Double)mediaMap.get("mediaSize"))).longValue());
		presentationSyncMedia.setMediaType((BigDecimal.valueOf((Double)mediaMap.get("mediaType"))).intValue());
		presentationSyncMedia.setPresentationId(presentationId);
		presentationSyncMedia.setCreateId(currentUserId);
		presentationSyncMedia.setUpdateId(currentUserId);
		presentationSyncMedia.setCreateTime(new Date());
		presentationSyncMedia.setUpdateTime(new Date());

		syncMediaList.add(presentationSyncMedia);
	}

	private void setSyncQuizs(String presentationId, String currentUserId,
			List<PresentationSyncQuizs> syncQuizList,
			Map<String, Object> pointMap) {
		PresentationSyncQuizs presentationSyncQuizs = new PresentationSyncQuizs();
		presentationSyncQuizs.setId(AppUtils.uuid());
		presentationSyncQuizs.setQuizId((String)pointMap.get("quizId"));
		presentationSyncQuizs.setPresentationId(presentationId);
		presentationSyncQuizs.setQuizName(((String)pointMap.get("name")).replaceAll("名称：",""));
		presentationSyncQuizs.setQuizDesc(((String)pointMap.get("desc")).replaceAll("描述：",""));
		presentationSyncQuizs.setQuizTime((BigDecimal.valueOf((Double)pointMap.get("time"))).intValue());
		presentationSyncQuizs.setCreateId(currentUserId);
		presentationSyncQuizs.setUpdateId(currentUserId);
		presentationSyncQuizs.setCreateTime(new Date());
		presentationSyncQuizs.setUpdateTime(new Date());
		syncQuizList.add(presentationSyncQuizs);
	}

	private void setSyncSurvey(String presentationId, String currentUserId,
			List<PresentationSyncSurvey>syncSurveyList,
			Map<String, Object> pointMap) {
		PresentationSyncSurvey presentationSyncSurvey = new PresentationSyncSurvey();
		presentationSyncSurvey.setId(AppUtils.uuid());
		presentationSyncSurvey.setSurveyId((String)pointMap.get("quizId"));
		presentationSyncSurvey.setPresentationId(presentationId);
		presentationSyncSurvey.setSurveyName(((String)pointMap.get("name")).replaceAll("名称：",""));
		presentationSyncSurvey.setSurveyDesc(((String)pointMap.get("desc")).replaceAll("描述：",""));
		presentationSyncSurvey.setSurveyTime((BigDecimal.valueOf((Double)pointMap.get("time"))).intValue());
		presentationSyncSurvey.setCreateId(currentUserId);
		presentationSyncSurvey.setUpdateId(currentUserId);
		presentationSyncSurvey.setCreateTime(new Date());
		presentationSyncSurvey.setUpdateTime(new Date());
		syncSurveyList.add(presentationSyncSurvey);
	}
	
	
	
	
	
	private void setSyncPoints(String presentationId, String currentUserId,
			List<PresentationSyncPoints> syncPointList,
			Map<String, Object> pointMap) {
		PresentationSyncPoints presentationSyncPoints = new PresentationSyncPoints();
		presentationSyncPoints.setId(AppUtils.uuid());
		presentationSyncPoints.setSyncSlideId((String)pointMap.get("slideId"));
		presentationSyncPoints.setPointDir(((String)pointMap.get("thumbnail")).replaceAll(CommonUtils.IMGS_SERVER_DIR, ""));
		presentationSyncPoints.setPointUrl(((String)pointMap.get("thumbnail")).replaceAll(CommonUtils.IMGS_SERVER_DIR, ""));
		presentationSyncPoints.setPointExt(((String)pointMap.get("thumbnail")).substring(((String)pointMap.get("thumbnail")).lastIndexOf(".")+1));
		presentationSyncPoints.setPointTime((BigDecimal.valueOf((Double)pointMap.get("time"))).intValue());
		presentationSyncPoints.setPresentationId(presentationId);
		presentationSyncPoints.setPointEnd(0);
		presentationSyncPoints.setCreateId(currentUserId);
		presentationSyncPoints.setUpdateId(currentUserId);
		presentationSyncPoints.setCreateTime(new Date());
		presentationSyncPoints.setUpdateTime(new Date());
		syncPointList.add(presentationSyncPoints);
	}
	
	public void savePresentation(PresentationBo presentationBo){
		List<PresentationSyncPoints> syncPoints = presentationBo.getPoints();
		
		PresentationSyncPointsExample example = new PresentationSyncPointsExample();
		example.createCriteria().andPresentationIdEqualTo(presentationBo.getId());
		presentationSyncPointsMapper.deleteByExample(example);
		
		if(!syncPoints.isEmpty()){
			for(int index=0; index<syncPoints.size();index++){
				PresentationSyncPoints syncPoint = syncPoints.get(index);
				syncPoint.setPointOrder(index);
				presentationSyncPointsMapper.insertSelective(syncPoint);
			}
		}
		
		List<PresentationSyncMedia> syncMedias = presentationBo.getMedias();
		
		PresentationSyncMediaExample example2 = new PresentationSyncMediaExample();
		example2.createCriteria().andPresentationIdEqualTo(presentationBo.getId());
		presentationSyncMediaMapper.deleteByExample(example2);
		
		if(!syncMedias.isEmpty()){
			for(int index=0; index<syncMedias.size();index++){
				PresentationSyncMedia syncMedia = syncMedias.get(index);
				syncMedia.setMediaOrder(index);
				presentationSyncMediaMapper.insertSelective(syncMedia);
			}
		}
		
		
		List<PresentationSyncQuizs> syncQuizs = presentationBo.getQuizs();
		
		PresentationSyncQuizsExample example3 = new PresentationSyncQuizsExample();
		example3.createCriteria().andPresentationIdEqualTo(presentationBo.getId());
		presentationSyncQuizsMapper.deleteByExample(example3);
		
		if(!syncQuizs.isEmpty()){
			for(int index=0; index<syncQuizs.size();index++){
				PresentationSyncQuizs syncQuiz = syncQuizs.get(index);
				syncQuiz.setQuizOrder(index);
				presentationSyncQuizsMapper.insertSelective(syncQuiz);
			}
		}
		List<PresentationSyncSurvey> surveyList = presentationBo.getSurvey();
		if(!surveyList.isEmpty()) {
			for(int index = 0,j = surveyList.size(); index<j; index++){
				PresentationSyncSurvey syncSurvey = surveyList.get(index);
				syncSurvey.setSurveyOrder(index);
				presentationSyncSurveyMapper.insertSelective(syncSurvey);
			}
		}
		
			
		
	}

	protected List<PresentationSyncQuizs> getQuizsByPresentationId(String presentationId) {
		PresentationSyncQuizsExample example = new PresentationSyncQuizsExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		example.setOrderByClause("quiz_order");
		return presentationSyncQuizsMapper.selectByExample(example);
	}

	protected List<PresentationSyncSlides> getSlidesByPresentationId(String presentationId) {
		PresentationSyncSlidesExample example = new PresentationSyncSlidesExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		example.setOrderByClause("slide_order");
		return presentationSyncSlidesMapper.selectByExample(example);
	}

	protected List<PresentationSyncPoints> getPointsByPresentationId(String presentationId) {
		PresentationSyncPointsExample example = new PresentationSyncPointsExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		example.setOrderByClause("point_order");
		return presentationSyncPointsMapper.selectByExample(example);
	}

	protected List<PresentationSyncMedia> getMediasByPresentationId(String presentationId) {
		
		PresentationSyncMediaExample example = new PresentationSyncMediaExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		example.setOrderByClause("media_order");
		return presentationSyncMediaMapper.selectByExample(example);
	}
	
	protected List<PresentationSyncSurvey> getSurveyByPresentationId(String presentationId) {
		PresentationSyncSurveyExample example = new PresentationSyncSurveyExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		example.setOrderByClause("survey_order");
		return presentationSyncSurveyMapper.selectByExample(example);
	}
	
	
	public PresentationModelSync getSildesByPresentationId(String presentationId) {
		PresentationModelSyncExample example = new PresentationModelSyncExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		
		
		List<PresentationModelSync> list = presentationModelSyncMapper.selectByExample(example);
		 
		 return list.isEmpty()?null:list.get(0);
	}
	
	
	/**
	 * 设置课程信息
	 * @param presentationName
	 * @param presentationDesc
	 * @param id
	 * @param userId
	 * @param presentationCover
	 * @param attachId
	 * @throws DeodioException
	 */
	public void insertPresentationInfo(String presentationName,String presentationDesc,String id,String userId,
			String presentationCover,String attachId,String tagsJson,String accountId,String classificationJson) throws DeodioException{
		Presentation presentation = new Presentation();
		presentation.setId(id);
		presentation.setPresentationName(presentationName);
		presentation.setPresentationDesc(presentationDesc);
		presentation.setPresentationCover(presentationCover);
		//设置发布状态为未发布
		presentation.setIsPublish(DConstants.TYPE_NO_PUBLISH.shortValue());
		//是否已经设置课程
		PresentationExample example = new PresentationExample();
		example.createCriteria().andIdEqualTo(id);
		List<Presentation> list = presentationMapper.selectByExample(example);
		
		//已经设置课程执行更新，否则执行插入
		if(list.isEmpty()){
			presentation.setCreateTime(new Date());
			presentation.setCreateId(userId);
			presentation.setAccountId(accountId);
			//课程主表
			presentationMapper.insertSelective(presentation);
			/*presentationCustomizeMapper.insertPersentationTags(tagsList);
			presentationCustomizeMapper.insertPersentationTagsRel(tagsRelList);*/
		}else{
			presentation.setUpdateTime(new Date());
			presentation.setUpdateId(userId);
			presentationMapper.updateByExampleSelective(presentation, example);
		}
		
		
		tagsService.saveTagsItemsRel(tagsJson,accountId, userId, id, DConstants.TAGS_ITEMS_TYPE_PRESENTATION);
		
		classificationService.saveClassificationItemsRel(classificationJson, userId, id, DConstants.CLASSIFICATION_ITEMS_TYPE_PRESENTATION);
		//图片表更新
		if(null!=attachId&&!"".equals(attachId)){
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
	}
	
	/**
	 * 课件类型设置(弹出选择窗口)
	 * @param id
	 * @param presentationModel
	 * @throws DeodioException
	 */
	public void updatePresentationInfo(String id,String presentationModel) throws DeodioException{
		Presentation presentation = new Presentation();
		presentation.setId(id);
		presentation.setPresentationModel(Short.parseShort(presentationModel));
		presentationMapper.updateByPrimaryKeySelective(presentation);
	}
	
	public void saveScromInfo(String presentationId, Integer isPassPercentage, String persentationPercentage,
			Integer isPassElements, Integer isCourse, String userId, Integer isCountDown,
			List itemId, List itemTime) {
		
		if (DConstants.PRESENTATION_IS_COUNT_DOWN_FLAG_YES==isCountDown) {
			for (int i = 0; i < itemId.size(); i++) {
				PresentationScromItem persentationScromItem = new PresentationScromItem();
				persentationScromItem.setPresentationId(presentationId);
				persentationScromItem.setId(itemId.get(i).toString());
				if(!"".equals(itemTime.get(i).toString())){
					persentationScromItem.setIsCountdown(isCountDown.shortValue());
					persentationScromItem.setTimeAllowed(Integer.valueOf(itemTime.get(i).toString()).longValue());
				}
				persentationScromItem.setUpdateId(userId);
				persentationScromItem.setUpdateTime(new Date());
				presentationScromItemMapper.updateByPrimaryKeySelective(persentationScromItem);
			}
		}
		
		PresentationModelScromExample presentationModelScromExample = new PresentationModelScromExample();
		presentationModelScromExample.createCriteria().andPresentationIdEqualTo(presentationId);
		presentationModelScromMapper.deleteByExample(presentationModelScromExample);
			
		PresentationModelScrom presentationModelScrom = new PresentationModelScrom();
		presentationModelScrom.setId(AppUtils.uuid());
		presentationModelScrom.setPresentationId(presentationId);
		presentationModelScrom.setIsCountDown(isCountDown.shortValue());
		presentationModelScrom.setCreateId(userId);
		presentationModelScrom.setUpdateId(userId);
		presentationModelScrom.setCreateTime(new Date());
		presentationModelScrom.setUpdateTime(new Date());
		presentationModelScromMapper.insert(presentationModelScrom);
		
		Presentation presentation = new Presentation();
		presentation.setPersentationPercentage(persentationPercentage);
//		presentation.setIsPassPercentage(isPassPercentage.shortValue());
		presentation.setIsPassElements(isPassElements.shortValue());
		presentation.setIsCourse(isCourse.shortValue());
		presentation.setId(presentationId);
		presentation.setUpdateId(userId);
		presentation.setUpdateTime(new Date());
		presentationMapper.updateByPrimaryKeySelective(presentation);
	}
	
	/**
	 * 设置课件规则
	 * @param id
	 * @param presentationId
	 * @param isSlideShow
	 * @param isManually
	 * @param length
	 * @param interval
	 * @param ifInitSyncPoint
	 * @param persentationPercentage
	 * @param ifPassHours
	 * @param ifPassElements
	 * @param ifPassQuizs
	 * @param ifCourse
	 * @param userId
	 * @throws DeodioException
	 */
	public boolean insertFlashRule(String id,String presentationId,Integer isSlideShow,Integer isManually,
			Long length,Long interval,Integer ifInitSyncPoint,String persentationPercentage,Integer ifPassHours,
			Integer ifPassQuizs,Integer ifCourse,String userId) throws DeodioException{
		PresentationModelSync presentationModelSync = new PresentationModelSync();
		presentationModelSync.setPresentationId(presentationId);
		presentationModelSync.setLength(length);
		// 自动时 获取 间隔时间，判断是否需要更改 原始PPT中每页间隔时间
		PresentationModelSyncExample example = new PresentationModelSyncExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationModelSync> list = presentationModelSyncMapper.selectByExample(example);
		boolean flag = false;
		
		// 单独为PPT时
		if(isSlideShow==0) {
			
			// 判断是否已经上传过PPT 如果上传过 进行 间隔时间图片处理
			PresentationSyncPointsExample example2 = new PresentationSyncPointsExample();
			example2.createCriteria().andPresentationIdEqualTo(presentationId);
			example2.setOrderByClause("point_time asc");
			List<PresentationSyncPoints> list2 = presentationSyncPointsMapper.selectByExample(example2);
			/*
			if(!list2.isEmpty()) {
				if(list2.get(0).getEnableFlag()!=isManually) {
					for(PresentationSyncPoints record : list2) {
						record.setEnableFlag(isManually);
						record.setPointTime(isManually==0?record.getPointOrder()*Integer.parseInt(String.valueOf(interval)):0);
						presentationSyncPointsMapper.updateByPrimaryKeySelective(record);
					}
				}
			}*/
			// 0 是自动，1是手动
			if(isManually!=null && isManually ==1) {
				// 
				if(!list.isEmpty()) {
					if(list.get(0).getIsSlideShow()==0) {
						if(list.get(0).getSlideLength() > Integer.parseInt(String.valueOf(length))) {
							flag = true;
						}
					}
					
				}
			
				presentationModelSync.setIsManually(isManually.shortValue());
				presentationModelSync.setInterval(null);
				presentationModelSync.setSlideLength(Integer.parseInt(String.valueOf(length)));
			} else {
				
				presentationModelSync.setInterval(interval);
				presentationModelSync.setIsManually(Constants.COURSE_TRAINEE_STATUS_ACTIVE);
				
				if(!list.isEmpty()) {
					
					Integer intervalInt = Integer.parseInt(String.valueOf(interval));
					
					if(list.get(0).getInterval()!=interval) {
						// 更新点 ppt 时间轴
						PresentationSyncPoints record = null;
						// list2 位空，之前为手动，手动不需要插入点。
						if(list2.isEmpty()) {
							
							List<AttachmentSlidesItem> itemsList = 	presentationSyncCustomizeMapper.getAttachmentSlidesItemByPresentationId(presentationId);
							PresentationSyncPoints syncPoints =  null;
							for(int i=0;i<itemsList.size();i++) {
								AttachmentSlidesItem  items =  itemsList.get(i);
								syncPoints = new PresentationSyncPoints();
								syncPoints.setId(AppUtils.uuid());
								syncPoints.setPresentationId(presentationId);
								syncPoints.setSyncSlideId(items.getSlideId());
								syncPoints.setPointExt(items.getSlideExt());
								syncPoints.setPointUrl(items.getSlideUrl());
								syncPoints.setPointDir(items.getSlideDir());
								syncPoints.setPointTime(i==0?intervalInt:intervalInt*i);
								syncPoints.setPointOrder(i);
								syncPoints.setPointEnd(0);
								syncPoints.setEnableFlag(0);
								syncPoints.setCreateId(items.getCreateId());
								syncPoints.setCreateTime(items.getCreateTime());
								presentationSyncPointsMapper.insertSelective(syncPoints);
							}
							
							
							
						}else {
							for(int i=0;i<list2.size();i++) {
								record = list2.get(i);
								record.setPointTime(intervalInt * i);
								//record.setPointTime(isManually==0?i*intervalInt:0);
								presentationSyncPointsMapper.updateByPrimaryKeySelective(record);
							}
							
						}
						
						
						
					}	
					presentationModelSync.setSlideLength(intervalInt * list2.size());
					
				}else {
					presentationModelSync.setSlideLength(null);
				}
				
				
			}
		}
		
		if(!flag) {
			// 判断是否上传过PPT 

			// 判断是否 已经上传了PPT ，如果上传PPT 判断是否初始化第一张
			if(ifInitSyncPoint==1) {
				
				PresentationSyncSlidesExample example3 = new PresentationSyncSlidesExample();
				example3.createCriteria().andPresentationIdEqualTo(presentationId);
				List<PresentationSyncSlides> list3 = presentationSyncSlidesMapper.selectByExample(example3);
				
				// 判断PPT 时间轴 是否已经有图片
				PresentationSyncPointsExample example5 = new PresentationSyncPointsExample();
				example5.createCriteria().andPresentationIdEqualTo(presentationId);
				
				List<PresentationSyncPoints> plist = presentationSyncPointsMapper.selectByExample(example5);
				
				if(!list3.isEmpty() && plist.isEmpty()) {
					PresentationSyncSlides syncSlides = list3.get(0);
					PresentationSyncPoints syncPoints = new PresentationSyncPoints();
					syncPoints.setId(AppUtils.uuid());
					syncPoints.setPresentationId(presentationId);
					syncPoints.setSyncSlideId(syncSlides.getSlideId());
					syncPoints.setPointExt(syncSlides.getSlideExt());
					syncPoints.setPointUrl(syncSlides.getSlideUrl());
					syncPoints.setPointDir(syncSlides.getSlideDir());
					syncPoints.setPointTime(0);
					syncPoints.setPointOrder(0);
					syncPoints.setPointEnd(0);
					syncPoints.setEnableFlag(0);
					syncPoints.setCreateId(syncSlides.getCreateId());
					syncPoints.setCreateTime(syncSlides.getCreateTime());
					presentationSyncPointsMapper.insertSelective(syncPoints);
					
				}
				
			}
			
			presentationModelSync.setIsSlideShow(isSlideShow.shortValue());
			presentationModelSync.setIsInitSyncPoint(ifInitSyncPoint.shortValue());
			//如果已经设置规则执行更新，否则插入
		
			int result = 0;
			if(list.isEmpty()){
				presentationModelSync.setId(id);
				presentationModelSync.setCreateTime(new Date());
				presentationModelSync.setCreateId(userId);
				result = presentationModelSyncMapper.insertSelective(presentationModelSync);
			}else{
				presentationModelSync.setUpdateTime(new Date());
				presentationModelSync.setUpdateId(userId);
				result = presentationCustomizeMapper.updatePresentationModelSync(presentationModelSync);
			}
			//课程主表更新
			if(result>0){
				Presentation presentation = new Presentation();
				presentation.setPersentationPercentage(persentationPercentage);
				presentation.setIsPassHours(ifPassHours.shortValue());
//				presentation.setIsPassElements(ifPassElements.shortValue());
				presentation.setIsPassQuizs(ifPassQuizs.shortValue());
				presentation.setIsCourse(ifCourse.shortValue());
				presentation.setId(presentationId);
				presentation.setUpdateId(userId);
				presentation.setUpdateTime(new Date());
				presentationMapper.updateByPrimaryKeySelective(presentation);
			}
		}
		return flag;
	}
	/**
	 * 课件规则详细
	 * @param presentationId
	 * @return
	 */
	public PresentationModelSync queryPresentationModelSync(String presentationId){
		PresentationModelSyncExample example = new PresentationModelSyncExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationModelSync> list = presentationModelSyncMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	/**
	 * Presentation详细信息获取（关联图片）
	 * @param params
	 * @return
	 */ 
	public Map<String,Object> queryPresentation(Map<String,Object> params){
		Map<String,Object> presentationMap = presentationCustomizeMapper.getPresentationInfo(params);
		presentationMap.put("presenationDescWithoutTags", StringUtils.removeHtmlTags(presentationMap.get("presentation_desc").toString()));
		presentationMap.put("presenationDescShortString", StringUtils.getStandardCharacters(StringUtils.removeHtmlTags(presentationMap.get("presentation_desc").toString()),160));
		
		return presentationMap;
	}
	
	/**
	 * 获取视频信息
	 * @param presentatonId
	 * @return
	 */
	public PresentationSyncMedia getPresentationSyncMediaInfor(String presentatonId) {
		PresentationSyncMediaExample example = new PresentationSyncMediaExample();
		example.createCriteria().andPresentationIdEqualTo(presentatonId);
		List<PresentationSyncMedia> list = presentationSyncMediaMapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 获取PPT 信息
	 * @param presentatonId
	 * @return
	 */
	public PresentationModelSync getPresentationModelSyncInfor(String presentatonId) {
		PresentationModelSyncExample example = new PresentationModelSyncExample();
		example.createCriteria().andPresentationIdEqualTo(presentatonId);
		List<PresentationModelSync> list = presentationModelSyncMapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
	
	
	
	public Map<String,Object> queryPresentationModelPackage(Map<String,Object> params){
		Map<String,Object> presetationPackage = presentationCustomizeMapper.getPresentationPackageInfo(params);
		return presetationPackage;
	}

	public int updatePresentationSyncMediaById(PresentationSyncMedia presentationSyncMedia){
		return presentationCustomizeMapper.updatePresentationSyncMediaById(presentationSyncMedia);
	}

	public List<PresentationSyncMedia> selectByExample(PresentationSyncMediaExample example){
		return presentationSyncMediaMapper.selectByExample(example);
	}
	
	public List<PresentationSyncSlides> selectSyncSlidesByExample(PresentationSyncSlidesExample example){
		return presentationSyncSlidesMapper.selectByExample(example);
	}
	
	public List<Media> selectByMediaExample(MediaExample mediaExample){
		return mediaMapper.selectByExample(mediaExample);
	}
	
	public List<Map<String,Object>> queryMediaInfoList(Map<String,Object> map){
		return presentationCustomizeMapper.findMediaInfoList(map);
	}
	
	public List<Map<String,Object>> querySlideInfoList(Map<String,Object> map){
		return presentationCustomizeMapper.findSlideInfoList(map);
	}
	
	public Integer getMediaCount(Map<String,Object> map){
		return presentationCustomizeMapper.getMediaCount(map);
	}
	
	public Integer getSlideCount(Map<String,Object> map){
		return presentationCustomizeMapper.getSlideCount(map);
	}
	
	public Integer saveMediaList(List<Map<String,Object>> selectedMediaList,String presentationId,String userId,String accountId){
		
		List<Media> syncMediaList = presentationCustomizeMapper.getMediaList(selectedMediaList);
		for(Media media : syncMediaList){
			String attachmentId = media.getId();
			MediaExample mediaExample = new MediaExample(); 
			mediaExample.createCriteria().andIdEqualTo(attachmentId);
			List<Media> mediaList = mediaMapper.selectByExample(mediaExample);
			if(null == mediaList || mediaList.isEmpty()){
				media.setAccountId(accountId);
				media.setCreateId(userId);
				media.setCreateTime(new Date());
				mediaMapper.insertSelective(media);
			}
		}
		
		List<PresentationSyncMedia> presentationSyncMediaList = presentationCustomizeMapper.getSyncMediaListFromAttachment(selectedMediaList);
		for(PresentationSyncMedia presentationSyncMedia : presentationSyncMediaList){
			presentationSyncMedia.setId(AppUtils.uuid());
			presentationSyncMedia.setPresentationId(presentationId);
			presentationSyncMedia.setCreateId(userId);
			presentationSyncMedia.setCreateTime(new Date());
		}
		return presentationCustomizeMapper.saveSyncMediaList(presentationSyncMediaList);
	}
	
	public Integer delMedia(String syncMediaId,String mediaId){
		PresentationSyncMedia presentationSyncMedia = presentationSyncMediaMapper.selectByPrimaryKey(syncMediaId);
		int num = presentationSyncMediaMapper.deleteByPrimaryKey(syncMediaId);
		if(num>0){
			//判断是否已经转换完成
			if(presentationSyncMedia.getMediaConvertStatus()==0){
				mediaMapper.deleteByPrimaryKey(mediaId);
			}
		}
		return num;
	}
	
	
	public Integer getSyncSlideMaxOrder(String presentationId) {
		return presentationCustomizeMapper.getSyncSlideMaxOrder(presentationId);
	}
	
	
	
	
	public Integer insertSyncSlidesList(List<PresentationSyncSlides> syncSlidesList,Integer isSyncPoint,Integer isSlideShow,String interval,String presentationId,boolean isImgFlag){
		
	
		
		//取第一张PPT，放在0秒 ｛PPT｝+｛video｝
		if(isSyncPoint==1 && isSlideShow==1) {
			insertFirstPPTToZeroTime(syncSlidesList);
		} else if(isSlideShow==0 && StringUtils.isNotBlank(interval)) {
			
			PresentationModelSync mdelSync = new PresentationModelSync();
			// 当前上传PPT时间
			Integer sildesLength = Integer.parseInt(interval) * syncSlidesList.size();
			
			// 自动
			// 获取PPT时间 累加
			PresentationModelSyncExample example = new PresentationModelSyncExample();
			example.createCriteria().andPresentationIdEqualTo(presentationId);
			
			List<PresentationModelSync> timeList = presentationModelSyncMapper.selectByExample(example);
			
			if(!timeList.isEmpty()) {
				
				sildesLength+=timeList.get(0).getSlideLength()==null?0:timeList.get(0).getSlideLength();
			}
			
			// 获取当前章节是否上传过PPT，如果上传过PPT 自动模式下，第二个多个PPT文件时，时间 等于 间隔时间+最大时间*当前PPT下标
			
			Integer pointMaxTime =  presentationCustomizeMapper.getSyncSlideMaxPointTime(presentationId);
		
			mdelSync.setSlideLength(sildesLength);
	
			presentationModelSyncMapper.updateByExampleSelective(mdelSync, example);
			
			
			PresentationSyncPoints syncPoints = null;
			for(int i=0,j=syncSlidesList.size();i<j;i++) {
				
				syncPoints = new PresentationSyncPoints();
				syncPoints.setId(AppUtils.uuid());
				syncPoints.setPresentationId(syncSlidesList.get(i).getPresentationId());
				syncPoints.setSyncSlideId(syncSlidesList.get(i).getSlideId());
				syncPoints.setPointExt(syncSlidesList.get(i).getSlideExt());
				syncPoints.setPointUrl(syncSlidesList.get(i).getSlideUrl());
				syncPoints.setPointDir(syncSlidesList.get(i).getSlideDir());
				syncPoints.setPointTime(pointMaxTime!=null?(pointMaxTime+Integer.parseInt(interval)*(i+1)):Integer.parseInt(interval)*i);
				syncPoints.setPointOrder(i);
				syncPoints.setPointEnd(0);
				syncPoints.setEnableFlag(0);
				syncPoints.setCreateId(syncSlidesList.get(i).getCreateId());
				syncPoints.setCreateTime(syncSlidesList.get(i).getCreateTime());
				presentationSyncPointsMapper.insertSelective(syncPoints);
			}
		
			
		} else {
			if(isSyncPoint==1) {
				insertFirstPPTToZeroTime(syncSlidesList);
			}
			
		}
		
		
		return !isImgFlag?presentationCustomizeMapper.insertSyncSlidesList(syncSlidesList):1;
	}

	private void insertFirstPPTToZeroTime(List<PresentationSyncSlides> syncSlidesList) {
		PresentationSyncSlides syncSlides = syncSlidesList.get(0);
		
		PresentationSyncPoints syncPoints = new PresentationSyncPoints();
		syncPoints.setId(AppUtils.uuid());
		syncPoints.setPresentationId(syncSlides.getPresentationId());
		syncPoints.setSyncSlideId(syncSlides.getSlideId());
		syncPoints.setPointExt(syncSlides.getSlideExt());
		syncPoints.setPointUrl(syncSlides.getSlideUrl());
		syncPoints.setPointDir(syncSlides.getSlideDir());
		syncPoints.setPointTime(0);
		syncPoints.setPointOrder(0);
		syncPoints.setPointEnd(0);
		syncPoints.setEnableFlag(1);
		syncPoints.setCreateId(syncSlides.getCreateId());
		syncPoints.setCreateTime(syncSlides.getCreateTime());
		presentationSyncPointsMapper.insertSelective(syncPoints);
	}
	
	public List<Slides> selectSyncSlidesList(String presentationId){
		return presentationCustomizeMapper.selectSyncSlidesList(presentationId);
	}
	
	public void delSyncSlide( String slideId,String presentationId,Integer sysFlag){
		
		
		
		
		if(sysFlag==0) {
			attachmentMapper.deleteByPrimaryKey(slideId);
		}
		slidesMapper.deleteByPrimaryKey(slideId);
		PresentationSyncSlidesExample example = new PresentationSyncSlidesExample();
		example.createCriteria().andSlideIdEqualTo(slideId);
		presentationSyncSlidesMapper.deleteByExample(example);
		
		PresentationSyncPointsExample example2 = new PresentationSyncPointsExample();
		example2.createCriteria().andPresentationIdEqualTo(presentationId).andSyncSlideIdEqualTo(slideId);
		
		presentationSyncPointsMapper.deleteByExample(example2);
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("slideId", slideId);
		map.put("presentationId", presentationId);
		
		presentationCustomizeMapper.delSyncSlide(map);
		 
	// 刪除文件 	
		 
		 
		 
		 
	}
	
	public Integer saveSyncList(List<Map<String,Object>> syncSlideList,String userId,String presentationId,String accountId){
		
		
		Integer maxOrder = presentationCustomizeMapper.getSyncSlideMaxOrder(presentationId);
	
		Integer nextOrder = maxOrder==null?0:maxOrder+1;
		List<AttachmentSlidesItem> attachmentPackageItems = null;
		Attachment attachment = null;
		//幻灯片存储List
		Integer rowCount = DConstants.NUMBER_ZERO;
		List<Map<String,Object>> pptList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> syncSlide:syncSlideList){
			String attachId = syncSlide.get("id").toString();
			//获取数据库attachment 信息
			 attachment = attachmentMapper.selectByPrimaryKey(attachId);
			if(null == attachment){
				continue;
			}
			//插入t_slides
			SlidesExample slidesExample = new SlidesExample();
			slidesExample.createCriteria().andIdEqualTo(attachId).andPresentationIdEqualTo(presentationId);
			List<Slides> slides =  slidesMapper.selectByExample(slidesExample);
			if(slides == null || slides.isEmpty()){
				Slides slide = new Slides();
				slide.setAccountId(accountId);
				slide.setCreateId(userId);
				slide.setCreateTime(new Date());
				slide.setId(attachId);
				slide.setSlideDir(attachment.getAttachDir());
				slide.setSlideExt(attachment.getAttachExt());
				slide.setSlideName(attachment.getGenerateName());
				slide.setSlideOriginalName(attachment.getAttachName());
				slide.setSlideSize(attachment.getAttachSize().longValue());
				slide.setSlideUrl(attachment.getAttachUrl());
				slide.setPresentationId(presentationId);
				slidesMapper.insertSelective(slide);
			}
		
			//判断是否是ppt
			String slideExt = attachment.getAttachExt();
			//if("ppt".equals(slideExt)||"pptx".equals(slideExt)){
			if(StringUtils.contain(DConstants.PPT_FORMAT, slideExt)){
				//获取并保存附件处理后数据
				AttachmentSlidesItemExample  example = new AttachmentSlidesItemExample();
				example.createCriteria().andSlideIdEqualTo(attachId);
				attachmentPackageItems = attachmentSlidesItemMapper.selectByExample(example);
				
				for(AttachmentSlidesItem item :attachmentPackageItems){
					Map<String,Object> syncSlidesMap = new HashMap<String,Object>();
					syncSlidesMap.put("id", AppUtils.uuid());
					syncSlidesMap.put("slide_id", item.getSlideId());
					syncSlidesMap.put("presentation_id",presentationId);
					syncSlidesMap.put("slide_name", item.getSlideName());
					syncSlidesMap.put("slide_size", item.getSlideSize());
					syncSlidesMap.put("slide_ext", DConstants.SLIDE_EXT_PNG);
					syncSlidesMap.put("slide_url", item.getSlideUrl());
					syncSlidesMap.put("slide_dir", item.getSlideDir());
					syncSlidesMap.put("slide_order", nextOrder==0?0+item.getSlideOrder():nextOrder+item.getSlideOrder());
					syncSlidesMap.put("create_id", userId);
					syncSlidesMap.put("create_time", new Date());
					syncSlidesMap.put("slide_original_name", item.getSlideOriginalName());
					pptList.add(syncSlidesMap);
				}
				nextOrder=attachmentPackageItems.size()+1;	
			}else{
				
				Map<String,Object> syncSlidesMap = new HashMap<String,Object>();
				syncSlidesMap.put("id", AppUtils.uuid());
				syncSlidesMap.put("slide_id", attachment.getId());
				syncSlidesMap.put("presentation_id", presentationId);
				syncSlidesMap.put("slide_name", attachment.getGenerateName());
				syncSlidesMap.put("slide_size", attachment.getAttachSize());
				syncSlidesMap.put("slide_ext", attachment.getAttachExt());
				syncSlidesMap.put("slide_url", attachment.getAttachUrl());
				syncSlidesMap.put("slide_dir", attachment.getAttachDir());
				syncSlidesMap.put("slide_order", nextOrder);
				syncSlidesMap.put("create_id", userId);
				syncSlidesMap.put("create_time", new Date());
				syncSlidesMap.put("slide_original_name", attachment.getAttachName());
				pptList.add(syncSlidesMap);
				nextOrder++;
			}
			
		}
		
		if(pptList != null && !pptList.isEmpty()){
			rowCount = presentationCustomizeMapper.saveSlideList(pptList);
			// 如果PPT 自动 将系统库 内容添加到 slides point 表中
			// 获取章节总时间 和 播放模式
			
			PresentationModelSyncExample example = new PresentationModelSyncExample();
			example.createCriteria().andPresentationIdEqualTo(presentationId);
			List<PresentationModelSync> syncList =  presentationModelSyncMapper.selectByExample(example);
			if(!syncList.isEmpty()) {
				PresentationModelSync modelSync = syncList.get(0);
				if(modelSync.getIsSlideShow()!=1) {
					Short slidesPlay = modelSync.getIsManually();
				
					Integer slidesLength = modelSync.getSlideLength();
					// 当为自动 修改point time 时间节点
					if(slidesPlay == Constants.SHORT_STATUS_ZERO) {
						Integer interval = modelSync.getInterval()==null?0:modelSync.getInterval().intValue();
						// 获取当前 point time 最大时间数据
						Integer maxPointTime = presentationCustomizeMapper.getSyncSlideMaxPointTime(presentationId);
						PresentationSyncPoints syncPoints = null;
						// PPT 文件
						Integer lastSlidesTime = 0;
						if(attachmentPackageItems!=null && !attachmentPackageItems.isEmpty()) {
							lastSlidesTime = slidesLength==null?0:slidesLength + interval * attachmentPackageItems.size();
							for(int i=0;i<attachmentPackageItems.size();i++) {
								syncPoints = new PresentationSyncPoints();
								syncPoints.setId(AppUtils.uuid());
								syncPoints.setPresentationId(presentationId);
								syncPoints.setSyncSlideId(attachmentPackageItems.get(i).getSlideId());
								syncPoints.setPointExt(attachmentPackageItems.get(i).getSlideExt());
								syncPoints.setPointUrl(attachmentPackageItems.get(i).getSlideUrl());
								syncPoints.setPointDir(attachmentPackageItems.get(i).getSlideDir());
								syncPoints.setPointTime((maxPointTime==null?0:maxPointTime)!=0?(maxPointTime+interval*(i+1)):interval*i);
								syncPoints.setPointOrder(i);
								syncPoints.setPointEnd(0);
								syncPoints.setEnableFlag(0);
								syncPoints.setCreateId(attachmentPackageItems.get(i).getCreateId());
								syncPoints.setCreateTime(attachmentPackageItems.get(i).getCreateTime());
								presentationSyncPointsMapper.insertSelective(syncPoints);
							}
						} else {
							// 单个图片文件形式
							lastSlidesTime = slidesLength + interval;
							syncPoints = new PresentationSyncPoints();
							syncPoints.setId(AppUtils.uuid());
							syncPoints.setPresentationId(presentationId);
							syncPoints.setSyncSlideId(attachment.getId());
							syncPoints.setPointExt(attachment.getAttachExt());
							syncPoints.setPointUrl(attachment.getAttachUrl());
							syncPoints.setPointDir(attachment.getAttachDir());
							syncPoints.setPointTime(maxPointTime!=0?(maxPointTime+interval):interval);
							syncPoints.setPointOrder(0);
							syncPoints.setPointEnd(0);
							syncPoints.setEnableFlag(0);
							syncPoints.setCreateId(attachment.getCreateId());
							syncPoints.setCreateTime(attachment.getCreateTime());
							presentationSyncPointsMapper.insertSelective(syncPoints);
						}
					
						// 更新章节时间总长度 
						modelSync.setSlideLength(lastSlidesTime);
						presentationModelSyncMapper.updateByExampleSelective(modelSync, example);
					}
					
				}
				
				
			}
			
			
		}
		return rowCount;
	}
	
	public void delSlide(String slideId,String logicFilePath){
		//删除slides表数据
		int result = slidesMapper.deleteByPrimaryKey(slideId);
		if(result>0){
			uploadService.deleteAttachByAttachDir(logicFilePath);
		}
	}
	
	public int updateMediaConvertStatus(String syncMediaId){
		PresentationSyncMedia presentationSyncMedia = new PresentationSyncMedia();
		presentationSyncMedia.setId(syncMediaId);
		presentationSyncMedia.setMediaConvertStatus(1);
		return presentationSyncMediaMapper.updateByPrimaryKeySelective(presentationSyncMedia);
	}
	
	public int getEditSlidesCount(Map<String,Object> map){
		return presentationCustomizeMapper.getEditSlidesCount(map);
	}
	
	public List<Map<String,Object>> getEditSlidesList(Map<String,Object> map){
		return presentationCustomizeMapper.getEditSlidesList(map);
	}
	
	public List<Map<String,Object>> getEditMediasList(Map<String,Object> map){
		return presentationCustomizeMapper.getEditMediasList(map);
	}
	
	public Slides getConvertSlidesInfo(String slideId){
		return slidesMapper.selectByPrimaryKey(slideId);
	}
	
	public Map<String,Object> getConvertMediasInfo(Map<String,Object> params){
		return presentationCustomizeMapper.getConvertMediasInfo(params);
	}
	
	public List<Map<String,Object>> queryClassificationList(Map<String,Object> params){
		return presentationCustomizeMapper.queryClassificationList(params);
	}
	
	public List<Map<String,Object>> isExistName(Map<String,Object> params){
		return presentationCustomizeMapper.isExistName(params);
	}
	
	public List<Map<String,Object>>  getPresentationList(Map<String,Object> params){
		return presentationCustomizeMapper.findPresentationList(params);
	}

	public void initPresentationProfile(Model model, String presentationId) {
		PresentationBo presentation = presentationCustomizeMapper.getPresentationInforById(presentationId);
		model.addAttribute("presentationId", presentationId);
		model.addAttribute("presentation",presentation);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("presentationId", presentationId);
		Map<String,Object> presentationMap = this.queryPresentation(map);
		model.addAttribute("presentationMap",presentationMap);
//		
//		//分类标签查询
//		List<Map<String,Object>> presentationTagsList = this.queryPresentationTagsList(map);
//		model.addAttribute("presentationTagsList",presentationTagsList);
	}
	
	public Integer delPresentationFiles(Map<String,Object> map){
		 /*int result = presentationCustomizeMapper.delPresentationFiles(map);
		 if(result>0){
			 presentationCustomizeMapper.delPresentationPackageItem(map);
		 }*/	
		
		
		presentationFilesMapper.deleteByPrimaryKey(map.get("presentationFilesId").toString());
		
		return presentationCustomizeMapper.delPresentationPackageItem(map);
	}
	
	
	public List<Map<String,Object>>  findPresentationListByKeyword(Map<String,Object> params)throws DeodioException{
		return presentationCustomizeMapper.findPresentationListByKeyword(params);
	}
	
	
	public void presentationPublish(Map<String,Object> params){
		
		String presentationId = (String) params.get("presentationId");
		String userId = (String) params.get("userId");
		Presentation presentation = presentationMapper.selectByPrimaryKey(presentationId);
		presentation.setIsPublish(DConstants.TYPE_IS_PUBLISH.shortValue());
		presentation.setUpdateId(userId);
		presentation.setUpdateTime(new Date());
		presentationMapper.updateByPrimaryKeySelective(presentation);
		
		Short isCourse = presentation.getIsCourse();
		if(DConstants.PRESENTATION_PUBLISH_IS_COURSE_YES.equals(isCourse)){
			params.put("createTime", new Date());
			generateCourseFromPresentation(params);
		}
	}
	
	/**
	 * 章节生成同名 线上课程
	 * @param params
	 */
	public void generateCourseFromPresentation(Map<String,Object> params){
		
		String presentationId = (String) params.get("presentationId");
		//生成课程信息
		String courseId = AppUtils.uuid();
		params.put("courseId", courseId);
		presentationCustomizeMapper.insertCourseInfoFromPresentation(params);
		//获取线上课程基本数据
		params.put("itemId", presentationId);
		params.put("itemType", DConstants.CLASSIFICATION_ITEMS_TYPE_PRESENTATION);
		//获取线上课程对应分类
		List<Map<String,Object>> selectedClassificationList =  classificationService.querySelectedClassificationByItemId(params);
		List<ClassificationItemsRel> classificationList = new ArrayList<ClassificationItemsRel>();
		for(Map<String,Object> classification : selectedClassificationList){
			String classificationId = (String) classification.get("id");
			ClassificationItemsRel itemRel = new ClassificationItemsRel();
			itemRel.setId(AppUtils.uuid());
			itemRel.setClassificationId(classificationId);
			classificationList.add(itemRel);
		}
		params.put("classificationList", classificationList);
		//插入课程分类信息
		presentationCustomizeMapper.insertCourseClassificationFromPresetation(params);
		//获取章节标签信息
		params.put("itemType", DConstants.TAGS_ITEMS_TYPE_PRESENTATION);
		List<Map<String,Object>> selectedTagsList =  tagsService.querySelectedTagsByItemId(params);
		List<TagsItemsRel> tagList = new ArrayList<TagsItemsRel>();
		for(Map<String,Object> tag : selectedTagsList){
			String tagId = (String) tag.get("id");
			TagsItemsRel itemRel = new TagsItemsRel();
			itemRel.setId(AppUtils.uuid());
			itemRel.setTagsId(tagId);
			tagList.add(itemRel);
		}
		params.put("tagList", tagList);
		//插入课程标签信息
		presentationCustomizeMapper.insertCourseTagFromPresetation(params);
		//插入线上课程内容信息
		params.put("courseItemId", AppUtils.uuid());
		presentationCustomizeMapper.insertCourseItemFromPresetation(params);
	}
	/**
	 * 修改发布状态
	 * @param presentationId
	 * @param status
	 */
	public void updatePresentationStatus(String presentationId, Short status,String accountId, String userId, String groupId) {
		
		
		if(status==1) {
			Presentation presentation = presentationMapper.selectByPrimaryKey(presentationId);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("presentationId", presentationId);
			params.put("accountId", accountId);
			params.put("userId", userId);
			params.put("groupId", groupId);
			if(DConstants.PRESENTATION_PUBLISH_IS_COURSE_YES.equals(presentation.getIsCourse())){
				params.put("createTime", new Date());
				generateCourseFromPresentation(params);
			}
			
		}
		
	
		Presentation record = new Presentation();
		record.setId(presentationId);
		record.setIsPublish(status);
		presentationMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 查询章节信息
	 * @param presentationId
	 * @return
	 */
	public  PresentationBo getPresentationInforById(String presentationId) {
		return presentationCustomizeMapper.getPresentationInforById(presentationId);

	}
	/**
	 * 查询章节信息
	 * @param presentationId
	 * @return
	 */
	public  Presentation getPresentationById(String presentationId) {
		return presentationMapper.selectByPrimaryKey(presentationId);

	}
	
	
	/**
	 * 章节分享
	 * @param presentationId
	 * @param teachUserId
	 */
	public void insertPresentationOwner(String presentationId, String teachUserId) {
		Presentation record = new Presentation();
		record.setPresentationOwner(teachUserId);
		record.setId(presentationId);
		presentationMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 查询到当前正解是否已经被分享
	 * @param presentationId
	 * @return
	 */
	public boolean getPresentationOwner(String presentationId) {
		Presentation record = presentationMapper.selectByPrimaryKey(presentationId);
		return StringUtils.isBlank(record.getPresentationOwner());
	}
	
	
	
	/**
	 * 查询章节预览评论
	 * @param presentationId
	 * @return
	 */
	public List<PresentationDiscussionDto> getPresentationDiscussion(String presentationId) {
		return presentationDiscussionCustomizeMapper.getPresentationDiscussion(presentationId);
	}
	/**
	 * 保存章节评论
	 * @param discussContent
	 * @param discussPkId
	 */
	public void savePresentationDiscussion(String replyContent, String discussPkId,String userId) {
		PresentationDiscussionReply record = new PresentationDiscussionReply();
		record.setPresentationDisId(discussPkId);
		record.setReplyDate(new Date());
		record.setReplyUserId(userId);
		record.setReplyContent(replyContent);
		record.setId(AppUtils.uuid());
		presentationDiscussionReplyMapper.insertSelective(record);
	}
	/**
	 * 章节评论帖的 回复
	 * @param discussPkId
	 */
	public void deleteReply(String discussPkId) {
		
		presentationDiscussionReplyMapper.deleteByPrimaryKey(discussPkId);
	}
	/**
	 * 删除整个评论包含子评论帖子
	 * @param id
	 */
	public void deleteDiscusstion(String id) {
		PresentationDiscussionReplyExample example = new PresentationDiscussionReplyExample();
		example.createCriteria().andPresentationDisIdEqualTo(id);
		presentationDiscussionReplyMapper.deleteByExample(example);
		presentationDiscussionMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 复制章节
	 * @param presentationId
	 */
	public String saveCopyPresentationInfor(Presentation presentaton,String userId,String accountId) {
		String pkId = AppUtils.uuid();
		presentaton.setId(pkId);
		presentaton.setIsPublish((short) 0);
		presentaton.setPresentationOwner(null);
		presentaton.setCreateTime(new Date());
		presentaton.setCreateId(userId);
		presentaton.setAccountId(accountId);
		presentationMapper.insertSelective(presentaton);
		return pkId;
	}
	/**
	 * 复制 章节主要信息 封面 图标签信息
	 * @param presentationId
	 * @param copyPresentationId
	 */
	public void saveTagsAndClassifacationAndImgForCopy(String presentationId,String copyPresentationId) {
		
	
		TagsItemsRelExample example = new TagsItemsRelExample();
		example.createCriteria().andItemIdEqualTo(presentationId).andItemTypeEqualTo(DConstants.TAGS_ITEMS_TYPE_PRESENTATION);
		
		List<TagsItemsRel> tagslist =  tagsItemsRelMapper.selectByExample(example);
		for(TagsItemsRel item:tagslist){
			item.setId(AppUtils.uuid());
			item.setItemId(copyPresentationId);
			tagsItemsRelMapper.insertSelective(item);
		}
		
		ClassificationItemsRelExample example2 = new ClassificationItemsRelExample();
		example2.createCriteria().andItemIdEqualTo(presentationId).andItemTypeEqualTo(DConstants.CLASSIFICATION_ITEMS_TYPE_PRESENTATION);
		
		List<ClassificationItemsRel> classificationItems = classificationItemsRelMapper.selectByExample(example2);
		
		for(ClassificationItemsRel items:classificationItems){
			items.setItemId(copyPresentationId);
			items.setId(AppUtils.uuid());
			classificationItemsRelMapper.insertSelective(items);
		}
		AttachmentExample example3 =new AttachmentExample();
		example3.createCriteria().andRelIdEqualTo(presentationId);
		
		
		//复制章节封面图片
			
		List<Attachment> attaList = attachmentMapper.selectByExample(example3);
		
		if(!attaList.isEmpty()) {
			Attachment att  = attaList.get(0);
		
		
			//获取物理路径 /222/22/22/5/xxx.jpg
			String filePath =  CommonUtils.IMGS_LOCAL_DIR + att.getAttachDir() + att.getGenerateName();
			
			//生成复制图片新路径
			String absDir = uploadService.generateDir(String.valueOf(Constants.NUMBER_FIVE));
			
		    String absUrlStr  = StringUtils.replace(absDir.toString(),DConstants.STRING_BACKSLASH,DConstants.STRING_SLASH);
			
		    String toFileFolder = CommonUtils.IMGS_LOCAL_DIR + File.separator + absDir;
			
		    String[]  generateName = uploadService.generateFileName(att.getGenerateName());
		    
		    
			String output =  toFileFolder + generateName[0];
			FileUtils.copy(filePath, output);
			att.setId(AppUtils.uuid());
			att.setRelId(copyPresentationId);
			att.setGenerateName(generateName[0]);
			att.setAttachUrl(absUrlStr);
			att.setAttachDir(absDir);
			attachmentMapper.insertSelective(att);
		}
		
		
	
		
		
		
	}
	
	

	
	
	
	public void saveCopyPresentation(Presentation presentaton,String userId,String accountId,String presentatonId) throws IOException {
		//获取封面图片
		String newPresentationId  = this.saveCopyPresentationInfor(presentaton, userId, accountId);
		this.saveTagsAndClassifacationAndImgForCopy(presentatonId, newPresentationId);
		
		switch (presentaton.getPresentationModel().intValue()) {
		case 0:
			this.saveCopyPresentationForScrom(presentatonId, userId, accountId,newPresentationId);
			break;
		case 1:
			this.saveCopyPresentationForPackage(presentatonId, userId, accountId,newPresentationId);
			break;
		case 2:
			this.saveCopyPresentationForSync(presentatonId, userId, accountId,newPresentationId);
			break;
		case 3:
			this.saveCopyPresentationForExternal(presentatonId, userId, accountId,newPresentationId);
			break;
		default:
			break;
		}
		
		
	}
	
	
	
	private void saveCopyPresentationForScrom( String presentationId, String userId, String accountId,String newPresentationId) throws IOException {
		
		
		
		
		AttachmentExample example = new AttachmentExample();
		example.createCriteria().andRelIdEqualTo(presentationId).andAttachTypeEqualTo(DConstants.ATTACHMENT_TYPE_SCROM.toString());
		List<Attachment> attList = attachmentMapper.selectByExample(example);
		if(!attList.isEmpty()) {
			Attachment attac = attList.get(0);
			//原文件路径
			String filePath = attac.getAttachDir();
			//创建新copy 章节 路径
			String dateStringFolder = DateTimeUtils.date2String(new Date(), "yyyyMMddHHmmss"); 
		
					
			String serverDir = CommonUtils.IMGS_LOCAL_DIR;
			StringBuilder absDir = new StringBuilder();
			StringBuilder scromDir = new StringBuilder();
			Calendar now = Calendar.getInstance();
			absDir.append(DConstants.STRING_SLASH).append(now.get(Calendar.YEAR)).append(DConstants.STRING_SLASH)
					.append((now.get(Calendar.MONTH) + 1)).append(DConstants.STRING_SLASH)
					.append(now.get(Calendar.DAY_OF_MONTH)).append(DConstants.STRING_SLASH);
			
			
			
			scromDir.append(DConstants.STRING_SLASH).append(DConstants.FOLDER_SCROM).append(DConstants.STRING_SLASH).append(accountId)
				.append(DConstants.STRING_SLASH).append(presentationId).append(DConstants.STRING_SLASH)
					.append(absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH)).append(dateStringFolder);
			//复制新路径
			String newCopyFilePath =  serverDir+scromDir+DConstants.STRING_SLASH;		
					
			org.apache.commons.io.FileUtils.copyDirectory(new File(serverDir+filePath), new File(newCopyFilePath));		
			
			String attachmentId = AppUtils.uuid();
			
			uploadService.insertAttament(DConstants.ATTACHMENT_TYPE_SCROM.toString(), attac.getAttachName(), userId,attac.getAttachExt(), attac.getGenerateName(), 
					scromDir.toString(), scromDir.toString(),newPresentationId,(int) attac.getAttachSize(),attachmentId,DConstants.ATTACHMENT_IS_CONVERT_NO);

			PresentationFiles presentationFiles = new PresentationFiles();
			presentationFiles.setId(attachmentId);
			presentationFiles.setFileName(attac.getGenerateName());
			presentationFiles.setFileSize(Long.parseLong(String.valueOf(attac.getAttachSize())));
			presentationFiles.setFileExt(attac.getAttachExt());
			presentationFiles.setFileUrl(scromDir.toString());
			presentationFiles.setFileDir(scromDir.toString());
			presentationFiles.setCreateId(userId);
			presentationFiles.setCreateTime(new Date());
			presentationFiles.setAccountId(accountId);
			presentationFiles.setPresentationId(newPresentationId);
			presentationFiles.setFileOriginalName(attac.getAttachName());
			presentationFiles.setFileType(DConstants.PRESENTATION_FILES_TYPE_SCORM);
			presentationFilesMapper.insertSelective(presentationFiles);
			
			//PresentationScromItem 中数据copy
			
			PresentationScromItemExample example2 = new PresentationScromItemExample();
			example2.createCriteria().andPresentationIdEqualTo(presentationId);
			
			List<PresentationScromItem>  scromItemList = presentationScromItemMapper.selectByExample(example2);
			
			for(PresentationScromItem items:scromItemList){
				String launchPath = items.getLaunch();
				if(StringUtils.isBlank(items.getIdentifierref()) || items.getIdentifierref().contains("null")) {
					items.setIdentifierref(null);
				}
				launchPath = StringUtils.substringAfter(launchPath, "scorm_items");
				String lastPath = scromDir + DConstants.STRING_SLASH+"scorm_items"+DConstants.STRING_SLASH+launchPath;
				items.setLaunch(lastPath);
				items.setId(AppUtils.uuid());
				items.setPresentationId(newPresentationId);
				presentationScromItemMapper.insertSelective(items);
			};
			
			
			PresentationModelScromExample example3 = new PresentationModelScromExample();
			example3.createCriteria().andPresentationIdEqualTo(presentationId);
			
			List<PresentationModelScrom>  scromModelList = presentationModelScromMapper.selectByExample(example3);
			
			for(PresentationModelScrom scromModel:scromModelList){
				scromModel.setId(AppUtils.uuid());
				scromModel.setPresentationId(newPresentationId);
				presentationModelScromMapper.insertSelective(scromModel);
			};
			
			
			
			
		}
		
		
	}
	private void saveCopyPresentationForPackage(String presentationId, String userId, String accountId, String newPresentationId) throws IOException {
		
		
		
		
		AttachmentExample example = new AttachmentExample();
		example.createCriteria().andRelIdEqualTo(presentationId).andAttachTypeEqualTo(DConstants.ATTACHMENT_TYPE_PACKAGE.toString());
		List<Attachment> attList = attachmentMapper.selectByExample(example);
		if(!attList.isEmpty()) {
			Attachment attac = attList.get(0);
			//原文件路径
			String filePath = attac.getAttachDir();
			
			StringBuilder absDir = new StringBuilder();
			StringBuilder packageDir = new StringBuilder();
			Calendar now = Calendar.getInstance();
			absDir.append(DConstants.STRING_SLASH).append(now.get(Calendar.YEAR)).append(DConstants.STRING_SLASH)
					.append((now.get(Calendar.MONTH) + 1)).append(DConstants.STRING_SLASH)
					.append(now.get(Calendar.DAY_OF_MONTH)).append(DConstants.STRING_SLASH);
			
			packageDir.append(DConstants.STRING_SLASH).append(DConstants.FOLDER_PACKAGE).append(DConstants.STRING_SLASH).append(accountId)
					.append(DConstants.STRING_SLASH).append(newPresentationId).append(DConstants.STRING_SLASH).append(userId)
					.append(absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH));
		
			String newCopyFilePath = CommonUtils.FILE_LOCAL_DIR + packageDir.toString();
			
			
					
			org.apache.commons.io.FileUtils.copyDirectory(new File(filePath), new File(newCopyFilePath));		
			
			
			String attachmentId = AppUtils.uuid();
			
			uploadService.insertAttament(DConstants.ATTACHMENT_TYPE_PACKAGE.toString(), attac.getAttachName(), userId,attac.getAttachExt(), 
					 attac.getGenerateName(), packageDir.toString(), packageDir.toString(),presentationId,attac.getAttachSize().intValue(),
					 attachmentId,DConstants.ATTACHMENT_IS_CONVERT_NO);
			
			
			AttachmentPackageItemExample example4 = new AttachmentPackageItemExample();
			example4.createCriteria().andPackageIdEqualTo(attac.getId());
			
			List<AttachmentPackageItem> apackItemsList = attachmentPackageItemMapper.selectByExample(example4);
			for(AttachmentPackageItem items:apackItemsList){
				items.setId(AppUtils.uuid());
				items.setPackageId(attachmentId);
				attachmentPackageItemMapper.insertSelective(items);
			};
			
			
			//上传文件存储
			PresentationFiles presentationFiles = new PresentationFiles();
			presentationFiles.setId(attachmentId);
			presentationFiles.setFileName(attac.getAttachName());
			presentationFiles.setFileSize(Long.parseLong(String.valueOf(attac.getAttachSize())));
			presentationFiles.setFileExt(attac.getAttachExt());
			presentationFiles.setFileUrl(packageDir.toString());
			presentationFiles.setFileDir(packageDir.toString());
			presentationFiles.setCreateId(userId);
			presentationFiles.setCreateTime(new Date());
			presentationFiles.setAccountId(accountId);
			presentationFiles.setFileOriginalName( attac.getGenerateName());
			presentationFiles.setFileType(DConstants.PRESENTATION_FILES_TYPE_PACKAGE);
			presentationFiles.setPresentationId(presentationId);
			presentationFilesMapper.insertSelective(presentationFiles);
			
			PresentationPackageItemExample example2 = new PresentationPackageItemExample();
			example2.createCriteria().andPresentationIdEqualTo(presentationId);
			
			String[] splitStr = StringUtils.split(filePath, DConstants.STRING_SLASH);
			
			StringBuilder splitfloder = new StringBuilder();
			splitfloder.append(splitStr[4]).append(DConstants.STRING_SLASH).append(splitStr[5]).append(DConstants.STRING_SLASH).append(splitStr[6]).append(DConstants.STRING_SLASH);
			
			List<PresentationPackageItem> pageLists = presentationPackageItemMapper.selectByExample(example2);
			
			for(PresentationPackageItem  items : pageLists){
				
				String newPagePath = StringUtils.substringAfter(items.getPackageDir(), splitfloder.toString());
				
				newPagePath = newCopyFilePath+newPagePath;
				items.setId(AppUtils.uuid());
				items.setPackageId(attachmentId);
				items.setPresentationId(newPresentationId);
				items.setPackageDir(newCopyFilePath+newPagePath);
				items.setPackageUrl(newPagePath);
				presentationPackageItemMapper.insertSelective(items);
			};
			
			
			PresentationModelPackageExample example3 = new PresentationModelPackageExample();
			example3.createCriteria().andPresentationIdEqualTo(presentationId);
			
			List<PresentationModelPackage> modelPackageList = presentationModelPackageMapper.selectByExample(example3);
			
			for(PresentationModelPackage pacageModel:modelPackageList){
			
				pacageModel.setId(AppUtils.uuid());
				pacageModel.setPresentationId(newPresentationId);
				presentationModelPackageMapper.insertSelective(pacageModel);
			};
			
			
		}
		
	}

	private void saveCopyPresentationForSync(String presentationId, String userId, String accountId, String newPresentationId) {
		// TODO Auto-generated method stub
		
	}

	private void saveCopyPresentationForExternal(String presentationId, String userId, String accountId, String newPresentationId) {
		PresentationExternalItemExample example2 = new PresentationExternalItemExample();
		example2.createCriteria().andPresentationIdEqualTo(presentationId);
		
		
		List<PresentationExternalItem> pageLists = presentationExternalItemMapper.selectByExample(example2);
		
		for(PresentationExternalItem  items:pageLists){
			
			items.setId(AppUtils.uuid());
			items.setPresentationId(newPresentationId);
		
			presentationExternalItemMapper.insertSelective(items);
		};
		
		
		
		PresentationModelExternalExample example3 = new PresentationModelExternalExample();
		example3.createCriteria().andPresentationIdEqualTo(presentationId);
		
		List<PresentationModelExternal> modelExternalList = presentationModelExternalMapper.selectByExample(example3);
		for(PresentationModelExternal pacageExternal:modelExternalList){
			pacageExternal.setId(AppUtils.uuid());
			pacageExternal.setPresentationId(newPresentationId);
			presentationModelExternalMapper.insertSelective(pacageExternal);
		};
		
		
		
	}

	public  PageData<Map<String, Object>> findPresentationQuoteList(String presentationId, PageRequest pageRequest) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("presentationId", presentationId);
		param.put("pagination", pageRequest);
		PageData<Map<String, Object>> page = new PageData<Map<String, Object>>();
		page.setList(presentationCustomizeMapper.findPresentationQuoteList(param));
		page.setPageRequest((PageRequest) param.get("pagination"));
		return page;
	}

	public PageData<Map<String, Object>> findQuoteForCourse(String presentationId, PageRequest pageRequest) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("presentationId", presentationId);
		param.put("pagination", pageRequest);
		PageData<Map<String, Object>> page = new PageData<Map<String, Object>>();
		page.setList(presentationCustomizeMapper.findQuoteForCourse(param));
		page.setPageRequest((PageRequest) param.get("pagination"));
		return page;
	}

	public boolean getPresentationIsQuoteForCourse(String presentationId) {
		CourseOnlineItemExample example = new CourseOnlineItemExample();
		example.createCriteria().andItemIdEqualTo(presentationId);
		List<CourseOnlineItem> list = courseOnlineItemMapper.selectByExample(example);
		return list.isEmpty();
	}

	public void delMedia(String mediaId) {
		
		
		AttachmentModelMediaExample example0 = new AttachmentModelMediaExample();
		example0.createCriteria().andMediaIdEqualTo(mediaId);
		
		attachmentModelMediaMapper.deleteByExample(example0);
		
		
		PresentationSyncMedia media = this.getPresentationSyncMediaByMediaId(mediaId);
		
		PresentationSyncMediaExample example = new PresentationSyncMediaExample();
		example.createCriteria().andMediaIdEqualTo(mediaId);
		presentationSyncMediaMapper.deleteByExample(example);
		
		org.apache.commons.io.FileUtils.deleteQuietly(new File(CommonUtils.FILE_LOCAL_DIR+media.getMediaDir()));
		
		org.apache.commons.io.FileUtils.deleteQuietly(new File(CommonUtils.FILE_LOCAL_DIR+media.getMediaCover()));
		attachmentMapper.deleteByPrimaryKey(mediaId);
		
	}
	
	public  PresentationSyncMedia getPresentationSyncMediaByMediaId(String mediaId) {
		PresentationSyncMediaExample example = new PresentationSyncMediaExample();
		example.createCriteria().andMediaIdEqualTo(mediaId);
		return presentationSyncMediaMapper.selectByExample(example).get(0);
	}
	
	
}
