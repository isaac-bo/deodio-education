package com.deodio.elearning.comparator;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.deodio.elearning.persistence.model.PresentationSyncQuizs;

public class SyncQuizsComparator implements Comparator<PresentationSyncQuizs> {

	@Override
	public int compare(PresentationSyncQuizs o1, PresentationSyncQuizs o2) {
		return o1.getQuizTime() - o2.getQuizTime();
	}

	
}
