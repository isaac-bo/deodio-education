package com.deodio.elearning.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

import com.deodio.elearning.exception.DeodioException;



public class MyResourceLoader extends ResourceLoader  {

	@Override
	public long getLastModified(Resource arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream getResourceStream(String templateContent)
			throws ResourceNotFoundException {
		
		InputStream result = null;  
		  
        if (templateContent == null || templateContent.length() == 0) {  
            throw new ResourceNotFoundException(  
                    "模板没有被定义~！");  
        }  
        try {
			result = new ByteArrayInputStream(templateContent.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new DeodioException("模板编码不正确,需要UTF-8编码.", e);
		}   
        return result;   
	}

	@Override
	public void init(ExtendedProperties arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSourceModified(Resource arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
