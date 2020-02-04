package com.deodio.elearning.commons.mediaUpload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.vod.client.DefaultVODClient;
import com.aliyun.vod.client.Media;
import com.aliyun.vod.client.MediaWorkflowInfo;
import com.aliyun.vod.client.MediaWorkflowMessage;
import com.aliyun.vod.client.SnapshotOutput;
import com.aliyun.vod.client.TranscodeOutput;
import com.aliyun.vod.client.VODClient;
import com.aliyun.vod.common.MediaWorkflowMessageType;
import com.aliyun.vod.common.MediaWorkflowState;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.deodio.elearning.constants.DConstants;
import com.deodio.elearning.utils.CommonUtils;

public class AliyunMediaUpload implements IMediaUpload{

    private static VODClient client;
    static{
		
        try {
        	client = new DefaultVODClient(CommonUtils.ACCESS_KEY_ID, CommonUtils.ACCESS_KEY_SECRET);
			DefaultProfile.addEndpoint(CommonUtils.VOD_REGION,CommonUtils.VOD_REGION,CommonUtils.VOD_PRODUCT,CommonUtils.VOD_ENDPOINT);
		} catch (ClientException e) {
			e.printStackTrace();
		}
    }

    @Override
    public  MediaUploadInfo excute(FileItem item,String slideDir) throws ClientException {
        return excute(item);
    }
    @Override
	public MediaUploadInfo queryProcess(String fileUrl) {
		return waitMediaWorkflowExecutionCompleted(fileUrl);
	}
    
    @Override
	public void deleteMessage(String fileUrl) throws Exception {
        	MediaWorkflowInfo mediaWorkflowInfo = getMediaWorkflowInfo();
        	String queueName = mediaWorkflowInfo.queueName();
            List<MediaWorkflowMessage> mediaWorkflowMessages = waitMediaWorkflowMessages(queueName, fileUrl);
            if(!mediaWorkflowMessages.isEmpty()){
            	deleteQueueMessage(queueName, mediaWorkflowMessages);
            }
	}
    private static MediaUploadInfo excute (FileItem item) throws ClientException {
    	
        MediaWorkflowInfo mediaWorkflowInfo = getMediaWorkflowInfo();
        String fileURL = uploadVideoFile(mediaWorkflowInfo.uploadBucket(), mediaWorkflowInfo.uploadObjectPrefix(),item);
        MediaUploadInfo info = new MediaUploadInfo();
        boolean result = queryMediaWorkflowExecutionCompleted(mediaWorkflowInfo, fileURL);
        if(result){
        	info = processMediaOutput(fileURL, mediaWorkflowInfo.mediaWorkflowName());
        }else{
        	info.setDeletPath(fileURL);
        	info.setTranscodeOutputUrl(fileURL);
        }
//        waitMediaWorkflowExecutionCompleted(fileURL);
//        info = processMediaOutput(fileURL, mediaWorkflowInfo.mediaWorkflowName());
        return info;
    }

    private static MediaWorkflowInfo getMediaWorkflowInfo() {
    	
        MediaWorkflowInfo mediaWorkflowInfo = client.getMediaWorkflowInfo(CommonUtils.MEDIA_WORKFLOW_NAME);
        assertMediaWorkflowValid(mediaWorkflowInfo);
        return mediaWorkflowInfo;
    }

    private static void assertMediaWorkflowValid(MediaWorkflowInfo mediaWorkflowInfo) {
        if (mediaWorkflowInfo.state() == MediaWorkflowState.Inactive) {
            throw new RuntimeException("Target MediaWorkflow \"" + CommonUtils.MEDIA_WORKFLOW_NAME + "\" + is inactive");
        }
        if (mediaWorkflowInfo.queueName() == null) {
            throw new RuntimeException("Target MediaWorkflow \"" + CommonUtils.MEDIA_WORKFLOW_NAME + "\" has not been specified message queue");
        }
    }

    private static String uploadVideoFile(String inputBucket, String objectPrefix,FileItem item) {
        String object = null;
        if (null == objectPrefix || objectPrefix.equals("")) {
            object = UUID.randomUUID().toString();
        } else {
            object = objectPrefix + "/" + UUID.randomUUID().toString();            
        }
        uploadLocalFile(inputBucket, object, item);
        if (CommonUtils.OSS_REGION.equals("oss-test"))
            return "http://" + inputBucket + "." + CommonUtils.OSS_REGION + ".aliyun-inc.com/" + object;
        return "http://" + inputBucket + "." + CommonUtils.OSS_REGION + ".aliyuncs.com/" + object;
    }

    private static void uploadLocalFile(String bucket, String object, FileItem item) {
        OSSClient client = new OSSClient(CommonUtils.OSS_ENDPOINT,CommonUtils.ACCESS_KEY_ID,CommonUtils.ACCESS_KEY_SECRET);
        
        boolean isBucketExist = client.doesBucketExist(bucket);
        if(!isBucketExist){
    	    throw new RuntimeException("fail@uploadLocalFile doesBucketExist");  
        }
        try {
        	InputStream content = item.getInputStream();
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(item.getSize());
            client.putObject(bucket, object, content, meta);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("fail@uploadLocalFile FileInputStream");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("fail@uploadLocalFile URLEncoder");
        } catch (IOException e) {
        	throw new RuntimeException("fail@ retrieve the contents of the file");
		}
    }

