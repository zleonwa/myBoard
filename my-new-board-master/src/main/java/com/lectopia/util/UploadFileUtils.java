package com.lectopia.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	
	
	public static String uploadFile(String uploadPath, String newName, byte[] fileData) throws IOException {

		File target = new File(uploadPath, newName);
		System.out.println("path: " + target.getAbsolutePath());
		
		// FileCopyUtils는 스프링 객체로 실제 파일 처리 하는 애임
		// copy() 데이터가 담긴 바이트 배열에 대한 파일을 생성. 529p 참고
		FileCopyUtils.copy(fileData, target);
		
		String formatName = newName.substring(newName.lastIndexOf(".")+1);
		String uploadFilename = null;
		
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadFilename = makeThumbnail("/test_file/upload/",newName);
		} else {
			uploadFilename = makeIcon("/test_file/upload/",newName);
		}
		
		return uploadFilename;
	}
	
	/*private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		logger.info(datePath);
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String yearPath, String monthPath, String datePath) {
		String[] paths = new String[]{yearPath, monthPath, datePath};
		
		if(new File(uploadPath + paths[paths.length-1]).exists()) {
			return;
		}
		
		for(String path:paths) {
			File dirPath = new File(uploadPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}
	
	public static String uploadFile(String uploadPath, String originalName, String userId) {
		String 
		return userId;
	}*/
	

	
	//String uploadPath, String path, String filename
	private static String makeThumbnail(String uploadPath, String filename) throws IOException {
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath, filename));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName = uploadPath + File.separator + "s_" + filename;
		File newFile = new File(thumbnailName);
		String formatName = filename.substring(filename.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
		/*String thumbnailName = uploadPath + path + File.separator + "s_" + filename;
		
		File newFile = new File(thumbnailName);
		String formatName = filename.substring(filename.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');*/
	}
	
	private static String makeIcon(String uploadPath,String filename) {
		String iconName = uploadPath+ File.separator + filename;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
