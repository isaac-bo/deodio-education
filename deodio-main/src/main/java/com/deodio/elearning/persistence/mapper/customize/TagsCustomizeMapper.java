package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.Tags;
import com.deodio.elearning.persistence.model.TagsItemsRel;

public interface TagsCustomizeMapper {
	public int insertTags(List<Tags> list);
	public List<Map<String,Object>> queryHotTagsList(Map<String,Object> params);
	public int insertTagsItemsRel(List<TagsItemsRel> list);
	public int deleteTags(Map<String,Object> params);
	public int deleteTagsItemsRel(Map<String,Object> params);
	public int updateTags(List<Tags> list);
	public List<Map<String,Object>> queryItemTagsList(Map<String,Object> params);
	public List<Map<String,Object>> queryAccountAllTags(Map<String,Object> params);
	
	public String querySelectedTagsNameByItemId(Map<String,Object> params);
	
	public List<Map<String,Object>> querySelectedTagsByItemId(Map<String,Object> params);
	
}