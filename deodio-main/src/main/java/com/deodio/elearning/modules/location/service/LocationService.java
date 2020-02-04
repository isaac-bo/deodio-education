package com.deodio.elearning.modules.location.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.LocationsMapper;
import com.deodio.elearning.persistence.mapper.customize.LocationsCustomizeMapper;
import com.deodio.elearning.persistence.model.Classification;
import com.deodio.elearning.persistence.model.ClassificationExample;
import com.deodio.elearning.persistence.model.KnowledgePointsExample;
import com.deodio.elearning.persistence.model.Locations;
import com.deodio.elearning.persistence.model.LocationsExample;

@Service
public class LocationService extends BaseService {

	@Autowired
	LocationsMapper locationsMapper;

	@Autowired
	LocationsCustomizeMapper locationsCustomizeMapper;

	public List<Map<String, Object>> findLocationsList(PageRequest pageRequest, String accountId, String keywords,String userId)
			throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pagination", pageRequest);
		params.put("accountId", accountId);
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("userId", userId);
		return locationsCustomizeMapper.findLocationsList(params);
	}
	
	
	public List<Map<String, Object>> getLocationsList(String accountId, String keywords,String userId,Integer enabled)
			throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("keywords", StringUtils.isNotBlank(keywords) ? keywords : null);
		params.put("userId", userId);
		params.put("enabled", enabled);
		return locationsCustomizeMapper.findLocationsList(params);
	}
	//校验名字
		public boolean getCheckLocationName(String locationName) {
			LocationsExample example=new LocationsExample();
			example.createCriteria().andLocationNameEqualTo(locationName);
			List<Locations> list=locationsMapper.selectByExample(example);
			return list.isEmpty()?false:true;
		}

	public void saveOrUpdateLocation(String locationId, String locationName, String locationAddress,
			String locationDesc, Integer isEnabled, String accountId, String userId, String locationLongitude,
			String locationLatitude, Integer locationZoom, Integer countryId, Integer provinceId, Integer cityId)
					throws DeodioException {

		Locations location = new Locations();
		if (StringUtils.isEmpty(locationId)) {
			location.setId(AppUtils.uuid());
			location.setCreateId(userId);
			location.setCreateTime(new Date());
		} else {
			location = locationsMapper.selectByPrimaryKey(locationId);
		}
		location.setLocationName(locationName);
		location.setLocationAddress(locationAddress);
		location.setLocationDesc(locationDesc);
		location.setIsEnabled(isEnabled);
		location.setUpdateId(userId);
		location.setUpdateTime(new Date());
		location.setAccountId(accountId);
		location.setLocationLongitude(locationLongitude);
		location.setLocationLatitude(locationLatitude);
		location.setLocationZoom(locationZoom);
		location.setCountryId(countryId);
		location.setProvinceId(provinceId);
		location.setCityId(cityId);
		if (StringUtils.isEmpty(locationId)) {
			locationsMapper.insertSelective(location);
		} else {
			locationsMapper.updateByPrimaryKeySelective(location);
		}

	}

	public Locations getLocation(String locationId) throws DeodioException {
		
		return locationsMapper.selectByPrimaryKey(locationId);
	}
public Locations getPreviewLocation(String locationId) throws DeodioException {
		
		return locationsMapper.selectByPrimaryKey(locationId);
	}

	public void delLocation(String locationId) throws DeodioException {
		
		locationsMapper.deleteByPrimaryKey(locationId);
	}

	public void delAllLocations(String locationIds) throws DeodioException {

		String[] arr = locationIds.split(",");
		List<Locations> locationList = new ArrayList<Locations>();
		for (String locationId : arr) {
			Locations location = new Locations();
			location.setId(locationId);
			locationList.add(location);
		}
		locationsCustomizeMapper.delAllLocations(locationList);
	}

}
