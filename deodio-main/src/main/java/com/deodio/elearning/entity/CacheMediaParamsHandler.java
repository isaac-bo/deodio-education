package com.deodio.elearning.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/*import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;*/

import com.deodio.convert.socket.model.CommandModel;
import com.deodio.convert.socket.model.ICallBackHandler;
import com.deodio.convert.socket.model.MediaResult;
import com.deodio.core.utils.MemcachedUtils;

public class CacheMediaParamsHandler implements ICallBackHandler{
	
	private MemcachedUtils memUtils;
	/*private final String MAP_REGEX = "(?<=map\\(\\) completion:\\s).*";
    private final String REDUCE_REGEX = "(?<=reduce\\(\\) completion:\\s).*";
    private final String STATUS_REGEX = "(?<=Job state:\\s).*";*/
	
	private static final String STREAM_LINE_REGEX = "Stream #0:0.*";
	private static final String DURATION_LINE_REGEX = "Duration:.*";
	private static final String CONVERT_LINE_REGEX = "ConvertFormat:.*";
	//帧数值
	private static final String FRAME_VALUE_REGEX = ".*\\b([1-9]+\\d*)x([1-9]+\\d*)\\b.*";
	//时长数值数据
	private static final String DURATION_VALUE_REGEX = ".*\\b(\\d{2}):(\\d{2}):(\\d*)\\b.*";
	//转换格式数据
	private static final String CONVERT_VALUE_REGEX = "ConvertFormat:\\s*(\\w+)";
	
	public static final String TEST_STR = "CommandModel [processType=3, query=null, fid=FGGW66ICV3KT6OPs15hDewOPpkQi0Hhx, filePath=/mnt/medies/deodioStatic/vod/4714bc52d79d491a83c31d33f771ade3/ce56ea4f40564e67a929a94c374f2e32/1cd6ac15fd6144679e9a0bb05b44252e/2017/2/7/FGGW66ICV3KT6OPs15hDewOPpkQi0Hhx.mp4, status=1, result=    encoder         : Lavf54.63.100\n"
									+"Duration: 00:00:05.01, start: 0.000000, bitrate: 1124 kb/s\n"
									+"Stream #0:0(und): Video: h264 (Main) (avc1 / 0x31637661), yuv420p, 1280x720, 951 kb/s, 29.97 fps, 29.97 tbr, 11988 tbn, 59.94 tbc (default)\n"
									+"ConvertFormat: mp4\n"
									+"]"; 
    
    
	public CacheMediaParamsHandler(MemcachedUtils memUtils) {
		super();
		this.memUtils = memUtils;
	}

	public static  String matchString(String sourceStr, String regex){
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
  
  	public static  Matcher matchArray(String sourceStr, String regex){
	  		Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(sourceStr);
			if(!matcher.matches()){
				matcher = null;
			}
			return matcher;
  	}
  	
  	public static double getTotalTime(Matcher durationValueMatcher){
  		double result = 0;
  		try{
  			if(durationValueMatcher != null && durationValueMatcher.groupCount() == 3){
  				Double hour =Double.parseDouble(durationValueMatcher.group(1));
  				Double min =Double.parseDouble(durationValueMatcher.group(2));
  				Double sec =Double.parseDouble(durationValueMatcher.group(3));
  				result = hour*3600+min*60+sec;
  			}
  		}catch(Exception ex){
  			ex.printStackTrace();
  		}
  		return result;
  	}
  	
  	public static Integer getFrameValue(Matcher frameValueMatcher,int groupIndex){
  		Integer res = 0;
  		String result = getMatcherValue(frameValueMatcher,groupIndex);
  		if(StringUtils.isNotBlank(result)){
  			res = Integer.valueOf(result);
  		}
  		return res;
  	}
  	
  	public static String getMatcherValue(Matcher valueMatcher,int groupIndex){
  		String value = "";
  		if(valueMatcher != null && valueMatcher.groupCount() >= groupIndex){
  			value = valueMatcher.group(groupIndex);
  		}
  		return value;
  	}
  	
  
  	public static void main(String[] args) {
  		
  		String durationStr  = matchString(TEST_STR,DURATION_LINE_REGEX);
  		String streamStr = matchString(TEST_STR,STREAM_LINE_REGEX);
  		String convertStr = matchString(TEST_STR,CONVERT_LINE_REGEX);
  		
  		double total = getTotalTime(matchArray(durationStr,DURATION_VALUE_REGEX));
  		
  		Matcher frameValueMatcher = matchArray(streamStr,FRAME_VALUE_REGEX);
  		Integer width = getFrameValue(frameValueMatcher,1);
  		Integer hight = getFrameValue(frameValueMatcher,2);
  		
  		Matcher convertValueMatcher = matchArray(convertStr,CONVERT_VALUE_REGEX);
  		String convertFormat = getMatcherValue(convertValueMatcher,1);
  		
		System.out.println("total:" + total);
  		System.out.println("width:" + width);
		System.out.println("hight:" + hight);
		System.out.println("convertFormat:" + convertFormat);
  	}
	
	@Override
	public void execute(Object obj) {
		CommandModel commandModel = (CommandModel)obj;
		String fid = commandModel.getFid();
		String returnStr = commandModel.getResult();
		
		String durationStr  = matchString(returnStr,DURATION_LINE_REGEX);
  		String streamStr = matchString(returnStr,STREAM_LINE_REGEX);
  		String convertStr = matchString(returnStr,CONVERT_LINE_REGEX);
  		//总时长
  		double total = getTotalTime(matchArray(durationStr,DURATION_VALUE_REGEX));
  		//帧数据
  		Matcher frameValueMatcher = matchArray(streamStr,FRAME_VALUE_REGEX);
  		Integer width = getFrameValue(frameValueMatcher,1);
  		Integer hight = getFrameValue(frameValueMatcher,2);
  		//转换的格式
  		Matcher convertValueMatcher = matchArray(convertStr,CONVERT_VALUE_REGEX);
  		String convertFormat = getMatcherValue(convertValueMatcher,1);
		
		long totalTime = (long) Math.floor(total);
		int mediaHeight = hight;
		int mediaWidth = width;
		
		MediaResult mediaResult = new MediaResult();
		//整个视频长度
		mediaResult.setMediaLength(totalTime);
		//整个视频宽度
		mediaResult.setMediaWidth(mediaWidth);
		//视频宽度
		mediaResult.setMediaHeight(mediaHeight);
		//转换格式
		mediaResult.setConvertFormat(convertFormat);
		memUtils.set("q_media_"+fid, mediaResult);
		
	}

}
