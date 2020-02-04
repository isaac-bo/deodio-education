package com.deodio.elearning.modules.courseman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.elearning.modules.courseman.service.ICourseManageService;
import com.deodio.elearning.persistence.mapper.customize.CourseManageCustomizeMapper;
import com.deodio.elearning.persistence.model.customize.CourseDto;

@Service("courseManageServiceImpl")
public class CourseManageServiceImpl implements ICourseManageService {

	@Autowired 
	private CourseManageCustomizeMapper courseManageCustomizeMapper;
	
	@Override
	public PageData<CourseDto> queryCourseList(CourseDto courseDto) {
		PageData<CourseDto> pageData = new PageData<CourseDto>();
		pageData.setList(courseManageCustomizeMapper.findCourseList(courseDto));
		pageData.setPageRequest(courseDto.getPagination());
		
		return pageData;
	}

}
