package com.deodio.elearning.modules.courseman.service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.elearning.persistence.model.customize.CourseDto;

public interface ICourseManageService {
	/**
	 * TODO
	 */
	PageData<CourseDto> queryCourseList(CourseDto courseDto);
}
