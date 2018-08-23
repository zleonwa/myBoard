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
		
		//insert board작업하기
		System.out.println(vo);
		
	}
	
	public static String uploadFile(String fileName, byte[] fileData, String userId) throws IOException {
		// 원본 파일의 이름과 파일 데이터를 바이트배열로 변환한 정보를 파라미터로 처리해서 실제로 파일을 업로드함.
		File dir = new File("/test_file/upload/");
		// File dir = new File(dirName + File.separator + "test_file/upload/"); // 톰캣에 올리기
		
		if(!dir.exists()) { // 폴더가 없으면 맹글어
			dir.mkdirs();	// mkdirs 모든 경로 일일이 다 만들어줌
			// mkdir은 맨 마지막 폴더 하나만 생성
		}
		String newName = getNewFilename(fileName);
		File target = new File(dir, newName);
		System.out.println("path: " + target.getAbsolutePath());
		
		// FileCopyUtils는 스프링 객체로 실제 파일 처리 하는 애임
		// copy() 데이터가 담긴 바이트 배열에 대한 파일을 생성. 529p 참고
		FileCopyUtils.copy(fileData, target);

		return newName;
	}
	private static String getNewFilename(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".")); //뒤에 확장자
		return UUID.randomUUID().toString() + ext;
	}
	/*public ResponseEntity<byte[]> displayFile(String fileName){
		InputStream in = null;
	}*/
}
