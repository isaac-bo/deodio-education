package com.deodio.elearning.commons.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deodio.core.service.BaseService;
import com.deodio.core.utils.AppUtils;
import com.deodio.core.utils.FileUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.exception.DeodioException;
import com.deodio.elearning.persistence.mapper.AccountAttachmentMapper;
import com.deodio.elearning.persistence.mapper.AttachmentMapper;
import com.deodio.elearning.persistence.mapper.AttachmentMediaItemMapper;
import com.deodio.elearning.persistence.mapper.AttachmentModelMediaMapper;
import com.deodio.elearning.persistence.mapper.AttachmentPackageItemMapper;
import com.deodio.elearning.persistence.mapper.customize.AttachmentCustomizeMapper;
import com.deodio.elearning.persistence.model.AccountAttachment;
import com.deodio.elearning.persistence.model.AccountAttachmentExample;
import com.deodio.elearning.persistence.model.Attachment;
import com.deodio.elearning.persistence.model.AttachmentExample;
import com.deodio.elearning.persistence.model.AttachmentFileItemRel;
import com.deodio.elearning.persistence.model.AttachmentItemsRel;
import com.deodio.elearning.persistence.model.AttachmentMediaItem;
import com.deodio.elearning.persistence.model.AttachmentMediaItemExample;
import com.deodio.elearning.persistence.model.AttachmentModelMedia;
import com.deodio.elearning.persistence.model.AttachmentModelMediaExample;
import com.deodio.elearning.persistence.model.AttachmentPackageItem;
import com.deodio.elearning.persistence.model.AttachmentScromItem;
import com.deodio.elearning.persistence.model.AttachmentSlidesItem;
import com.deodio.elearning.utils.CommonUtils;

@Service
public class AttachmentService extends BaseService {

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private AttachmentCustomizeMapper attachmentCustomizeMapper;

	@Autowired
	private AttachmentModelMediaMapper attachmentModelMediaMapper;

	@Autowired
	private AttachmentMediaItemMapper attachmentMediaItemMapper;

	@Autowired
	private AttachmentPackageItemMapper attachmentPackageItemMapper;

	@Autowired
	private AccountAttachmentMapper accountAttachmentMapper;
	
	@Autowired
	private UploadService uploadService;

	public Attachment queryItemsRelAttachement(Map<String, String> paramsMap) throws DeodioException {
		Attachment result = new Attachment();
		AttachmentExample example = new AttachmentExample();
		System.err.println(getClass().getName()+"\tparamsMap:\t"+paramsMap.toString());
		String relId = paramsMap.get("relId");
		String generateName = paramsMap.get("generateName");
		generateName = StringUtils.isBlank(generateName) ? StringUtils.EMPTY : generateName;
		if(paramsMap.containsKey("attachType")) {				
			String attachType = paramsMap.get("attachType");
			example.createCriteria().andRelIdEqualTo(relId).andGenerateNameEqualTo(generateName).andAttachTypeEqualTo(attachType);
		}else {
			example.createCriteria().andRelIdEqualTo(relId).andGenerateNameEqualTo(generateName);
		}
		List<Attachment> list = attachmentMapper.selectByExample(example);
		System.err.println(getClass().getName()+"\t===71===list:\t"+list.toString());
		if (!list.isEmpty()) {
			if (list.size() > DConstants.NUMBER_ONE) {
				result = list.get(DConstants.NUMBER_ZERO);
				//throw new DeodioException("attachment.data.not.uniqe.err.msg");
			} else {
				result = list.get(DConstants.NUMBER_ZERO);
			}
		}
		return result;
	}

	/**
	 * 插入附件转换后数据
	 * @param items
	 * @param params String attachmentId,String accountId,String userId,String itemId
	 */
	public <T> void insertAttachmentItem(List<T> items, Map<String, Object> params) {
		// 插入附件处理数据
		if (items != null && !items.isEmpty()) {
			T item = items.get(0);
			if (item instanceof AttachmentMediaItem) {
				attachmentCustomizeMapper.insertMediaBatch(items);
			} else if (item instanceof AttachmentPackageItem) {
				attachmentCustomizeMapper.insertPackageBatch(items);
			} else if (item instanceof AttachmentScromItem) {
				attachmentCustomizeMapper.insertScromBatch(items);
			} else if (item instanceof AttachmentSlidesItem) {
				attachmentCustomizeMapper.insertSlidesBatch(items);
			}
		}
		// 不插入附件关联数据
		if (params != null) {
			// 插入关联数据
			params.put("createTime", new Date());
			params.put("id", AppUtils.uuid());
			// 查询插入数据是否存在
			Integer rowCount = attachmentCustomizeMapper.selectAttachmentItemRelByAttachmentAndItem(params);
			if (rowCount <= DConstants.NUMBER_ZERO) {
				attachmentCustomizeMapper.insertAttachmentItemRel(params);
			}
		}
	}


