package com.deodio.elearning.modules.course.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CourseHomeworkMapper;
import com.deodio.elearning.persistence.mapper.CourseMapper;
import com.deodio.elearning.persistence.mapper.CourseMaterialMapper;
import com.deodio.elearning.persistence.mapper.CourseModelOfflineMapper;
import com.deodio.elearning.persistence.mapper.CourseNoticeMapper;
import com.deodio.elearning.persistence.mapper.CourseOfflineItemMapper;
import com.deodio.elearning.persistence.mapper.CourseOnlineItemMapper;
import com.deodio.elearning.persistence.mapper.CourseOnliveItemMapper;
import com.deodio.elearning.persistence.mapper.CoursePackageItemsMapper;
import com.deodio.elearning.persistence.mapper.CourseQuizMapper;
import com.deodio.elearning.persistence.mapper.CourseRegisteRuleMapper;
import com.deodio.elearning.persistence.mapper.CourseRelatedMapper;
import com.deodio.elearning.persistence.mapper.CourseSurveyMapper;
import com.deodio.elearning.persistence.mapper.CourseUserRelMapper;
import com.deodio.elearning.persistence.mapper.TraineeAppraiseMapper;
import com.deodio.elearning.persistence.mapper.TraineeCourseMapper;
import com.deodio.elearning.persistence.mapper.TraineeCourseRecordMapper;
import com.deodio.elearning.persistence.mapper.TraineeFavoriteMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.CourseCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.TraineeAppraiseCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.TraineeCourseRecordCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseExample;
import com.deodio.elearning.persistence.model.CourseHomework;
import com.deodio.elearning.persistence.model.CourseHomeworkExample;
import com.deodio.elearning.persistence.model.CourseMaterial;
import com.deodio.elearning.persistence.model.CourseModelOffline;
import com.deodio.elearning.persistence.model.CourseNotice;
import com.deodio.elearning.persistence.model.CourseNoticeExample;
import com.deodio.elearning.persistence.model.CourseOfflineItemExample;
import com.deodio.elearning.persistence.model.CourseOnlineItemExample;
import com.deodio.elearning.persistence.model.CourseOnliveItemExample;
import com.deodio.elearning.persistence.model.CoursePackageItemsExample;
import com.deodio.elearning.persistence.model.CourseQuiz;
import com.deodio.elearning.persistence.model.CourseQuizExample;
import com.deodio.elearning.persistence.model.CourseRegisteRule;
import com.deodio.elearning.persistence.model.CourseRegisteRuleExample;
import com.deodio.elearning.persistence.model.CourseRelated;
import com.deodio.elearning.persistence.model.CourseSurvey;
import com.deodio.elearning.persistence.model.CourseSurveyExample;
import com.deodio.elearning.persistence.model.CourseUserRel;
import com.deodio.elearning.persistence.model.CourseUserRelExample;
import com.deodio.elearning.persistence.model.TraineeAppraiseExample;
import com.deodio.elearning.persistence.model.TraineeCourse;
import com.deodio.elearning.persistence.model.TraineeCourseExample;
import com.deodio.elearning.persistence.model.TraineeCourseRecord;
import com.deodio.elearning.persistence.model.TraineeFavorite;
import com.deodio.elearning.persistence.model.TraineeFavoriteExample;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.customize.CourseBo;
import com.deodio.elearning.persistence.model.customize.TraineeFavoriteBo;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author p0085398
 *
 */
@Service
public class CourseService extends BaseService {

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private CourseCustomizeMapper courseCustomizeMapper;

	@Autowired
	private CourseHomeworkMapper courseHomeworkMapper;

	@Autowired
	private CourseNoticeMapper courseNoticeMapper;

	@Autowired
	private CourseMaterialMapper courseMaterialMapper;

	@Autowired
	private CourseQuizMapper courseQuizMapper;

	@Autowired
	private CourseSurveyMapper courseSurveyMapper;

	@Autowired
	private CourseRelatedMapper courseRelatedMapper;

	@Autowired
	private CourseUserRelMapper courseUserRelMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private CourseRegisteRuleMapper courseRegisteRuleMapper;

	@Autowired
	private UserModelMapper userModelMapper;

	@Autowired
	private TraineeCourseRecordMapper traineeCourseRecordMapper;

	@Autowired
	private TraineeCourseMapper traineeCourseMapper;

	@Autowired
	private TraineeCourseRecordCustomizeMapper traineeCourseRecordCustomizeMapper;

	@Autowired
	private CourseOnlineItemMapper courseOnlineItemMapper;

	@Autowired
	private CourseOfflineItemMapper courseOfflineItemMapper;

	@Autowired
	private CourseModelOfflineMapper courseModelOfflineMapper;

	@Autowired
	private CourseOnliveItemMapper courseOnliveItemMapper;

	@Autowired
	private CoursePackageItemsMapper coursePackageItemsMapper;

	@Autowired
	private TraineeFavoriteMapper traineeFavoriteMapper;

	@Autowired
	private TraineeAppraiseCustomizeMapper traineeAppraiseCustomizeMapper;

	@Autowired
	private TraineeAppraiseMapper traineeAppraiseMapper;