    private static MediaUploadInfo waitMediaWorkflowExecutionCompleted(String fileURL) {
    	MediaUploadInfo info = new MediaUploadInfo();
    	MediaWorkflowInfo mediaWorkflowInfo = getMediaWorkflowInfo();
    	String queueName = mediaWorkflowInfo.queueName();
        List<MediaWorkflowMessage> mediaWorkflowMessages = waitMediaWorkflowMessages(queueName, fileURL);
        if(!mediaWorkflowMessages.isEmpty()){
        	info = processMediaOutput(fileURL, mediaWorkflowInfo.mediaWorkflowName());
        }
        return info;
    }

    private static List<MediaWorkflowMessage> waitMediaWorkflowMessages(String queueName, String fileURL) {
        while (true) {
            List<MediaWorkflowMessage> messages = queryMediaWorkflowMessages(queueName,fileURL);
            if(!messages.isEmpty()){
            	return messages;
            }
        }
//            List<MediaWorkflowMessage> messages = queryMediaWorkflowMessages(queueName,fileURL);
//           return messages;
    }

    private static boolean queryMediaWorkflowExecutionCompleted(MediaWorkflowInfo mediaWorkflowInfo, String fileURL) {
    	boolean res = false;
        List<MediaWorkflowMessage> mediaWorkflowMessages = queryMediaWorkflowMessages(mediaWorkflowInfo.queueName(), fileURL);
        if(!mediaWorkflowMessages.isEmpty()){
//	        processMessage(mediaWorkflowMessages);
//	        deleteQueueMessage(mediaWorkflowInfo.queueName(), mediaWorkflowMessages);
	        res = true;
        }
        return res;
    }
    
    private static List<MediaWorkflowMessage> queryMediaWorkflowMessages(String queueName, String fileURL){
    	List<MediaWorkflowMessage> result = new ArrayList<MediaWorkflowMessage>();
    	 List<MediaWorkflowMessage> messages = client.popMessages(CommonUtils.MNS_ENDPOINT, queueName, DConstants.QUEUE_WAIT_SECONDS);
         for (MediaWorkflowMessage message : messages) {
             if (message.type() == MediaWorkflowMessageType.Report && message.fileURL().equals(fileURL)) {
            	 result = messages;
             }
         }
         return result;
    }

    private static void deleteQueueMessage(String queueName, List<MediaWorkflowMessage> mediaWorkflowMessages) {
        List<String> receiptHandles = new ArrayList<String>();
        for (MediaWorkflowMessage mediaWorkflowMessage : mediaWorkflowMessages) {
            receiptHandles.add(mediaWorkflowMessage.receiptHandle());
        }
        client.deleteQueueMessages(CommonUtils.MNS_ENDPOINT, queueName, receiptHandles);
    }

    private static MediaUploadInfo processMediaOutput(String fileURL, String mediaWorkflowName) {
    	
    	MediaUploadInfo info = new MediaUploadInfo();
        Media media = client.getMedia(mediaWorkflowName, fileURL);
        info.setMediaInfo(media.mediaInfo());
        info.setStorageUrl(fileURL);
        for (TranscodeOutput transcodeOutput : media.workflowExecutionOutputs().get(0).transcodeOutputs()) {
            String url = transcodeOutput.ossUrl();
            info.setTranscodeOutputUrl(url);
        }

        for (SnapshotOutput snapshotOutput : media.workflowExecutionOutputs().get(0).snapshotOutputs()) {
            String url = snapshotOutput.ossUrl();
            info.setSnapshotOutputUrl(url);
        }
        info.setStatus("1");
        return info;
    }
    
//  private static void processMessage(List<MediaWorkflowMessage> mediaWorkflowMessages) {
//  for (MediaWorkflowMessage mediaWorkflowMessage : mediaWorkflowMessages) {
//      System.out.println(mediaWorkflowMessage.toString());
//      Media media = client.getMedia(mediaWorkflowMessage.messageWorkflowName(), mediaWorkflowMessage.fileURL());
////      outputMediaInfo(media.mediaInfo());
//  }
//}


//    private static String processTranscodeOutput(TranscodeOutput transcodeOutput) {
//        System.out.println("TranscodeOutput state:\t" + transcodeOutput.state());
//        outputMediaInfo(transcodeOutput.mediaInfo());
//        System.out.println("TranscodeOutput OSS URL:\t" + transcodeOutput.ossUrl());
//        System.out.println("TranscodeOutput Presigned OSS URL:\t" + transcodeOutput.generatePresignedOSSUrl());
//        return transcodeOutput.ossUrl();
//    }

//    private static String processSnapshotOutput(SnapshotOutput snapshotOutput) {
//        System.out.println("SnapshotOutput state:\t" + snapshotOutput.state());
//        System.out.println("SnapshotOutput OSS URL:\t" + snapshotOutput.ossUrl());
//        System.out.println("SnapshotOutput Presigned OSS URL:\t" + snapshotOutput.generatePresignedOSSUrl());
//        return snapshotOutput.ossUrl();
//    }

//    private static void outputMediaInfo(MediaInfo mediaInfo) {
//        System.out.println("Width: " + mediaInfo.width() + ", Height: " + mediaInfo.height() + ", Duration: " + mediaInfo.duration()
//                + ", Format: " + mediaInfo.format() + ", FileSize: " + mediaInfo.fileSize() + ", Bitrate: " + mediaInfo.bitrate());
//    }
	
}
