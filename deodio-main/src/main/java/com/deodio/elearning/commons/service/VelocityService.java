package com.deodio.elearning.commons.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.service.BaseService;
import com.deodio.elearning.persistence.mapper.SystemTemplateMapper;
import com.deodio.elearning.persistence.model.SystemTemplate;

@Service
public class VelocityService extends BaseService{
	@Autowired
	private SystemTemplateMapper systemTemplateMapper;
	@Autowired  
	private VelocityEngine velocityEngine;
	/**
	 * 获得 根据模板类型 velocity 模板
	 * @Title: getVelocityTemplate
	 * @param templateId
	 * @param data
	 * @return
	 * @throws Exception
	 * @return String
	 */
	public String getVelocityTemplate(Integer templateId,Object data) throws Exception{
		
		SystemTemplate st = systemTemplateMapper.selectByPrimaryKey(templateId);
		
		String templateConent = st.getTemplateContent();
		
		Template velocity_template = velocityEngine.getTemplate(templateConent);
		
		// 2.取得velocity的上下文context
		VelocityContext context = new VelocityContext();
		// 3.把数据填入上下文
		context.put("data", data);
		
		// 5. 输出流
		StringWriter writer = new StringWriter();
		// 6.转换输出
		velocity_template.merge(context, writer);
		
		return writer.toString();
		
	}
	
	/**
	 * 获取模板内容，用于群发。避免多次数据库调用
	 * @Title: getVelocityTemplate
	 * @param templateId
	 * @return
	 * @throws Exception
	 * @return Template
	 */
	public List<String> getVelocityTemplate(Integer templateId,List<Map<String,Object>> contentMaps)throws Exception{
		SystemTemplate st = systemTemplateMapper.selectByPrimaryKey(templateId);
		
		String templateConent = st.getTemplateContent();
		
		Template velocity_template =  velocityEngine.getTemplate(templateConent);
		
		 List<String> contents = new ArrayList<String>();
		// 2.取得velocity的上下文context
		VelocityContext context=null;
		for(Map<String,Object> objMap : contentMaps){
			context = new VelocityContext();
			// 3.把数据填入上下文
			context.put("data", objMap);
			
			// 5. 输出流
			StringWriter writer = new StringWriter();
			// 6.转换输出
			velocity_template.merge(context, writer);
			
			contents.add(writer.toString());
		}
		return contents;
	}
	
	
}
