package com.deodio.elearning.commons.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.elearning.persistence.mapper.MediaMapper;
import com.deodio.elearning.persistence.mapper.PresentationSyncMediaMapper;
import com.deodio.elearning.persistence.model.Media;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;

@Service("mediaService")
public class MediaService {
	

	@Autowired
	private MediaMapper mediaMapper;
	
	@Autowired
	private PresentationSyncMediaMapper presentationSyncMediaMapper;

	public Media getMedia(String mediaId){
		return mediaMapper.selectByPrimaryKey(mediaId);
	}
	
	public int saveMedia(Media media){
		return mediaMapper.insertSelective(media);
	}
	
	public int updateMediaById(Media media){
		return mediaMapper.updateByPrimaryKeySelective(media);
	}
	
	public int savePresentationSyncMedia(PresentationSyncMedia presentationSyncMedia){
		return presentationSyncMediaMapper.insertSelective(presentationSyncMedia);
	}

}
