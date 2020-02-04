package com.deodio.elearning.commons.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.SysActionMapper;
import com.deodio.elearning.persistence.model.SysAction;
import com.deodio.elearning.persistence.model.SysActionExample;

@Service
public class SysActionService {
	
	@Autowired
	private SysActionMapper sysActionMapper;
	
	/**
	 * 获得应用action
	 * @Title: getSysActionList
	 * @return
	 * @throws DeodioException
	 * @return List<SysAction>
	 */
	public List<SysAction> getSysActionList()throws DeodioException{
		return sysActionMapper.selectByExample(null);
	}
	
	/**
	 * 保存应用action信息
	 * @param params
	 * @return
	 */
	public int saveSysActioin (SysAction sysAction)throws DeodioException{
		
		String actionUrl = sysAction.getActionUrl();
		SysActionExample example = new SysActionExample();
		example.createCriteria().andActionUrlEqualTo(actionUrl);
		int rowCount = 0;
		
		List<SysAction> list = sysActionMapper.selectByExample(example);
		
		if(list != null && !list.isEmpty()){
			if(list.size() > DConstants.NUMBER_ONE){
				sysActionMapper.deleteByExample(example);
			}
			
			SysAction info = list.get(0);
			info.setActionName(sysAction.getActionName());
			info.setCreateId(null);
			info.setCreateTime(null);
			info.setUpdateId(sysAction.getUpdateId());
			info.setUpdateTime(new Date());
			
			rowCount = sysActionMapper.updateByPrimaryKeySelective(info);
			
		}else{
			sysAction.setId(AppUtils.uuid());
			sysAction.setCreateTime(new Date());
			sysAction.setUpdateId(null);
			sysAction.setUpdateTime(null);
			rowCount = sysActionMapper.insertSelective(sysAction);
		}
		
		return rowCount;
	}
}
