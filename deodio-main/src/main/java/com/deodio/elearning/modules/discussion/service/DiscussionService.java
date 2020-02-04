package com.deodio.elearning.modules.discussion.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.constants.Constants;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.DiscussionModelMapper;
import com.deodio.elearning.persistence.mapper.customize.DiscussionModelCustomizeMapper;
import com.deodio.elearning.persistence.model.DiscussionModel;
import com.deodio.elearning.persistence.model.customize.DiscussionModelBo;

/**
 * @author p0085398
 *
 */
@Service
public class DiscussionService extends BaseService{
	
	@Autowired
	private DiscussionModelCustomizeMapper discussionModelCustomizeMapper;
	
	@Autowired
	private DiscussionModelMapper discussionModelMapper;
	

	/**
	 * 查询课程评论数据
	 * @param params
	 * @return
	 */
	public List<DiscussionModelBo>  getCourseDiscussionList(Map<String,Object> params)throws DeodioException{
		String userId = (String) params.get("userId");
		String accountId = (String) params.get("accountId");
		if(StringUtils.isBlank(userId)){
			throw new DeodioException("传入用户ID参数为空！");
		}
		if(StringUtils.isBlank(accountId)){
			throw new DeodioException("传入账号ID参数为空！");
		}
		return discussionModelCustomizeMapper.queryCourseDiscussionList(params);
	}
	
	/**
	 * 查询课程评论数据
	 * @param params
	 * @return
	 */
	public List<DiscussionModelBo>  getCourseDiscussionReplys(Map<String,Object> params)throws DeodioException{
		String userId = (String) params.get("userId");
		String accountId = (String) params.get("accountId");
		if(StringUtils.isBlank(userId)){
			throw new DeodioException("传入用户ID参数为空！");
		}
		if(StringUtils.isBlank(accountId)){
			throw new DeodioException("传入账号ID参数为空！");
		}
		return discussionModelCustomizeMapper.queryCourseDiscussionReplys(params);
	}
	
	/**
	 * 发布课程评论
	 * @param params  courseId  课程主键编号
	 * @return
	 */
	private void saveCourseDiscussion(DiscussionModel discussionModel,String userId,String accountId)throws DeodioException{
		if(null == discussionModel ){
			throw new DeodioException("课程品论：增加数据为空！");
		}else{
			discussionModel.setId(AppUtils.uuid());
			Date now = new Date();
			discussionModel.setCreateId(userId);
			discussionModel.setCreateTime(now);
			discussionModel.setUpdateId(userId);
			discussionModel.setUpdateTime(now);
			discussionModel.setAccountId(accountId);
		}
		
		Integer rowCount = discussionModelMapper.insertSelective(discussionModel); 
		if(rowCount.equals(Constants.NUMBER_ZERO)){
			throw new DeodioException("课程评论增加失败！");
		}
	}
	
	/**
	 * 赞同课程评论
	 * @param params  courseId  课程主键编号
	 * @return
	 */
	public void agreeCourseDiscussion(String discussionId,String userId,String accountId)throws DeodioException{
		//更新回复评论的回复次数
		Map<String,Object> discussionMap = new HashMap<String,Object>();
		discussionMap.put("discussionId", discussionId);
		discussionMap.put("userId", userId);
		discussionMap.put("accountId", accountId);
		
		Integer rowCount = discussionModelCustomizeMapper.updateCourseDiscussionAgreeNum(discussionMap);
		if(rowCount.equals(Constants.NUMBER_ZERO)){
			throw new DeodioException("课程评论增加失败！");
		}
	}
	
