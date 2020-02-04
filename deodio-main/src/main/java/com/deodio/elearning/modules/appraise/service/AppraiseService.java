package com.deodio.elearning.modules.appraise.service;

import com.deodio.elearning.persistence.model.TraineeAppraise;

/**
 * 评价
 *
 * @author P0113869
 * @create 2018-03-19 14:13
 */
public interface AppraiseService {

    /**
     * 保存评价信息
     * @param traineeAppraise
     */
    int saveAppraise(TraineeAppraise traineeAppraise);
    /**
     * 
     * @Title: getAppraise   
     * @Description: 查询评价信息   
     * @param: @param contentId  
     * @return: TraineeAppraise      
     * @throws
     */
    TraineeAppraise getAppraise(String contentId);
}
