package com.deodio.elearning.commons.service;

import org.springframework.stereotype.Service;

import com.deodio.elearning.utils.CommonUtils;

@Service("systemService")
public class SystemService {
	
	public String getFmsServer() {
		return CommonUtils.FMS_PROTOCAL_RMTP + CommonUtils.FMS_SERVER_NAME + CommonUtils.FMS_SERVER_APP;
	}

	public String getFmsServerUrl() {
		return CommonUtils.FMS_PROTOCAL_RMTP + CommonUtils.FMS_SERVER_NAME;
	}
	
	public String getImageServerUrl(){
		return CommonUtils.IMGS_SERVER_DIR;
	}
	
	public Boolean isRed5(){
		return Boolean.parseBoolean(CommonUtils.FMS_SERVER_IS_RED5);
	}
	
	public String getSystemUrl(){
		return CommonUtils.APPLICATION_URL + CommonUtils.APPLICATION_NAME + CommonUtils.APPLICATION_PATH;
	}
}
