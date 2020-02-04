package com.deodio.elearning.modules.presentation.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//ADL imports

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.commons.service.AttachmentService;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.mapper.PresentationScromItemMapper;
import com.deodio.elearning.persistence.model.AttachmentScromItem;
import com.deodio.elearning.persistence.model.PresentationScromItem;
import com.deodio.elearning.persistence.model.PresentationScromItemExample;
import com.deodio.elearning.utils.CommonUtils;

/**
 * 新建课程presentation相关设置Service
 * 
 */
@Service
public class PresentationScromService {
	@Autowired
	private PresentationScromItemMapper presentationScromItemMapper;
	
	@Autowired
	private AttachmentService attachmentService;

	public String saveScromItemInfo(String filePath, String zipFileName, String presentationId,String userId,String uploadDir,String attachmentId) {
		String result = "flase";
		PresentationScromItem tempItem = null;
		List<PresentationScromItem> items = new ArrayList<PresentationScromItem>();
		
		List<AttachmentScromItem> attachmentScromItems = new ArrayList<AttachmentScromItem>();
		
		try {
			File f = new File(filePath);
			if (f.exists()) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(f);
				// 获取根节点 imsmanifest <manifest>
				Node contentNode = doc.getDocumentElement();
				// 获取子节点
				NodeList contentChildren = contentNode.getChildNodes();
				for (int i = 0; i < contentChildren.getLength(); i++) {
					Node currentNode = contentChildren.item(i);
					if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
						// 判断节点名称
						if (currentNode.getNodeName().equalsIgnoreCase("organizations")) {
							// 解析organizations下节点
							result = processOrganization(currentNode,presentationId,tempItem,items);
							if (result.equals("false")) {
								break;
							}
						}
					}
				}
				for (int i = 0; i < contentChildren.getLength(); i++) {
					Node currentNode = contentChildren.item(i);
					if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
						// 判断节点名称
						if (currentNode.getNodeName().equalsIgnoreCase("resources")) {
							// 解析resources下节点
							result = processResources(currentNode,presentationId,tempItem,items,uploadDir);
							if (result.equals("false")) {
								break;
							}
						}

					}
				}
				// 保存到数据库
				PresentationScromItemExample presentationScromItemExample = new PresentationScromItemExample();
				presentationScromItemExample.createCriteria().andPresentationIdEqualTo(presentationId);
				presentationScromItemMapper.deleteByExample(presentationScromItemExample);
				
				
				for (int i = 0; i < items.size(); i++) {
					String id = AppUtils.uuid();
					tempItem = items.get(i);
					tempItem.setId(id);
					tempItem.setPresentationId(presentationId);
					tempItem.setCreateId(userId);
					tempItem.setCreateTime(new Date());
					presentationScromItemMapper.insertSelective(tempItem);
					
					//拼接附件转换后的数据
					AttachmentScromItem attachmentScromItem = new AttachmentScromItem();
					attachmentScromItem.setId(AppUtils.uuid());
					attachmentScromItem.setIdentifier(tempItem.getIdentifier());
					attachmentScromItem.setTitle(tempItem.getTitle());
					attachmentScromItem.setMasteryScore(tempItem.getMasteryScore());
					attachmentScromItem.setIdentifierref(tempItem.getIdentifierref());
					attachmentScromItem.setScromId(attachmentId);
					attachmentScromItem.setSequence(tempItem.getSequence());
					tempItem.setTheLevel(tempItem.getTheLevel());
					tempItem.setType(tempItem.getType());
					attachmentScromItem.setLaunch(tempItem.getLaunch());
					attachmentScromItems.add(attachmentScromItem);
				}
				
				//保存信息值附件处理表中
				if(attachmentScromItems != null && attachmentScromItems.size() > 0){
					attachmentService.insertAttachmentItem(attachmentScromItems,null);
					//更新附件转换状态
					attachmentService.uploadAttachmentConvertStatusByPk(attachmentId, DConstants.ATTACHMENT_IS_CONVERT_YES, userId);
				}
				
				ZipFile archive = new ZipFile(uploadDir + DConstants.STRING_SLASH + zipFileName);
				byte[] buffer = new byte[16384];