	/**
	 * 回复课程评论
	 * @param params  courseId  课程主键编号
	 * @return
	 */
	public void replayCourseDiscussion(DiscussionModel discussionModel,String userId,String accountId)throws DeodioException{
		//保存课程评论
		saveCourseDiscussion(discussionModel,userId,accountId);
		//关联评论编号不为空，更新被回复评论的评论次数
		String relatedId = discussionModel.getRelatedId();
		if(StringUtils.isNotBlank(relatedId)){
			//更新回复评论的回复次数
			Map<String,Object> discussionMap = new HashMap<String,Object>();
			discussionMap.put("discussionId", discussionModel.getRelatedId());
			discussionMap.put("userId", userId);
			discussionMap.put("accountId", accountId);
			
			Integer rowCount = discussionModelCustomizeMapper.updateCourseDiscussionReplyNum(discussionMap); 
			if(rowCount.equals(Constants.NUMBER_ZERO)){
				throw new DeodioException("课程评论增加失败！");
			}
		}
		
	}
//	
//	/**
//	 * 更新课程作业
//	 * @param courseHomework
//	 * @param attachId
//	 * @param userId
//	 * @param accountId
//	 */
//	public void updateCourseHomework(CourseHomework courseHomework,String attachId,String userId,String accountId)throws DeodioException{
//		Date now = new Date();
//		String id = courseHomework.getId(); 
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseHomework.setId(id);
//		}
//		courseHomework.setAccountId(accountId);
//		courseHomework.setHomeworkPublishTime(now);
//				
//		CourseHomework temp = courseHomeworkMapper.selectByPrimaryKey(id);
//		if(temp == null){
//			courseHomework.setCreateId(userId);
//			courseHomework.setCreateTime(now);
//			courseHomeworkMapper.insertSelective(courseHomework);
//		}else{
//			courseHomework.setUpdateId(userId);
//			courseHomework.setUpdateTime(now);
//			courseCustomizeMapper.updateCourseHomeworkByPrimaryKey(courseHomework);
//		}
//		
//		//图片表更新
//		if(StringUtils.isNotBlank(attachId)){
//			Attachment Attachment = new Attachment();
//			Attachment.setId(attachId);
//			Attachment.setRelId(id);
//			attachmentMapper.updateByPrimaryKeySelective(Attachment);
//		}
//	}
//	
//	/**
//	 * 删除课程作业
//	 * @param homeworkId
//	 */
//	public void delCourseHomework(String homeworkId)throws DeodioException{
//		courseHomeworkMapper.deleteByPrimaryKey(homeworkId);
//	}
//	
//	/**
//	 * 查询课程作业列表
//	 * @param params	
//	 * @return
//	 */
//	public List<Map<String,Object>>  findCourseHomeworkList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.findCourseHomeworkList(params);
//	}
//	
//	/**
//	 * 通过主键获取课程作业信息
//	 * @param params	
//	 * @return
//	 */
//	public Map<String,Object> queryCourseHomeworkByPk(String homeworkId)throws DeodioException{
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("homeworkId", homeworkId);
//		 return courseCustomizeMapper.queryCourseHomeworkById(map);
//	}
//	
//	
//	/**
//	 * 更新课程通知
//	 * @param courseNotice
//	 * @param userId
//	 * @param accountId
//	 */
//	public void updateCourseNotice(CourseNotice courseNotice,String userId,String accountId)throws DeodioException{
//		Date now = new Date();
//		String id = courseNotice.getId(); 
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseNotice.setId(id);
//		}
//		courseNotice.setAccountId(accountId);
//		
//		CourseNotice temp = courseNoticeMapper.selectByPrimaryKey(id);
//		if(temp == null){
//			courseNotice.setCreateId(userId);
//			courseNotice.setCreateTime(now);
//			courseNoticeMapper.insertSelective(courseNotice);
//		}else{
//			courseNotice.setUpdateId(userId);
//			courseNotice.setUpdateTime(now);
//			courseNoticeMapper.updateByPrimaryKeySelective(courseNotice);
//		}
//	}
//	
//	/**
//	 * 删除课通知
//	 * @param homeworkId
//	 */
//	public void delCourseNotice(String noticeId)throws DeodioException{
//		courseNoticeMapper.deleteByPrimaryKey(noticeId);
//	}
//	
//	/**
//	 * 查询课程通知列表
//	 * @param params	
//	 * @return
//	 */
//	public List<Map<String,Object>>  findCourseNoticeList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.findCourseNoticeList(params);
//	}
//	
//	/**
//	 * 通过分主键获取课程通知详细信息
//	 * @param params	
//	 * @return
//	 */
//	public Map<String,Object> queryCourseNoticeByPk(String noticeId)throws DeodioException{
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("noticeId", noticeId);
//		 return courseCustomizeMapper.queryCourseNoticeById(map);
//	}
//	
//	public CourseBo getCourseById(String courseId) throws DeodioException{
//		CourseBo courseBo = new CourseBo();
//		Course course =  courseMapper.selectByPrimaryKey(courseId);
//		BeanUtils.copyProperties(course, courseBo);
//		return courseBo;
//	}
//	
//	
//	/**
//	 * 更新课程附件
//	 * @param courseMaterial
//	 * @param userId
//	 * @param accountId
//	 */
//	public void updateCourseMaterial(CourseMaterial courseMaterial,String userId,String accountId,String attachId)throws DeodioException{
//		Date now = new Date();
//		String id = courseMaterial.getId(); 
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseMaterial.setId(id);
//		}
//		if(StringUtils.isBlank(courseMaterial.getMaterialSize())){
//			courseMaterial.setMaterialSize(null);
//		}
//		courseMaterial.setAccountId(accountId);
//		CourseMaterial temp = courseMaterialMapper.selectByPrimaryKey(id);
//		if(temp == null){
//			courseMaterial.setCreateId(userId);
//			courseMaterial.setCreateTime(now);
//			courseMaterialMapper.insertSelective(courseMaterial);
//		}else{
//			courseMaterial.setUpdateId(userId);
//			courseMaterial.setUpdateTime(now);
//			courseMaterialMapper.updateByPrimaryKeySelective(courseMaterial);
//		}
//		
//		//图片表更新
//		if(StringUtils.isNotBlank(attachId)){
//			Attachment Attachment = new Attachment();
//			Attachment.setId(attachId);
//			Attachment.setRelId(id);
//			attachmentMapper.updateByPrimaryKeySelective(Attachment);
//		}
//	}
//	
//	/**
//	 * 删除课附件
//	 * @param materialId
//	 */
//	public void delCourseMaterial(String materialId)throws DeodioException{
//		courseMaterialMapper.deleteByPrimaryKey(materialId);
//	}
//	
//	/**
//	 * 查询课程附件列表
//	 * @param params	
//	 * @return
//	 */
//	public List<Map<String,Object>>  findCourseMaterialList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.findCourseMaterialList(params);
//	}
//	
//	/**
//	 * 通过主键获取课程文件信息
//	 * @param params	
//	 * @return
//	 */
//	public CourseMaterial queryCourseMaterialByPk(String materialId)throws DeodioException{
//		return courseMaterialMapper.selectByPrimaryKey(materialId);
//	}
//	
//	/**
//	 * 通过主键获取课程考试
//	 * @param quizeId
//	 * @return
//	 * @throws DeodioException
//	 */
//	public CourseQuiz queryCourseQuizByPk(String quizId)throws DeodioException{
//		return courseQuizMapper.selectByPrimaryKey(quizId);
//	}
//	
//	/**
//	 * 删除课程考试
//	 * @param quizId
//	 * @throws DeodioException
//	 */
//	public void delCourseQuiz(String quizId)throws DeodioException{
//		courseQuizMapper.deleteByPrimaryKey(quizId);
//	}
//	
//	/**
//	 * 更新课程考试
//	 * @param courseQuiz
//	 * @param userId
//	 * @param accountId
//	 * @throws DeodioException
//	 */
//	public void updateCourseQuiz(CourseQuiz courseQuiz,String userId,String accountId)throws DeodioException{
//		String id = courseQuiz.getId();
//		Date now = new Date();
//		courseQuiz.setAccountId(accountId);
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseQuiz.setId(id);
//			courseQuiz.setCreateId(userId);
//			courseQuiz.setCreateTime(now);
//			courseQuizMapper.insertSelective(courseQuiz);
//		}
//		else{
//			courseQuiz.setUpdateId(userId);
//			courseQuiz.setUpdateTime(now);
//			courseQuizMapper.updateByPrimaryKeySelective(courseQuiz);
//		}
//	}
//	
//	
//	/**
//	 * 查询课程考试列表
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public List<Map<String,Object>>  getCourseQuizList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.getCourseQuizList(params);
//	}
//	
//	/**
//	 * 通过主键获取课程问卷调查
//	 * @param quizeId
//	 * @return
//	 * @throws DeodioException
//	 */
//	public CourseSurvey queryCourseSurveyByPk(String surveyId)throws DeodioException{
//		return courseSurveyMapper.selectByPrimaryKey(surveyId);
//	}
//	
//	/**
//	 * 删除课程问卷调查
//	 * @param surveyId
//	 * @throws DeodioException
//	 */
//	public void delCourseSurvey(String surveyId)throws DeodioException{
//		courseSurveyMapper.deleteByPrimaryKey(surveyId);
//	}
//	
//	/**
//	 * 更新课程问卷调查
//	 * @param courseSurvey
//	 * @param userId
//	 * @param accountId
//	 * @throws DeodioException
//	 */
//	public void updateCourseSurvey(CourseSurvey courseSurvey,String userId,String accountId)throws DeodioException{
//		String id = courseSurvey.getId();
//		Date now = new Date();
//		courseSurvey.setAccountId(accountId);
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseSurvey.setId(id);
//			courseSurvey.setCreateId(userId);
//			courseSurvey.setCreateTime(now);
//			courseSurveyMapper.insertSelective(courseSurvey);
//		}
//		else{
//			courseSurvey.setUpdateId(userId);
//			courseSurvey.setUpdateTime(now);
//			courseSurveyMapper.updateByPrimaryKeySelective(courseSurvey);
//		}
//	}
//	
//	/**
//	 * 查询课程问卷调查列表
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public List<Map<String,Object>>  getCourseSurveyList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.getCourseSurveyList(params);
//	}
//	
//	/**
//	 * 通过关键字查询课程信息列表   courseType  accountId   keywords
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public List<Map<String,Object>>  findCourseDataListByKeywords(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.findCourseDataListByKeywords(params);
//	}
//	
//	
//	
//	
//	/**
//	 * 通过主键获取课程问卷调查
//	 * @param quizeId
//	 * @return
//	 * @throws DeodioException
//	 */
//	public Map<String, Object> queryCourseRelatedByPk(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.queryCourseRelatedById(params);
//	}
//	
//	/**
//	 * 删除课程相关
//	 * @param relatedId
//	 * @throws DeodioException
//	 */
//	public void delCourseRelated(String relatedId)throws DeodioException{
//		courseRelatedMapper.deleteByPrimaryKey(relatedId);
//	}
//	
//	/**
//	 * 更新课程相关
//	 * @param courseSurvey
//	 * @param userId
//	 * @param accountId
//	 * @throws DeodioException
//	 */
//	public void updateCourseRelated(CourseRelated courseRelated,String userId,String accountId)throws DeodioException{
//		String id = courseRelated.getId();
//		Date now = new Date();
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseRelated.setId(id);
//			courseRelated.setCreateId(userId);
//			courseRelated.setCreateTime(now);
//			courseRelatedMapper.insertSelective(courseRelated);
//		}
//		else{
//			courseRelated.setUpdateId(userId);
//			courseRelated.setUpdateTime(now);
//			courseRelatedMapper.updateByPrimaryKeySelective(courseRelated);
//		}
//	}
//	
//	/**
//	 * 查询课程相关列表
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public List<Map<String,Object>>  getCourseRelatedList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.getCourseRelatedList(params);
//	}
//	
//	/**
//	 * 查询在线课程相关内容
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public List<Map<String,Object>>  getOfflineCourseList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.findOfflineCourseList(params);
//	}
//	
//	/**
//	 * 查询线下课程相关内容
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public List<Map<String,Object>>  getOnlineCourseList(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.findOnlineCourseList(params);
//	}
//	
//	
//	/**
//	 * 激活课程学员
//	 * @param params
//	 * @throws DeodioException
//	 */
//	public void  activeCourseTrainees(Map<String,String> params)throws DeodioException{
//		String relatedId = params.get("relatedId");
//		String userId =  params.get("userId");
//		Date now = new Date();
//		@SuppressWarnings("unchecked")
//		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
//		CourseUserRelExample example = new CourseUserRelExample();
//		example.createCriteria().andIdIn(ids);
//		CourseUserRel record = new CourseUserRel();
//		record.setUpdateId(userId);
//		record.setUpdateTime(now);
//		record.setUserStatus(Constants.COURSE_TRAINEE_STATUS_ACTIVE);
//		record.setJoinTime(now);
//		courseUserRelMapper.updateByExampleSelective(record, example);
//	}
//	
//	/**
//	 * 暂停 课程学员
//	 * @param params
//	 * @throws DeodioException
//	 */
//	public void  suspendCourseTrainees(Map<String,String> params)throws DeodioException{
//		String relatedId = params.get("relatedId");
//		String userId =  params.get("userId");
//		@SuppressWarnings("unchecked")
//		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
//		CourseUserRelExample example = new CourseUserRelExample();
//		example.createCriteria().andIdIn(ids);
//		CourseUserRel record = new CourseUserRel();
//		record.setUpdateId(userId);
//		record.setUpdateTime(new Date());
//		record.setUserStatus(Constants.COURSE_TRAINEE_STATUS_SUSPEND);
//		courseUserRelMapper.updateByExampleSelective(record, example);
//	}
//	
//	/**
//	 * 删除课程学员(暂停人员可以被删除)
//	 * @param params
//	 * @throws DeodioException
//	 */
//	public void deleteCourseTrainees(Map<String,String> params)throws DeodioException{
//		String relatedId = (String) params.get("relatedId");
//		@SuppressWarnings("unchecked")
//		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
//		CourseUserRelExample example = new CourseUserRelExample();
//		example.createCriteria().andIdIn(ids).andUserStatusEqualTo(Constants.COURSE_TRAINEE_STATUS_SUSPEND);
//		courseUserRelMapper.deleteByExample(example);
//	}
//	
//	
//	/**
//	 * 获取指定课程下的学员数量
//	 * @param params
//	 * @return
//	 * @throws DeodioException
//	 */
//	public Integer numCourseTrainee(Map<String,Object> params)throws DeodioException{
//		return courseCustomizeMapper.numCourseTrainee(params);
//	}
//	
//	/**
//	 * 获取课程注册规则
//	 * @param params
//	 * @return
//	 */
//	public CourseRegisteRule queryCourseRegisteRule(Map<String,String> params)throws DeodioException{
//		String courseId = params.get("courseId");
//		CourseRegisteRuleExample example = new CourseRegisteRuleExample();
//		example.createCriteria().andCourseIdEqualTo(courseId);
//		List<CourseRegisteRule> list = courseRegisteRuleMapper.selectByExample(example);
//		CourseRegisteRule result = new CourseRegisteRule();
//		if(!list.isEmpty()){
//			result = list.get(DConstants.NUMBER_ZERO);
//		}
//		return result;
//	}
//	
//	/**
//	 * 更新课程注册规则
//	 * @param courseRegisteRule
//	 * @param userId
//	 * @param accountId
//	 */
//	public void updateCourseRegisteRule(CourseRegisteRule courseRegisteRule,String userId,String accountId)throws DeodioException{
//		String id = courseRegisteRule.getId();
//		Date now = new Date();
//		if(StringUtils.isBlank(id)){
//			id = AppUtils.uuid();
//			courseRegisteRule.setId(id);
//			courseRegisteRule.setCreateId(userId);
//			courseRegisteRule.setCreateTime(now);
//			courseRegisteRuleMapper.insertSelective(courseRegisteRule);
//		}else{
//			courseRegisteRule.setUpdateId(userId);
//			courseRegisteRule.setUpdateTime(now);
//			courseRegisteRuleMapper.updateByPrimaryKeySelective(courseRegisteRule);
//		}
//	}
//	
//	
//	public List<Map<String,Object>> findCourseShortCutList(Map<String,Object> params){
//		return courseCustomizeMapper.findCourseShortcutList(params);
//	}
//	
//	
//	public List<Map<String,Object>> findGroupCourseList(Map<String,Object> params){
//		return courseCustomizeMapper.findGroupCourseList(params);
//	}
//	
//	public List<Map<String,Object>> findUnSelectedGroupCourseList(Map<String,Object> params){
//		return courseCustomizeMapper.findUnSelectedGroupCourseList(params);
//	}
//	
//	public List<Map<String,Object>> getItemsByCreator(String userId,String accountId) throws DeodioException{
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("accountId", accountId);
//		params.put("userId", userId);
//		return courseCustomizeMapper.getItemsByCreator(params);
//	}
//
//
//	public List<Map<String, Object>> getItemsChartByCreator(String userId,String accountId) {
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("accountId", accountId);
//		params.put("userId", userId);
//		return courseCustomizeMapper.getItemsChartByCreator(params);
//	}
//	
//	/**
//	 * 查询学员课程详细信息
//	 * @param params
//	 * @return
//	 */
//	public Map<String,Object> queryTraineeCourseInfo(Map<String,Object> params){
//		return courseCustomizeMapper.queryTraineeCourseInfo(params);
//	}
//	
//	/**
//	 * 查询学员课程章节信息
//	 * @param params
//	 * @return
//	 */
//	public List<Map<String,Object>> queryTraineeCourseItemInfo(Map<String,Object> params){
//		return courseCustomizeMapper.queryTraineeCourseItemInfo(params);
//	}
//	
	
}
