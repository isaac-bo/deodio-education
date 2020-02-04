package com.deodio.elearning.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deodio.convert.socket.model.CommandModel;
import com.deodio.convert.socket.model.ICallBackHandler;
import com.deodio.convert.socket.model.MRResult;
import com.deodio.core.utils.MemcachedUtils;


public class CacheResultHandler implements ICallBackHandler{
	
	private MemcachedUtils memUtils;
	private final String MAP_REGEX = "(?<=map\\(\\) completion:\\s).*";
    private final String REDUCE_REGEX = "(?<=reduce\\(\\) completion:\\s).*";
    private final String STATUS_REGEX = "(?<=Job state:\\s).*";
  
    
    
  public CacheResultHandler(MemcachedUtils memUtils) {
		super();
		this.memUtils = memUtils;
	}

private String matchString(String sourceStr, String regex){
  	Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sourceStr);
		String result = null;
		while(matcher.find()){
			for (int i = 0; i <= matcher.groupCount(); i++) {
				result =  matcher.group(i);
			}
		}
		return result;
  }
	
	@Override
	public void execute(Object obj) {
		CommandModel commandModel = (CommandModel)obj;
		String fid = commandModel.getFid();
		String returnStr = commandModel.getResult();
    	MRResult mResult = new MRResult();
    	mResult.setmProcess(matchString(returnStr,MAP_REGEX));
    	mResult.setrProcess(matchString(returnStr,REDUCE_REGEX));
    	mResult.setMrStat(matchString(returnStr,STATUS_REGEX));
    	memUtils.set("q_model_"+fid, mResult);
		
	}

}
