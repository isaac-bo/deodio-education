package com.deodio.elearning.modules.courseselect.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.service.BaseService;
import com.deodio.elearning.modules.courseselect.service.CourseSelectService;
import com.deodio.elearning.persistence.mapper.customize.CourseSelectCustomizeMapper;
/**
 * 
    * @ClassName: CourseSelectServiceImpl  
    * @Description: 选课
    * @author P0113869  
    * @date 2018年3月29日 上午10:48:10  
    *
 */
@Service
public class CourseSelectServiceImpl implements CourseSelectService {

	@Autowired 
	private CourseSelectCustomizeMapper courseSelectCustomizeMapper;
	
	@Override
	public PageData<Map<String,Object>> queryCourseList(Map<String,Object> params) {
		PageData<Map<String,Object>> pageData = new PageData<Map<String,Object>>();
		pageData.setList(courseSelectCustomizeMapper.findCourseSelectList(params));
		pageData.setPageRequest((PageRequest)params.get("pagination"));
		return pageData;
	}
	
}