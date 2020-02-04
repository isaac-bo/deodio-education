package com.deodio.elearning.modules.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.deodio.core.constants.Constants;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CourseMapper;
import com.deodio.elearning.persistence.mapper.CourseModelOfflineMapper;
import com.deodio.elearning.persistence.mapper.CourseOfflineItemMapper;
import com.deodio.elearning.persistence.mapper.CourseRegisteRuleMapper;
import com.deodio.elearning.persistence.mapper.CourseUserRelMapper;
import com.deodio.elearning.persistence.mapper.UserAccountRelMapper;
import com.deodio.elearning.persistence.mapper.UserModelMapper;
import com.deodio.elearning.persistence.mapper.customize.CourseModelOfflineCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseExample;
import com.deodio.elearning.persistence.model.CourseModelOffline;
import com.deodio.elearning.persistence.model.CourseModelOfflineExample;
import com.deodio.elearning.persistence.model.CourseOfflineItem;
import com.deodio.elearning.persistence.model.CourseOfflineItemExample;
import com.deodio.elearning.persistence.model.CourseRegisteRule;
import com.deodio.elearning.persistence.model.CourseRegisteRuleExample;
import com.deodio.elearning.persistence.model.CourseUserRel;
import com.deodio.elearning.persistence.model.CourseUserRelExample;
import com.deodio.elearning.persistence.model.UserAccountRel;
import com.deodio.elearning.persistence.model.UserAccountRelExample;
import com.deodio.elearning.persistence.model.UserModel;
import com.deodio.elearning.persistence.model.customize.CourseModelOfflineBo;
import com.deodio.elearning.persistence.model.customize.CourseOfflineItemBo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author p0085398
 *
 */
@Service
public class CourseOfflineService extends BaseService {
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CourseModelOfflineMapper courseModelOfflineMapper;

	@Autowired
	private CourseModelOfflineCustomizeMapper courseModelOfflineCustomizeMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private CourseService courseService;

	@Autowired
	private TagsService tagsService;

	@Autowired
	private ClassificationService classificationService;

	@Autowired
	private CourseUserRelMapper courseUserRelMapper;
	@Autowired
	private CourseOfflineItemMapper courseOfflineItemMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private UserAccountRelMapper userAccountRelMapper;
	@Autowired
	private UserModelMapper userModelMapper;
	@Autowired
	private CourseRegisteRuleMapper courseRegisteRuleMapper;

	/**
	 * 插入线下课程
	 * 
	 * @param courseInfo
	 * @param attachId
	 * @param userId
	 * @param accountId
	 * @param tagsList
	 * @param classificationsRelList
	 * @throws DeodioException
	 */
	public String insertCourseOfflineInfo(String courseInfoJson, String attachId, String userId, String accountId,
			String groupId, String tagsList, String classificationsRelList) throws DeodioException {

		Gson courseGson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		;
		Course courseInfo = courseGson.fromJson(courseInfoJson, new TypeToken<Course>() {
		}.getType());

		Gson courseModelOfflineGson = new Gson();
		CourseModelOfflineBo courseModelOfflineBo = courseModelOfflineGson.fromJson(courseInfoJson,
				new TypeToken<CourseModelOfflineBo>() {
				}.getType());

		String id = courseInfo.getId();
		if (StringUtils.isBlank(id)) {
			id = AppUtils.uuid();
		}
		courseService.saveCourse(id, courseInfo, DConstants.COURSE_TYPE_OFFLINE, userId, accountId, groupId);
		updateCourseModelOffline(id, courseModelOfflineBo, userId, accountId);
		tagsService.saveTagsItemsRel(tagsList, accountId, userId, id, DConstants.TAGS_ITEMS_TYPE_COURSE_OFFLINE);
		classificationService.saveClassificationItemsRel(classificationsRelList, userId, id,
				DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_OFFLINE);

		// 图片表更新
		if (StringUtils.isNotBlank(attachId)) {
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}

		return id;
	}