	/**
	 * 获取attachment 
	 * @param params  String itemId  Integer itemType
	 * @return
	 */
	public List<Map<String, Object>> selectAttachmentByItem(Map<String, Object> params) {
		return attachmentCustomizeMapper.selectAttachmentByItem(params);
	}


	/**
	 * @param params  String attachmentId ;String itemId,String userId
	 * @return
	 */
	public Integer deleteAttachmentItemRel(Map<String, Object> params) {
		return attachmentCustomizeMapper.deleteAttachmentItemRel(params);
	}

	/**
	 * 查询未选中的附件
	 * @param params  String userId,String itemName,String itemType;String userId,String itemId
	 * @return
	 */
	public List<Map<String, Object>> findAttachmentNotSelected(Map<String, Object> params) {
		return attachmentCustomizeMapper.findAttachmentNotSelected(params);
	}


	/**
	 * 处理后的附件信息
	 * @param params
	 * @param items
	 */
	public void saveAttachmentItems(Map<String, Object> params, List<AttachmentItemsRel> items) {
		if (items != null && !items.isEmpty()) {
			for (AttachmentItemsRel item : items) {
				item.setId(AppUtils.uuid());
			}
			params.put("list", items);
			attachmentCustomizeMapper.insertAttachmentItemRelBatch(params);
		}
	}


	/**
	 * 保存附件媒体独有信息
	 * @param mediaInfo
	 */
	public int inserAttachmentModelMediaInfo(AttachmentModelMedia mediaInfo) {
		return attachmentModelMediaMapper.insertSelective(mediaInfo);
	}

	public int updateAttachmentModelMediaInfo(AttachmentModelMedia mediaInfo) {
		AttachmentModelMediaExample example = new AttachmentModelMediaExample();
		example.createCriteria().andMediaIdEqualTo(mediaInfo.getMediaId());
		return attachmentModelMediaMapper.updateByExampleSelective(mediaInfo, example);
	}

	public int updateAttachmentMediaItem(AttachmentMediaItem itemInfo) {
		AttachmentMediaItemExample example = new AttachmentMediaItemExample();
		example.createCriteria().andMediaIdEqualTo(itemInfo.getMediaId());
		return attachmentMediaItemMapper.updateByExampleSelective(itemInfo, example);
	}

	/**
	 * @param params String fileItemType;String itemId
	 * @return
	 */
	public List<Map<String, Object>> queryAttachmentItemSetting(Map<String, Object> params) {
		return attachmentCustomizeMapper.queryAttachmentItemSetting(params);
	}

	public Integer insertAttachmentItemSettingBatch(List<AttachmentFileItemRel> fileItemRels) {
		return attachmentCustomizeMapper.insertAttachmentItemSettingBatch(fileItemRels);
	}

	/**
	 * @param params  String itemId,String userId
	 * @return
	 */
	public Integer deleteAttachmentItemSetting(Map<String, Object> params) {
		return attachmentCustomizeMapper.deleteAttachmentItemSetting(params);
	}

	/**
	 * @param fileItemRels 
	 * @param params String itemId,String userId
	 * @return
	 */
	public Integer saveAttachmentItemSetting(List<AttachmentFileItemRel> fileItemRels, Map<String, Object> params) {
		Integer rowCount = DConstants.NUMBER_ZERO;
		rowCount = deleteAttachmentItemSetting(params);
		rowCount = insertAttachmentItemSettingBatch(fileItemRels);
		return rowCount;
	}

	/**
	 * 通过主键获取附件信息
	 * @param id
	 * @return
	 */
	public Attachment queryAttachmentById(String id) {
		return attachmentMapper.selectByPrimaryKey(id);
	}

	/**
	 * @param params String attachType;String itemId;
	 * @return
	 */
	public List<Map<String, Object>> queryAttachmentItems(Map<String, Object> params) {
		return attachmentCustomizeMapper.queryAttachmentItems(params);
	}

	/**
	 * 更新packageitem  转化状态
	 * @param packageItem
	 * @return
	 */
	public Integer updatePackageItemConvertStatus(AttachmentPackageItem packageItem) {
		return attachmentPackageItemMapper.updateByPrimaryKeySelective(packageItem);
	}

