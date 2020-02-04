package com.deodio.elearning.modules.course.packages.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.constants.Constants;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.DateTimeUtils;
import com.deodio.elearning.comparator.CoursePackageItemsAscComparator;
import com.deodio.elearning.comparator.CoursePackageSeriesAscComparator;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.classification.service.ClassificationService;
import com.deodio.elearning.modules.tags.service.TagsService;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.CoursePackageItemsMapper;
import com.deodio.elearning.persistence.mapper.CoursePackageItemsRelMapper;
import com.deodio.elearning.persistence.mapper.CoursePackageMapper;
import com.deodio.elearning.persistence.mapper.CoursePackageSeriesMapper;
import com.deodio.elearning.persistence.mapper.customize.CoursePackageCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.CoursePackage;
import com.deodio.elearning.persistence.model.CoursePackageExample;
import com.deodio.elearning.persistence.model.CoursePackageItems;
import com.deodio.elearning.persistence.model.CoursePackageItemsElementsRel;
import com.deodio.elearning.persistence.model.CoursePackageItemsExample;
import com.deodio.elearning.persistence.model.CoursePackageItemsRel;
import com.deodio.elearning.persistence.model.CoursePackageItemsRelExample;
import com.deodio.elearning.persistence.model.CoursePackageSeries;
import com.deodio.elearning.persistence.model.CoursePackageSeriesExample;
import com.deodio.elearning.persistence.model.customize.CommitDataSetBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageItemsBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageItemsElementsRelBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageItemsRelBo;
import com.deodio.elearning.persistence.model.customize.CoursePackageSeriesBo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import edu.emory.mathcs.backport.java.util.Collections;
/**
 * @author p0085398
 *
 */
@Service
public class CoursePackagesService extends BaseService{
	
	@Autowired
	private CoursePackageMapper coursePackageMapper;
	
	@Autowired
	private CoursePackageItemsRelMapper coursePackageItemsRelMapper;
	
	@Autowired
	private CoursePackageCustomizeMapper coursePackageCustomizeMapper;
	
	@Autowired
	private CoursePackageSeriesMapper coursePackageSeriesMapper;
	
	@Autowired
	private CoursePackageItemsMapper coursePackageItemsMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private ClassificationService classificationService;
	
	/**
	 * 保存课程包
	 * @param coursePackageInfoJson
	 * @param attachId
	 * @param userId
	 * @param accountId
	 * @param tagsList
	 * @param classificationsRelList
	 * @throws DeodioException
	 */
	public String saveCoursePackageInfo( String coursePackageInfoJson,String attachId,String userId,String accountId,String tagsList,
			 String classificationsRelList) throws DeodioException{
		
		Gson coursePackageGson = new GsonBuilder().setDateFormat(DateTimeUtils.FORMAT_YYYYMMDD).create();;  
		CoursePackage coursePackageInfo = coursePackageGson.fromJson(coursePackageInfoJson, new TypeToken<CoursePackage>(){}.getType());
		
		String id = coursePackageInfo.getId();
		Date now = new Date();
		coursePackageInfo.setAccountId(accountId);
		
		if(StringUtils.isBlank(id)){
			id = AppUtils.uuid();
			coursePackageInfo.setId(id);
			coursePackageInfo.setCreateId(userId);
			coursePackageInfo.setCreateTime(now);
			coursePackageMapper.insertSelective(coursePackageInfo);
		}else{
			coursePackageInfo.setUpdateId(userId);
			coursePackageInfo.setUpdateTime(now);
			coursePackageMapper.updateByPrimaryKeySelective(coursePackageInfo);
		}
		
		if(StringUtils.isNotBlank(tagsList)){
			tagsService.saveTagsItemsRel(tagsList,accountId, userId, id, DConstants.TAGS_ITEMS_TYPE_COURSEPACKAGE);
		}
		if(StringUtils.isNotBlank(classificationsRelList)){
			classificationService.saveClassificationItemsRel(classificationsRelList, userId, id, DConstants.CLASSIFICATION_ITEMS_TYPE_COURSEPACKAGE);
		}
		
		//图片表更新
		if(StringUtils.isNotBlank(attachId)){
			Attachment Attachment = new Attachment();
			Attachment.setId(attachId);
			Attachment.setRelId(id);
			attachmentMapper.updateByPrimaryKeySelective(Attachment);
		}
		
		return id;
	}
	
	
	public Integer publishCoursePackage(Map<String,Object> params)throws DeodioException{
		return coursePackageCustomizeMapper.publishCoursePackage(params);
	}
	
	
	/**
	 *查询课程包基本信息
	 * @param paramsMap
	 * @return
	 * @throws DeodioException
	 */
	public CoursePackage queryCoursePackageInfo(Map<String, Object> paramsMap)throws DeodioException{
		String id = (String) paramsMap.get("packageId");
		String accountId = String.valueOf(paramsMap.get("accountId"));
		
		CoursePackageExample example = new CoursePackageExample();
		example.createCriteria().andIdEqualTo(id).andAccountIdEqualTo(accountId);
		
		return coursePackageMapper.selectByExample(example).get(0);
	}
	
