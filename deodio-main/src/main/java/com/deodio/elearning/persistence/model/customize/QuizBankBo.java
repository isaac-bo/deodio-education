package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.QuizBanks;

public class QuizBankBo extends QuizBanks {

	private String classificationName;
	
	private Integer subjectCount;
	
	public String getClassificationName() {
		return classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	public Integer getSubjectCount() {
		return subjectCount;
	}

	public void setSubjectCount(Integer subjectCount) {
		this.subjectCount = subjectCount;
	}
	
}
