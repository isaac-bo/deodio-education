
/**
 * @Title: FilePathUtils.java
 * @Package com.deodio.elearning.utils
 * @author isaac
 * @date 2015-1-12
 * @version V1.0
*/
	
package com.deodio.elearning.utils;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.codehaus.plexus.util.StringUtils;

import com.deodio.core.utils.AppUtils;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.persistence.model.customize.FFmpegBo;
import com.google.gson.Gson;


/**
 * @ClassName: FilePathUtils
 * @author isaac
 * @date 2015-1-12
 */

public class PPTUtils extends AppUtils{

	
	public static final String PPT_CONVERT_URL = PROPERTIES_UTILS.getValue("pdf2html.tool.location.windows");


	public static boolean ppt2png(String pptFile,String pngFolder) throws Exception {
		
		//替换文件符（临时修正）
//		pptFile = StringUtils.replace(pptFile, DConstants.STRING_SLASH, File.separator);
//		pngFolder = StringUtils.replace(pngFolder, DConstants.STRING_SLASH, File.separator);
		
		System.out.println("pptfile:" + pptFile);
		System.out.println("pngFolder:" + pngFolder);
		
		//判断目标文件是否存在
		com.deodio.core.utils.FileUtils.createDir(pngFolder);
		
		NameValuePair[] pairs = { new NameValuePair("pptFile",pptFile),new NameValuePair("pngFolder",pngFolder) } ; 
		String params = EncodingUtil.formUrlEncode(pairs, "UTF-8");
		/*
		HttpClient client = new HttpClient(); 
//		HttpMethod method=new GetMethod("http://192.168.122.109:9090/Convert/ppt2png" + "?" + params);
		HttpMethod method=new GetMethod(PPT_CONVERT_URL + "?" + params);
		try {
			client.executeMethod(method);
			//打印服务器返回的状态
			System.out.println(method.getStatusLine());
			//打印返回的信息
			System.out.println(method.getResponseBodyAsString());
		
			if("{\"result\":1}".equals(method.getResponseBodyAsString())){
				return Boolean.TRUE;
			}else{
				return Boolean.FALSE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}finally {
			//释放连接
			method.releaseConnection();
		}*/
		Gson gson = new Gson();
		String result = HttpUtils.pptCoverImage(PPT_CONVERT_URL, pptFile, pngFolder);
		FFmpegBo ffmpegBo = gson.fromJson(result, FFmpegBo.class);
		if("ok".equals(ffmpegBo.getStatus())) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
		
	}
	
//	public static void main(String[] args) {
//		String pptFile = "Z:\\deodioStatic\\img\\4714bc52d79d491a83c31d33f771ade3\\d9043cfc22dc4ebb90752d75229bec40\\1cd6ac15fd6144679e9a0bb05b44252e\\2017\\3\\2\\dhB2uWONVJsnbj4XukpZ5Pp24ohkAWlM.ppt";
//		String pngFolder = "Z:\\deodioStatic\\img\\4714bc52d79d491a83c31d33f771ade3\\d9043cfc22dc4ebb90752d75229bec40\\1cd6ac15fd6144679e9a0bb05b44252e\\2017\\3\\2\\dhB2uWONVJsnbj4XukpZ5Pp24ohkAWlM";
//		//ppt2png(pptFile,pngFolder);
//	}
}
