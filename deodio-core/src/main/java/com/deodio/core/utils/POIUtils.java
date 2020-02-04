package com.deodio.core.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import com.deodio.core.constants.Constants;

public class POIUtils {

	/**
	 * PPT转图片 （jpeg）(2007)
	 * 
	 * @param file
	 * @param path
	 *            存放路径
	 * @param picName
	 *            图片前缀名称 如 a 生成后为a_1,a_2 ...
	 * @param picType
	 *            转成图片的类型，无点 如 jpg bmp png ...
	 * @return true/false
	 */
	public static boolean doPPTtoImage2007(File file, String path,
			String picName, String picType) {
		try {
			FileInputStream is = new FileInputStream(file);
			XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
			java.awt.Dimension pgsize = xmlSlideShow.getPageSize();
			XSLFSlide[] xslfSlides = xmlSlideShow.getSlides();

			is.close();
			for (int i = 0; i < xslfSlides.length; i++) {

				System.out.print("第" + i + "页。");
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
						BufferedImage.TYPE_INT_RGB);

				Graphics2D graphics = img.createGraphics();
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				xslfSlides[i].draw(graphics);

				FileOutputStream out = new FileOutputStream(path + "/"
						+ picName + "_" + (i + 1) + "." + picType);
				javax.imageio.ImageIO.write(img, "jpeg", out);
				out.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void ppt2Png(String file,Integer scale) throws InvalidFormatException, IOException{
        
	
		XMLSlideShow ppt = new XMLSlideShow(OPCPackage.open(file));
		
		scale = scale==null?1:scale;

        Dimension pgsize = ppt.getPageSize();
        int width = (int) (pgsize.width * scale);
        int height = (int) (pgsize.height * scale);

        XSLFSlide[] slide = ppt.getSlides();
        for (int i = 0; i < slide.length; i++) {

            String title = slide[i].getTitle();
            System.out.println("Rendering slide " + (i + 1) + (title == null ? "" : ": " + title));

            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();

            // default rendering options
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            graphics.setColor(Color.white);
            graphics.clearRect(0, 0, width, height);

            graphics.scale(scale, scale);

            // draw stuff
            slide[i].draw(graphics);

            // save the result
            int sep = file.lastIndexOf(".");
            String fname = file.substring(0, sep == -1 ? file.length() : sep) + "-" + (i + 1) +".png";
            FileOutputStream out = new FileOutputStream(fname);
            ImageIO.write(img, "png", out);
            out.close();
        } 
		
	}
	
	
	 public static String WordToHtml(final String wordPath,final String imageDir,String htmlName,String httpUrl) throws IOException, TransformerException, ParserConfigurationException  {
	    String resultHtml = StringUtils.EMPTY;
   	  	File file = new File(wordPath);
	    String wordName = StringUtils.substringBefore(file.getName(), ".");
	    String htmlFolder = StringUtils.EMPTY_STRING;
	    //未指定html名称时，使用初始文件名称；
	    if(StringUtils.isBlank(htmlName)){
	    	htmlFolder = wordName;
	    	htmlName = wordName + Constants.URL_SUFFIX;
	    }else{
	    	htmlFolder = FileUtils.getName(htmlName, false);
	    }
	    
	    String filePath = file.getParent() + File.separator + htmlFolder + File.separator;
	    //图片网络路径转换
	    String htmlImgs = StringUtils.substringAfterLast(filePath, "package"); 
	    htmlImgs = httpUrl+"/"+"package"+StringUtils.replace(htmlImgs,File.separator,"/");
	    
	    final String imagePath = filePath + imageDir + File.separator;
	      
        if (!file.exists()) {  
            System.out.println("Sorry File does not Exists!");  
        } else {  
       	 	InputStream in = new FileInputStream(file);  
       	 	
       	 	FileUtils.createDir(filePath);
       	 	
            if (file.getName().endsWith(".docx") || file.getName().endsWith(".DOCX")) {  
                // 1) 加载word文档生成 XWPFDocument对象  
                XWPFDocument document = new XWPFDocument(in);  
                // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)  
                File imageFolderFile = new File(imagePath);  
                if(!imageFolderFile.exists()){//图片目录不存在则创建
               	 imageFolderFile.mkdirs();
	             }
                XHTMLOptions options = XHTMLOptions.create();
                options.setExtractor(new FileImageExtractor(imageFolderFile));  
                options.URIResolver(new BasicURIResolver(htmlImgs+imageDir));
                options.setIgnoreStylesIfUnused(false);  
                options.setFragment(true);  
                // 3) 将 XWPFDocument转换成XHTML  
                OutputStream out = new FileOutputStream(new File(filePath + htmlName));  
                XHTMLConverter.getInstance().convert(document, out, options);  
                
            } else if (file.getName().endsWith(".doc") || file.getName().endsWith(".DOC")){  
	   		      HWPFDocument wordDocument = new HWPFDocument(in);
	   		      WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	   		      //设置图片存放的位置
	   		      wordToHtmlConverter.setPicturesManager(new PicturesManager() {
	   		          public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
	   		              File imgPath = new File(imagePath);
	   		              if(!imgPath.exists()){//图片目录不存在则创建
	   		                  imgPath.mkdirs();
	   		              }
	   		              File file = new File(imagePath + suggestedName);
	   		              try {
	   		                  OutputStream os = new FileOutputStream(file);
	   		                  os.write(content);
	   		                  os.close();
	   		              } catch (FileNotFoundException e) {
	   		                  e.printStackTrace();
	   		              } catch (IOException e) {
	   		                  e.printStackTrace();
	   		              }
	   		              return imageDir + File.separator +suggestedName;
	   		          }
	   		      });
	   		      //解析word文档
	   		      wordToHtmlConverter.processDocument(wordDocument);
	   		      Document htmlDocument = wordToHtmlConverter.getDocument();
	   		      File htmlFile = new File(filePath + htmlName);
	   		      OutputStream outStream = new FileOutputStream(htmlFile);
	   		      DOMSource domSource = new DOMSource(htmlDocument);
	   		      StreamResult streamResult = new StreamResult(outStream);
	   		
	   		      TransformerFactory factory = TransformerFactory.newInstance();
	   		      Transformer serializer = factory.newTransformer();
	   		      serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	   		      serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	   		      serializer.setOutputProperty(OutputKeys.METHOD, "html");
	   		      serializer.transform(domSource, streamResult);
	   		
	   		      outStream.close();
            }  
        } 
        
        resultHtml = filePath + htmlName;
        return resultHtml;
    }
	
	public static void main(String[] args) {
//		//doPPTtoImage2007(new File("/Users/a2/Documents/work/doc/BigData.pptx"),"/Users/a2/Documents/work/doc","","png");
//		try {
////			ppt2Png("/Users/a2/Documents/work/doc/ppt/BigData.pptx",1);
//			
//			//convert2Html("E://test//ibm.doc","E://test//abc.html"); 
//			System.out.println("start");
//      	  	String filePath = "z:/deodioStatic/package/4714bc52d79d491a83c31d33f771ade3/c14eab870c0a4d18a1f3b412254e272f/1cd6ac15fd6144679e9a0bb05b44252e/2017/2/27/IDHH1HIpsG8e5OxbZSvWJ5Qi8wvwsmUU.doc";
//      	  	final String imageDirectory= "image";
//      	  	String htmlName = "";
//      	  	WordToHtml(filePath,imageDirectory,htmlName);
//      	  	System.out.println("success");
//			
//		} 
////		catch (InvalidFormatException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TransformerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String s1 ="z:xxx\\package\\0d54a569d8234b3d8f8a0972ec2e4839\\04a6977f1e594c498f230e99990637b5\\f75740f38a044404a67670f779ee156a\\2018\\7\\26\\0MoznRYSw54OtYWx2SYUgGVbzWpfH6u6\\";
		s1 =StringUtils.substringAfterLast(s1, "package"); 
		s1 = StringUtils.replace(s1,File.separator,"/");
		System.out.println("package"+s1);
		
	}

}
