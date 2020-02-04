package com.deodio.elearning.persistence.model.customize;

import java.util.ArrayList;
import java.util.List;

import com.deodio.elearning.persistence.model.TraineeExamRecordItem;
import com.deodio.elearning.persistence.model.TraineeExamRecordSubject;

/**
 * @author P0113869
 */
public class TraineeExamRecordSubjectBo extends TraineeExamRecordSubject {

	private List<TraineeExamRecordItem> itemsList = new ArrayList<TraineeExamRecordItem>();

	public List<TraineeExamRecordItem> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<TraineeExamRecordItem> itemsList) {
		this.itemsList = itemsList;
	}
	
}
