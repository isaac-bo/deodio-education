package com.deodio.core.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deodio.core.constants.Constants;

public class BaseService{
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	protected Boolean getBooleanSatatus(int status){
		if(status == Constants.TRUE_STATUS){
			return Boolean.TRUE;
		}
		return Boolean.TRUE;
	}
	
	protected int getIntStatus(Boolean status){
		return status ? Constants.TRUE_STATUS : Constants.FALSE_STATUS;
	}
	
	public Map<String,Object> getSideMap(String title, String path){
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put(Constants.STR_TITLE, title);
		result.put(Constants.STR_PATH, path);
		return result;
	}
}
