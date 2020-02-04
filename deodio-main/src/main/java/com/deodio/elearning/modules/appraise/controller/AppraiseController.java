package com.deodio.elearning.modules.appraise.controller;

import com.deodio.core.constants.Constants;
import com.deodio.core.controller.BaseController;
import com.deodio.core.exception.CommonException;
import com.deodio.core.utils.AjaxResultModel;
import com.deodio.core.utils.CookieUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.modules.appraise.service.AppraiseService;
import com.deodio.elearning.persistence.model.TraineeAppraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * 评价
 *
 * @author P0113869
 * @create 2018-03-19 14:12
 */
@Controller
public class AppraiseController extends BaseController{

    @Autowired
    private AppraiseService appraiseService;

    /**
     * 保存评价信息
     * @param request
     * @param traineeAppraise
     * @return
     * @throws CommonException
     */
    @RequestMapping("/course/appraise/submit" + Constants.URL_SUFFIX)
    @ResponseBody
    public AjaxResultModel submitAppraise(TraineeAppraise traineeAppraise) throws CommonException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        String userId = (String)CookieUtils.getCookie(request, DConstants.COOKIE_USER_ID);
        String accountId = (String) CookieUtils.getCookie(request, DConstants.COOKIE_ACCOUNT_ID);
        
        traineeAppraise.setTraineeId(userId);
        traineeAppraise.setAccountId(accountId);
        traineeAppraise.setCreateId(userId);
        traineeAppraise.setCreateTime(new Date());
        traineeAppraise.setUpdateId(userId);
        traineeAppraise.setUpdateTime(new Date());
        
        return this.getAjaxResult(appraiseService.saveAppraise(traineeAppraise), AjaxResultModel.SUCCESS);

    }
    
    @RequestMapping("/course/appraise/detail" + Constants.URL_SUFFIX)
    @ResponseBody
    public AjaxResultModel getAppraise(@RequestParam String courseId) throws CommonException {
    	System.out.println("---------------------------------------------------------");
    	return this.getAjaxResult(appraiseService.getAppraise(courseId), AjaxResultModel.SUCCESS);
    }
}
