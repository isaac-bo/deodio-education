package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

public interface MessageTextCustomizeMapper {
	
	public List<Map<String, Object>> findMessageTextList(Map<String, Object> params);

}
