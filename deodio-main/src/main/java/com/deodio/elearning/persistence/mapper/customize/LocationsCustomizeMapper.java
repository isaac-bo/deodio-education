package com.deodio.elearning.persistence.mapper.customize;

import java.util.List;
import java.util.Map;

import com.deodio.elearning.persistence.model.Locations;

public interface LocationsCustomizeMapper {
	
	public List<Map<String,Object>> findLocationsList(Map<String, Object> params);
	
	public List<Map<String,Object>> getLocationsList(Map<String, Object> params);
	
	public void delAllLocations(List<Locations> params);
}