	/**
	 * 更新线下课程内容
	 * 
	 * @param contentList
	 *            内容列表
	 * @param courseId
	 *            课程标号
	 * @param userId
	 *            用户编号
	 * @param accountId
	 *            账号编号
	 */
	public void updateCourseOfflineContentInfo(List<CourseOfflineItemBo> contentList, String userId, String accountId)
			throws DeodioException {

		// if(StringUtils.isBlank(courseId)){
		// throw new DeodioException("数据异常：课程编号为空！");
		// }
		Date now = new Date();
		List<CourseOfflineItem> insertList = new ArrayList<CourseOfflineItem>();
		List<CourseOfflineItem> updateList = new ArrayList<CourseOfflineItem>();
		List<CourseOfflineItem> delList = new ArrayList<CourseOfflineItem>();
		for (CourseOfflineItemBo item : contentList) {

			String id = item.getId();
			int operateType = item.getOperateType();
			item.setAccountId(accountId);
			if (StringUtils.isBlank(id)) {
				item.setCreateId(userId);
				item.setCreateTime(now);
				// item.setCourseId(courseId);
				item.setId(AppUtils.uuid());
				insertList.add(item);
			} else {

				if (Constants.COURSE_OPERATE_TYPE_DEL == operateType) {
					delList.add(item);
				} else if ((Constants.COURSE_OPERATE_TYPE_UPDATE == operateType)) {
					item.setUpdateId(userId);
					item.setUpdateTime(now);
					updateList.add(item);
				}
			}
		}

		if (!insertList.isEmpty()) {
			courseModelOfflineCustomizeMapper.insertCourseOfflineItemBatch(insertList);
		}

		if (!updateList.isEmpty()) {
			courseModelOfflineCustomizeMapper.updateCourseOfflineItemBatch(updateList);
		}

		if (!delList.isEmpty()) {
			courseModelOfflineCustomizeMapper.delCourseOfflineItemBatch(delList);
		}
	}

	/**
	 * 通过课程主键获取课程信息
	 * 
	 * @param params
	 *            courseId
	 * @return
	 */
	public Map<String, Object> queryCourseOfflineInfo(Map<String, Object> params) throws DeodioException {
		Map<String, Object> courseOnlineInfo = courseModelOfflineCustomizeMapper.queryCourseOfflineInfo(params);
		return courseOnlineInfo;
	}

	public int queryCourseOfflineContentTraineeSum(Map<String, Object> params) throws DeodioException {
		int sumTrainee = courseModelOfflineCustomizeMapper.queryCourseOfflineContentTraineeSum(params);
		return sumTrainee;
	}

	/**
	 * 通过课程主键获取课程信息(profile页面)
	 * 
	 * @param params
	 *            courseId
	 * @return
	 */
	public Map<String, Object> queryCourseOfflineProfile(Map<String, Object> params) throws DeodioException {
		Map<String, Object> courseOnlineInfo = courseModelOfflineCustomizeMapper.queryCourseOfflineProfile(params);
		return courseOnlineInfo;
	}

	/**
	 * 通过课程编号获取课程内容信息(content页面)
	 * 
	 * @param params
	 *            courseId accountId stepNo
	 * @return
	 */
	public List<Map<String, Object>> queryCourseOfflineContent(Map<String, Object> params) throws DeodioException {
		return courseModelOfflineCustomizeMapper.queryCourseOfflineItem(params);
	}

	/**
	 * 查询线下课程培训人员信息
	 * 
	 * @param params
	 * @return
	 * @throws DeodioException
	 */
	public List<Map<String, Object>> findCourseOfflineTrainees(Map<String, Object> params) throws DeodioException {
		return courseModelOfflineCustomizeMapper.findCourseOfflineTrainees(params);
	}

	/**
	 * 更新线下课程
	 * 
	 * @param courseModelOnlineId
	 * @param courseModelOnline
	 * @param userId
	 * @param accountId
	 */
	private void updateCourseModelOffline(String courseModelOfflineId, CourseModelOffline courseModelOffline,
			String userId, String accountId) {

		CourseModelOfflineExample example = new CourseModelOfflineExample();
		example.createCriteria().andIdEqualTo(courseModelOfflineId);

		List<CourseModelOffline> offlineList = courseModelOfflineMapper.selectByExample(example);
		if (offlineList.isEmpty()) {
			courseModelOffline.setId(courseModelOfflineId);
			courseModelOffline.setCourseId(courseModelOfflineId);
			courseModelOffline.setCreateId(userId);
			courseModelOffline.setCreateTime(new Date());
			courseModelOfflineMapper.insertSelective(courseModelOffline);
		} else {
			courseModelOffline.setUpdateId(userId);
			courseModelOffline.setUpdateTime(new Date());
			courseModelOfflineMapper.updateByExampleSelective(courseModelOffline, example);
		}
	}

