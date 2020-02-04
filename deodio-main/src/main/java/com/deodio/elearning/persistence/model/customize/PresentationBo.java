package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationModelSync;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncPoints;
import com.deodio.elearning.persistence.model.PresentationSyncQuizs;
import com.deodio.elearning.persistence.model.PresentationSyncSlides;
import com.deodio.elearning.persistence.model.PresentationSyncSurvey;

public class PresentationBo extends Presentation{
	
	private String attachUrl;
	private String generateName;
	
	
	
	private List<PresentationSyncMedia> medias;
	private List<PresentationSyncPoints> points;
	private List<PresentationSyncSlides> slides;
	private List<PresentationSyncQuizs> quizs;
	private List<PresentationSyncSurvey> survey;
	private PresentationModelSync  modelSync;
	
	public PresentationModelSync getModelSync() {
		return modelSync;
	}
	public void setModelSync(PresentationModelSync modelSync) {
		this.modelSync = modelSync;
	}
	public List<PresentationSyncSurvey> getSurvey() {
		return survey;
	}
	public void setSurvey(List<PresentationSyncSurvey> survey) {
		this.survey = survey;
	}
	public List<PresentationSyncQuizs> getQuizs() {
		return quizs;
	}
	public void setQuizs(List<PresentationSyncQuizs> quizs) {
		this.quizs = quizs;
	}
	public List<PresentationSyncMedia> getMedias() {
		return medias;
	}
	public void setMedias(List<PresentationSyncMedia> medias) {
		this.medias = medias;
	}
	public List<PresentationSyncPoints> getPoints() {
		return points;
	}
	public void setPoints(List<PresentationSyncPoints> points) {
		this.points = points;
	}
	public List<PresentationSyncSlides> getSlides() {
		return slides;
	}
	public void setSlides(List<PresentationSyncSlides> slides) {
		this.slides = slides;
	}
	public String getAttachUrl() {
		return attachUrl;
	}
	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}
	public String getGenerateName() {
		return generateName;
	}
	public void setGenerateName(String generateName) {
		this.generateName = generateName;
	}
	
}
