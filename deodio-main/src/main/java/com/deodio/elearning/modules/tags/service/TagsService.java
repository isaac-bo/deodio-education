package com.deodio.elearning.modules.tags.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.TagsItemsRelMapper;
import com.deodio.elearning.persistence.mapper.TagsMapper;
import com.deodio.elearning.persistence.mapper.customize.TagsCustomizeMapper;
import com.deodio.elearning.persistence.model.Tags;
import com.deodio.elearning.persistence.model.TagsExample;
import com.deodio.elearning.persistence.model.TagsItemsRel;
import com.deodio.elearning.persistence.model.TagsItemsRelExample;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class TagsService {

	@Autowired
	private TagsCustomizeMapper tagsCustomizeMapper;
	
	@Autowired
	private TagsMapper tagsMapper;
	@Autowired
	private TagsItemsRelMapper tagsItemsRelMapper;
	
	public List<Map<String,Object>> queryHotTagsList(Map<String,Object> params){
		return tagsCustomizeMapper.queryHotTagsList(params);
	}
	
	public List<Map<String,Object>> queryItemTagsList(Map<String,Object> params){
		return tagsCustomizeMapper.queryItemTagsList(params);
	}
	
	
	public void saveTagsItemsRel(String tagsJson,String accountId,String userId,String itemId,Short itemType) throws DeodioException{
		Gson gson = new Gson();  
		List<Tags> tagsList = gson.fromJson(tagsJson, new TypeToken<List<Tags>>(){}.getType());
		
		
		//标签表
		List<TagsItemsRel> tagsRelList = new ArrayList<TagsItemsRel>();
		//新增tagsList
		List<Tags> insertTagsList = new ArrayList<Tags>();
		
		//组装标签List
		for(int i=0;i<tagsList.size();i++){
			Tags items = tagsList.get(i);
			TagsItemsRel tagsItemsRel = new TagsItemsRel();
			//判断提交的标签是否存在Tags表中
			TagsExample tagsExample = new TagsExample();
			tagsExample.createCriteria().andAccountIdEqualTo(accountId).andTagNameEqualTo(items.getTagName());
			List<Tags> ifHaveTagsList = tagsMapper.selectByExample(tagsExample);
			String tagsId = "";
			if(ifHaveTagsList.isEmpty()){
				tagsId = AppUtils.uuid();
				items.setId(tagsId);
				items.setAccountId(accountId);
				items.setCreateId(userId);
				items.setCreateTime(new Date());
				items.setUpdateId(userId);
				items.setUpdateTime(new Date());
				insertTagsList.add(items);
			}else{
				tagsId = ifHaveTagsList.get(0).getId();
			}
			tagsItemsRel.setId(AppUtils.uuid());
			tagsItemsRel.setItemId(itemId);
			tagsItemsRel.setItemType(itemType);
			tagsItemsRel.setTagsId(tagsId);
			tagsItemsRel.setCreateTime(new Date());
			tagsItemsRel.setCreateId(userId);
			tagsItemsRel.setUpdateId(userId);
			tagsItemsRel.setUpdateTime(new Date());
			tagsRelList.add(tagsItemsRel);
		}

		//新增tags标签库
		if(!insertTagsList.isEmpty()){
			tagsCustomizeMapper.insertTags(insertTagsList);
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("itemId", itemId);
		param.put("itemType", itemType);
		//删除该presentationId关联标签库
		tagsCustomizeMapper.deleteTagsItemsRel(param);
		//新增标签关联关系
		tagsCustomizeMapper.insertTagsItemsRel(tagsRelList);

	}

	public List<Map<String, Object>> queryTagsByItemType(Map<String, Object> params) {
		return tagsCustomizeMapper.queryAccountAllTags(params);
	}
	
	public String querySelectedTagsNameByItemId(Map<String, Object> params) {
		return tagsCustomizeMapper.querySelectedTagsNameByItemId(params);
	}
	
	public List<Map<String, Object>> querySelectedTagsByItemId(Map<String, Object> params) {
		return tagsCustomizeMapper.querySelectedTagsByItemId(params);
	}
	/**
	 * @param courseId
	 * @param newCourseId
	 * 复制标签关联表
	 */
	public void copyTagItemRel(String courseId,String newCourseId){
				List<TagsItemsRel> tagsRelList = new ArrayList<TagsItemsRel>();
				TagsItemsRelExample tagsItemExample = new TagsItemsRelExample();
				tagsItemExample.createCriteria().andItemIdEqualTo(courseId);
				List<TagsItemsRel> tagitemsList=tagsItemsRelMapper.selectByExample(tagsItemExample);
				//组装标签List
				for(int i=0;i<tagitemsList.size();i++){
					TagsItemsRel tagsItemsRel = tagitemsList.get(i);
					tagsItemsRel.setId(AppUtils.uuid());
					tagsItemsRel.setItemId(newCourseId);
					tagsRelList.add(tagsItemsRel);
				}
				//新增标签关联关系
				tagsCustomizeMapper.insertTagsItemsRel(tagsRelList);
	}
	/**
	 * 复制标签
	 */
	public void copyTag(String id, String newId, String userId) {
		
		TagsItemsRelExample tagsItemsRelExample = new TagsItemsRelExample();
		tagsItemsRelExample.createCriteria().andItemIdEqualTo(id);
		List<TagsItemsRel> tagsItemsRels = tagsItemsRelMapper.selectByExample(tagsItemsRelExample);
		if (!tagsItemsRels.isEmpty()) {
			List<TagsItemsRel> list = new LinkedList<TagsItemsRel>();
			for (TagsItemsRel item : tagsItemsRels) {
				item.setId(AppUtils.uuid());
				item.setItemId(newId);
				item.setCreateId(userId);
				item.setCreateTime(new Date());
				item.setUpdateId(userId);
				item.setUpdateTime(new Date());
				list.add(item);
			}
			tagsCustomizeMapper.insertTagsItemsRel(list);
		}
	}
}