	/**
	 * 查询课程包相同的数据个数
	 * @param paramsMap
	 * @return
	 */
	public Integer queryPackageNameCount(Map<String,String> paramsMap){
		String packageId = paramsMap.get("packageId");
		String packageName = paramsMap.get("packageName");
		CoursePackageExample example = new CoursePackageExample();
		CoursePackageExample.Criteria criteria =  example.createCriteria();
		if(StringUtils.isNotBlank(packageId)){
			criteria.andIdNotEqualTo(packageId);
		}
		criteria.andPackageNameEqualTo(packageName);
		return coursePackageMapper.countByExample(example);
	}
	
	
	/**
	 * 查询课程包信息
	 * @param params
	 * @return
	 */
	public CoursePackageBo queryPackageContent(Map<String,Object> params)throws DeodioException{
		CoursePackageBo packageBo = new CoursePackageBo();
		//获取指定课程包下所有item
		List<CoursePackageItemsBo> packageItemsBo = coursePackageCustomizeMapper.queryCoursePackageItemsBo(params);
		//排序item
		Collections.sort(packageItemsBo, new CoursePackageItemsAscComparator());
		
		//通过items获取 series数据
		List<CoursePackageSeriesBo> packageSeries = convertToCoursePackageSeriesBo(packageItemsBo);
		//排序series
		Collections.sort(packageSeries, new CoursePackageSeriesAscComparator());
		
		//暂存series 与 items 对应关系
		Map<Integer,List<CoursePackageItemsBo>> seriesMap = new HashMap<Integer,List<CoursePackageItemsBo>>();
		for(CoursePackageItemsBo itemBo : packageItemsBo){
			 Integer seriesOrder = (int) itemBo.getSeriesOrder();
			 List<CoursePackageItemsBo> items = seriesMap.get(seriesOrder);
			 if(items == null){
				 items = new ArrayList<CoursePackageItemsBo>();
			 }
			 items.add(itemBo);
			 seriesMap.put(seriesOrder, items);
		}
		//设定series 域  items 对应关系
		for(CoursePackageSeriesBo seriesBo :packageSeries){
			Integer seriesOrder = (int) seriesBo.getSeriesOrder();
			seriesBo.setItems(seriesMap.get(seriesOrder));
		}
		packageBo.setSeries(packageSeries);
		
		//获取课程包itemsRel 关联数据
		List<CoursePackageItemsRel> itemsRelList = queryCoursePackageItemsRel(params);
		packageBo.setItemsRel(itemsRelList);
		
		return packageBo;
	}
	
	
	/**
	 * 保存课程包内容信息
	 * @param seriesJson
	 * @param itemsJson
	 * @param itemsRelJson
	 * @param userId
	 */
	public void saveCoursePackageContent(String packageId,String seriesJson,String itemsJson,String itemsRelJson,String userId,String accountId)throws DeodioException{
		
		Gson gson = new Gson();
		try {
			//保存课程包列数据
			List<CoursePackageSeriesBo> series = gson.fromJson(seriesJson, new TypeToken<List<CoursePackageSeriesBo>>(){}.getType());
			
			CommitDataSetBo<CoursePackageSeries>  seriesCommitDataSet = setCommitDataSet(series,userId,accountId);
			if(!seriesCommitDataSet.getInsertList().isEmpty()){
				
				
				CoursePackageSeriesExample coursePackageSeriesExample = new CoursePackageSeriesExample();
				coursePackageSeriesExample.createCriteria().andPackageIdEqualTo(packageId);
				coursePackageSeriesMapper.deleteByExample(coursePackageSeriesExample);
				coursePackageCustomizeMapper.insertCoursePackageSeriesBatch(seriesCommitDataSet.getInsertList());
			}
			if(!seriesCommitDataSet.getUpdateList().isEmpty()){
				coursePackageCustomizeMapper.updateCoursePackageSeriesBatch(seriesCommitDataSet.getUpdateList());
			}
			if(!seriesCommitDataSet.getDeleteList().isEmpty()){
				Map<String,Object> seriesDelList = new HashMap<String,Object>();
				seriesDelList.put("series", seriesCommitDataSet.getDeleteList());
				seriesDelList.put("accountId",accountId);
				coursePackageCustomizeMapper.delCoursePackageSeriesBatch(seriesDelList);
			}
			
			//保存课程包item 数据
			List<CoursePackageItemsBo> items = gson.fromJson(itemsJson, new TypeToken<List<CoursePackageItemsBo>>(){}.getType());
			CommitDataSetBo<CoursePackageItems>  itemsCommitDataSet = setCommitDataSet(items,userId,accountId);
			if(!itemsCommitDataSet.getInsertList().isEmpty()){
				CoursePackageItemsExample coursePackageItemsExample = new CoursePackageItemsExample();
				coursePackageItemsExample.createCriteria().andPackageIdEqualTo(packageId);
				coursePackageItemsMapper.deleteByExample(coursePackageItemsExample);
				coursePackageCustomizeMapper.insertCoursePackageItemsBatch(itemsCommitDataSet.getInsertList());
			}
			if(!itemsCommitDataSet.getUpdateList().isEmpty()){
				coursePackageCustomizeMapper.updateCoursePackageItemsBatch(itemsCommitDataSet.getUpdateList());
			}
			if(!itemsCommitDataSet.getDeleteList().isEmpty()){
				Map<String,Object> itemsDelList = new HashMap<String,Object>();
				itemsDelList.put("items", itemsCommitDataSet.getDeleteList());
				itemsDelList.put("accountId",accountId);
				coursePackageCustomizeMapper.delCoursePackageItemsBatch(itemsDelList);
			}
			
			//保存课程item关联数据
			GsonBuilder itemsRelGsonBuilder = new GsonBuilder();
	        // Register an adapter to manage the date types as long values
			itemsRelGsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
				@Override
				public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
						throws JsonParseException {
					return new Date(json.getAsJsonPrimitive().getAsLong());
				}
	        });
	        Gson itemsGson = itemsRelGsonBuilder.create();
			
