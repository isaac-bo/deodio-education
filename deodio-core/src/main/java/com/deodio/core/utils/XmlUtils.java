package com.deodio.core.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtils {
    private final static Logger LOGGER= LoggerFactory.getLogger(XmlUtils.class);
    
    public static final String  XML_PCDATA_LEFT_STR = "<![CDATA[";
    public static final String  XML_PCDATA_RIGHT_STR = "]]>";

    public Document loadXmlFile(String xmlFile) {
        SAXReader saxReader = new SAXReader();
        FileInputStream fis = null;
        Document doc = null;
        try {  
            fis = new FileInputStream(xmlFile);
            doc = saxReader.read(fis);
            return doc;
        } catch (Exception e) {  
            LOGGER.error(e.getMessage(),e);  
        }  
        return doc;
    }
    
    public Document loadXmlFile(InputStream is) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {  
            doc = saxReader.read(is);
            return doc;
        } catch (Exception e) {  
            LOGGER.error(e.getMessage(),e);  
        }  
        return doc;
    }
    
    public Document loadXmlFile(URL xmlUrl) {
        SAXReader saxReader = new SAXReader();
        Document doc = null;
        try {  
            doc = saxReader.read(xmlUrl);
            return doc;
        } catch (Exception e) {  
            LOGGER.error(e.getMessage(),e);  
        }  
        return doc;
    }

    public Element findElement(Element searchedElement,String targetNodePrefix, String targetNodeAttributeName,String targetNodeAttributeValue) {
        Element elementTarget = null;
        for (Iterator<Element> i = searchedElement.elementIterator(targetNodePrefix); i.hasNext();) {
            Element element = i.next();
            String strManagerName = element.attributeValue(targetNodeAttributeName);
            if (strManagerName.equals(targetNodeAttributeValue)) {
                elementTarget = element;
                break;
            }
        }
        return elementTarget;
    }
    
    /**
     * build element value rel map.
     * @param searchedElement
     * @param targetNodePrefix
     * @return
     */
    public Map<String,String> buildElementMap(Element searchedElement,String targetNodePrefix) {
        Map<String,String> eleMap = new HashMap<String,String>();
        for (Iterator<Element> i = searchedElement.elementIterator(targetNodePrefix); i.hasNext();) {
            Element element = i.next();
            String strManagerName = element.attributeValue("id");
            eleMap.put(strManagerName, getElementVal(element));
           
        }
        return eleMap;
    }
    
    public String getElementVal(Element e){
        if(null==e){
            return "";
        }
        return e.getTextTrim();
    }

}
