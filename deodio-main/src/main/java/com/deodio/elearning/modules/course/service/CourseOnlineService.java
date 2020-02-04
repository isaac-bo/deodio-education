package com.deodio.elearning.modules.course.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.commons.service.UploadService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CourseMapper;
import com.deodio.elearning.persistence.mapper.CourseModelOnlineMapper;
import com.deodio.elearning.persistence.mapper.CourseOnlineItemMapper;
import com.deodio.elearning.persistence.mapper.customize.CourseCustomizeMapper;
import com.deodio.elearning.persistence.mapper.customize.CourseModelOnlineCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentExample;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseExample;
import com.deodio.elearning.persistence.model.CourseModelOnline;
import com.deodio.elearning.persistence.model.CourseModelOnlineExample;
import com.deodio.elearning.persistence.model.CourseOnlineItem;
import com.deodio.elearning.persistence.model.CourseOnlineItemExample;
import com.deodio.elearning.persistence.model.customize.CourseModelOnlineBo;
import com.deodio.elearning.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class CourseOnlineService extends BaseService {

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private TagsService tagsService;

	@Autowired
	private ClassificationService classificationService;

	@Autowired
	private CourseModelOnlineMapper courseModelOnlineMapper;

	@Autowired
	private CourseModelOnlineCustomizeMapper courseModelOnlineCustomizeMapper;
	
	@Autowired
	private CourseCustomizeMapper courseCustomizeMapper;

	@Autowired
	private CourseOnlineItemMapper courseOnlineItemMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private CourseService courseService;

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private UploadService uploadService;

	/**
	 * 插入线上课程信息
	 */
	public String insertCourseOnlineInfo(String courseInfoJson, String attachId, String userId, String accountId,
			String groupId, String tagsJson, String classificationJson) throws DeodioException {

		Gson courseGson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		Course courseInfo = courseGson.fromJson(courseInfoJson, new TypeToken<Course>() {
		}.getType());
		Gson courseModelOnlineGson = new Gson();
		CourseModelOnlineBo courseModelOnlineBo = courseModelOnlineGson.fromJson(courseInfoJson,
				new TypeToken<CourseModelOnlineBo>() {
				}.getType());
		String id = courseInfo.getId();
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
		}
		courseService.saveCourse(id, courseInfo, DConstants.COURSE_TYPE_ONLINE, userId, accountId, groupId);
		updateCourseModelOnline(id, courseModelOnlineBo, userId, accountId);
		tagsService.saveTagsItemsRel(tagsJson, accountId, userId, id, DConstants.TAGS_ITEMS_TYPE_COURSE_ONLINE);
		classificationService.saveClassificationItemsRel(classificationJson, userId, id,
				DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLINE);
		// 图片表更新
		if (StringUtils.isNotBlank(attachId)) {
			attachmentService.updateAttachement(attachId, id, userId);
		}

		return id;
	}

	/**
	 * 更新线上课程
	 * @param courseModelOnlineId
	 * @param courseModelOnline
	 * @param userId
	 * @param accountId
	 */
	private void updateCourseModelOnline(String courseModelOnlineId, CourseModelOnline courseModelOnline, String userId,
			String accountId) {
		CourseModelOnlineExample example = new CourseModelOnlineExample();
		example.createCriteria().andIdEqualTo(courseModelOnlineId);

		List<CourseModelOnline> onlineList = courseModelOnlineMapper.selectByExample(example);
		if (onlineList.isEmpty()) {
			courseModelOnline.setId(courseModelOnlineId);
			courseModelOnline.setCourseId(courseModelOnlineId);
			courseModelOnline.setCreateId(userId);
			courseModelOnline.setCreateTime(new Date());
			courseModelOnlineMapper.insertSelective(courseModelOnline);
		} else {
			courseModelOnline.setUpdateId(userId);
			courseModelOnline.setUpdateTime(new Date());
			courseModelOnlineMapper.updateByExampleSelective(courseModelOnline, example);
		}
	}

	/**
	 *  更新线上课程内容
	 * @param items   内容列表
	 * @param courseId		 课程标号
	 * @param userId		 用户编号
	 * @param accountId		账号编号
	 */
	public void updateCourseOnlineContent(List<CourseOnlineItem> items, String courseId, String userId,
			String accountId) throws DeodioException {

		if (StringUtils.isBlank(courseId)) {
			throw new DeodioException("course.id.null.err.msg");
		}
		Date now = new Date();
		int len = items.size();
		for (CourseOnlineItem item : items) {
			item.setId(AppUtils.uuid());
			item.setCourseId(courseId);
			item.setCreateId(userId);
			item.setCreateTime(now);
			item.setUpdateId(userId);
			item.setUpdateTime(now);
			// 第一项 ,不设置比它更小的阈值
			if (Constants.NUMBER_ONE == item.getItemSort()) {
				item.setItemLt(Constants.NUMBER_ZERO);
				item.setItemLtPos(Constants.NUMBER_ZERO);
			}
			// 最后一项 ,不设置比它更大的阈值
			if (len == item.getItemSort()) {
				item.setItemGt(Constants.NUMBER_ZERO);
				item.setItemGtPos(Constants.NUMBER_ZERO);
				;
			}
		}
		CourseOnlineItemExample example = new CourseOnlineItemExample();
		example.createCriteria().andCourseIdEqualTo(courseId);
		// 删除课程相关内容
		courseOnlineItemMapper.deleteByExample(example);
		if (!items.isEmpty()) {
			courseModelOnlineCustomizeMapper.insertCourseOnlineItem(items);
		}
	}

	/**
	 * 通过课程主键获取课程信息
	 * @param params	courseId 
	 * @return
	 */
	public Map<String, Object> queryCourseOnlineInfo(Map<String, Object> params) throws DeodioException {
		Map<String, Object> courseOnlineInfo = courseModelOnlineCustomizeMapper.queryCourseOnlineInfoById(params);
		return courseOnlineInfo;
	}

	/**
	 * 通过课程主键获取课程信息(profile页面)
	 */
	public Map<String, Object> queryCourseOnlineProfile(Map<String, Object> params) throws DeodioException {

		return courseModelOnlineCustomizeMapper.queryCourseOnlineProfileById(params);
	}

	/**
	 * 通过课程编号获取课程内容信息(content页面)
	 * @param params	courseId   
	 * @return
	 */
	public List<Map<String, Object>> queryCourseOnlineContent(Map<String, Object> params) throws DeodioException {

		return courseModelOnlineCustomizeMapper.queryCourseOnlineItem(params);
	}

	/**
	 * 校验课程名称是否重复
	 * @return true : 不重复 false : 重复
	 */
	public boolean validateCourseName(String courseName, String accountId) {

		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andCourseNameEqualTo(courseName).andAccountIdEqualTo(accountId)
				.andCourseTypeEqualTo(DConstants.COURSE_TYPE_ONLINE);

		return courseMapper.selectByExample(courseExample).isEmpty();
	}

	/**
	 * 发布课程 
	 */
	public int publishCourse(String courseId) {

		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andIdEqualTo(courseId);
		List<Course> courses = courseMapper.selectByExample(courseExample);
		Course course = new Course();
		course.setIsPublish(DConstants.TYPE_IS_PUBLISH.shortValue());
		course.setCourseOwner(courses.get(0).getCreateId());
		course.setIsEdit(DConstants.ISNOT_EDIT);

		return courseMapper.updateByExampleSelective(course, courseExample);
	}

	/**
	 * 取消发布课程
	 */
	public void cancelPublish(String courseId, String userId) {

		Course course = new Course();
		course.setId(courseId);
		course.setIsPublish(DConstants.TYPE_NO_PUBLISH.shortValue());
		course.setUpdateId(userId);
		course.setUpdateTime(new Date());
		courseMapper.updateByPrimaryKeySelective(course);
	}

	/**
	 * 复制课程
	 */
	public void copyCourse(String courseId, String courseName, String userId, String accountId, String groupId) {

		Course courseInfo = courseMapper.selectByPrimaryKey(courseId);
		String newCourseId = AppUtils.uuid();
		courseInfo.setId(newCourseId);
		courseInfo.setCourseName(courseName);
		courseService.saveCourse(courseInfo.getId(), courseInfo, DConstants.COURSE_TYPE_ONLINE, userId, accountId,groupId);
		CourseModelOnline courseModelOnline = courseModelOnlineMapper.selectByPrimaryKey(courseId);
		courseModelOnline.setId(newCourseId);
		courseModelOnline.setCourseId(newCourseId);
		updateCourseModelOnline(courseInfo.getId(), courseModelOnline, userId, accountId);
		// 复制课程内容
		CourseOnlineItemExample courseOnlineItemExample = new CourseOnlineItemExample();
		courseOnlineItemExample.createCriteria().andCourseIdEqualTo(courseId);
		List<CourseOnlineItem> courseOnlineItems = courseOnlineItemMapper.selectByExample(courseOnlineItemExample);
		if (!courseOnlineItems.isEmpty()) {
			List<CourseOnlineItem> list = new LinkedList<CourseOnlineItem>();
			for (CourseOnlineItem item : courseOnlineItems) {
				item.setId(AppUtils.uuid());
				item.setCourseId(newCourseId);
				item.setUpdateId(userId);
				item.setUpdateTime(new Date());
				list.add(item);
			}
			courseModelOnlineCustomizeMapper.insertCourseOnlineItem(list);
		}
		// 复制标签
		tagsService.copyTag(courseId, newCourseId, userId);
		// 复制分类
		classificationService.copyClassification(courseId, newCourseId, userId);
		// 复制图片
		AttachmentExample attachmentExample = new AttachmentExample();
		attachmentExample.createCriteria().andRelIdEqualTo(courseId);
		List<Attachment> attachments = attachmentMapper.selectByExample(attachmentExample);
		
		if(!attachments.isEmpty()) {
			try {
				attachmentService.copyNewAttachement(courseId, newCourseId, userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Attachment att  = attachments.get(0);
//		
//			//获取物理路径 /222/22/22/5/xxx.jpg
//			String filePath =  CommonUtils.IMGS_LOCAL_DIR + att.getAttachDir() + att.getGenerateName();
//			
//			//生成复制图片新路径
//			String absDir = uploadService.generateDir(String.valueOf(Constants.NUMBER_FIVE));
//		    String absUrlStr  = StringUtils.replace(absDir.toString(),DConstants.STRING_BACKSLASH,DConstants.STRING_SLASH);
//		    String toFileFolder = CommonUtils.IMGS_LOCAL_DIR + File.separator + absDir;
//		    String[]  generateName = uploadService.generateFileName(att.getGenerateName());
//		    String output =  toFileFolder + generateName[0];
//		    FileUtils.copy(filePath, output);
//		    att.setId(AppUtils.uuid());
//		    att.setRelId(newCourseId);
//		    att.setGenerateName(generateName[0]);
//			att.setAttachUrl(absUrlStr);
//			att.setAttachDir(absDir);
//		    att.setCreateId(userId);
//		    att.setCreateTime(new Date());
//		    att.setUpdateId(userId);
//		    att.setUpdateTime(new Date());
//			attachmentMapper.insert(att);
		}
	}

	/**
	 * 分享课程
	 */
	public int shareCourse(String courseId, String courseOwner, String userId) {

		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andIdEqualTo(courseId);
		Course course = new Course();
		course.setCourseOwner(courseOwner);
		course.setIsEdit(DConstants.ISNOT_EDIT);
		course.setUpdateId(userId);
		course.setUpdateTime(new Date());

		return courseMapper.updateByExampleSelective(course, courseExample);
	}

	/**
	 * 获取课程引用课件列表
	 */
	public PageData<Map<String, Object>> findQuoteCoursewareList(String courseId, PageRequest pageRequest) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseId", courseId);
		param.put("pagination", pageRequest);
		PageData<Map<String, Object>> page = new PageData<Map<String, Object>>();
		page.setList(courseModelOnlineCustomizeMapper.findQuoteCoursewareList(param));
		page.setPageRequest((PageRequest) param.get("pagination"));

		return page;
	}

	/**
	 * 通过课程ID更新课程表
	 */
	public int updateCourse(Course course) throws DeodioException {

		return courseMapper.updateByPrimaryKeySelective(course);
	}
	
	public List<Map<String,Object>> findCourseHomeworkQuiz(Map<String,Object> params){
		return courseCustomizeMapper.findCourseHomeworkQuiz(params);
	}

	/**
	 * 通过课程ID获取课程信息
	 */
	public Course queryCourse(String courseId) {

		return courseMapper.selectByPrimaryKey(courseId);
	}

	/**
	 * 通过课程名查询课程信息
	 */
	public List<Course> queryCourseByCourseName(String courseName, String accountId) {

		CourseExample courseExample = new CourseExample();
		if (StringUtils.isNotBlank(courseName)) {
			courseName = "%" + courseName + "%";
		}
		courseExample.createCriteria().andCourseNameLike(courseName).andAccountIdEqualTo(accountId);

		return courseMapper.selectByExample(courseExample);
	}
}
