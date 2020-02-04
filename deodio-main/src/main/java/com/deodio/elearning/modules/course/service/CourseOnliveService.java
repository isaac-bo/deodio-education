package com.deodio.elearning.modules.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.constants.Constants;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CourseModelOnliveMapper;
import com.deodio.elearning.persistence.mapper.customize.CourseModelOnliveCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.CourseModelOnlive;
import com.deodio.elearning.persistence.model.CourseModelOnliveExample;
import com.deodio.elearning.persistence.model.CourseOnliveItem;
import com.deodio.elearning.persistence.model.customize.CourseModelOnliveBo;
import com.deodio.elearning.persistence.model.customize.CourseOnliveItemBo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class CourseOnliveService extends  BaseService{

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private ClassificationService classificationService;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private CourseModelOnliveCustomizeMapper courseModelOnliveCustomizeMapper;
	
	@Autowired
	private CourseModelOnliveMapper courseModelOnliveMapper;
	
	public String insertCourseOnliveInfo( String courseInfoJson,String attachId,String userId,String accountId,
			String groupId, String tagsJson, String classificationJson) throws DeodioException{
		Gson courseGson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();
		Course courseInfo = courseGson.fromJson(courseInfoJson, new TypeToken<Course>(){}.getType());
		
		Gson courseModelOnlineGson = new Gson();  
		CourseModelOnliveBo courseModelOnliveBo = courseModelOnlineGson.fromJson(courseInfoJson, new TypeToken<CourseModelOnliveBo>(){}.getType());
		
		String id = courseInfo.getId();
		if(StringUtils.isBlank(id)){
			id = AppUtils.uuid();
		}
		courseService.saveCourse(id, courseInfo, DConstants.COURSE_TYPE_ONLIVE, userId, accountId, groupId);
		updateCourseModelOnlive(id,courseModelOnliveBo,userId, accountId);
		tagsService.saveTagsItemsRel(tagsJson,accountId, userId, id, DConstants.TAGS_ITEMS_TYPE_COURSE_ONLIVE);
		classificationService.saveClassificationItemsRel(classificationJson, userId, id, DConstants.CLASSIFICATION_ITEMS_TYPE_COURSE_ONLIVE);
		
		//图片表更新
		if(StringUtils.isNotBlank(attachId)){
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
		
		return id;
	}
	
	/**
	 * 通过课程主键获取课程信息
	 * @param params	courseId 
	 * @return
	 */
	public Map<String,Object>  queryCourseOnliveProfile(Map<String,Object> params)throws DeodioException{
		Map<String,Object> courseOnlineInfo =  courseModelOnliveCustomizeMapper.queryCourseOnliveProfileById(params);
		return courseOnlineInfo;
	}
	
	/**
	 * 更新线上课程
	 * @param courseModelOnliveId
	 * @param courseModelOnlive
	 * @param userId
	 * @param accountId
	 */
	private void updateCourseModelOnlive(String courseId,CourseModelOnliveBo courseModelOnliveBo,String userId,String accountId) {
		String courseModelOnliveId = courseModelOnliveBo.getCourseOnliveId();
		CourseModelOnliveExample example = new CourseModelOnliveExample();
		example.createCriteria().andIdEqualTo(courseModelOnliveId);
		example.or().andCourseIdEqualTo(courseId);
		List<CourseModelOnlive> onlineList = courseModelOnliveMapper.selectByExample(example);
		if(onlineList.isEmpty()){
			courseModelOnliveBo.setId(AppUtils.uuid());
			courseModelOnliveBo.setCourseId(courseId);
			courseModelOnliveBo.setCreateId(userId);
			courseModelOnliveBo.setCreateTime(new Date());
			courseModelOnliveMapper.insertSelective(courseModelOnliveBo);
		}else{
			courseModelOnliveBo.setUpdateId(userId);
			courseModelOnliveBo.setUpdateTime(new Date());
			courseModelOnliveMapper.updateByExampleSelective(courseModelOnliveBo, example);
		}
	}
	
	
	/**
	 * 通过课程编号获取课程内容信息(content页面)
	 * @param params	courseId   accountId  stepNo
	 * @return
	 */
	public List<Map<String,Object>>  queryCourseOnliveContent(Map<String,Object> params)throws DeodioException{
		return courseModelOnliveCustomizeMapper.queryCourseOnliveItem(params);
	}
	
	/**
	 *  更新线下课程内容
	 * @param contentList   内容列表
	 * @param courseId		 课程标号
	 * @param userId		 用户编号
	 * @param accountId		账号编号
	 */
	public void saveCourseOnliveContentInfo(List<CourseOnliveItemBo> contentList,String userId,String accountId)throws DeodioException{
		
//		if(StringUtils.isBlank(courseId)){
//			throw new DeodioException("数据异常：课程编号为空！");
//		}
		Date now = new Date();
		List<CourseOnliveItem> insertList = new ArrayList<CourseOnliveItem>();
		List<CourseOnliveItem> updateList = new ArrayList<CourseOnliveItem>();
		List<CourseOnliveItem> delList = new ArrayList<CourseOnliveItem>();
		for(CourseOnliveItemBo item : contentList){
			
			String id = item.getId();
			int operateType = item.getOperateType();
			item.setAccountId(accountId);
			if(StringUtils.isBlank(id)){
				item.setCreateId(userId);
				item.setCreateTime(now);
//				item.setCourseId(courseId);
				item.setId(AppUtils.uuid());
				insertList.add(item);
			}else{
				if(Constants.COURSE_OPERATE_TYPE_DEL == operateType){
					delList.add(item);
				}else if((Constants.COURSE_OPERATE_TYPE_UPDATE == operateType)){
					item.setUpdateId(userId);
					item.setUpdateTime(now);
					updateList.add(item);
				}
			}
		}
		if(!insertList.isEmpty()){
			courseModelOnliveCustomizeMapper.insertCourseOnliveItemBatch(insertList);
		}
		if(!updateList.isEmpty()){
			courseModelOnliveCustomizeMapper.updateCourseOnliveItemBatch(updateList);
		}
		if(!delList.isEmpty()){
			courseModelOnliveCustomizeMapper.delCourseOnliveItemBatch(delList);
		}
	}
}