	/**
	 * 跟新附件转换状态
	 * @param id
	 * @param convertStatus
	 * @param userId
	 * @return
	 */
	public Integer uploadAttachmentConvertStatusByPk(String id, Integer convertStatus, String userId) {
		Attachment attachment = new Attachment();
		attachment.setId(id);
		attachment.setIsConvert(convertStatus.shortValue());
		attachment.setUpdateId(userId);
		attachment.setUpdateTime(new Date());
		return attachmentMapper.updateByPrimaryKeySelective(attachment);
	}


	public AccountAttachment queryAccountAttachementByAccountId(String accountId) throws DeodioException {
		AccountAttachment result = new AccountAttachment();
		AccountAttachmentExample example = new AccountAttachmentExample();

		example.createCriteria().andAccountIdEqualTo(accountId);

		List<AccountAttachment> list = accountAttachmentMapper.selectByExample(example);
		if (!list.isEmpty()) {
			if (list.size() > DConstants.NUMBER_ONE) {
				throw new DeodioException("attachment.data.not.uniqe.err.msg");
			} else {
				result = list.get(DConstants.NUMBER_ZERO);
			}
		}
		return result;
	}

	/**
	 * 图片更新
	 */
	public int updateAttachement(String attachId, String relId, String userId) {

		Attachment attachment = new Attachment();
		attachment.setId(attachId);
		attachment.setRelId(relId);
		attachment.setUpdateId(userId);
		attachment.setUpdateTime(new Date());

		return attachmentMapper.updateByPrimaryKeySelective(attachment);
	}

	/**
	 * 图片复制
	 */
	public int copyAttachement(String relId, String newRelId, String userId) {

		AttachmentExample attachmentExample = new AttachmentExample();
		attachmentExample.createCriteria().andRelIdEqualTo(relId);
		List<Attachment> attachments = attachmentMapper.selectByExample(attachmentExample);
		if (attachments.isEmpty()) {
			return 0;
		}
		Attachment attachment = attachments.get(0);
		attachment.setId(AppUtils.uuid());
		attachment.setRelId(newRelId);
		attachment.setCreateId(userId);
		attachment.setCreateTime(new Date());
		attachment.setUpdateId(userId);
		attachment.setUpdateTime(new Date());

		return attachmentMapper.insert(attachment);
	}
	//复制附件
	public String copyNewAttachement(String oldRelId, String newRelId, String userId) throws Exception
	{
		// TODO Auto-generated method stub
		AttachmentExample example=new AttachmentExample();
		example.createCriteria().andRelIdEqualTo(oldRelId);
		List<Attachment> list = attachmentMapper.selectByExample(example);
		if (!list.isEmpty()) {
			Attachment attachment=list.get(0);
			//获取附件地址
			String absDir=uploadService.generateDir(attachment.getAttachType());
			String absUrlStr  = StringUtils.replace(absDir.toString(),DConstants.STRING_BACKSLASH,DConstants.STRING_SLASH);
			//获取附件名称
			String[]  generateNames = uploadService.generateFileName(attachment.getAttachName());
			String generateName=attachment.getGenerateName();
			String serverDir = CommonUtils.IMGS_LOCAL_DIR;
			//设置新的附件的url
			String newFolder=serverDir + File.separator + absDir.toString().replace(DConstants.STRING_BACKSLASH, DConstants.STRING_SLASH);
			String newUrl=newFolder+generateNames[0];
	        //复制附件
			String url=serverDir + File.separator + attachment.getAttachUrl()+generateName;
			try {			
				System.err.println(getClass().getName()+"\tnewFolder:\t"+newFolder);
				System.err.println(getClass().getName()+"\turl:\t"+url);
				System.err.println(getClass().getName()+"\tnewUrl:\t"+newUrl);
				FileUtils.createDir(newFolder);
				FileUtils.copy(url, newUrl);
				System.err.println("复制成功");
			}catch (Exception e) {
				// TODO: handle exception
				System.err.println("复制失败");
				e.printStackTrace();
			}
			Date nowDate =new Date();
			String newAttachId = AppUtils.uuid();
			attachment.setId(newAttachId);
			attachment.setRelId(newRelId);
			attachment.setCreateTime(nowDate);
			attachment.setUpdateTime(nowDate);
			attachment.setCreateId(userId);
			attachment.setUpdateId(userId);
			attachment.setAttachDir(absDir);
			attachment.setAttachUrl(absUrlStr);
			attachment.setAttachExt(generateNames[1]);
			attachment.setGenerateName(generateNames[0]);
			attachment.setIsConvert(DConstants.ATTACHMENT_IS_CONVERT_NO.shortValue());
			attachmentMapper.insertSelective(attachment);
			return newAttachId;		
		}else {
			return null;
		}
		
		
	}

}