			List<CoursePackageItemsRelBo> itemsRel = itemsGson.fromJson(itemsRelJson, new TypeToken<List<CoursePackageItemsRelBo>>(){}.getType());
			CommitDataSetBo<CoursePackageItemsRel>  itemsRelCommitDataSet = setCommitDataSet(itemsRel,userId,accountId);
			CoursePackageItemsRelExample itemsRelExample = new CoursePackageItemsRelExample();
			itemsRelExample.createCriteria().andPackageIdEqualTo(packageId);
			coursePackageItemsRelMapper.deleteByExample(itemsRelExample);
			if(!itemsRelCommitDataSet.getInsertList().isEmpty()){
				coursePackageCustomizeMapper.insertCoursePackageItemsRelBatch(itemsRelCommitDataSet.getInsertList());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DeodioException("course.package.convert.err.msg");
		} 
	}
	
	
	/**
	 * 通过itemId 查询 element 列表
	 * @param params
	 * @return
	 */
	public List<CoursePackageItemsElementsRelBo> queryCoursePackageItemsElementsRelList(Map<String,String> params)throws DeodioException{
		return coursePackageCustomizeMapper.queryCoursePackageItemsElementsRelBo(params);
	}
	
	public void  saveCoursePackageItemsElementsRel(String elementsJson,String userId,String accountId)throws DeodioException{
		Gson gson = new Gson();
		List<CoursePackageItemsElementsRelBo> elements = gson.fromJson(elementsJson, new TypeToken<List<CoursePackageItemsElementsRelBo>>(){}.getType());
		
		CommitDataSetBo<CoursePackageItemsElementsRel> elementsCommitDataSet;
		try {
			elementsCommitDataSet = setCommitDataSet(elements,userId,accountId);
			
			if(!elementsCommitDataSet.getInsertList().isEmpty()){
				coursePackageCustomizeMapper.insertCoursePackageItemsElementsRelBatch(elementsCommitDataSet.getInsertList());
			}
			if(!elementsCommitDataSet.getUpdateList().isEmpty()){
				coursePackageCustomizeMapper.updateCoursePackageItemsElementsRelBatch(elementsCommitDataSet.getUpdateList());
			}
			if(!elementsCommitDataSet.getDeleteList().isEmpty()){
				Map<String,Object> elementsDelList = new HashMap<String,Object>();
				elementsDelList.put("elements", elementsCommitDataSet.getDeleteList());
				elementsDelList.put("accountId",accountId);
				coursePackageCustomizeMapper.delCoursePackageItemsElementsRelBatch(elementsDelList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过分类及标签获取课程列表
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>>  findCoursePackageList(Map<String,Object> params)throws DeodioException{
		return coursePackageCustomizeMapper.findCoursePackageList(params);
	}
	
	
	/**查询课程包item关联数据
	 * @param params
	 * @return
	 */
	public List<CoursePackageItemsRel> queryCoursePackageItemsRel(Map<String,Object> params){
		CoursePackageItemsRelExample example = new CoursePackageItemsRelExample();
		String packageId = String.valueOf(params.get("packageId"));
		example.createCriteria().andPackageIdEqualTo(packageId);
		return coursePackageItemsRelMapper.selectByExample(example);
	}
	
	
	private List<CoursePackageSeriesBo> convertToCoursePackageSeriesBo(List<CoursePackageItemsBo> packageItems){
		Gson gson = new Gson();
		String itemsJson = gson.toJson(packageItems);
		Gson targetJson = new Gson();
		List<CoursePackageSeriesBo> targetList = targetJson.fromJson(itemsJson, new TypeToken<List<CoursePackageSeriesBo>>(){}.getType());
		Set<CoursePackageSeriesBo> h  =   new  HashSet<CoursePackageSeriesBo>(targetList); 
		targetList.clear(); 
		targetList.addAll(h); 
		return targetList;
		
		
//		return convertToTargetList(packageItems);
	}
	
//	private<T,V> List<T> convertToTargetList(List<V> sourceList){
//		Gson gson = new Gson();
//		String itemsJson = gson.toJson(sourceList);
//		Gson targetJson = new Gson();
//		List<T> targetList = targetJson.fromJson(itemsJson, new TypeToken<List<T>>(){}.getType());
//		HashSet<T> h  =   new  HashSet<T>(targetList); 
//		targetList.clear(); 
//		targetList.addAll(h); 
//		return targetList;
//	}
	
	
	/**
	 * 获取 提交数据库  insertList   updateList  deleteList  (后期需改进，现使用反射机制)
	 * @param sourceList
	 * @param userId
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 */
	private<T,K extends T> CommitDataSetBo<T> setCommitDataSet(List<K> sourceList,String userId,String accountId) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		
		CommitDataSetBo<T> dataSetBo = new CommitDataSetBo<T>();
		
		List<T> insertList = new ArrayList<T>();
		List<T> updateList = new ArrayList<T>();
		List<T> deleteList = new ArrayList<T>();
		Date now = new Date();
		
		for(K element : sourceList){
			
			try{
				Method setAccountId = element.getClass().getMethod("setAccountId", String.class);
				setAccountId.invoke(element, accountId);
			}catch(NoSuchMethodException noSuchMethodException){
				log.debug("Method setAccountId is not exist in " + element.getClass().getName());
			}
			
			
			Field operateTypeField = element.getClass().getDeclaredField("operateType");
			operateTypeField.setAccessible(true);
			int operateType = operateTypeField.getInt(element);
			if(Constants.COURSE_OPERATE_TYPE_INSERT  == operateType){
				
				Method setCreateId = element.getClass().getMethod("setCreateId", String.class);
				setCreateId.invoke(element, userId);
				
				Method setCreateTime = element.getClass().getMethod("setCreateTime", Date.class);
				setCreateTime.invoke(element, now);
				
				insertList.add(element);
			}else if(Constants.COURSE_OPERATE_TYPE_UPDATE  == operateType){
				Method setUpdateId = element.getClass().getMethod("setUpdateId", String.class);
				setUpdateId.invoke(element, userId);
				
				Method setUpdateTime = element.getClass().getMethod("setUpdateTime", Date.class);
				setUpdateTime.invoke(element, now);
				
				updateList.add(element);
			}else{
				deleteList.add(element);
			}
		}
		dataSetBo.setInsertList(insertList);
		dataSetBo.setUpdateList(updateList);
		dataSetBo.setDeleteList(deleteList);
		return dataSetBo;
	}
	
}
