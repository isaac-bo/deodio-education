package com.deodio.elearning.modules.knowledge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.ClassificationItemsRelMapper;
import com.deodio.elearning.persistence.mapper.ClassificationMapper;
import com.deodio.elearning.persistence.mapper.KnowledgePointsMapper;
import com.deodio.elearning.persistence.mapper.customize.KnowledgeCustomizeMapper;
import com.deodio.elearning.persistence.model.Classification;
import com.deodio.elearning.persistence.model.ClassificationExample;
import com.deodio.elearning.persistence.model.ClassificationItemsRel;
import com.deodio.elearning.persistence.model.ClassificationItemsRelExample;
import com.deodio.elearning.persistence.model.KnowledgePoints;
import com.deodio.elearning.persistence.model.KnowledgePointsExample;

@Service
public class KnowledgeService {

	@Autowired
	KnowledgeCustomizeMapper knowledgeCustomizeMapper;

	@Autowired
	KnowledgePointsMapper knowledgePointsMapper;
	
	@Autowired
	ClassificationMapper classificationMapper;
	
	@Autowired
	ClassificationItemsRelMapper classificationItemsRelMapper;

	public List<Map<String, Object>> findKnowledgeList(String classificationId, String keywords,
			PageRequest pageRequest, String accountId, String userId) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		params.put("classificationId", classificationId);
		params.put("keywords", keywords);
		params.put("pagination", pageRequest);
		return knowledgeCustomizeMapper.findKnowledgeList(params);
	}

	public void submitKnowledgePoint(String knowledgePointId, String knowledgePointName, String knowledgePointDesc,Date updateTime,
			String classificationIds, String accountId, String userId) throws DeodioException {

		KnowledgePoints knowledgePoints = new KnowledgePoints();
		if (StringUtils.isEmpty(knowledgePointId)) {
			knowledgePoints.setId(AppUtils.uuid());
			knowledgePoints.setCreateId(userId);
			knowledgePoints.setCreateTime(new Date());	
			knowledgePoints.setUpdateTime(new Date());
			
		} else {
			knowledgePoints = knowledgePointsMapper.selectByPrimaryKey(knowledgePointId);
		}
		knowledgePoints.setAccountId(accountId);
		knowledgePoints.setKnowledgePointName(knowledgePointName);
		knowledgePoints.setKnowledgePointDesc(knowledgePointDesc);
		if (StringUtils.isEmpty(knowledgePointId)) {
			knowledgePointsMapper.insertSelective(knowledgePoints);
		} else {
			KnowledgePointsExample example1 = new KnowledgePointsExample();
			if(updateTime!=null){
				example1.createCriteria().andIdEqualTo("knowledgePointId").andUpdateTimeEqualTo(updateTime);
			}else{
				example1.createCriteria().andIdEqualTo("knowledgePointId");
			}
			knowledgePointsMapper.updateByExampleSelective(knowledgePoints, example1);
			knowledgePointsMapper.updateByPrimaryKeySelective(knowledgePoints);
		}
		

		
		
		ClassificationItemsRelExample example = new ClassificationItemsRelExample();
		example.createCriteria().andItemIdEqualTo(knowledgePoints.getId()).andItemTypeEqualTo(DConstants.CLASSIFICATION_ITEMS_TYPE_KNOWLEDGEPOINT);
		classificationItemsRelMapper.deleteByExample(example);

		String [] classificationIdArr = classificationIds.split(",");
		for(String classificationId:classificationIdArr){
			ClassificationItemsRel  classificationItemsRel = new ClassificationItemsRel();
			classificationItemsRel.setId(AppUtils.uuid());
			classificationItemsRel.setItemId(knowledgePoints.getId());
			classificationItemsRel.setItemType(DConstants.CLASSIFICATION_ITEMS_TYPE_KNOWLEDGEPOINT);
			classificationItemsRel.setClassificationId(classificationId);
			classificationItemsRel.setCreateTime(new Date());
			classificationItemsRel.setCreateId(userId);
			classificationItemsRel.setUpdateId(userId);
			classificationItemsRel.setUpdateTime(new Date());
			classificationItemsRelMapper.insert(classificationItemsRel);
		}
	}

	public Map<String,Object> getKnowledgePoint(String knowledgePointId,String classificationId) throws DeodioException {
		KnowledgePoints knowledgePoints = knowledgePointsMapper.selectByPrimaryKey(knowledgePointId);
		Classification classification = classificationMapper.selectByPrimaryKey(classificationId);
//		return knowledgePointsMapper.selectByPrimaryKey(knowledgePointId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("knowledgePoints", knowledgePoints);
		result.put("classification",classification);
		return result;
	}

	public void delKnowledgePoint(String knowledgePointId,String classificationId) throws DeodioException {
		ClassificationItemsRelExample example = new ClassificationItemsRelExample();
		example.createCriteria().andItemIdEqualTo(knowledgePointId).andItemTypeEqualTo(DConstants.CLASSIFICATION_ITEMS_TYPE_KNOWLEDGEPOINT);
		List<ClassificationItemsRel> classificationItemsRels = classificationItemsRelMapper.selectByExample(example);
		if(classificationItemsRels.size() == 1){
			knowledgePointsMapper.deleteByPrimaryKey(knowledgePointId);
		}
		
		classificationItemsRelMapper.deleteByExample(example);
	}

	public void delAllKnowledgePoints(String knowledgePointIds) throws DeodioException {

		String[] arr = knowledgePointIds.split(",");
		List<KnowledgePoints> knowledgePointsList = new ArrayList<KnowledgePoints>();
		for (String id : arr) {
			KnowledgePoints knowledgePoints = new KnowledgePoints();
			knowledgePoints.setId(id);
			knowledgePointsList.add(knowledgePoints);
			
			ClassificationItemsRelExample example = new ClassificationItemsRelExample();
			example.createCriteria().andItemIdEqualTo(id).andItemTypeEqualTo(DConstants.CLASSIFICATION_ITEMS_TYPE_KNOWLEDGEPOINT);
			classificationItemsRelMapper.deleteByExample(example);
		}
		knowledgeCustomizeMapper.delAllKnowledgePoints(knowledgePointsList);
	}

	public List<Map<String,Object>> getKnowledgePointByClassificationId(String classificationId,String accountId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("accountId", accountId);
		params.put("classificationId", classificationId);
		return knowledgeCustomizeMapper.getKnowledgePointByClassificationId(params);
	}
	//校验名字
//		public boolean getCheckKnowledgePointName(String KnowledgePointName) {
//			KnowledgePointsExample example=new KnowledgePointsExample();
//			example.createCriteria().andKnowledgePointNameEqualTo(KnowledgePointName);
//			List<KnowledgePoints> list=knowledgePointsMapper.selectByExample(example);
//			if(list.size()==0){
//				return true;
//			}else{
//				return false;
//			}
//		}
}
