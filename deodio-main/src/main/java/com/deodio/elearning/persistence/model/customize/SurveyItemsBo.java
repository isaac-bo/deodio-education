package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.SurveyItems;

public class SurveyItemsBo extends SurveyItems {
	/**
	 *  0：保存  1  提交  
	 */
	private int operateType;

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
}
