package com.deodio.elearning.modules.classification.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AccountClassificationRelMapper;
import com.deodio.elearning.persistence.mapper.ClassificationItemsRelMapper;
import com.deodio.elearning.persistence.mapper.ClassificationMapper;
import com.deodio.elearning.persistence.mapper.customize.ClassificationCustomizeMapper;
import com.deodio.elearning.persistence.model.AccountClassificationRel;
import com.deodio.elearning.persistence.model.AccountClassificationRelExample;
import com.deodio.elearning.persistence.model.Classification;
import com.deodio.elearning.persistence.model.ClassificationExample;
import com.deodio.elearning.persistence.model.ClassificationItemsRel;
import com.deodio.elearning.persistence.model.ClassificationItemsRelExample;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service
public class ClassificationService {

	@Autowired
	private ClassificationMapper classificationMapper;
	
	@Autowired
	private ClassificationCustomizeMapper classificationCustomizeMapper;
	
	@Autowired
	private AccountClassificationRelMapper accountClassificationRelMapper;
	@Autowired
	private ClassificationItemsRelMapper classificationItemsRelMapper;
	
	
	public List<Map<String, Object>>  findClassifications(String classificationId,
					String keywords, PageRequest pageRequest, String accountId,String userId) throws DeodioException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		params.put("classificationId", classificationId);
		params.put("keywords", keywords);
		params.put("pagination", pageRequest);
		
