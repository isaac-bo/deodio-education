package com.deodio.elearning.modules.presentation.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.AttachmentPackageItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationFilesMapper;
import com.deodio.elearning.persistence.mapper.PresentationMapper;
import com.deodio.elearning.persistence.mapper.PresentationModelPackageMapper;
import com.deodio.elearning.persistence.mapper.PresentationPackageItemMapper;
import com.deodio.elearning.persistence.mapper.PresentationScromItemMapper;
import com.deodio.elearning.persistence.mapper.customize.PresentationCustomizeMapper;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentPackageItem;
import com.deodio.elearning.persistence.model.AttachmentPackageItemExample;
import com.deodio.elearning.persistence.model.Presentation;
import com.deodio.elearning.persistence.model.PresentationFiles;
import com.deodio.elearning.persistence.model.PresentationFilesExample;
import com.deodio.elearning.persistence.model.PresentationModelPackage;
import com.deodio.elearning.persistence.model.PresentationModelPackageExample;
import com.deodio.elearning.persistence.model.PresentationPackageItem;
import com.deodio.elearning.persistence.model.PresentationPackageItemExample;
import com.deodio.elearning.persistence.model.PresentationScromItem;
import com.deodio.elearning.persistence.model.PresentationScromItemExample;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class PresentationPackageService extends PresentationService{
	
	@Autowired
	private PresentationModelPackageMapper presentationModelPackageMapper;
	
	@Autowired
	private PresentationCustomizeMapper presentationCustomizeMapper;
	
	@Autowired
	private PresentationPackageItemMapper presentationPackageItemMapper;
	
	@Autowired
	private PresentationMapper presentationMapper;
	
	@Autowired
	private PresentationFilesMapper presentationFilesMapper;
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private AttachmentPackageItemMapper attachmentPackageItemMapper;
	
	@Autowired
	private PresentationScromItemMapper presentationScromItemMapper;
	
	
	
	/**
	 * 非标准课件包设置
	 * @param params
	 * @return
	 */
	public Integer submitPresentationPackageProfile(String packagesItemsJson,String userId,String presentationId,String packageProfileJson){
		
		Gson json = new Gson();
		int rowCount = DConstants.NUMBER_ZERO;
		if(StringUtils.isNotBlank(packageProfileJson)){
			PresentationModelPackage presentationPackage = json.fromJson(packageProfileJson, new TypeToken<PresentationModelPackage>(){}.getType());
			Presentation presentation = json.fromJson(packageProfileJson, new TypeToken<Presentation>(){}.getType());
			
			//更新章节主表信息
			if(null != presentation){
				presentation.setUpdateId(userId);
				presentation.setUpdateTime(new Date());
				rowCount = presentationMapper.updateByPrimaryKeySelective(presentation);
			}
			
			
			//开启定时完成
			if(DConstants.PRESENTATION_IS_COUNT_DOWN_FLAG_YES == presentationPackage.getIsCountDown()) {
				//若启动规定时间内完成该章节，则设置非标准课件解压后数据()
				if(StringUtils.isNotBlank(packagesItemsJson)){
					List<PresentationPackageItem> packageItems = json.fromJson(packagesItemsJson, new TypeToken<List<PresentationPackageItem>>(){}.getType());
					for(PresentationPackageItem item : packageItems){
						item.setUpdateTime(new Date());
						item.setUpdateId(userId);
						presentationPackageItemMapper.updateByPrimaryKeySelective(item);
					}
				}
				
				PresentationModelPackage presentationPackage1 =  new PresentationModelPackage();
		
				presentationPackage1.setIsCountDown(presentationPackage.getIsCountDown());
			
				PresentationModelPackageExample example = new PresentationModelPackageExample();
				example.createCriteria().andPresentationIdEqualTo(presentationId);
		
				presentationModelPackageMapper.updateByExampleSelective(presentationPackage1, example);
				
				
			}else {
				
				presentationPackage.setId(null);
				presentationPackage.setUpdateId(userId);
				presentationPackage.setUpdateTime(new Date());
				PresentationModelPackageExample example = new PresentationModelPackageExample();
				example.createCriteria().andPresentationIdEqualTo(presentationId);
				rowCount = presentationModelPackageMapper.updateByExampleSelective(presentationPackage, example);
				
				//不存在，则插入数据
				if(DConstants.NUMBER_ZERO == rowCount || StringUtils.isBlank(packageProfileJson)){
					PresentationModelPackage presentationPackage1 =  new PresentationModelPackage();
					presentationPackage1.setId(AppUtils.uuid());
					presentationPackage1.setIsCountDown(DConstants.PRESENTATION_IS_COUNT_DOWN_FLAG_NO);
					presentationPackage1.setPresentationId(presentationId);
					presentationPackage1.setCreateId(userId);
					presentationPackage1.setCreateTime(new Date());
					presentationModelPackageMapper.insertSelective(presentationPackage1);
				}
			}
		}

	
		
		return rowCount;
	}
	
	
	
	public List<PresentationFiles> findPresentationPackageList(Map<String,Object> map){
		return presentationCustomizeMapper.findPresentationPackageList(map);
	}
	
	public List<PresentationFiles> findAttachmentPresentationPackageList(Map<String,Object> map){
		return presentationCustomizeMapper.findAttachmentPresentationPackageList(map);
	}
	
	/**
	 * 初始化加载课件包
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> selectFileList(Map<String,Object> map){
		return presentationCustomizeMapper.selectFileList(map);
	}
	
	public List<Map<String,Object>> selectPresenetionList(Map<String,Object> map){
		return presentationCustomizeMapper.selectPresenetionList(map);
	}
	
	
	public void saveSyncPackageList(List<Map<String,Object>> syncPackageList,String userId,String accountId,String presentationId) throws NumberFormatException, Exception{
		//系统文件库上传添加
		for(int i=0;i<syncPackageList.size();i++){
			
			String attachId = syncPackageList.get(i).get("id").toString();
			//获取数据库attachment 信息
			Attachment attachment = attachmentMapper.selectByPrimaryKey(attachId);
			
			if(null != attachment){
				//保存附件数据(id值与attachment中  主键值一致，PresentationFiles 中使用联合主键 )
				PresentationFiles presentationFiles = new PresentationFiles();
				String packageId  = attachId;
				
				//判断当前数据是否已经存在
				PresentationFilesExample  presentationFilesExample = new PresentationFilesExample();
				presentationFilesExample.createCriteria().andIdEqualTo(packageId).andPresentationIdEqualTo(presentationId);
				List<PresentationFiles> presentationFilesList = presentationFilesMapper.selectByExample(presentationFilesExample);
				//不存在则插入数据
				if(null == presentationFilesList || presentationFilesList.isEmpty()){
					presentationFiles.setId(packageId);
					presentationFiles.setFileName(attachment.getGenerateName());
					presentationFiles.setFileSize(attachment.getAttachSize().longValue());
					presentationFiles.setFileExt(attachment.getAttachExt());
					presentationFiles.setFileUrl(attachment.getAttachUrl());
					presentationFiles.setFileDir(attachment.getAttachDir());
					presentationFiles.setCreateId(userId);
					presentationFiles.setCreateTime(new Date());
					presentationFiles.setAccountId(accountId);
					presentationFiles.setFileOriginalName(attachment.getAttachName());
					presentationFiles.setFileType(DConstants.PRESENTATION_FILES_TYPE_PACKAGE);
					presentationFiles.setPresentationId(presentationId);
					presentationFilesMapper.insertSelective(presentationFiles);
				}
				//获取并保存附件处理后数据
				AttachmentPackageItemExample  example = new AttachmentPackageItemExample();
				example.createCriteria().andPackageIdEqualTo(attachId);
				List<AttachmentPackageItem> attachmentPackageItems = attachmentPackageItemMapper.selectByExample(example);
				for(AttachmentPackageItem item : attachmentPackageItems){
					PresentationPackageItem  presentationPackageItem = new PresentationPackageItem();
					presentationPackageItem.setId(AppUtils.uuid());
					presentationPackageItem.setAccountId(accountId);
					presentationPackageItem.setCreateId(userId);
					presentationPackageItem.setCreateTime(new Date());
					presentationPackageItem.setPackageDir(item.getPackageDir());
					presentationPackageItem.setPackageExt(item.getPackageExt());
					presentationPackageItem.setPackageGenerateName(item.getPackageGenerateName());
					presentationPackageItem.setPackageId(packageId);
					presentationPackageItem.setPackageName(item.getPackageName());
					presentationPackageItem.setPackageSize(item.getPackageSize());
					presentationPackageItem.setPackageUrl(item.getPackageUrl());
					presentationPackageItem.setPresentationId(presentationId);
					presentationPackageItemMapper.insertSelective(presentationPackageItem);
				}
			}
		}
	}
	
	
	/**
	 * 查询 presetation package 信息
	 * @param presentationId
	 * @return
	 */
	public PresentationModelPackage queryPresentationPackageInfo(String presentationId){
		PresentationModelPackage info = new PresentationModelPackage();
		PresentationModelPackageExample example = new PresentationModelPackageExample();
		example.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationModelPackage> list = presentationModelPackageMapper.selectByExample(example);
		if(list != null && !list.isEmpty()){
			info = list.get(DConstants.NUMBER_ZERO);
		}
		return info;
	}
	
	
	public List<PresentationFiles> queryPresentationFiles(String presentationId){
		PresentationFilesExample presentationFilesExample = new PresentationFilesExample();
		presentationFilesExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationFiles> presentationFiles=presentationFilesMapper.selectByExample(presentationFilesExample);
		return presentationFiles;
	}
	
	public List<PresentationPackageItem> queryPresentationPackageItem(String presentationId){
		PresentationPackageItemExample presentationPackageItemExample = new PresentationPackageItemExample();
		presentationPackageItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationPackageItem> presentationPackageItem=presentationPackageItemMapper.selectByExample(presentationPackageItemExample);
		return presentationPackageItem;
	}
	
	/**
	 * 查询 presetation package 规则属性 信息
	 * @param presentationId
	 * @return
	 */
	public List<Map<String,Object>> queryPresentationPackages(String presentationId,String accountId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("presentationId", presentationId);
		params.put("accountId", accountId);
		return presentationCustomizeMapper.getPresentationPackgesProfile(params);
	}
	
	public PresentationPackageItem queryPresentationPackageItemById(String packageItemId) {
		
		return presentationPackageItemMapper.selectByPrimaryKey(packageItemId);
	}
	
	public List<PresentationScromItem> queryPresentationScromItem(String presentationId){
		PresentationScromItemExample presentationScromItemExample = new PresentationScromItemExample();
		presentationScromItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
		List<PresentationScromItem> item=presentationScromItemMapper.selectByExample(presentationScromItemExample);
		return item;
	}
	
}
