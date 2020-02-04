package com.deodio.elearning.persistence.model.customize;

import java.util.List;

import com.deodio.elearning.persistence.model.PresentationSyncPoints;
import com.deodio.elearning.persistence.model.PresentationSyncRecords;

public class PresentationSyncRecordsBo extends PresentationSyncPoints{

	private List<PresentationSyncRecords> syncRecords;

	public List<PresentationSyncRecords> getSyncRecords() {
		return syncRecords;
	}

	public void setSyncRecords(List<PresentationSyncRecords> syncRecords) {
		this.syncRecords = syncRecords;
	}
}
