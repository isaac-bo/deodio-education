package com.deodio.elearning.persistence.mapper.customize;

import java.util.Map;

public interface AuthVerificationCustomizeMapper {
		
	public Map<String,Object> getValidateInfoForGroupUser(Map<String,Object> params);
	
}
