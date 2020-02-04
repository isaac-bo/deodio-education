package com.deodio.elearning.entity;

import com.deodio.convert.socket.model.CommandModel;
import com.deodio.convert.socket.model.ICallBackHandler;
import com.deodio.convert.socket.model.MRResult;
import com.deodio.core.utils.MemcachedUtils;

public class CacheDeleteResultHandler implements ICallBackHandler{
	
	private MemcachedUtils memUtils;
	
	public CacheDeleteResultHandler(MemcachedUtils memUtils) {
		super();
		this.memUtils = memUtils;
	}
	
	@Override
	public void execute(Object obj) {
		CommandModel commandModel = (CommandModel)obj;
		String fid = commandModel.getFid();
    	MRResult mResult = new MRResult();
    	mResult.setStatus("1");
    	memUtils.set("q_delete_"+fid, mResult);
	}

}