		return classificationCustomizeMapper.findClassifications(params);
	}
	
	
	public List<Map<String, Object>> getClassificationTree(String accountId, String classificationId) throws DeodioException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("classificationId", classificationId);
		List<Map<String, Object>> resultList = classificationCustomizeMapper.getKnowledgeTree(params);
		for (Map<String, Object> result : resultList) {
			result.put("isParent", (String) result.get("isparent"));
		}
		return resultList;
	}

	
	public List<Map<String, Object>> getLeftCtreeByLevel(String accountId, String classificationId, String queryText)
			throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("queryText", queryText);
		params.put("classificationId", classificationId);
		List<Map<String, Object>> resultList = classificationCustomizeMapper.getLeftCtreeByLevel(params);
		for (Map<String, Object> result : resultList) {
			// 查看pid是否被查出如果没被查出，查询出来添加到List中
			result.put("isParent", (String) result.get("isparent"));
		}
		return resultList;
	}
	
	public List<Map<String, Object>> getLeftCtreeByText(String accountId, String classificationId, String queryText,
			String[] selectedClassification,String userId) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("queryText", queryText);
		params.put("classificationId", classificationId);
		params.put("selectedClassification", selectedClassification);
		params.put("userId",userId);
		if (null == selectedClassification) {
			params.put("selectedCount", 0);
		} else {
			params.put("selectedCount", selectedClassification.length);
		}
		List<Map<String, Object>> resultList = classificationCustomizeMapper.getLeftCtreeByText(params);
		for (Map<String, Object> result : resultList) {
			// 查看pid是否被查出如果没被查出，查询出来添加到List中
			result.put("isParent", (String) result.get("isparent"));
		}
		return resultList;
	}
	
	
	public List<Map<String, Object>> queryReversionCtreeByText(String accountId, String classificationId,
			String queryText) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("queryText", queryText);
		params.put("classificationId", classificationId);
		List<Map<String, Object>> resultList = classificationCustomizeMapper.queryReversionCtreeByText(params);
		for (Map<String, Object> result : resultList) {
			// 查看pid是否被查出如果没被查出，查询出来添加到List中
			result.put("isParent", (String) result.get("isparent"));
		}
		return resultList;
	}



	public Map<String,Object> submitClassification(String classificaitonId,String classificationName, String classificationDesc,
			String classificationParentId, String accountId, String userId) {
		
		Classification classification = new Classification();
		Map<String,Object> result = new HashMap<String,Object>();
		if(StringUtils.isEmpty(classificaitonId)){
			result.put("isUpdate", Boolean.FALSE);
			classification.setId(AppUtils.uuid());
			classification.setCreateId(userId);
			classification.setCreateTime(new Date());
		}else{
			result.put("isUpdate", Boolean.TRUE);
			classification = classificationMapper.selectByPrimaryKey(classificaitonId);
		}
		
		classification.setAccountId(accountId);
		classification.setClassificationDesc(classificationDesc);
		classification.setClassificationName(classificationName);
		classification.setClassificationParentId(classificationParentId);
		result.put("classificationId", classification.getId());
		result.put("pclassification", classificationMapper.selectByPrimaryKey(classificationParentId));
		if(StringUtils.isEmpty(classificaitonId)){
			classificationMapper.insertSelective(classification);
		}else{
			classificationMapper.updateByPrimaryKeySelective(classification);
		}
		return result;
	}
	//创建校验名字
	public boolean getCheckClassificationName(String classificationName) {
		ClassificationExample example=new ClassificationExample();
		example.createCriteria().andClassificationNameEqualTo(classificationName);
		List<Classification> list=classificationMapper.selectByExample(example);
		return list.isEmpty()?false:true;
	}
	
	public Classification getClassification(String classificationId) throws DeodioException{
		return classificationMapper.selectByPrimaryKey(classificationId);
	}
	
	public void delClassification(String classificationId) throws DeodioException{
		
		classificationMapper.deleteByPrimaryKey(classificationId);
	}
	
	public void delAllClassifications(String classificationIds) throws DeodioException{
		
		String [] arr = classificationIds.split(",");
		List<Classification> cList = new ArrayList<Classification>();
		for (String classificationId : arr) {
			Classification record = new Classification();
			record.setId(classificationId);
			cList.add(record);
		}
		classificationCustomizeMapper.delAllClassifications(cList);
	}
	
	public Map<String, Object> queryClassificationDesc(String id) throws DeodioException {

		return classificationCustomizeMapper.queryClassificationDesc(id);
	}
	
	
	public List<Map<String,Object>>  queryClassificationById(Map<String,Object> params){
		return classificationCustomizeMapper.queryClassificationById(params);
	}
	

	public Integer numChildrenClassification(Map<String,Object> params){
		return classificationCustomizeMapper.numChildrenClassification(params);
	}

	public void saveClassificationItemsRel(String classificationJson,String userId,String itemId,Short itemType) throws DeodioException{
		
		Gson classificationGson = new Gson();  
		List<ClassificationItemsRel> classificationsRelList = classificationGson.fromJson(classificationJson, new TypeToken<List<ClassificationItemsRel>>(){}.getType());
		
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("itemId", itemId);
		param.put("itemType", itemType);
		
		//删除该presentationId分类的标签库
		classificationCustomizeMapper.deleteClassificationItemsRel(param);
		
		//新增分类关联关系
		for(ClassificationItemsRel classificationItemsRel : classificationsRelList){
			classificationItemsRel.setId(AppUtils.uuid());
			classificationItemsRel.setItemId(itemId);
			classificationItemsRel.setItemType(itemType);
			classificationItemsRel.setCreateTime(new Date());
			classificationItemsRel.setUpdateTime(new Date());
			classificationItemsRel.setCreateId(userId);
			classificationItemsRel.setUpdateId(userId);
		}
		//新增分类关联关系
		classificationCustomizeMapper.insertClassificationItemsRel(classificationsRelList);
	}
	
	public String querySelectedClassificationNameByItemId(Map<String,Object> params)throws DeodioException{
		return classificationCustomizeMapper.querySelectedClassificationNameByItemId(params);
	}
	
	
	public List<Map<String,Object>>  querySelectedClassificationByItemId(Map<String,Object> params)throws DeodioException{
		return classificationCustomizeMapper.querySelectedClassificationByItemId(params);
	}
	
	/**
	 * 获取系统默认顶级分类
	 * @return
	 */
	public List<Map<String,Object>> getDefaultTopClassification(String relatedIndustry)throws DeodioException{
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("relatedIndustry", relatedIndustry);
		return classificationCustomizeMapper.getRegisterClassificationList(paramsMap);
	}
	
	/**
	 * 插入账户与顶级类别关联关系
	 * @param userId
	 * @param accountId
	 * @param classificationJson
	 * @param accountType
	 */
	public void insertSysClassificationsToAccount(String userId,String accountId,String classificationJson,boolean isPreDel,Integer maxCount)throws DeodioException{
		
		Gson gson = new Gson();
		List<String> classifications = gson.fromJson(classificationJson, new TypeToken<List<String>>(){}.getType());
		
		if(classifications.size() > maxCount ){
			throw new DeodioException("register.classification.gt.than.max.err.msg");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("accountId", accountId);
		params.put("userId", userId);
		params.put("createId", userId);
		params.put("createTime", new Date());
		params.put("classificationIds", classifications);
		
		if(isPreDel){
			classificationCustomizeMapper.delAllClassificationsInAccount(params);
			
			AccountClassificationRelExample example = new AccountClassificationRelExample();
			example.createCriteria().andAccountIdEqualTo(accountId);
			accountClassificationRelMapper.deleteByExample(example);
		}
		
		if(classifications != null && !classifications.isEmpty()){
			List<AccountClassificationRel> list =  generateAccountClassificationRelList(accountId,userId,classifications);
			classificationCustomizeMapper.insertSysClassificationsAccountRel(list);
		}
	}
	
	public int getSelectedTopSysClassificationCount(String accountId)throws DeodioException{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("accountId", accountId);
		return classificationCustomizeMapper.getSelectedTopSysClassificationCount(params);
	}
	
	public int delAllClassificationsInAccount(Map<String,Object> params)throws DeodioException{
		return classificationCustomizeMapper.delAllClassificationsInAccount(params);
	}
	
	private List<AccountClassificationRel> generateAccountClassificationRelList(String accountId,String userId,List<String> classificationIds ){
		List<AccountClassificationRel> list = new ArrayList<AccountClassificationRel>();
		Date now = new Date();
		for(String classificationId : classificationIds){
			AccountClassificationRel rel = new AccountClassificationRel();
			rel.setId(AppUtils.uuid());
			rel.setAccountId(accountId);
			rel.setClassificationId(classificationId);
			rel.setCreateId(userId);
			rel.setCreateTime(now);
			list.add(rel);
		}
		return list;
		
	}
public void copyClassificationItemsRel(String courseId,String itemId) throws DeodioException{
		
	   ClassificationItemsRelExample example=new ClassificationItemsRelExample();
	   example.createCriteria().andItemIdEqualTo(courseId);
	   List<ClassificationItemsRel> classificationsRelList=classificationItemsRelMapper.selectByExample(example);
	   List<ClassificationItemsRel> classificationsRelNewList=new ArrayList<ClassificationItemsRel>();
		//新增分类关联关系
	   for(ClassificationItemsRel classificationItemsRel : classificationsRelList){
			classificationItemsRel.setId(AppUtils.uuid());
			classificationItemsRel.setItemId(itemId);
			classificationsRelNewList.add(classificationItemsRel);
		}
		//新增分类关联关系
		classificationCustomizeMapper.insertClassificationItemsRel(classificationsRelNewList);
	}
	/**
	 * 复制分类
	 */
	public void copyClassification(String id, String newId, String userId) throws DeodioException {
		
		ClassificationItemsRelExample classificationItemsRelExample = new ClassificationItemsRelExample();
		classificationItemsRelExample.createCriteria().andItemIdEqualTo(id);
		List<ClassificationItemsRel> classificationItemsRels = classificationItemsRelMapper.selectByExample(classificationItemsRelExample);
		if (!classificationItemsRels.isEmpty()) {
			List<ClassificationItemsRel> list = new LinkedList<ClassificationItemsRel>();
			for (ClassificationItemsRel item : classificationItemsRels) {
				item.setId(AppUtils.uuid());
				item.setItemId(newId);
				item.setCreateId(userId);
				item.setCreateTime(new Date());
				item.setUpdateId(userId);
				item.setUpdateTime(new Date());
				list.add(item);
			}
			classificationCustomizeMapper.insertClassificationItemsRel(list);
		}
	}
}
