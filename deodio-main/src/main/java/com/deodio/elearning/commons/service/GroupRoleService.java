package com.deodio.elearning.commons.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.customize.GroupCustomizeMapper;

@Service
public class GroupRoleService {
	
	@Autowired
	private GroupCustomizeMapper groupCustomizeMapper;
	
	/**
	 * 获得小组角色
	 * @Title: getGroupRoleModelList
	 * @param countryId
	 * @return
	 * @throws DeodioException
	 * @return List<DicProvinceModel>
	 */
	public List<Map<String, Object>> getGroupRoleModelList(Map<String,Object> params)throws DeodioException{
		return groupCustomizeMapper.queryGroupRoleForUser(params);
	}
	
	/**
	 * 获取用户对应小组信息
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryGroupListForUser(Map<String,Object> params){
		return groupCustomizeMapper.queryGroupListForUser(params);
	}
}