	public void saveCourse(String courseId, Course courseInfo, Integer courseType, String userId, String accountId,
			String groupId) throws DeodioException
	{
		// 设置课程信息
		courseInfo.setId(courseId);
		courseInfo.setCourseType(courseType);
		courseInfo.setIsPublish(DConstants.TYPE_NO_PUBLISH.shortValue());
		courseInfo.setAccountId(accountId);
		courseInfo.setGroupId(groupId);
		courseInfo.setCourseOwner(userId);
		courseInfo.setIsEdit(DConstants.ISNOT_EDIT);
		// 判断是否存在课程数据，存在是更新，否则插入
		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andIdEqualTo(courseId);
		List<Course> list = courseMapper.selectByExample(courseExample);
		if (list.isEmpty()) {
			courseInfo.setCreateId(userId);
			courseInfo.setCreateTime(new Date());
			courseMapper.insertSelective(courseInfo);
		} else {
			courseInfo.setUpdateId(userId);
			courseInfo.setUpdateTime(new Date());
			courseMapper.updateByPrimaryKeySelective(courseInfo);
		}
	}

	public boolean deleteCourse(Map<String, String> params) throws DeodioException {

		boolean result = false;
		String userId = params.get("userId");
		String accountId = params.get("accountId");
		String courseId = params.get("courseId");

		CourseExample example = new CourseExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andIdEqualTo(courseId).andCreateIdEqualTo(userId)
				.andIsPublishEqualTo(new Short("0"));
		int rowCounts = courseMapper.deleteByExample(example);

		if (rowCounts > DConstants.NUMBER_ZERO) {
			result = true;
		}
		return result;
	}

	/**
	 * 查询指定名称的课程表
	 * 
	 * @param params
	 *            课程名称
	 * @return
	 */
	public List<Map<String, Object>> isExistName(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.isExistName(params);
	}

	/**
	 * 通过分类及标签获取课程列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getCourseList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseList(params);
	}

	/**
	 * 发布课程
	 * 
	 * @param params
	 *            courseId 课程主键编号
	 * @return
	 */
	public Integer publishCourse(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.publishCourse(params);
	}
	/**
	 * 取消发布课程
	 * @param params  courseId  课程主键编号
	 * @return
	 */
	public Integer cancelPublishCourse(Map<String,Object> params)throws DeodioException{
		return courseCustomizeMapper.cancelPublishCourse(params);
	}
	/**
	 * 更新课程作业
	 * 
	 * @param courseHomework
	 * @param attachId
	 * @param userId
	 * @param accountId
	 */
	public void updateCourseHomework(CourseHomework courseHomework, String attachId, String userId, String accountId)
			throws DeodioException
	{
		Date now = new Date();
		String id = courseHomework.getId();
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
			courseHomework.setId(id);
		}
		courseHomework.setAccountId(accountId);
		courseHomework.setHomeworkPublishTime(now);

		CourseHomework temp = courseHomeworkMapper.selectByPrimaryKey(id);
		if (temp == null) {
			courseHomework.setCreateId(userId);
			courseHomework.setCreateTime(now);
			courseHomeworkMapper.insertSelective(courseHomework);
		} else {
			courseHomework.setUpdateId(userId);
			courseHomework.setUpdateTime(now);
			courseCustomizeMapper.updateCourseHomeworkByPrimaryKey(courseHomework);
		}

		// 图片表更新
		if (StringUtils.isNotBlank(attachId)) {
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
	}

	/**
	 * 删除课程作业
	 * 
	 * @param homeworkId
	 */
	public void delCourseHomework(String homeworkId) throws DeodioException {
		courseHomeworkMapper.deleteByPrimaryKey(homeworkId);
	}

	/**
	 * 查询课程作业列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findCourseHomeworkList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseHomeworkList(params);
	}

	/**
	 * 查询课程作业管理列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findCourseHomeworkManagerList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseHomeworkManagerList(params);
	}

	/**
	 * 通过主键获取课程作业信息
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryCourseHomeworkByPk(String homeworkId) throws DeodioException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("homeworkId", homeworkId);
		return courseCustomizeMapper.queryCourseHomeworkById(map);
	}

	/**
	 * 更新课程通知
	 * 
	 * @param courseNotice
	 * @param userId
	 * @param accountId
	 */
	public void updateCourseNotice(CourseNotice courseNotice, String userId, String accountId) throws DeodioException {
		Date now = new Date();
		String id = courseNotice.getId();
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
			courseNotice.setId(id);
		}
		courseNotice.setAccountId(accountId);

