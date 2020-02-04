package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.QuizItems;
import com.deodio.elearning.persistence.model.QuizSubject;

public class QuizSubjectBo extends QuizSubject {
	
	public QuizSubjectBo() {}

	private List<QuizItems> quizItems;

	private String quizKnowledgePointId;

	private Integer quizSubjectOrder;

	public String getQuizKnowledgePointId() {
		return quizKnowledgePointId;
	}

	public void setQuizKnowledgePointId(String quizKnowledgePointId) {
		this.quizKnowledgePointId = quizKnowledgePointId;
	}

	public List<QuizItems> getQuizItems() {
		return quizItems;
	}

	public void setQuizItems(List<QuizItems> quizItems) {
		this.quizItems = quizItems;
	}

	public Integer getQuizSubjectOrder() {
		return quizSubjectOrder;
	}

	public void setQuizSubjectOrder(Integer quizSubjectOrder) {
		this.quizSubjectOrder = quizSubjectOrder;
	}

}