	/**
	 * @param courseId
	 * @param courseName
	 * @throws Exception
	 */
	public void copyCourseOfflineInfo(String courseId, String courseName, String userId) throws Exception {
		String id = AppUtils.uuid();
		courseService.copyCourse(courseId, id, courseName, userId);
		courseService.copyCourseModelOffline(courseId, id);
		copyAttachment(courseId, id, userId);
		tagsService.copyTagItemRel(courseId, id);
		classificationService.copyClassificationItemsRel(courseId, id);
		copyCourseOfflineItem(courseId, id);
		copyCourseRegisterRule(courseId, id);
	}

	/**
	 * @param courseId
	 *            复制线下课程明细
	 */
	public void copyCourseOfflineItem(String courseId, String id) {
		CourseOfflineItemExample example = new CourseOfflineItemExample();
		example.createCriteria().andCourseIdEqualTo(courseId);
		List<CourseOfflineItem> list = courseOfflineItemMapper.selectByExample(example);
		List<CourseOfflineItem> insertList = new ArrayList<CourseOfflineItem>();
		for (CourseOfflineItem item : list) {
			item.setId(AppUtils.uuid());
			item.setCourseId(id);
			item.setCreateTime(new Date());
			insertList.add(item);
		}
		courseModelOfflineCustomizeMapper.insertCourseOfflineItemBatch(insertList);
	}

	public void copyAttachment(String oldId, String newId, String userId) throws Exception {
		attachmentService.copyNewAttachement(oldId, newId, userId);
	}

	public void copyCourseRegisterRule(String courseId, String id) {
		CourseRegisteRuleExample example = new CourseRegisteRuleExample();
		example.createCriteria().andCourseIdEqualTo(courseId);
		List<CourseRegisteRule> list = courseRegisteRuleMapper.selectByExample(example);
		if (!list.isEmpty()) {
			CourseRegisteRule record = list.get(0);
			record.setId(AppUtils.uuid());
			record.setCourseId(id);
			record.setCreateTime(new Date());
			courseRegisteRuleMapper.insertSelective(record);
		}
	}

	/**
	 * 批量导入报名人员
	 * 
	 * @param courseUser
	 * @param courseId
	 * @param itemId
	 * @throws DeodioException
	 */
	public void importDateCourseUser(String courseUser, String courseId, String itemId, String userId)
			throws DeodioException {

		String[] items = courseUser.split(DConstants.DELIMITER_ROW);
		for (int index = 0; index < items.length; index++) {
			if (StringUtils.isNotBlank(items[index])) {
				String item[] = items[index].split(DConstants.DELIMITER_DATA);
				for (int jndex = 0; jndex < item.length; jndex++) {
					// 0-nickName 1-userName 2-mail //已存在用户更新覆盖
					CourseUserRel record = getValidateEmailAndCouserIdExists(item[1], courseId, itemId);
					Boolean isUpdate = true;
					if (record == null) {
						record = new CourseUserRel();
						record.setId(AppUtils.uuid());
						record.setCreateId(userId);
						record.setCreateTime(new Date());
						isUpdate = false;
						record.setUserStatus(Short.valueOf("3"));
						record.setRegisteType(Short.valueOf("2"));
					}

					record.setCourseId(courseId);
					record.setItemId(itemId);
					record.setUpdateTime(new Date());
					record.setUserMail(item[1]);

					if (isUpdate) {
						courseUserRelMapper.updateByPrimaryKeySelective(record);
					} else {
						courseUserRelMapper.insertSelective(record);
					}
				}
			}
		}
	}

