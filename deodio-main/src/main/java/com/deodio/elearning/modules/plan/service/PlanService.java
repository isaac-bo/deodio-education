package com.deodio.elearning.modules.plan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.elearning.persistence.mapper.customize.ServicePlanCustomizeCmoboMapper;

@Service
public class PlanService {
	
	@Autowired
	private ServicePlanCustomizeCmoboMapper servicePlanCustomizeCmoboMapper;
	
	/**
	 * 获取指定account 的cmobo设定值
	 * @param params
	 * @return
	 */
	public Integer getCmoboSetValue(Map<String,Object> params){
		return servicePlanCustomizeCmoboMapper.getServicePlanComboByIdentifier(params);
	}
	
}
