package com.deodio.core.utils.Pdf2htmlEXUtil;

import java.io.File;
import java.util.Properties;

import com.deodio.core.constants.Constants;
import com.deodio.core.utils.FileUtils;
import com.deodio.core.utils.StringUtils;

/**
 *  pdf文件转html工具类
 */
public class Pdf2htmlEXUtil {
    /**
     * 调用pdf2htmlEX将pdf文件转换为html文件
     * 
     * @param exeFilePath
     *            pdf2htmlEX.exe文件路径
     * @param pdfFile
     *            pdf文件绝对路径
     * @param [destDir] 生成的html文件存放路径
     * @param htmlName
     *            生成的html文件名称
     * @return
     */
    public static boolean pdf2html_windows(String exeFilePath, String pdfFile,
            String destDir, String htmlFileName) {
        if (!(exeFilePath != null && !"".equals(exeFilePath) && pdfFile != null
                && !"".equals(pdfFile) && htmlFileName != null && !""
                    .equals(htmlFileName))) {
            System.out.println("传递的参数有误！");
            return false;
        }
        Runtime rt = Runtime.getRuntime();
        StringBuilder command = new StringBuilder();
        command.append(exeFilePath).append(" ");
        if (destDir != null && !"".equals(destDir.trim()))// 生成文件存放位置,需要替换文件路径中的空格
            command.append("--dest-dir ").append(destDir.replace(" ", "\" \""))
                    .append(" ");
        command.append("--optimize-text 1 ");// 尽量减少用于文本的HTML元素的数目 (default: 0)
        command.append("--zoom 1.4 ");
        command.append("--process-outline 0 ");// html中显示链接：0——false，1——true
        command.append("--font-format woff ");// 嵌入html中的字体后缀(default ttf)
                                                // ttf,otf,woff,svg
        command.append(pdfFile.replace(" ", "\" \"")).append(" ");// 需要替换文件路径中的空格
        if (htmlFileName != null && !"".equals(htmlFileName.trim())) {
            command.append(htmlFileName);
            if (htmlFileName.indexOf(".html") == -1)
                command.append(".html");
        }
        try {
            System.out.println("Command：" + command.toString());
            Process p = rt.exec(command.toString());
            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(),
                    "ERROR");
            // 开启屏幕标准错误流
            errorGobbler.start();
            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(),
                    "STDOUT");
            // 开启屏幕标准输出流
            outGobbler.start();
            int w = p.waitFor();
            int v = p.exitValue();
            if (w == 0 && v == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean pdf2html_linux(String pdfFile, String destDir,
            String htmlFileName) {
        if (!(pdfFile != null && !"".equals(pdfFile) && htmlFileName != null && !""
                .equals(htmlFileName))) {
            System.out.println("传递的参数有误！");
            return false;
        }
        Runtime rt = Runtime.getRuntime();
        StringBuilder command = new StringBuilder();
        command.append("pdf2htmlEX").append(" ");
        if (destDir != null && !"".equals(destDir.trim()))// 生成文件存放位置,需要替换文件路径中的空格
            command.append("--dest-dir ").append(destDir.replace(" ", "\" \""))
                    .append(" ");
        command.append("--optimize-text 1 ");// 尽量减少用于文本的HTML元素的数目 (default: 0)
        command.append("--process-outline 0 ");// html中显示链接：0——false，1——true
        command.append("--font-format woff ");// 嵌入html中的字体后缀(default ttf)
                                                // ttf,otf,woff,svg
        command.append(pdfFile.replace(" ", "\" \"")).append(" ");// 需要替换文件路径中的空格
        if (htmlFileName != null && !"".equals(htmlFileName.trim())) {
            command.append(htmlFileName);
            if (htmlFileName.indexOf(".html") == -1)
                command.append(".html");
        }
        try {
            System.out.println("Command：" + command.toString());
            Process p = rt.exec(command.toString());
            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(),
                    "ERROR");
            // 开启屏幕标准错误流
            errorGobbler.start();
            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(),
                    "STDOUT");
            // 开启屏幕标准输出流
            outGobbler.start();
            int w = p.waitFor();
            int v = p.exitValue();
            if (w == 0 && v == 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String pdf2Html(String exeFilePath, String pdfFile,
            String destDir, String htmlFileName){
    	String result = StringUtils.EMPTY;
    	//判断当前操作系统
    	Properties props = System.getProperties();
    	String osName = props.getProperty("os.name");
    	File destDirFile = new File(pdfFile);
    	String pdfName = StringUtils.substringBefore(destDirFile.getName(), ".");
    	String htmlFolder = StringUtils.EMPTY_STRING;
    	
    	if(StringUtils.isBlank(htmlFileName)){
    		 htmlFolder = pdfName;
    		 htmlFileName = pdfName + Constants.URL_SUFFIX;
 	    }else{
 	    	htmlFolder = FileUtils.getName(htmlFileName, false);
 	    }
    	 
    	 String filePath = destDirFile.getParent() + File.separator ;
    	 final String htmlPath = filePath + htmlFolder;
//    	 final String htmlPath = filePath ;
 	      
    	if(!destDirFile.exists()){
    		System.out.println("Sorry File does not Exists!");  
    	}else{
			File imageFolderFile = new File(htmlPath);
			if (!imageFolderFile.exists()) {// 图片目录不存在则创建
				imageFolderFile.mkdirs();
			}
    	}
    	boolean flag = Boolean.FALSE;
    	if(osName.toLowerCase().startsWith("win")){
    		flag = pdf2html_windows(exeFilePath,pdfFile,htmlPath,htmlFileName);
    	}else{
    		flag = pdf2html_linux(pdfFile,htmlPath,htmlFileName);
    	}
    	
    	if(flag){
    		result = htmlPath + File.separator + htmlFileName;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
//        pdf2html("G:\\pdf2htmlEX-v1.0\\pdf2htmlEX.exe","G:\\pdf2htmlEX-v1.0\\PDF\\my.pdf","G:\\pdf2htmlEX-v1.0\\HTML","my.html");
//    	pdf2Html("G:\\pdf2htmlEX-v1.0\\pdf2htmlEX.exe","Z:\\deodioStatic\\package\\4714bc52d79d491a83c31d33f771ade3\\ebdb71a382294f0eadebca39c8785c61\\1cd6ac15fd6144679e9a0bb05b44252e\\2017\\3\\8\\joRS6LtBQe1UEiLv9FAl5wBF1pI0JUvd\\测试030.pdf",
//    			"html","joRS6LtBQe1UEiLv9FAl5wBF1pI0JUvd.html");
    	
    	pdf2Html("G:\\pdf2htmlEX-v1.0\\pdf2htmlEX.exe","Z:\\deodioStatic\\package\\4714bc52d79d491a83c31d33f771ade3\\ebdb71a382294f0eadebca39c8785c61\\1cd6ac15fd6144679e9a0bb05b44252e\\2017\\3\\8\\iByj79Rv4rdQE9CL1jWC92l0Q8gyafkG.pdf",
    			"html","");
    }
}