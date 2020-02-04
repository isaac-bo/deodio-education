package com.deodio.elearning.utils;
  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.deodio.core.constants.Constants;
import com.deodio.core.exception.ExcelException;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.StringUtils;
import com.deodio.elearning.constants.DConstants;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
  
/**
 * 解压缩公共类
 * @author P0076724
 *
 */
public class ZipUtils {
	
	private static final int BUFFEREDSIZE = 1024;
	
	/**
	 * 解压缩Action
	 * @param zipFilename
	 * @param outputDirectory
	 * @param packageId
	 * @throws IOException
	 * @throws RarException
	 * @throws InterruptedException
	 */
	public static void unPackage(String zipFilename, String outputDirectory,String packageId) throws IOException, RarException, InterruptedException{
		if (zipFilename.toLowerCase().endsWith(".rar")||zipFilename.toLowerCase().endsWith(".RAR")) {
			unRarFile(zipFilename,outputDirectory,packageId);
        }else if(zipFilename.toLowerCase().endsWith(".zip")||zipFilename.toLowerCase().endsWith(".ZIP")){
        	unzip(zipFilename,outputDirectory,packageId);
        }
	}
	
	
	/**
	 * 抽取压缩包中指定文件到指定路径
	 * @param packageFilePath
	 * @param newFilePath
	 * @throws IOException 
	 */
	public static  boolean createFileFromPackage(String packageFilePath,String newFilePath,String newFileName) throws IOException{
		boolean result = false;
		ZipInputStream in = new ZipInputStream(new FileInputStream(packageFilePath));
		System.out.print(newFilePath);
		OutputStream out = new FileOutputStream(newFilePath);
		ZipEntry entry;
		byte[] buf = new byte[1024];
		int len;
		int flag = 0;
		while (flag != 1) {
			entry = in.getNextEntry();
			if (entry != null) {
				if ((entry.getName()).equalsIgnoreCase(newFileName)) {
					flag = 1;
				}
			} else {
				break;
			}
		}
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		out.close();
		in.close();
		result = true;
		return result;
	}

	/**
	 * 解压zip或者rar包的内容到指定的目录下，可以处理其文件夹下包含子文件夹的情况
	 * @param zipFilename
	 * @param outputDirectory
	 * @param packageId
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void unzip(String zipFilename, String outputDirectory,String packageId)
			throws IOException, InterruptedException {
		File outFile = new File(outputDirectory);
		if (!outFile.exists()) {
			outFile.mkdirs();
		}
		boolean delFlag = false;
		
		Charset gbk = Charset.forName("GBK");
		
		ZipFile zipFile = new ZipFile(zipFilename,gbk);
		Enumeration<? extends ZipEntry> en = zipFile.entries();
		ZipEntry zipEntry = null;
		while (en.hasMoreElements()) {
			zipEntry = en.nextElement();
			if (zipEntry.isDirectory()) {
				// mkdir directory
				String dirName = zipEntry.getName();
				dirName = dirName.substring(0, dirName.length() - 1);
				File f = new File(outFile.getPath() + File.separator + dirName);
				f.mkdirs();
			} else {
				String strFilePath = outFile.getPath() + File.separator
						+ zipEntry.getName();
				File f = new File(strFilePath);
				// 判断文件不存在的话，就创建该文件所在文件夹的目录
				String fileType = StringUtils.substringAfter(f.getName(), Constants.STRING_DOT);
	        	if(StringUtils.contain(DConstants.OFFICE_FILE_TYPES,fileType)){
					if (!f.exists()) {
						String[] arrFolderName = zipEntry.getName().split("/");
						String strRealFolder = "";
						for (int i = 0; i < (arrFolderName.length - 1); i++) {
							strRealFolder += arrFolderName[i] + File.separator;
						}
						strRealFolder = outFile.getPath() + File.separator
								+ strRealFolder;
						File tempDir = new File(strRealFolder);
						// 此处使用.mkdirs()方法，而不能用.mkdir()
						tempDir.mkdirs();
					}
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[BUFFEREDSIZE];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
	        	}else{
	        		System.out.println("############含有非标准文件::"+f.getName()+"！############");
	        		delFlag = true;
	        		break ;
	        	}
			}
		}
		zipFile.close();
		if(delFlag){
			File file = new File(zipFilename);
			//删除文件夹
			FileUtils.removeDir(StringUtils.substringBefore(file.getPath(), file.getName()));
			//删除文件
			Thread.sleep(1000);
			if (file.exists()) {
				file.delete();  
	        } 
			/*//删除数据
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("presentationFilesId", packageId);
			presentationService.delPresentationFiles(map);*/
			throw new ExcelException("zip含有非标准文件");
		}
	}
	
	/**
	 * 根据原始rar路径，解压到指定文件夹下. 
	 * @param srcRarPath
	 * @param dstDirectoryPath
	 * @param packageId
	 * @throws RarException
	 * @throws IOException
	 */
	private static void unRarFile(String srcRarPath, String dstDirectoryPath,String packageId) throws RarException, IOException {
        if (!srcRarPath.toLowerCase().endsWith(".rar")) {
            System.out.println("非rar文件！");
            return;
        }
        File dstDiretory = new File(dstDirectoryPath);
        if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹
            dstDiretory.mkdirs();
        }
        Archive a = null;
        File file = new File(srcRarPath);
        a = new Archive(file);
        if (a != null) {
            a.getMainHeader().print(); // 打印文件信息.
            FileHeader fh = a.nextFileHeader();
            while (fh != null) {
            	
            	//获取文件名，修正中文乱码现象  modify by xuxiangdong 2017.2.13 start  
            	String fileName = StringUtils.EMPTY_STRING;
            	if(StringUtils.isNotBlank(fh.getFileNameW())){
            		fileName = fh.getFileNameW();
            	}else{
            		fileName = fh.getFileNameString();
            	}
            	//获取文件名，修正中文乱码现象  modify by xuxiangdong 2017.2.13 end
            	
                if (fh.isDirectory()) { // 文件夹 
                    File fol = new File(dstDirectoryPath + File.separator + fileName);
                    fol.mkdirs();
                } else { // 文件
                	File out = new File(dstDirectoryPath + File.separator + fileName);
                	String fileType = StringUtils.substringAfter(out.getName(), Constants.STRING_DOT);
                	if(StringUtils.contain(DConstants.OFFICE_FILE_TYPES,fileType)){
                		if (!out.exists()) {
                            if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录. 
                                out.getParentFile().mkdirs();
                            }
                            out.createNewFile();
                        }
                        FileOutputStream os = new FileOutputStream(out);
                        a.extractFile(fh, os);
                        os.close();
                	}else{
                		System.out.println("############含有非标准文件::"+out.getName()+"！############");
                		a.close();
                		//删除文件夹
                		FileUtils.removeDir(StringUtils.substringBefore(out.getPath(), out.getName()));
                		//删除文件
                		if (file.exists()) {  
                			file.delete();  
                        }
                		/*//删除数据
                		Map<String,Object> map = new HashMap<String,Object>();
                		map.put("presentationFilesId", packageId);
                		presentationService.delPresentationFiles(map);
                		throw new ExcelException("rar含有非标准文件");*/
                	}
                }
                fh = a.nextFileHeader();
            }
            a.close();
        }
    }
}