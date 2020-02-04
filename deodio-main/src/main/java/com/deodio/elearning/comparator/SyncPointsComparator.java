package com.deodio.elearning.comparator;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.deodio.elearning.persistence.model.PresentationSyncPoints;

public class SyncPointsComparator implements Comparator<PresentationSyncPoints> {

	@Override
	public int compare(PresentationSyncPoints o1, PresentationSyncPoints o2) {
		return o1.getPointTime() - o2.getPointTime();
	}

	
}
