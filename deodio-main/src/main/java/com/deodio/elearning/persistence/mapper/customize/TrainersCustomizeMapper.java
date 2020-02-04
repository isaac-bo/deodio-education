package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.Trainers;

public interface TrainersCustomizeMapper {
	
	public List<Map<String,Object>> getTrainerList(Map<String, Object> params);
	
	public List<Map<String,Object>> findTrainerList(Map<String, Object> params);
	
	public void delAllTrainers(List<Trainers> trainersList);
	
	
	/**
	 * 获取学员课程 考试、调查问卷及作业 列表
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getTrainerCourseRelate(Map<String,Object> params);
	
	//获取分享的讲师列表
	public List<Map<String, Object>> findLecturerList(Map<String, Object> param);
	
}
