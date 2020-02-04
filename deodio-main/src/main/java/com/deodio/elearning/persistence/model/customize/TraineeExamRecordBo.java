package com.deodio.elearning.persistence.model.customize;

import java.util.ArrayList;
import java.util.List;

import com.deodio.elearning.persistence.model.TraineeExamRecord;

/**
 * @author P0113869
 */
public class TraineeExamRecordBo extends TraineeExamRecord {

	private List<TraineeExamRecordSubjectBo> subjectList = new ArrayList<TraineeExamRecordSubjectBo>();

	public List<TraineeExamRecordSubjectBo> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<TraineeExamRecordSubjectBo> subjectList) {
		this.subjectList = subjectList;
	}
	
}
