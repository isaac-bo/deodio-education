package com.deodio.elearning.modules.presentation.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.persistence.mapper.PresentationExternalItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelExternalMapper;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationExternalItem;
import com.deodio.elearning.persistence.model.PresentationExternalItemExample;
import com.deodio.elearning.persistence.model.PresentationModelExternal;
import com.deodio.elearning.persistence.model.PresentationModelExternalExample;

@Service
public class PresentationExternalService extends PresentationService{

	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private PresentationModelExternalMapper presentationModelExternalMapper;
	
	@Autowired
	private PresentationExternalItemMapper presentationExternalItemMapper;
	
	public void saveExternalInfo(String presentationId, Integer isCourse,
						String userId, Integer externalType, String externalUrl,String externalVideoUrl) {
		
		Presentation presentation = presentationMapper.selectByPrimaryKey(presentationId);
		presentation.setIsCourse(isCourse.shortValue());
		presentation.setUpdateId(userId);
		presentation.setUpdateTime(new Date());
		presentationMapper.updateByPrimaryKeySelective(presentation);
		
		PresentationModelExternalExample presentationModelExternalExample = new PresentationModelExternalExample();
		presentationModelExternalExample.createCriteria().andPresentationIdEqualTo(presentationId);
		presentationModelExternalMapper.deleteByExample(presentationModelExternalExample);
		
		PresentationModelExternal presentationModelExternal = new PresentationModelExternal();
		presentationModelExternal.setId(AppUtils.uuid());
		presentationModelExternal.setPresentationId(presentationId);
		presentationModelExternal.setExternalType(externalType.shortValue());
		presentationModelExternal.setCreateId(userId);
		presentationModelExternal.setCreateTime(new Date());
		presentationModelExternal.setUpdateId(userId);
		presentationModelExternal.setUpdateTime(new Date());
		presentationModelExternalMapper.insertSelective(presentationModelExternal);
		
		PresentationExternalItemExample presentationExternalItemExample = new PresentationExternalItemExample();
		presentationExternalItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
		presentationExternalItemMapper.deleteByExample(presentationExternalItemExample);
		
		PresentationExternalItem presentationExternalItem = new PresentationExternalItem();
		presentationExternalItem.setId(AppUtils.uuid());
		presentationExternalItem.setPresentationId(presentationId);
		presentationExternalItem.setCreateId(userId);
		presentationExternalItem.setCreateTime(new Date());
		presentationExternalItem.setUpdateId(userId);
		presentationExternalItem.setUpdateTime(new Date());
		presentationExternalItem.setExternalUrl(externalUrl);
		presentationExternalItem.setExternalVideoUrl(externalVideoUrl);
		presentationExternalItemMapper.insertSelective(presentationExternalItem);
		
		
	}

}
