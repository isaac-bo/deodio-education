package com.deodio.elearning.commons.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.messaging.MessageTemplate;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.MemcachedUtils;

import flex.messaging.MessageBroker;
import flex.messaging.messages.AsyncMessage;
import flex.messaging.MessageBrokerServlet;
import flex.messaging.util.UUIDUtils;


@Service("messageService")
public class MessageService {

	@Autowired
	private MemcachedUtils cacheUtils;
	
	public void executeLiveStudio(String presentationId,String presentationCreator){
		cacheUtils.set(presentationId+"_"+presentationCreator, Boolean.TRUE);
	}
	
	public void executeCurrentLiveUser(String presentationId,String currentUserId){
		cacheUtils.set(presentationId+"_current_user", currentUserId);
	}
	
	
	public Object getMemcacheValue(String key){
		return cacheUtils.get(key);
	}
	
	public void stopLiveStudio(String presentationId,String presentationCreator){
		cacheUtils.set(presentationId+"_"+presentationCreator, Boolean.FALSE);
	}
	
	public void executeRequestLiveRoom(String presentationId,String userId){
		List requestList = null;
		if(cacheUtils.get(presentationId+"_liveroom")!=null && cacheUtils.get(presentationId+"_liveroom") instanceof ArrayList){
			requestList = (ArrayList)cacheUtils.get(presentationId+"_liveroom");
		}else{
			requestList = new ArrayList();
		}
		
		requestList.add(userId);
		List requestListWithoutDup = new ArrayList(new HashSet(requestList));
		cacheUtils.set(presentationId+"_liveroom", requestListWithoutDup );
	}
}