	/**
	 * 是否报名
	 * 
	 * @Title: getValidateEmailAndCouserIdExists
	 * @param mail
	 * @return
	 * @return boolean
	 */
	public CourseUserRel getValidateEmailAndCouserIdExists(String mail, String couserId, String itemId)
			throws DeodioException {

		CourseUserRelExample example = new CourseUserRelExample();
		example.createCriteria().andUserMailEqualTo(mail).andCourseIdEqualTo(couserId).andItemIdEqualTo(itemId);
		List<CourseUserRel> list = courseUserRelMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * @param params
	 * @return
	 * @throws DeodioException
	 *             查询课程内容
	 */
	public Map<String, Object> queryCourseOfflineItemInfo(Map<String, Object> params) throws DeodioException {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("courseId", params.get("courseId"));
		Map<String, Object> courseContent = new HashMap<String, Object>();
		List<Map<String, Object>> courseOnlineInfoList = courseModelOfflineCustomizeMapper.getCourseTrainTime(map1);
		String startTime = "", expireTime = "";
		if (null != courseOnlineInfoList && courseOnlineInfoList.size() > 0) {
			Map<String, Object> map = courseOnlineInfoList.get(0);
			startTime = map.get("start_time").toString();
			expireTime = (String) map.get("expire_time").toString();
		}
		courseContent.put("locationName", courseModelOfflineCustomizeMapper.getCourseTrainLocation(map1));
		courseContent.put("startTime", StringUtils.isNotBlank(startTime) ? startTime.substring(0, 10) : null);
		courseContent.put("expireTime", StringUtils.isNotBlank(expireTime) ? expireTime.substring(0, 10) : null);
		return courseContent;
	}

	/**
	 * 分享线下课程
	 */
	public int shareCourse(String courseId, String courseOwner, String userId) {

		CourseExample courseExample = new CourseExample();
		courseExample.createCriteria().andIdEqualTo(courseId);
		Course course = new Course();
		course.setCourseOwner(courseOwner);
		course.setIsEdit(DConstants.ISNOT_EDIT);
		course.setUpdateId(userId);
		course.setUpdateTime(new Date());
		System.err.println(getClass().getName() + "\tcourseId:\t" + courseId + "\tcourseOwner:\t" + courseOwner
				+ "\tuserId:\t" + userId);
		return courseMapper.updateByExampleSelective(course, courseExample);
	}

	public int selectCourseOfflineItemCount(String courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("courseId", courseId);
		return courseModelOfflineCustomizeMapper.getCourseOfflineItemCount(params);
	}

	/**
	 * 通过课程ID更新课程表
	 */
	public int updateCourse(String courseId) throws DeodioException {
		Course course = new Course();
		course.setId(courseId);
		course.setIsEdit(DConstants.IS_EDIT);
		return courseMapper.updateByPrimaryKeySelective(course);
	}

	public int selectSubstitudeNum(String courseId, String itemIdValue, String userStatus) {
		CourseUserRelExample userRelExample = new CourseUserRelExample();
		userRelExample.createCriteria().andCourseIdEqualTo(courseId).andItemIdEqualTo(itemIdValue)
				.andUserStatusEqualTo(new Short(userStatus));
		return courseUserRelMapper.countByExample(userRelExample);
	}

	public void insertUserAccount(String userName, String userMail, String accountId, String createId) {
		String id = AppUtils.uuid();
		UserModel record = new UserModel();
		record.setId(id);
		record.setUserName(userName);
		record.setUserMail(userMail);
		record.setPassWord(DigestUtils.md5DigestAsHex(DConstants.DEFAULT_PASS_WORD.getBytes()));
		record.setRegisterType(DConstants.USE_REGISTER_TYPE_OTHERS);
		userModelMapper.insert(record);
		if (this.getExsistAccountRelByUserId(id, accountId)) {
			UserAccountRel record2 = new UserAccountRel();
			record2.setId(AppUtils.uuid());
			record2.setAccountId(accountId);
			record2.setUserId(id);
			record2.setCreateTime(new Date());
			record2.setCreateId(createId);
			userAccountRelMapper.insertSelective(record2);
		}
	}

	/**
	 * 判断当前account 中是否包含此用户
	 * 
	 * @Title: getExsistAccountRelByUserId
	 * @param userId
	 * @param accountId
	 * @return
	 * @throws DeodioException
	 * @return boolean
	 */
	private boolean getExsistAccountRelByUserId(String userId, String accountId) throws DeodioException {
		UserAccountRelExample example = new UserAccountRelExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andUserIdEqualTo(userId);
		return userAccountRelMapper.selectByExample(example).isEmpty();

	}
	public boolean checkEmail(String email) {
		String format = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		if (email.matches(format)) {
			return true;
		} else {
			return false;// 邮箱名不合法，返回false
		}
	}
}
