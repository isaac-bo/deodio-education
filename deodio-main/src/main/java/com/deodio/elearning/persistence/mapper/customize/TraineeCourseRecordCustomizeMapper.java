package com.deodio.elearning.persistence.mapper.customize;

import com.deodio.elearning.persistence.model.TraineeCourseRecord;

public interface TraineeCourseRecordCustomizeMapper {
	/*更新未完成课程章节数据（在次学习）*/
	public Integer updateUncompleteCourseItemLearnInfo(TraineeCourseRecord record);
	
	/*获取指定条件的课程章节学习记录*/
	public TraineeCourseRecord queryCourseItemLearnInfo(TraineeCourseRecord record);
}