				for (Enumeration<? extends ZipEntry> e = archive.entries(); e.hasMoreElements();) {
					ZipEntry entry = (ZipEntry) e.nextElement();
					if (!entry.isDirectory()) {
						String filename = entry.getName();
						filename = uploadDir + DConstants.STRING_SLASH+ "scorm_items" + DConstants.STRING_SLASH + filename;
						java.io.File destFile = new java.io.File(filename);

						String parent = destFile.getParent();
						if (parent != null) {
							java.io.File parentFile = new java.io.File(parent);
							if (!parentFile.exists()) {
								parentFile.mkdirs();
							}
						}

						InputStream in = archive.getInputStream(entry);

						OutputStream outStream = new FileOutputStream(filename);

						int count;
						while ((count = in.read(buffer)) != -1)
							outStream.write(buffer, 0, count);

						in.close();
						outStream.close();
					}
				}
				archive.close();
				result = "true";
			}else{
				result = "flase";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String processResources(Node resourcesNode,String presentationId,PresentationScromItem tempItem,List<PresentationScromItem> items,String scormDir) {

		String result = "true";
//		int level = 1;
		// 获取子节点
		NodeList resourcesChildren = resourcesNode.getChildNodes();
		for (int i = 0; i < resourcesChildren.getLength(); i++) {
			Node currentNode = resourcesChildren.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equalsIgnoreCase("resource")) {
					// 解析resource下节点
					result = processResource(currentNode,presentationId,tempItem,items,scormDir);
					if (result.equals("false")) {
						break;
					}
				}
			}
		}

		return result;
	}

	public String processItem(Node item,String presentationId,PresentationScromItem tempItem,List<PresentationScromItem> items) {
//		int size = 1;
		String result = "true";
		NodeList itemsChildren = item.getChildNodes();
		for (int i = 0; i < itemsChildren.getLength(); i++) {
			Node currentNode = itemsChildren.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equalsIgnoreCase("item")) {
					String identifier = getAttribute(currentNode, "identifier");
					String title = getSubElement(currentNode, "title");
					String masteryscore = getSubElement(currentNode, "masteryscore");
					String identifierref = getAttribute(currentNode, "identifierref");
					tempItem = new PresentationScromItem();
					tempItem.setIdentifier(identifier);
					tempItem.setTitle(title);
					tempItem.setMasteryScore(masteryscore);
					tempItem.setIdentifierref(identifierref);
					items.add(tempItem);
					processItem(currentNode,presentationId,tempItem,items);
				}
			}

		}

		return result;
	}

	public String processOrganization(Node organizationsNode,String presentationId,PresentationScromItem tempItem,List<PresentationScromItem> items) {

		String result = "true";
		// 获取子节点
		NodeList resourcesChildren = organizationsNode.getChildNodes();
		for (int i = 0; i < resourcesChildren.getLength(); i++) {
			Node currentNode = resourcesChildren.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equalsIgnoreCase("organization")) {
					result = processItems(currentNode,presentationId,tempItem,items);
					if (result.equals("false")) {
						break;
					}
				}
			}
		}

		return result;
	}

	public String processItems(Node organizationNode,String presentationId,PresentationScromItem tempItem,List<PresentationScromItem> items) {
		String result = "true";
		// 获取子节点
		NodeList resourcesChildren = organizationNode.getChildNodes();
		for (int i = 0; i < resourcesChildren.getLength(); i++) {
			Node currentNode = resourcesChildren.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equalsIgnoreCase("item")) {
					String identifier = getAttribute(currentNode, "identifier");
//					String parameters = getAttribute(currentNode, "parameters");
					String title = getSubElement(currentNode, "title");
//					String prerequisites = getSubElement(currentNode, "prerequisites");
//					String timelimitaction = getSubElement(currentNode, "timelimitaction");
//					String maxtimeallowed = getSubElement(currentNode, "maxtimeallowed");
//					String datafromlms = getSubElement(currentNode, "datafromlms");
					String masteryscore = getSubElement(currentNode, "masteryscore");
					String identifierref = getAttribute(currentNode, "identifierref");
					tempItem = new PresentationScromItem();
					tempItem.setIdentifier(identifier);
					tempItem.setTitle(title);
					tempItem.setMasteryScore(masteryscore);
					tempItem.setIdentifierref(identifierref);
					items.add(tempItem);
					processItem(currentNode,presentationId,tempItem,items);
				}
			}
		}
		return result;
	}

	public String getSubElement(Node node, String element) {
		String value = new String();
		NodeList kids = node.getChildNodes();
		// cycle through all children of node to get the text
		if (kids != null) {
			for (int i = 0; i < kids.getLength(); i++) {
				if (kids.item(i).getNodeType() == Node.ELEMENT_NODE) {
					// look for the asked for element
					if (kids.item(i).getNodeName().equalsIgnoreCase(element)) {
						value = getText(kids.item(i));
						return value;
					}
				}
			}
		}
		return value;
	}

	public String getText(Node node) {
		String value = new String();
		NodeList kids = node.getChildNodes();
		// cycle through all children of node to get the text
		if (kids != null) {
			for (int i = 0; i < kids.getLength(); i++) {
				// make sure we have a text element
				if ((kids.item(i).getNodeType() == Node.TEXT_NODE)
						|| (kids.item(i).getNodeType() == Node.CDATA_SECTION_NODE)) {
					value = value + kids.item(i).getNodeValue().trim();
				}
			}
		}

		return value;
	}

	public String processResource(Node resourceNode,String presentationId,PresentationScromItem tempItem,List<PresentationScromItem> items,String uploadDir) {
		String result = "true";
		String id = getAttribute(resourceNode, "identifier");
		String scormType = getAttribute(resourceNode, "adlcp:scormtype");
		String type = getAttribute(resourceNode, "type");
		String href = getAttribute(resourceNode, "href");
		// 更新item集合
		updateItemList(id, scormType, type,uploadDir, href,presentationId,tempItem,items);

		return result;

	}

	// 获取节点属性值
	protected String getAttribute(Node theNode, String theAttribute) {
		String returnValue = new String();

		// 获取该节点所有属性
		Attr attrs[] = sortAttributes(theNode.getAttributes());

		// 匹配属性名称返回属性值
		Attr attribute;
		for (int i = 0; i < attrs.length; i++) {
			attribute = attrs[i];

			if (attribute.getName().equals(theAttribute)) {
				returnValue = attribute.getValue();
				break;
			}
		}

		return returnValue;
	}

	protected Attr[] sortAttributes(NamedNodeMap attrs) {
		int len = (attrs != null) ? attrs.getLength() : 0;
		Attr array[] = new Attr[len];
		for (int i = 0; i < len; i++) {
			array[i] = (Attr) attrs.item(i);
		}
		for (int i = 0; i < len - 1; i++) {
			String name = array[i].getNodeName();
			int index = i;
			for (int j = i + 1; j < len; j++) {
				String curName = array[j].getNodeName();
				if (curName.compareTo(name) < 0) {
					name = curName;
					index = j;
				}
			}
			if (index != i) {
				Attr temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}

		return (array);

	}

	// 更新item集合信息
	public void updateItemList(String id, String scormType, String type,String uploadDir, String href,String presentationId,PresentationScromItem tempItem,List<PresentationScromItem> items) {
		tempItem = new PresentationScromItem();
		for (int i = 0; i < items.size(); i++) {
			tempItem = items.get(i);
			tempItem.setPresentationId(presentationId);
			tempItem.setSequence(new Long(i));
			tempItem.setTheLevel(new Long(i + 1));
			String idref = tempItem.getIdentifierref();
			if (idref.equals(id)) {
				if (presentationId != null && !presentationId.equals("")) {
					tempItem.setPresentationId(presentationId);
				}
				if (scormType != null && !scormType.equals("")) {
					tempItem.setType(scormType);
				}
				if (href != null && !href.equals("")) {
					href = uploadDir.replace(CommonUtils.IMGS_LOCAL_DIR, "") + DConstants.STRING_SLASH + "scorm_items"+ DConstants.STRING_SLASH + href;
					tempItem.setLaunch(href);
				}
			}
			items.remove(i);
			items.add(i, tempItem);
		}
	}

}
