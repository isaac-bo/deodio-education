package com.deodio.elearning.persistence.model.customize;

import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.persistence.model.Course;

public class CourseBo extends Course {

	private String courseDescWithoutTags;

	public String getCourseDescWithoutTags() {
		return StringUtils.removeHtmlTags(this.getCourseDescription());
	}

	public void setCourseDescWithoutTags(String courseDescWithoutTags) {
		this.courseDescWithoutTags = courseDescWithoutTags;
	}
}
