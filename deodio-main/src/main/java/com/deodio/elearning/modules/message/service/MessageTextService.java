package com.deodio.elearning.modules.message.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.mapper.MessageMapper;
import com.deodio.elearning.persistence.mapper.MessageTextMapper;
import com.deodio.elearning.persistence.mapper.customize.MessageTextCustomizeMapper;
import com.deodio.elearning.persistence.model.Message;
import com.deodio.elearning.persistence.model.MessageText;


@Service
public class MessageTextService {
	
	@Autowired
	private MessageTextCustomizeMapper messageTextCustomizeMapper;
	
	@Autowired
	private MessageTextMapper messageTextMapper;
	
	@Autowired
	private MessageMapper messageMapper;
	
	 public List<Map<String, Object>> findMessageList(Map<String, Object> map){
		
		return messageTextCustomizeMapper.findMessageTextList(map);
	 }
	 
	 
	 /*
		 * 我的小组--邀请加入--站内信添加
		 */
		public void insertMessage(String title,String content,String createId,String status,String messageType,Integer recType,String recId,String viewStatus,String logo){
			MessageText messageText=new MessageText();
			messageText.setMessageTextId(AppUtils.uuid());
			messageText.setTitle(title);
			messageText.setText(content);
			messageText.setStatus(status);
			messageText.setSendDate(new Date());
			messageText.setMessageType(messageType);
			messageText.setCreateBy(createId);
			messageText.setCreateDate(new Date());
			messageText.setRecType(recType);
			messageText.setImgUrl(logo);
			messageTextMapper.insertSelective(messageText);
			
			Message message=new Message();
			message.setMessageId(AppUtils.uuid());
			message.setSendId(createId);
			message.setRecId(recId);
			message.setMessageTextId(messageText.getMessageTextId());
			message.setViewStatus(viewStatus);
			message.setCreateDate(new Date());
			messageMapper.insert(message);
		}

}
