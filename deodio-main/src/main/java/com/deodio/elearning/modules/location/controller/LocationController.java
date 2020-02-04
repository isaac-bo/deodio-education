package com.deodio.elearning.modules.location.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.elearning.commons.service.RegionService;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.location.service.LocationService;
import com.deodio.elearning.persistence.model.AjaxResultModel;

@Controller
public class LocationController extends BaseController {

	@Autowired
	LocationService locationService;

	@Autowired
	private RegionService regionService;

	@RequestMapping("/location/list" + Constants.URL_SUFFIX)
	public String toLocationList(Model model) throws DeodioException {

		model.addAttribute("provinceList", regionService.getDicProvinceModelList(1));
		return "/modules/location/location_list";
	}

	@ResponseBody
	@RequestMapping("/location/select_province" + Constants.URL_SUFFIX)
	public AjaxResultModel selectProince(Model model, @RequestParam Integer countryId) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("provinceList", regionService.getDicProvinceModelList(countryId));
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@ResponseBody
	@RequestMapping("/location/select_city" + Constants.URL_SUFFIX)
	public AjaxResultModel selectCity(Model model, @RequestParam Integer provinceId) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		model.addAttribute("cityList", regionService.getDicCityModelList(provinceId));
		jsonMap.put("cityList", regionService.getDicCityModelList(provinceId));
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/location/load_location_list" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel loadLocationList(@RequestParam Integer pageNo,
			@RequestParam(required = false) String keywords, @RequestParam Integer pageSize, HttpServletRequest request)
					throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		PageRequest pageRequest = new PageRequest(pageNo);
		if (pageSize != null && pageSize.compareTo(0) > 0) {
			pageRequest.setPageSize(pageSize);
			pageRequest.getPagination().setPageSize(pageSize);
		}
		List<Map<String, Object>> dataList = locationService.findLocationsList(pageRequest,
				this.getCookieAccount(request), keywords, this.getCookieUserId(request));
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("dataList", dataList);
		jsonMap.put("currePage", pageNo);
		jsonMap.put("totalPage", pageRequest.getPageTotal());
		arm.setData(jsonMap);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/location/crerate_location" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel createLocation(@RequestParam(required = false) String locationId,
			@RequestParam String locationName, @RequestParam String locationAddress, @RequestParam String locationDesc,
			@RequestParam Integer isEnabled, HttpServletRequest request, @RequestParam String locationLongitude,
			@RequestParam String locationLatitude, @RequestParam Integer locationZoom, @RequestParam Integer countryId,
			@RequestParam Integer provinceId, @RequestParam Integer cityId) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		locationService.saveOrUpdateLocation(locationId, locationName, locationAddress, locationDesc, isEnabled,
				this.getCookieAccount(request), this.getCookieUserId(request), locationLongitude, locationLatitude,
				locationZoom, countryId, provinceId, cityId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	
	//校验名字
		@RequestMapping("/location/getCheck_location" + Constants.URL_SUFFIX)
		@ResponseBody
		public AjaxResultModel getCheckLocationName(
				@RequestParam String locationName,
				
				HttpServletRequest request, HttpServletResponse response) {
			AjaxResultModel arm = new AjaxResultModel();	
			arm.setData(locationService.getCheckLocationName(locationName));
			arm.setStatus(AjaxResultModel.SUCCESS);
			return arm;
	}

	@RequestMapping("/location/get_location" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel getLocation(@RequestParam String locationId, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(locationService.getLocation(locationId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
	//预览场地
	@RequestMapping("/location/get_preview_location" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel priviewLocation(@RequestParam String locationId, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		arm.setData(locationService.getPreviewLocation(locationId));
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/location/del_location" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delLocation(@RequestParam String locationId, HttpServletRequest request)
			throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		locationService.delLocation(locationId);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}

	@RequestMapping("/location/del_all_locations" + Constants.URL_SUFFIX)
	@ResponseBody
	public AjaxResultModel delAllLocations(@RequestParam(required = false) String locationIds,
			HttpServletRequest request) throws DeodioException {

		AjaxResultModel arm = new AjaxResultModel();
		locationService.delAllLocations(locationIds);
		arm.setStatus(AjaxResultModel.SUCCESS);
		return arm;
	}
}
