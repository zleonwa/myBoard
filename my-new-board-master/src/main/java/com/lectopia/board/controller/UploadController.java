package com.lectopia.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.lectopia.board.domain.BoardVO;


@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	//commons-fileupload library
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public void uploadForm(MultipartFile file, Model model,HttpServletRequest req) throws Exception{
		
		BoardVO vo = new BoardVO();
		vo.setWriter(req.getParameter("userid"));
		if(file!=null) {
			//System.out.println("UserId : "+req.getParameter("userid"));
			logger.info("originalName : "+file.getOriginalFilename());
			logger.info("size: "+file.getSize());
			logger.info("contentType: "+file.getContentType());

			String newFilename = uploadFile(file.getOriginalFilename(),file.getBytes(),vo.getWriter());
			//model.addAttribute("savedName", file.getOriginalFilename());
			
			vo.setFilename(newFilename);
			vo.setFilesize((int)file.getSize());
			vo.setOldname(file.getOriginalFilename());
			
		}
		
		//insert board�۾��ϱ�
		System.out.println(vo);
		
	}
	
	public static String uploadFile(String fileName, byte[] fileData, String userId) throws IOException {
		// ���� ������ �̸��� ���� �����͸� ����Ʈ�迭�� ��ȯ�� ������ �Ķ���ͷ� ó���ؼ� ������ ������ ���ε���.
		File dir = new File("/test_file/upload/");
		// File dir = new File(dirName + File.separator + "test_file/upload/"); // ��Ĺ�� �ø���
		
		if(!dir.exists()) { // ������ ������ �ͱ۾�
			dir.mkdirs();	// mkdirs ��� ��� ������ �� �������
			// mkdir�� �� ������ ���� �ϳ��� ����
		}
		String newName = getNewFilename(fileName);
		File target = new File(dir, newName);
		System.out.println("path: " + target.getAbsolutePath());
		
		// FileCopyUtils�� ������ ��ü�� ���� ���� ó�� �ϴ� ����
		// copy() �����Ͱ� ��� ����Ʈ �迭�� ���� ������ ����. 529p ����
		FileCopyUtils.copy(fileData, target);

		return newName;
	}
	private static String getNewFilename(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".")); //�ڿ� Ȯ����
		return UUID.randomUUID().toString() + ext;
	}
	/*public ResponseEntity<byte[]> displayFile(String fileName){
		InputStream in = null;
	}*/
}
