package com.deodio.elearning.persistence.model.customize;

import com.deodio.elearning.persistence.model.TraineeFavorite;

public class TraineeFavoriteBo extends TraineeFavorite {
//	INSERT = 0;UPDATE = 1;DEL = 2;
	private int operateType;

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	
}
