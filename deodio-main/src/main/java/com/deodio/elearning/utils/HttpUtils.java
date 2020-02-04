package com.deodio.elearning.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.deodio.elearning.persistence.model.customize.FFmpegBo;
import com.google.gson.Gson;


public class HttpUtils {
	public static String pdfForHtml(String httpServer,String pdfFile,String htmlFileName) throws ParseException, IOException {

        StringBuilder requestUrl = new StringBuilder(httpServer);
        requestUrl.append("/api/pdfTohtml?pdfFile=")
                  .append(pdfFile)
                  .append("&htmlFileName=")
                  .append(htmlFileName);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl.toString()); 
        CloseableHttpResponse response = client.execute(httpGet);
     
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity).toString();
        
            System.out.println(result);
            return result;
        } finally {
            response.close();
        }

	}
	
	
	public static String videoInfo(String httpServer,String filePath,String imgOutPath) throws Exception{
		  StringBuilder requestUrl = new StringBuilder(httpServer);
	        requestUrl.append("/api/getvideo?videoPath=")
	                  .append(filePath)
	                  .append("&imgOutPath=").append(imgOutPath);
	        CloseableHttpClient client = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(requestUrl.toString()); 
	        CloseableHttpResponse response = client.execute(httpGet);
	     
	        try {
	            System.out.println(response.getStatusLine());
	            HttpEntity entity = response.getEntity();
	            String result = EntityUtils.toString(entity).toString();
	        
	            System.out.println(result);
	            return result;
	        } finally {
	            response.close();
	        }
		
	}
	
	
	public static String pptCoverImage(String httpServer,String pptFilePath,String outFilePath)throws Exception{
		 StringBuilder requestUrl = new StringBuilder(httpServer);
	        requestUrl.append("/api/pptCover?absPPTFileTemp=")
	                  .append(pptFilePath)
	                  .append("&absPNGFolderTemp=").append(outFilePath);
	        CloseableHttpClient client = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(requestUrl.toString()); 
	        CloseableHttpResponse response = client.execute(httpGet);
	     
	        try {
	            System.out.println(response.getStatusLine());
	            HttpEntity entity = response.getEntity();
	            String result = EntityUtils.toString(entity).toString();
	        
	            System.out.println(result);
	            return result;
	        } finally {
	            response.close();
	        }
	}
	
	
	
	
	
	public static void main(String[] args) {
		String httpServer = "http://192.168.122.109:8088";
		String filePath = "/vod/730482e06d9b4b4a9be14060687d30d9/852ec1c580894f26a9cd7648489c4f2a/498b6798c4c34c39b411e4b756e8bebd/2018/8/13/mE6Q7BdG5VtMrOfGukzebI4nNXrAUCp7.mp4";
		String imgOutPath="";
		
		try {
			String result  = HttpUtils.videoInfo(httpServer, filePath,imgOutPath);
			//{"time":75,"videoTime":"00:01:15.08","status":"ok"}
			Gson gson = new Gson();
			FFmpegBo ffmpegBo = gson.fromJson(result, FFmpegBo.class);
			
			System.out.println(ffmpegBo.getStatus());
			System.out.println(ffmpegBo.getVideoTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
