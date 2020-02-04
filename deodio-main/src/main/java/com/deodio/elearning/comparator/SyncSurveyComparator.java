package com.deodio.elearning.comparator;

import java.util.Comparator;


import com.deodio.elearning.persistence.model.PresentationSyncSurvey;

public class SyncSurveyComparator implements Comparator<PresentationSyncSurvey> {

	@Override
	public int compare(PresentationSyncSurvey o1, PresentationSyncSurvey o2) {
		
		return o1.getSurveyTime() - o2.getSurveyTime();
	}

}
