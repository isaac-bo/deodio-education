package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.customize.CourseDto;
/**
 * 
    * @ClassName: CourseManageCustomizeMapper  
    * @Description: 课程管理 
    * @author P0113869  
    * @date 2018年4月17日 上午10:28:50  
    *
 */
public interface CourseManageCustomizeMapper {
	/**
	 * 获取课程列表
	 */
	List<CourseDto> findCourseList(CourseDto courseDto);

}
