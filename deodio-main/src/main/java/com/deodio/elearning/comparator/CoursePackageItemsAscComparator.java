package com.deodio.elearning.comparator;

import java.util.Comparator;

import com.deodio.elearning.persistence.model.customize.CoursePackageItemsBo;

public class CoursePackageItemsAscComparator implements Comparator<CoursePackageItemsBo> {

	@Override
	public int compare(CoursePackageItemsBo o1, CoursePackageItemsBo o2) {
		
//		String packageId1 = o1.getPackageId();
//		String packageId2 = o2.getPackageId();
//		
//		String seriesId1 = o1.getId();
//		String seriesId2 = o2.getId();
//		
//		String itemId1 = o1.getItemId();
//		String itemId2 = o2.getItemId();
		
		Short seriesOrder1 = o1.getSeriesOrder();
		Short seriesOrder2 = o2.getSeriesOrder();
		
		Integer itemOrder1 = o1.getItemOrder();
		Integer itemOrder2 = o2.getItemOrder();
		
		
		int flg = seriesOrder1 - seriesOrder2;
        if (flg == 0){  
            flg = itemOrder1 - itemOrder2;
        }  
        return flg;  
		
	}

	

}
