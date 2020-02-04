package com.deodio.elearning.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.DicCityModelMapper;
import com.deodio.elearning.persistence.mapper.DicCountryModelMapper;
import com.deodio.elearning.persistence.mapper.DicProvinceModelMapper;
import com.deodio.elearning.persistence.model.DicCityModel;
import com.deodio.elearning.persistence.model.DicCityModelExample;
import com.deodio.elearning.persistence.model.DicCountryModel;
import com.deodio.elearning.persistence.model.DicProvinceModel;
import com.deodio.elearning.persistence.model.DicProvinceModelExample;

@Service
public class RegionService {
	
	@Autowired
	private DicProvinceModelMapper dicProvinceModelMapper;
	@Autowired
	private DicCityModelMapper dicCityModelMapper;
	@Autowired
	private DicCountryModelMapper dicCountryModelMapper;
	
	
	
	/**
	 * 获得省/州地区
	 * @Title: getDicProvinceModelList
	 * @param countryId
	 * @return
	 * @throws DeodioException
	 * @return List<DicProvinceModel>
	 */
	public List<DicProvinceModel> getDicProvinceModelList(Integer countryId)throws DeodioException{
		DicProvinceModelExample example = new DicProvinceModelExample();
		
		if(null == countryId){
			example = null;
		}else{
			example.createCriteria().andCountryIdEqualTo(countryId);
		}
		
		return dicProvinceModelMapper.selectByExample(example);
	}
	
	/**
	 * 获得省/州地区
	 * @Title: getDicProvinceModelList
	 * @param countryId
	 * @return
	 * @throws DeodioException
	 * @return List<DicProvinceModel>
	 */
	public List<DicProvinceModel> getDicProvinceModelList()throws DeodioException{
		
		return getDicProvinceModelList(null);
	}
	
	/**
	 * 获取城市信息
	 * @Title: getDicCityModelList
	 * @param provinceId
	 * @return
	 * @throws DeodioException
	 * @return List<DicCityModel>
	 */
	public List<DicCityModel> getDicCityModelList(Integer provinceId) throws DeodioException{
		DicCityModelExample example = new DicCityModelExample();
		
		if(null == provinceId){
			example = null;
		}else{
			example.createCriteria().andProvinceIdEqualTo(provinceId);
		}
		
		return dicCityModelMapper.selectByExample(example);
	}
	
	/**
	 * 获取全部城市信息
	 * @Title: getDicCityModelList
	 * @param provinceId
	 * @return
	 * @throws DeodioException
	 * @return List<DicCityModel>
	 */
	public List<DicCityModel> getDicCityModelList() throws DeodioException{
		return getDicCityModelList(null);
	}
	
	/**
	 * 获取国家信息
	 * @Title: getDicCountryModelList
	 * @return
	 * @throws DeodioException
	 * @return List<DicCountryModel>
	 */
	public List<DicCountryModel> getDicCountryModelList()throws DeodioException{
		return dicCountryModelMapper.selectByExample(null);
	}
	
}
