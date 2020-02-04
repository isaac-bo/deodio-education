package com.deodio.elearning.modules.presentation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.elearning.persistence.mapper.PresentationSyncSlidesMapper;
import com.deodio.elearning.persistence.mapper.SlidesMapper;
import com.deodio.elearning.persistence.model.PresentationSyncSlides;
import com.deodio.elearning.persistence.model.Slides;

@Service
public class SlidesService {

	@Autowired
	private SlidesMapper slidesMapper;
	
	@Autowired
	private PresentationSyncSlidesMapper presentationSyncSlidesMapper;
	
	public int insertSlides(Slides slides){
		return slidesMapper.insertSelective(slides);
	} 
	
	public int insertSyncSlides(PresentationSyncSlides syncSlides){
		return presentationSyncSlidesMapper.insertSelective(syncSlides);
	}
}
