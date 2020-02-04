package com.deodio.elearning.comparator;

import java.util.Comparator;

import com.deodio.elearning.persistence.model.customize.CoursePackageSeriesBo;

public class CoursePackageSeriesAscComparator implements Comparator<CoursePackageSeriesBo> {

	@Override
	public int compare(CoursePackageSeriesBo o1, CoursePackageSeriesBo o2) {
		
		String packageId1 = o1.getPackageId();
		String packageId2 = o2.getPackageId();
		
//		String seriesId1 = o1.getId();
//		String seriesId2 = o2.getId();
		
		Short seriesOrder1 = o1.getSeriesOrder();
		Short seriesOrder2 = o2.getSeriesOrder();
		
		int flag = packageId1.compareTo(packageId2);
		if(flag == 0){
			flag = seriesOrder1 - seriesOrder2;
		}
		
		return flag;
		
	}

	

}
