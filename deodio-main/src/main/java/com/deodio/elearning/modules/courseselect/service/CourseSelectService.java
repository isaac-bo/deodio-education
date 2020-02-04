package com.deodio.elearning.modules.courseselect.service;

import java.util.Map;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.elearning.persistence.model.Course;
import com.deodio.elearning.persistence.model.customize.CourseDto;

/**
 * 
    * @ClassName: CourseSelectService  
    * @Description: 选课
    * @author P0113869  
    * @date 2018年3月29日 上午10:48:55  
    *
 */
public interface CourseSelectService {
	/**
	 * 
	 */
	PageData<Map<String,Object>> queryCourseList(Map<String,Object> params);
}