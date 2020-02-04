package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.customize.QuizBankBo;

public interface QuizBanksCustomizeMapper {
	
	public List<QuizBankBo> findQuizBankList(Map<String,Object> param);
}
