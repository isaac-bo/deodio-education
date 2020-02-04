package com.deodio.elearning.modules.convert.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.convert.nio.client.SocketClient;
import com.deodio.convert.socket.model.CommandModel;
import com.deodio.convert.socket.model.MRResult;
import com.deodio.convert.socket.model.MediaResult;
import com.deodio.core.constants.Constants;
import com.deodio.core.utils.MemcachedUtils;
import com.deodio.elearning.entity.CacheDeleteResultHandler;
import com.deodio.elearning.entity.CacheMediaParamsHandler;
import com.deodio.elearning.entity.CacheResultHandler;
import com.deodio.elearning.utils.CommonUtils;
import com.deodio.elearning.utils.PPTUtils;


@Service
public class ConvertService {
	
	@Autowired
	private MemcachedUtils cacheUtils ;
	
	public boolean mediaTranscoding(String filePath){
		//if(isNeedConvert(StringUtils.substringAfter( filePath, "."))){
	    	CommandModel commandModel = new CommandModel();
	    	commandModel.setProcessType(Constants.SOCKET_MODEL_PROCESSTYPE_MEDIA_CONVERT);
	    	commandModel.setFilePath(filePath);
	    	try {
	    		Long t1 = System.currentTimeMillis();
				new SocketClient(CommonUtils.CONVERT_SERVER_URL,Integer.parseInt(CommonUtils.CONVERT_SERVER_PORT),commandModel,null).run();
				Long t2 = System.currentTimeMillis();
				System.out.println(t2-t1);
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	    	return Boolean.TRUE;
		/*}else{
			return Boolean.FALSE;
		}*/
	}
	
	/**
	 * 查询多媒体转换进度
	 * @param fid
	 * @param jobId
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public MRResult queryProcess(String fid,String jobId) throws NumberFormatException, Exception{
		MRResult result =null;
		//查询起始时间
		Date startTime = new Date();
		//每次执行结束时间
		Date endTime = null;
		//程序强制运行结束时间(毫秒)
		Long timeOut = (long) 300000;
		if(null==jobId||"".equals(jobId)){
			while(true){
				Thread.sleep(5000);
				jobId = cacheUtils.get("q_task_"+ fid);
				System.out.println("########jobId#######:"+jobId);
				 if(null!=jobId){
					 break;
				 }
				 endTime = new Date();
				//程序运行
				Long runningTime = endTime.getTime()-startTime.getTime();
				System.out.println("#####运行时间####:"+runningTime);
				if(runningTime>timeOut){
					result = new MRResult();
					result.setmProcess("-1");
					result.setrProcess("-1");
					return result;
				}
			}
		}
		CommandModel commandModel = new CommandModel();
    	commandModel.setProcessType(Constants.SOCKET_MODEL_PROCESSTYPE_PROGRESS_QUERY);
    	commandModel.setFid(fid);
    	commandModel.setQuery(jobId);
    	new SocketClient(CommonUtils.CONVERT_SERVER_URL,Integer.parseInt(CommonUtils.CONVERT_SERVER_PORT),commandModel,new CacheResultHandler(cacheUtils)).run();
    	if(cacheUtils.get("q_model_"+fid)!=null){
    		result = cacheUtils.get("q_model_"+fid);
    	}else{
    		result = new MRResult();
    		result.setmProcess("-1");
			result.setrProcess("-1");
    	}
    	result.setJobId(jobId);
    	return result;
	}
	
	/**
	 * 查询多媒体参数
	 * @param jobId
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public MediaResult queryParams(String filePath,String fid) throws NumberFormatException, Exception{
		MediaResult result = null;
		CommandModel commandModel = new CommandModel();
    	commandModel.setProcessType(Constants.SOCKET_MODEL_PROCESSTYPE_PARAMS_QUERY);
    	commandModel.setFilePath(filePath);
    	commandModel.setFid(fid);
    	new SocketClient(CommonUtils.CONVERT_SERVER_URL,Integer.parseInt(CommonUtils.CONVERT_SERVER_PORT),commandModel,new CacheMediaParamsHandler(cacheUtils)).run();
    	if(cacheUtils.get("q_media_"+fid)!=null){
    		result = cacheUtils.get("q_media_"+fid);
    	}else{
    		result = new MediaResult();
    	}
    	return result;
	}
	
	public Boolean ppt2png(String pptFile,String pngFolder) throws Exception{
		return PPTUtils.ppt2png(pptFile, pngFolder);
	}
	
	private boolean isNeedConvert(String fileExt){
		if(fileExt.equalsIgnoreCase("mp4")){
			return Boolean.FALSE;
		}else{
			return Boolean.TRUE;
		}
	}
	
	/**
	 * 取消转换进程
	 * @param jobId
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public MRResult delConverMedia(String jobId,String fid) throws NumberFormatException, Exception{
		MRResult result = null;
		CommandModel commandModel = new CommandModel();
    	commandModel.setProcessType(Constants.SOCKET_MODEL_PROCESSTYPE_MEDIA_DELETE);
    	commandModel.setQuery(jobId);
    	new SocketClient(CommonUtils.CONVERT_SERVER_URL,Integer.parseInt(CommonUtils.CONVERT_SERVER_PORT),commandModel,new CacheDeleteResultHandler(cacheUtils)).run();
    	if(cacheUtils.get("q_delete_"+fid)!=null){
    		result = cacheUtils.get("q_delete_"+fid);
    	}else{
    		result = new MRResult();
    	}
    	return result;
	}
	
}
