package com.deodio.elearning.persistence.mapper.customize;

import java.util.Map;

public interface TraineeAppraiseCustomizeMapper {

	/**
	 * 获取学员课程平均分数
	 * @param params
	 * @return
	 */
	public Integer courseAvgScore(Map<String,Object>params);
}