package com.deodio.elearning.modules.presentation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.elearning.persistence.mapper.PresentationSyncRecordsMapper;
import com.deodio.elearning.persistence.model.PresentationSyncMedia;
import com.deodio.elearning.persistence.model.PresentationSyncPoints;
import com.deodio.elearning.persistence.model.PresentationSyncRecords;
import com.deodio.elearning.persistence.model.PresentationSyncRecordsExample;
import com.deodio.elearning.persistence.model.customize.PresentationSyncRecordsBo;

@Service
public class PresentationRecordService extends PresentationService{

	@Autowired
	private PresentationSyncRecordsMapper presentationSyncRecordsMapper;
	
	public List<PresentationSyncRecords> getPresentationSyncRecords(String presentationId){
		PresentationSyncRecordsExample example = new PresentationSyncRecordsExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		example.setOrderByClause("record_order");
		return presentationSyncRecordsMapper.selectByExample(example);
	}
	
	public List<PresentationSyncRecordsBo> getPresentationSyncRecordsBo(String presentationId){
		int clipsLength = calcClipsLength(presentationId);
		List<PresentationSyncPoints> presentationSyncPoints = getPointsByPresentationId(presentationId);
		List<PresentationSyncRecords> presentationSyncRecords = getPresentationSyncRecords(presentationId);
		List<PresentationSyncRecordsBo> presentationSyncRecordsBoList = new ArrayList<PresentationSyncRecordsBo>();
		for(PresentationSyncPoints syncPoints : presentationSyncPoints){
			List<PresentationSyncRecords> syncRecords = new ArrayList<PresentationSyncRecords>();
			PresentationSyncRecordsBo syncRecordsBo = new PresentationSyncRecordsBo();
			BeanUtils.copyProperties(syncPoints, syncRecordsBo);
			syncRecordsBo
					.setPointEnd(presentationSyncPoints.indexOf(syncPoints) < presentationSyncPoints
							.size() - 1 ? (presentationSyncPoints
							.get(presentationSyncPoints.indexOf(syncPoints) + 1))
							.getPointTime()
							: clipsLength);
			for(PresentationSyncRecords syncRecord : presentationSyncRecords){
				if(syncRecord.getPointId().equals(syncPoints.getId())){
					syncRecords.add(syncRecord);
				}
			}
			
			syncRecordsBo.setSyncRecords(syncRecords);
			presentationSyncRecordsBoList.add(syncRecordsBo);
		}
		
		return presentationSyncRecordsBoList;
	}

	private int calcClipsLength(String presentationId) {
		int clipsLength = 0;
		List<PresentationSyncMedia> presentationSyncMedia = getMediasByPresentationId(presentationId);
		for(PresentationSyncMedia syncMedias : presentationSyncMedia){
			clipsLength += syncMedias.getMediaEnd() - syncMedias.getMediaStart();
		}
		return clipsLength;
	}
}
