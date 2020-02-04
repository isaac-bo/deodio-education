package com.deodio.elearning.modules.trainers.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.components.pagination.page.PageData;
import com.deodio.components.pagination.page.PageRequest;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.TrainersMapper;
import com.deodio.elearning.persistence.mapper.customize.TrainersCustomizeMapper;
import com.deodio.elearning.persistence.model.Locations;
import com.deodio.elearning.persistence.model.LocationsExample;
import com.deodio.elearning.persistence.model.Trainers;
import com.deodio.elearning.persistence.model.TrainersExample;

@Service
public class TrainersService {

	@Autowired
	TrainersMapper trainersMapper;

	@Autowired
	TrainersCustomizeMapper trainersCustomizeMapper;

	public List<Map<String, Object>> getTrainerList(String keywords, String accountId,String trainerType) throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("keywords", StringUtils.isBlank(keywords) ? null : keywords);
		params.put("trainerType", StringUtils.isBlank(trainerType) ? null:Integer.parseInt(trainerType));
		return trainersCustomizeMapper.getTrainerList(params);
	}

	public List<Map<String, Object>> findTrainerList(PageRequest pageRequest, String accountId, String keywords, String userId)
			throws DeodioException {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		params.put("keywords", StringUtils.isBlank(keywords) ? null : keywords);
		params.put("pagination", pageRequest);
		params.put("userId", userId);
		return trainersCustomizeMapper.findTrainerList(params);
	}

	public Trainers getTrainers(String trainerId) throws DeodioException {
		return trainersMapper.selectByPrimaryKey(trainerId);
	}
	public Trainers getPreviewTrainers(String trainerId) throws DeodioException {
		return trainersMapper.selectByPrimaryKey(trainerId);
	}
	//校验名字
			public boolean getCheckTrainerName(String trainerName) {
				TrainersExample example=new TrainersExample();
				example.createCriteria().andTrainerNameEqualTo(trainerName);
				List<Trainers> list=trainersMapper.selectByExample(example);
				return list.isEmpty()?false:true;
			}
			
	public void saveUpdateTrainer(String trainerId, Integer trainerType, Integer trainerGender, String trainerName,
			String trainerTitle, String trainerMobilePhone, String trainerEmail, Integer trainerLevel,
			String trainerOrganization, Integer isRecommended, String trainerDesc, String trainerPortraitUrl,
			String userId, String accountId) throws DeodioException {

		Trainers trainer = new Trainers();
		if (trainerId.isEmpty()) {
			trainer.setId(AppUtils.uuid());
			trainer.setCreateId(userId);
			trainer.setCreateTime(new Date());
		} else {
			trainer = trainersMapper.selectByPrimaryKey(trainerId);
		}
		trainer.setIsRecommended(isRecommended);
		trainer.setTrainerDesc(trainerDesc);
		trainer.setTrainerGender(trainerGender);
		trainer.setTrainerLevel(trainerLevel);
		trainer.setTrainerMail(trainerEmail);
		trainer.setTrainerMobilePhone(trainerMobilePhone);
		trainer.setTrainerName(trainerName);
		trainer.setTrainerOrganization(trainerOrganization);
		trainer.setTrainerTitle(trainerTitle);
		trainer.setTrainerType(trainerType);
		trainer.setTrainerPortraitUrl(trainerPortraitUrl);
		trainer.setUpdateId(userId);
		trainer.setUpdateTime(new Date());
		trainer.setAccountId(accountId);
		if (trainerId.isEmpty()) {
			trainersMapper.insertSelective(trainer);
		} else {
			trainersMapper.updateByPrimaryKeySelective(trainer);
		}

	}

	public void delTrainers(String trainerId) throws DeodioException {
		
		trainersMapper.deleteByPrimaryKey(trainerId);
	}

	public void delAllTrainers(String trainerIds) throws DeodioException {

		if (!(StringUtils.isBlank(trainerIds))) {
			String[] arr = trainerIds.split(",");
			List<Trainers> trainersList = new ArrayList<Trainers>();
			for (String id : arr) {
				Trainers trainers = new Trainers();
				trainers.setId(id);
				trainersList.add(trainers);
			}
			trainersCustomizeMapper.delAllTrainers(trainersList);
		}
	}

	/**
	 * 获取学员课程 考试、调查问卷及作业 列表
	 * @param params groupId traineeId courseId accountId
	 * @return
	 */
	public List<Map<String,Object>> getTrainerCourseRelate(Map<String,Object> params){
		return trainersCustomizeMapper.getTrainerCourseRelate(params);
	}
	
	/**
	 * 获取分享的讲师列表
	 * @param accountId
	 * @param userId
	 * @param keyWord
	 * @param pageRequest
	 * @return
	 */
	public PageData<Map<String, Object>> findLecturerList(String accountId, 
			String groupId, String userId, String keyWord, PageRequest pageRequest) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("accountId", accountId);
		param.put("groupId", groupId);
		param.put("createId", userId);
		param.put("keyWord", keyWord);
		param.put("pagination", pageRequest);
		PageData<Map<String, Object>> page = new PageData<Map<String, Object>>();
		page.setList(trainersCustomizeMapper.findLecturerList(param));
		page.setPageRequest((PageRequest)param.get("pagination"));
		
		return page;
	}
}
