package com.deodio.elearning.modules.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.modules.appraise.service.AppraiseService;
import com.deodio.elearning.persistence.mapper.TraineeAppraiseMapper;
import com.deodio.elearning.persistence.model.TraineeAppraise;
import com.deodio.elearning.persistence.model.TraineeAppraiseExample;

/**
 * 
    * @ClassName: AppraiseServiceImpl  
    * @Description: 评价 
    * @author P0113869  
    * @date 2018年3月28日 下午5:58:35  
    *
 */
@Service
public class AppraiseServiceImpl extends BaseService implements AppraiseService {

	@Autowired
    private TraineeAppraiseMapper traineeAppraiseMapper;
	
	@Override
	public int saveAppraise(TraineeAppraise traineeAppraise) throws DeodioException {
		traineeAppraise.setId(AppUtils.uuid());	
        return traineeAppraiseMapper.insert(traineeAppraise);
	}

	@Override
	public TraineeAppraise getAppraise(String contentId) throws DeodioException {
		TraineeAppraiseExample example = new TraineeAppraiseExample();
		example.createCriteria().andContentIdEqualTo(contentId);
		
		return traineeAppraiseMapper.selectByExample(example).get(0);
	}
	
}