		CourseNotice temp = courseNoticeMapper.selectByPrimaryKey(id);
		if (temp == null) {
			courseNotice.setCreateId(userId);
			courseNotice.setCreateTime(now);
			courseNoticeMapper.insertSelective(courseNotice);
		} else {
			courseNotice.setUpdateId(userId);
			courseNotice.setUpdateTime(now);
			courseNoticeMapper.updateByPrimaryKeySelective(courseNotice);
		}
	}

	/**
	 * 删除课通知
	 * 
	 * @param homeworkId
	 */
	public void delCourseNotice(String noticeId) throws DeodioException {
		courseNoticeMapper.deleteByPrimaryKey(noticeId);
	}

	/**
	 * 查询课程通知列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findCourseNoticeList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseNoticeList(params);
	}

	/**
	 * 通过分主键获取课程通知详细信息
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryCourseNoticeByPk(String noticeId) throws DeodioException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noticeId", noticeId);
		return courseCustomizeMapper.queryCourseNoticeById(map);
	}

	public CourseBo getCourseById(String courseId) throws DeodioException {
		CourseBo courseBo = new CourseBo();
		Course course = courseMapper.selectByPrimaryKey(courseId);
		BeanUtils.copyProperties(course, courseBo);
		return courseBo;
	}

	/**
	 * 更新课程附件
	 * 
	 * @param courseMaterial
	 * @param userId
	 * @param accountId
	 */
	public void updateCourseMaterial(CourseMaterial courseMaterial, String userId, String accountId, String attachId)
			throws DeodioException
	{
		Date now = new Date();
		String id = courseMaterial.getId();
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
			courseMaterial.setId(id);
		}
		if (StringUtils.isBlank(courseMaterial.getMaterialSize())) {
			courseMaterial.setMaterialSize(null);
		}
		courseMaterial.setAccountId(accountId);
		CourseMaterial temp = courseMaterialMapper.selectByPrimaryKey(id);
		if (temp == null) {
			courseMaterial.setCreateId(userId);
			courseMaterial.setCreateTime(now);
			courseMaterialMapper.insertSelective(courseMaterial);
		} else {
			courseMaterial.setUpdateId(userId);
			courseMaterial.setUpdateTime(now);
			courseMaterialMapper.updateByPrimaryKeySelective(courseMaterial);
		}

		// 图片表更新
		if (StringUtils.isNotBlank(attachId)) {
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
	}

	/**
	 * 删除课附件
	 * 
	 * @param materialId
	 */
	public void delCourseMaterial(String materialId) throws DeodioException {
		courseMaterialMapper.deleteByPrimaryKey(materialId);
	}

	/**
	 * 查询课程附件列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> findCourseMaterialList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseMaterialList(params);
	}

	/**
	 * 通过主键获取课程文件信息
	 * 
	 * @param params
	 * @return
	 */
	public CourseMaterial queryCourseMaterialByPk(String materialId) throws DeodioException {
		return courseMaterialMapper.selectByPrimaryKey(materialId);
	}

	/**
	 * 通过主键获取课程考试
	 * 
	 * @param quizeId
	 * @return
	 * @throws DeodioException
	 */
	public CourseQuiz queryCourseQuizByPk(String quizId) throws DeodioException {
		return courseQuizMapper.selectByPrimaryKey(quizId);
	}

	/**
	 * 删除课程考试
	 * 
	 * @param quizId
	 * @throws DeodioException
	 */
	public void delCourseQuiz(String quizId) throws DeodioException {
		courseQuizMapper.deleteByPrimaryKey(quizId);
	}

	/**
	 * 更新课程考试
	 * 
	 * @param courseQuiz
	 * @param userId
	 * @param accountId
	 * @throws DeodioException
	 */
	public void updateCourseQuiz(CourseQuiz courseQuiz, String userId, String accountId) throws DeodioException {
		String id = courseQuiz.getId();
		Date now = new Date();
		courseQuiz.setAccountId(accountId);
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
		}
		courseQuiz.setId(id);
		courseQuiz.setCreateId(userId);
		courseQuiz.setUpdateId(userId);
		courseQuiz.setUpdateTime(now);
		courseQuiz.setCreateTime(now);
		courseQuizMapper.insertSelective(courseQuiz);
		// if(StringUtils.isBlank(id)){
		// id = AppUtils.uuid();
		// courseQuiz.setId(id);
		// courseQuiz.setCreateId(userId);
		// courseQuiz.setCreateTime(now);
		// courseQuizMapper.insertSelective(courseQuiz);
		// }
		// else{
		// courseQuiz.setUpdateId(userId);
		// courseQuiz.setUpdateTime(now);
		// courseQuizMapper.updateByPrimaryKeySelective(courseQuiz);
		// }
	}

	/**
	 * 查询课程考试列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findCourseQuizList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseQuizList(params);
	}

	/**
	 * 查询已添加课程考试列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> getCourseQuizList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.getCourseQuizList(params);
	}

	/**
	 * 查询课程考试成绩列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findCourseQuizManagerList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseQuizManagerList(params);
	}

	/**
	 * 通过主键获取课程问卷调查
	 * 
	 * @param quizeId
	 * @return
	 * @throws DeodioException
	 */
	public CourseSurvey queryCourseSurveyByPk(String surveyId) throws DeodioException {
		return courseSurveyMapper.selectByPrimaryKey(surveyId);
	}

	/**
	 * 删除课程问卷调查
	 * 
	 * @param surveyId
	 * @throws DeodioException
	 */
	public void delCourseSurvey(String id) throws DeodioException {
		courseSurveyMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 更新课程问卷调查
	 * 
	 * @param courseSurvey
	 * @param userId
	 * @param accountId
	 * @throws DeodioException
	 * @throws ParseException 
	 */


	public void saveBantchCourseSurvey(String[] dataJson,String userId,String accountId,String courseId)throws DeodioException, ParseException{
//		CourseSurveyExample example = new CourseSurveyExample();
//		example.createCriteria().andAccountIdEqualTo(accountId).andCourseIdEqualTo(courseId);
//		courseSurveyMapper.deleteByExample(example);
		CourseSurvey courseSurvey =null;
		for(String str:dataJson) {
			if(str.contains("]#[")) {
				String[] data =StringUtils.split(str, "]#[");
				for(int dataI=0;dataI<data.length;dataI++) {
					System.err.println(getClass().getName()+"\t\t data["+dataI+"]:\t\t"+data[dataI]);
				}
				courseSurvey = new CourseSurvey();
				Date now = new Date();				
				CourseSurveyExample example = new CourseSurveyExample();
				example.createCriteria().andSurveyIdEqualTo(data[0]).
				andAccountIdEqualTo(accountId).andCourseIdEqualTo(courseId);
				List<CourseSurvey> courseSurveys=courseSurveyMapper.selectByExample(example);
				if (courseSurveys.isEmpty()) {
					courseSurvey.setAccountId(accountId);
					courseSurvey.setId(AppUtils.uuid());
					courseSurvey.setCreateId(userId);
					courseSurvey.setCreateTime(now);
					courseSurvey.setSurveyName(data[1]);
					courseSurvey.setCourseId(courseId);
					courseSurvey.setSurveyId(data[0]);
					courseSurvey.setSurveyDesc(data[2]);
					courseSurvey.setSurveyStartTime(DateTimeUtils.parse(data[3]));
					courseSurvey.setSurveyEndTime(DateTimeUtils.parse(data[4]));
					courseSurvey.setSurveyModel(Integer.parseInt(data[5]));
					courseSurvey.setUpdateId(userId);
					courseSurvey.setUpdateTime(now);
					courseSurveyMapper.insertSelective(courseSurvey);				
				}else {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");				
					courseSurvey=courseSurveys.get(0);
					System.err.println("courseSurvey.toString:\t"+courseSurvey.toString());
					boolean needUpdate=false;
					String surveyDesc=data[2];
					if (!courseSurvey.getSurveyDesc().equals(surveyDesc)) {
						courseSurvey.setSurveyDesc(data[2]);
						needUpdate=true;
					}
					String surveyStartDate=sdf.format(courseSurvey.getSurveyStartTime());
					if(!surveyStartDate.equals(data[3])) {
						courseSurvey.setSurveyStartTime(sdf.parse(surveyStartDate));
						needUpdate=true;
					}
					String surveyEndDate=sdf.format(courseSurvey.getSurveyStartTime());
					if(!surveyEndDate.equals(data[4])) {
						courseSurvey.setSurveyEndTime(sdf.parse(surveyEndDate));
						needUpdate=true;
					}
					Integer surveyModel=Integer.parseInt(data[5]);
					if(surveyModel!=courseSurvey.getSurveyModel()) {
						courseSurvey.setSurveyModel(surveyModel);
						needUpdate=true;
					}
					System.err.println("surveyDesc:\t"+surveyDesc+
							"\tsurveyStartDate:\t"+surveyStartDate+
							"\tsurveyEndDate:\t"+surveyEndDate+
							"\tsurveyModel:\t"+surveyModel+
							"\tneedUpdate:\t"+needUpdate);
					if(needUpdate) {
						courseSurveyMapper.updateByPrimaryKeySelective(courseSurvey);
					}
				}
				
			}
		}
		
	}

	
	
	
	/**
	 * 查询课程问卷调查列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findCourseSurveyList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseSurveyList(params);
	}

	/**
	 * 查询已添加课程问卷调查列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> getCourseSurveyList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.getCourseSurveyList(params);
	}

	/**
	 * 查询课程问卷调查结果列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findCourseSurveyManagerList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseSurveyManagerList(params);
	}

	/**
	 * 通过关键字查询课程信息列表 courseType accountId keywords
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findCourseDataListByKeywords(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findCourseDataListByKeywords(params);
	}

	/**
	 * 通过主键获取课程问卷调查
	 * 
	 * @param quizeId
	 * @return
	 * @throws DeodioException
	 */
	public Map<String, Object> queryCourseRelatedByPk(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.queryCourseRelatedById(params);
	}

	/**
	 * 删除课程相关
	 * 
	 * @param relatedId
	 * @throws DeodioException
	 */
	public void delCourseRelated(String relatedId) throws DeodioException {
		courseRelatedMapper.deleteByPrimaryKey(relatedId);
	}

	/**
	 * 更新课程相关
	 * 
	 * @param courseSurvey
	 * @param userId
	 * @param accountId
	 * @throws DeodioException
	 */
	public void updateCourseRelated(CourseRelated courseRelated, String userId, String accountId)
			throws DeodioException
	{
		String id = courseRelated.getId();
		Date now = new Date();
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
			courseRelated.setId(id);
			courseRelated.setCreateId(userId);
			courseRelated.setCreateTime(now);
			courseRelatedMapper.insertSelective(courseRelated);
		} else {
			courseRelated.setUpdateId(userId);
			courseRelated.setUpdateTime(now);
			courseRelatedMapper.updateByPrimaryKeySelective(courseRelated);
		}
	}

	/**
	 * 查询课程相关列表
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> getCourseRelatedList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.getCourseRelatedList(params);
	}

	/**
	 * 查询在线课程相关内容
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> getOfflineCourseList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findOfflineCourseList(params);
	}

	/**
	 * 查询线下课程相关内容
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> getOnlineCourseList(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.findOnlineCourseList(params);
	}

	/**
	 * 激活课程学员
	 * 
	 * @param params
	 * @throws DeodioException
	 */
	public void activeCourseTrainees(Map<String, String> params) throws DeodioException {
		String relatedId = params.get("relatedId");
		String userId = params.get("userId");
		Date now = new Date();
		@SuppressWarnings("unchecked")
		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
		CourseUserRelExample example = new CourseUserRelExample();
		example.createCriteria().andIdIn(ids);
		CourseUserRel record = new CourseUserRel();
		record.setUpdateId(userId);
		record.setUpdateTime(now);
		record.setUserStatus(Constants.COURSE_TRAINEE_STATUS_ACTIVE);
		record.setJoinTime(now);
		courseUserRelMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 暂停 课程学员
	 * 
	 * @param params
	 * @throws DeodioException
	 */
	public void suspendCourseTrainees(Map<String, String> params) throws DeodioException {
		String relatedId = params.get("relatedId");
		String userId = params.get("userId");
		@SuppressWarnings("unchecked")
		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
		CourseUserRelExample example = new CourseUserRelExample();
		example.createCriteria().andIdIn(ids);
		CourseUserRel record = new CourseUserRel();
		record.setUpdateId(userId);
		record.setUpdateTime(new Date());
		record.setUserStatus(Constants.COURSE_TRAINEE_STATUS_SUSPEND);
		courseUserRelMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 通过/替补/拒绝 课程学员
	 * 
	 * @param params
	 * @throws DeodioException
	 */
	public void updateCourseTraineesUserStatus(Map<String, String> params, short userStatus) throws DeodioException {
		String relatedId = params.get("relatedId");
		String userId = params.get("userId");
		@SuppressWarnings("unchecked")
		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
		CourseUserRelExample example = new CourseUserRelExample();
		example.createCriteria().andIdIn(ids);
		CourseUserRel record = new CourseUserRel();
		record.setUpdateId(userId);
		record.setUpdateTime(new Date());
		record.setUserStatus(userStatus);
		record.setJoinTime(new Date());
		courseUserRelMapper.updateByExampleSelective(record, example);
	}

	/**
	 * 删除课程学员(暂停人员可以被删除)
	 * 
	 * @param params
	 * @throws DeodioException
	 */
	public void deleteCourseTrainees(Map<String, String> params) throws DeodioException {
		String relatedId = (String) params.get("relatedId");
		@SuppressWarnings("unchecked")
		List<String> ids = Arrays.asList(relatedId.split(Constants.STRING_COMMA));
		CourseUserRelExample example = new CourseUserRelExample();
		example.createCriteria().andIdIn(ids).andUserStatusEqualTo(Constants.COURSE_TRAINEE_STATUS_SUSPEND);
		courseUserRelMapper.deleteByExample(example);
	}

	/**
	 * 获取指定课程下的学员数量
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public Integer numCourseTrainee(Map<String, Object> params) throws DeodioException {
		return courseCustomizeMapper.numCourseTrainee(params);
	}

	/**
	 * 获取指定课程下的审批通过的学员数量
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public Integer numCourseTraineeUserStatus(Map<String, Object> params, short userStatus) throws DeodioException {
		params.put("userStatus", userStatus);
		return courseCustomizeMapper.numCourseTraineeUserStatus(params);
	}

	/**
	 * 获取课程注册规则
	 * 
	 * @param params
	 * @return
	 */
	public CourseRegisteRule queryCourseRegisteRule(Map<String, String> params) throws DeodioException {
		String courseId = params.get("courseId");
		CourseRegisteRuleExample example = new CourseRegisteRuleExample();
		example.createCriteria().andCourseIdEqualTo(courseId);
		List<CourseRegisteRule> list = courseRegisteRuleMapper.selectByExample(example);
		CourseRegisteRule result = new CourseRegisteRule();
		if (!list.isEmpty()) {
			result = list.get(DConstants.NUMBER_ZERO);
		}
		return result;
	}

	/**
	 * 更新课程注册规则
	 * 
	 * @param courseRegisteRule
	 * @param userId
	 * @param accountId
	 */
	public void updateCourseRegisteRule(CourseRegisteRule courseRegisteRule, String userId, String accountId)
			throws DeodioException
	{
		CourseRegisteRuleExample example=new CourseRegisteRuleExample();
		example.createCriteria().andCourseIdEqualTo(courseRegisteRule.getCourseId());
		List<CourseRegisteRule> courseRegisteRulelist=courseRegisteRuleMapper.selectByExample(example);
		Date now = new Date();
		if (null==courseRegisteRulelist||courseRegisteRulelist.size()==0) {
			String id = AppUtils.uuid();
			courseRegisteRule.setId(id);
			courseRegisteRule.setCreateId(userId);
			courseRegisteRule.setCreateTime(now);
			courseRegisteRuleMapper.insertSelective(courseRegisteRule);
		} else {
			courseRegisteRule.setUpdateId(userId);
			courseRegisteRule.setUpdateTime(now);
			courseRegisteRuleMapper.updateByPrimaryKeySelective(courseRegisteRule);
		}
	}

	public List<Map<String, Object>> findCourseShortCutList(Map<String, Object> params) {
		return courseCustomizeMapper.findCourseShortcutList(params);
	}

	public List<Map<String, Object>> findGroupCourseList(Map<String, Object> params) {
		return courseCustomizeMapper.findGroupCourseList(params);
	}

	public List<Map<String, Object>> findGroupCourseSelectList(Map<String, Object> params) {
		return courseCustomizeMapper.findGroupCourseSelectList(params);
	}

	public List<Map<String, Object>> findUnSelectedGroupCourseList(Map<String, Object> params) {
		return courseCustomizeMapper.findUnSelectedGroupCourseList(params);
	}

	public List<Map<String, Object>> getItemsByCreator(String userId, String accountId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		return courseCustomizeMapper.getItemsByCreator(params);
	}

	public List<Map<String, Object>> getItemsChartByCreator(String userId, String accountId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		return courseCustomizeMapper.getItemsChartByCreator(params);
	}

	public List<Map<String, Object>> findCourseViewerList(Map<String, Object> params) {
		return courseCustomizeMapper.findCourseViewerList(params);
	}

	/**
	 * 查询学员课程详细信息
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryTraineeCourseInfo(Map<String, Object> params) {
		// 查询学员课程信息
		Map<String, Object> courseInfo = courseCustomizeMapper.queryTraineeCourseInfo(params);
		// 设置检索条件课程类别（（1：线上2：线下3：直播））
		Integer courseType = (Integer) courseInfo.get("course_type");
		params.put("courseType", courseType);
		// 查询课程是否有在线视频
		Map<String, Object> courseModel = courseCustomizeMapper.queryCourseModelInfo(params);
		Integer hasVideo = null;
		if (courseModel != null) {
			hasVideo = (Integer) courseModel.get("has_video");
		}
		courseInfo.put("hasVideo", hasVideo);
		// 课程章节数量
		Integer courseItemNum = courseCustomizeMapper.queryCourseItemNum(params);
		courseInfo.put("item_num", courseItemNum);
		// 已完成章节数量
		Integer itemPassedNum = courseCustomizeMapper.queryTraineeCompleteCourseItemNum(params);
		courseInfo.put("item_pass_num", itemPassedNum);
		// 课程完成度
		Integer completePercent = courseItemNum.equals(0) ? 100 : 100 * itemPassedNum / courseItemNum;
		courseInfo.put("complete_percent", completePercent);
		// 查询课程发布者（创建者）
		String userId = String.valueOf(courseInfo.get("create_id"));
		UserModel userInfo = userModelMapper.selectByPrimaryKey(userId);
		String userName = userInfo != null ? userInfo.getNickName() : "";
		courseInfo.put("publisher", userName);
		return courseInfo;
	}

	/**
	 * 查询学员课程章节信息
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryTraineeCourseItemInfo(Map<String, Object> params) {
		Map<String, Object> courseInfo = courseCustomizeMapper.queryTraineeCourseInfo(params);
		// 设置检索条件课程类别（（1：线上2：线下3：直播））
		Integer courseType = (Integer) courseInfo.get("course_type");
		params.put("courseType", courseType);
		return courseCustomizeMapper.queryTraineeCourseItemInfo(params);
	}
	
	public List<Map<String,Object>> findCourseOfflineItems(Map<String,Object> params){
		return courseCustomizeMapper.findCourseOfflineItems(params);
	}

	/**
	 * 查询学员课程章节次序信息
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryTraineeCourseItemSortInfo(Map<String, Object> params) {
		// 查询学员课程信息
		return courseCustomizeMapper.queryTraineeCourseItemSortInfo(params);
	}

	/**
	 * 获取课程指定偏移量的课程
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> queryCourseItemOffset(Map<String, Object> params) {
		return courseCustomizeMapper.queryCourseItemOffset(params);
	}

	public List<Map<String, Object>> findPackageCourseList(Map<String, Object> params) {
		return courseCustomizeMapper.findPackageCourseList(params);
	}

	/**
	 * 插入学员课程学习记录
	 * 
	 * @param record
	 * @return
	 */
	public void saveTraineeCourseLearnRecord(TraineeCourseRecord record, String accountId, String userId) {
		Date now = new Date();
		record.setCreateTime(now);
		record.setUpdteTime(now);
		record.setCreateId(userId);
		record.setUpdateId(userId);
		record.setAccountId(accountId);
		record.setTraineeId(userId);
		record.setStartTime(now);

		TraineeCourseRecord databaseRecord = traineeCourseRecordCustomizeMapper.queryCourseItemLearnInfo(record);
		// 数据库中无学习记录时，添加学习记录
		if (databaseRecord == null) {
			record.setId(AppUtils.uuid());
			traineeCourseRecordMapper.insertSelective(record);
		} else if (DConstants.COURSE_RECORD_IS_CONTINUE.equals(databaseRecord.getIsPass())) {
			// 学习记录为未完成时，更新学习记录
			traineeCourseRecordCustomizeMapper.updateUncompleteCourseItemLearnInfo(record);
		}
	}

	public void saveTraineeSelectCourseToLearn(TraineeCourse traineeCourse, String accountId, String userId) {
		// 验证是否已经选择
		String courseId = traineeCourse.getItemId();
		short courseType = traineeCourse.getItemType();
		TraineeCourseExample example = new TraineeCourseExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andTraineeIdEqualTo(userId).andItemIdEqualTo(courseId);

		int rowCount = traineeCourseMapper.countByExample(example);
		if (DConstants.NUMBER_ZERO == rowCount) {
			if (DConstants.COURSE_TYPE_ONLINE == courseType || DConstants.COURSE_TYPE_OFFLINE == courseType
					|| DConstants.COURSE_TYPE_ONLIVE == courseType)
			{
				traineeCourse.setItemType(DConstants.TRAINEE_COURSE_REL_COURSE.shortValue());
			} else {
				traineeCourse.setItemType(DConstants.TRAINEE_COURSE_REL_PACKAGE.shortValue());
			}
			traineeCourse.setId(AppUtils.uuid());
			traineeCourse.setCreateId(userId);
			traineeCourse.setUpdateId(userId);
			traineeCourse.setCreateTime(new Date());
			traineeCourse.setUpdateTime(new Date());
			traineeCourse.setStudyTime(new Date());
			// 获取章节数量
			Integer itemsCount = getCourseItemsCount(courseId, courseType);
			traineeCourse.setChapterCount(itemsCount.shortValue());
			traineeCourseMapper.insert(traineeCourse);
		}

	}

	/**
	 * 查询课程下属章节数 （后续可能需要扩充课程包）
	 * 
	 * @param courseId
	 * @param courseType
	 * @return
	 */
	public Integer getCourseItemsCount(String courseId, short courseType) {
		Integer rowCount = 0;

		if (DConstants.COURSE_TYPE_ONLINE == courseType) {
			CourseOnlineItemExample example = new CourseOnlineItemExample();
			example.createCriteria().andCourseIdEqualTo(courseId);
			rowCount = courseOnlineItemMapper.countByExample(example);

		} else if (DConstants.COURSE_TYPE_OFFLINE == courseType) {
			CourseOfflineItemExample example = new CourseOfflineItemExample();
			example.createCriteria().andCourseIdEqualTo(courseId);
			rowCount = courseOfflineItemMapper.countByExample(example);
		} else if (DConstants.COURSE_TYPE_ONLIVE == courseType) {
			CourseOnliveItemExample example = new CourseOnliveItemExample();
			example.createCriteria().andCourseIdEqualTo(courseId);
			rowCount = courseOnliveItemMapper.countByExample(example);
		} else {
			CoursePackageItemsExample example = new CoursePackageItemsExample();
			example.createCriteria().andPackageIdEqualTo(courseId);
			rowCount = coursePackageItemsMapper.countByExample(example);
		}
		return rowCount;
	}

	/**
	 * 判断课程是否被某人关注
	 * 
	 * @param params
	 * @return
	 */
	public boolean hasFavoriteCourse(Map<String, Object> params) {
		TraineeFavoriteExample example = new TraineeFavoriteExample();
		String userId = (String) params.get("userId");
		String courseId = (String) params.get("courseId");
		String accountId = (String) params.get("accountId");
		example.createCriteria().andTraineeIdEqualTo(userId).andAccountIdEqualTo(accountId)
				.andContentIdEqualTo(courseId);
		int count = traineeFavoriteMapper.countByExample(example);
		boolean flag = false;
		if (count > Constants.NUMBER_ZERO) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 关注课程
	 * 
	 * @param params
	 */
	public void favoriteCourse(TraineeFavorite record, String userId) {
		String id = AppUtils.uuid();
		Date now = new Date();
		record.setId(id);
		record.setCreateId(userId);
		record.setCreateTime(now);
		record.setUpdateId(userId);
		record.setUpdateTime(now);
		traineeFavoriteMapper.insertSelective(record);
	}

	/**
	 * 取消关注课程
	 * 
	 * @param record
	 */
	public void delCourseFavorite(TraineeFavorite record) {
		String contentId = record.getContentId();
		String traineeId = record.getTraineeId();
		String accountId = record.getAccountId();
		TraineeFavoriteExample example = new TraineeFavoriteExample();
		example.createCriteria().andContentIdEqualTo(contentId).andTraineeIdEqualTo(traineeId)
				.andAccountIdEqualTo(accountId);
		traineeFavoriteMapper.deleteByExample(example);
	}

	/**
	 * 保存关注课程
	 * 
	 * @param record
	 */
	public void saveCourseFavorite(TraineeFavoriteBo traineeFavoriteBo, String userId) {
		int operateType = traineeFavoriteBo.getOperateType();
		switch (operateType) {
		case Constants.COURSE_OPERATE_TYPE_INSERT:
			this.favoriteCourse(traineeFavoriteBo, userId);
			break;
		case Constants.COURSE_OPERATE_TYPE_DEL:
			this.delCourseFavorite(traineeFavoriteBo);
			break;
		default:
			throw new DeodioException("operateType is not valid");
		}
	}

	/**
	 * 判断学员是否已经选择该课程
	 * 
	 * @return
	 */
	public boolean traineeHasSelectCourse(Map<String, Object> params) {
		boolean result = false;
		String courseId = (String) params.get("courseId");
		String accountId = (String) params.get("accountId");
		String userId = (String) params.get("userId");
		TraineeCourseExample example = new TraineeCourseExample();
		example.createCriteria().andItemIdEqualTo(courseId).andAccountIdEqualTo(accountId).andTraineeIdEqualTo(userId);
		int count = traineeCourseMapper.countByExample(example);
		if (Integer.compare(count, 0) > DConstants.NUMBER_ZERO) {
			result = true;
		}
		return result;
	}

	/**
	 * 获取课程学员数量
	 * 
	 * @param params
	 *            contentId accountId
	 * @return
	 */
	public int courseLearnCount(Map<String, Object> params) {
		String courseId = (String) params.get("courseId");
		String accountId = (String) params.get("accountId");
		TraineeCourseExample example = new TraineeCourseExample();
		example.createCriteria().andItemIdEqualTo(courseId).andAccountIdEqualTo(accountId);
		return traineeCourseMapper.countByExample(example);
	}

	/**
	 * 课程平均评分
	 * 
	 * @param params
	 *            contentId accountId
	 * @return
	 */
	public int courseApraiseAvg(Map<String, Object> params) {
		String courseId = (String) params.get("courseId");
		params.put("contentId", courseId);
		return traineeAppraiseCustomizeMapper.courseAvgScore(params);
	}

	/**
	 * 课程平均评分
	 * 
	 * @param params
	 *            contentId accountId
	 * @return
	 */
	public int courseApraiseCount(Map<String, Object> params) {
		String courseId = (String) params.get("courseId");
		String accountId = (String) params.get("accountId");
		TraineeAppraiseExample example = new TraineeAppraiseExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andContentIdEqualTo(courseId);
		return traineeAppraiseMapper.countByExample(example);
	}

	public void copyCourse(String oldId, String newId, String courseName,String userId) {
		Course record = courseMapper.selectByPrimaryKey(oldId);
		record.setId(newId);
		record.setCourseName(courseName);
		record.setIsPublish(new Short("0"));
		record.setCourseOwner(userId);
		record.setIsEdit(DConstants.ISNOT_EDIT);
		record.setCreateTime(new Date());
		record.setUpdateTime(null);
		courseMapper.insert(record);
	}

	public void copyCourseModelOffline(String oldId, String newId) {
		CourseModelOffline courseModelOffline = courseModelOfflineMapper.selectByPrimaryKey(oldId);
		courseModelOffline.setId(newId);
		courseModelOffline.setCourseId(newId);
		courseModelOfflineMapper.insert(courseModelOffline);
	}

	public void copyAttachment(String oldId, String newId) {
		Attachment attachment = attachmentMapper.selectByPrimaryKey(oldId);
		if (null != attachment) {
			attachment.setId(newId);
			attachment.setRelId(newId);
			attachmentMapper.insert(attachment);
		}
	}

	public Course selectyCourseByPrimaryId(String courseId) {
		Course record = courseMapper.selectByPrimaryKey(courseId);
		return record;
	}

	public void delAllCourseQuizByCourseId(Map<String, Object> params) {
		// TODO Auto-generated method stub
		courseQuizMapper.delAllCourseQuizByCourseId(params);
	}


	public void delAllCourseSurveyByCourseId(String[] arrys,String accountId,String courseId) {
		CourseSurveyExample example=null;
		for(String str : arrys) {
			example = new CourseSurveyExample();
			example.createCriteria().andCourseIdEqualTo(courseId).andAccountIdEqualTo(accountId).andSurveyIdEqualTo(str);
			courseSurveyMapper.deleteByExample(example);
		}
		
	}
	
	public boolean getCheckHomeworkName(String homeworkName, String courseId) {
		CourseHomeworkExample example = new CourseHomeworkExample();
		example.createCriteria().andHomeworkNameEqualTo(homeworkName).andCourseIdEqualTo(courseId);
		List<CourseHomework> list = courseHomeworkMapper.selectByExample(example);
		return list.isEmpty() ? false : true;
	}

	public boolean checkNoticeName(String noticeName) {
		// TODO Auto-generated method stub
		CourseNoticeExample example = new CourseNoticeExample();
		example.createCriteria().andNoticeNameEqualTo(noticeName);
		List<CourseNotice> list = courseNoticeMapper.selectByExample(example);
		return !list.isEmpty();
	}

	public Map<String, Object> findCourseTestQuizList(String courseId, String keyword, Integer pageNo) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = new PageRequest(pageNo);
		pageRequest.getPagination().setPageSize(Constants.COURSE_MODULE_PAGESIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keyword", StringUtils.isNotBlank(keyword) ? keyword : null);
		params.put("courseId", courseId);
		params.put("pagination", pageRequest);
		List<Map<String, Object>> dataList = courseCustomizeMapper.findCourseTestQuizList(params);	
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		return jsonMap;
	}

	public List<CourseUserRel> selectyCourseUserRel(String courseId) {
		CourseUserRelExample example=new CourseUserRelExample();
		example.createCriteria().andCourseIdEqualTo(courseId).andRegisteTypeEqualTo(new Short("2"));
		List<CourseUserRel> list = courseUserRelMapper.selectByExample(example);
		return list;
	}
	public void deleteCourseUserRel(String courseId) {
		CourseUserRelExample example=new CourseUserRelExample();
		example.createCriteria().andCourseIdEqualTo(courseId).andRegisteTypeEqualTo(new Short("2"));
		courseUserRelMapper.deleteByExample(example);
	}
	public void saveBantchCourseQuiz(String[] dataJson, String userId, String accountId, String courseId) {
		// TODO Auto-generated method stub
		CourseQuizExample example = new CourseQuizExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andCourseIdEqualTo(courseId);		
		courseQuizMapper.deleteByExample(example);
		CourseQuiz courseQuiz =null;
		for(String str:dataJson) {
			if(str.contains("]#[")) {
				String[] data =StringUtils.split(str, "]#[");
				courseQuiz = new CourseQuiz();
				Date now = new Date();
				courseQuiz.setAccountId(accountId);
				courseQuiz.setId(AppUtils.uuid());
				courseQuiz.setCreateId(userId);
				courseQuiz.setUpdateId(userId);
				courseQuiz.setUpdateTime(now);
				courseQuiz.setCreateTime(now);
				
				courseQuiz.setQuizName(data[1]);
				courseQuiz.setQuizAlias(data[2]);
				courseQuiz.setQuizPassScore("".equals(data[3].trim())?0:Integer.parseInt(data[3]));
				courseQuiz.setQuizFinishTime("".equals(data[4].trim())?0:Integer.parseInt(data[4]));
				courseQuiz.setQuizMaxTimes("".equals(data[5].trim())?0:Integer.parseInt(data[5]));
				courseQuiz.setQuizFinallyResultType("".equals(data[6].trim())?0:Integer.parseInt(data[6]));
				courseQuiz.setQuizPublishResultType(data[7]);
				courseQuiz.setQuizSafe(data[8]);
				courseQuiz.setQuizId(data[0]);
				courseQuiz.setCourseId(courseId);
				courseQuizMapper.insertSelective(courseQuiz);
//				System.err.println(getClass().getName()+"\tstr:\t"+str);
//				courseQuiz.setSurveyDesc(data[2]);
//				courseQuiz.setSurveyName(data[1]);
//				courseQuiz.setSurveyStartTime(DateTimeUtils.parse(data[3]));
//				courseQuiz.setSurveyEndTime(DateTimeUtils.parse(data[4]));
//				courseQuiz.setSurveyModel(Integer.parseInt(data[5]));
//				courseQuiz.setCourseId(courseId);
//				courseQuiz.setSurveyId(data[0]);
			}

		}
		
	}
	public void saveTraineeSEnrollCourseToLearn(TraineeCourse traineeCourse,String userId) {
		// 验证是否已经选择
		String courseId = traineeCourse.getItemId();
		short courseType = traineeCourse.getItemType();
			traineeCourse.setId(AppUtils.uuid());
			traineeCourse.setCreateId(userId);
			traineeCourse.setUpdateId(userId);
			traineeCourse.setCreateTime(new Date());
			traineeCourse.setUpdateTime(new Date());
			traineeCourse.setStudyTime(new Date());
			// 获取章节数量
			Integer itemsCount = getCourseItemsCount(courseId, courseType);
			traineeCourse.setChapterCount(itemsCount.shortValue());
			traineeCourseMapper.insert(traineeCourse);
	}
	/**获取课程作业信息
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> findCourseHomeworkInfo(Map<String,Object> params){
		return courseCustomizeMapper.findCourseHomeworkInfo(params);
	}
}
