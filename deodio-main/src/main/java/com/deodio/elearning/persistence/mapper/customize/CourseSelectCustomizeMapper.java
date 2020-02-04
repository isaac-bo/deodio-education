package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

public interface CourseSelectCustomizeMapper {
	/**
	 * 
	 * @Title: findCourseSelectList   
	 * @Description: TODO  
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> findCourseSelectList(Map<String,Object> params);

}
