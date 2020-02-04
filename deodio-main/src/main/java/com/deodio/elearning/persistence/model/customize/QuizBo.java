package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.Quiz;

public class QuizBo extends Quiz {

	private String userName;

	private String quizNum;

	private String score;

	private String tagName;

	private String attachId;

	private String attachUrl;

	private String generateName;

	private String groupId;

	private Integer isRequired;
	
	private Integer quoteCount;

	private List<QuizSubjectBo> subjectList;

	private List<Integer> subjectCount;
	
	private List<QuizBankBo> bankList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuizNum() {
		return quizNum;
	}

	public void setQuizNum(String quizNum) {
		this.quizNum = quizNum;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}

	public Integer getQuoteCount() {
		return quoteCount;
	}

	public void setQuoteCount(Integer quoteCount) {
		this.quoteCount = quoteCount;
	}

	public List<QuizSubjectBo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectArray(List<QuizSubjectBo> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Integer> getSubjectCount() {
		return subjectCount;
	}

	public void setSubjectCount(List<Integer> subjectCount) {
		this.subjectCount = subjectCount;
	}

	public List<QuizBankBo> getBankList() {
		return bankList;
	}

	public void setBankList(List<QuizBankBo> bankList) {
		this.bankList = bankList;
	}

}
