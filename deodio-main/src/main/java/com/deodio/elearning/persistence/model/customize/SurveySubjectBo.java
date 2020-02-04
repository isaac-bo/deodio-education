package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.SurveyItems;
import com.deodio.elearning.persistence.model.SurveySubject;

public class SurveySubjectBo extends SurveySubject {

	private List<SurveyItems> surveyItemList;
	
	private String itemOptions;

	public List<SurveyItems> getSurveyItemList() {
		return surveyItemList;
	}

	public void setSurveyItemList(List<SurveyItems> surveyItemList) {
		this.surveyItemList = surveyItemList;
	}

	public String getItemOptions() {
		return itemOptions;
	}

	public void setItem_options(String itemOptions) {
		this.itemOptions = itemOptions;
	}
	
}
