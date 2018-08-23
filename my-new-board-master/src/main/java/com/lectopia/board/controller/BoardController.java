package com.lectopia.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lectopia.board.domain.BoardVO;
import com.lectopia.board.domain.Criteria;
import com.lectopia.board.domain.PageMaker;
import com.lectopia.board.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public String listAll(Model model) throws Exception {
		
		logger.info("show all list......................");
	    model.addAttribute("list", boardService.getAll());
	    return "/board/listAll";
	}
	
	@RequestMapping(value="/listPage", method=RequestMethod.GET)
	public void listPage(Criteria cri, Model model, HttpSession session) throws Exception {
		//String namespace;
		logger.info(cri.toString());

		// 목록 데이터를 Model에 저장하는 작업 && PageMaker를 구성해서 Model에 담는 작업
	    model.addAttribute("list", boardService.listCriteria(cri));
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    //pageMaker.setTotalCount(1900);
	    
	    pageMaker.setTotalCount(boardService.listCountCriteria(cri));
	    
	    model.addAttribute("pageMaker", pageMaker);
	    
	    //session.selectOne(namespace + ".countPaging", cri);
	}

	@RequestMapping(value="/readPage",method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		model.addAttribute(boardService.get(bno));
	}

	
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam("bno") int bno, @ModelAttribute("cri")Criteria cri, Model model) throws Exception {
		logger.info("Modify get method called....");
		
		// DB 검색 -> Modify 페이지로 이동 
		// Model org.springframework.ui.Model.addAttribute(String attributeName, Object attributeValue)
		model.addAttribute("boardVO", boardService.get(bno));
		System.out.println(cri.getPage());
		
		return "/board/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPost(BoardVO vo, Model model, Criteria cri, RedirectAttributes rda) throws Exception {
		logger.info("Modify post method called....");
		System.out.println(cri.getPage());
		
		try {
			boardService.modify(vo);
			rda.addAttribute("page", cri.getPage());
			rda.addAttribute("perPageNum", cri.getPerPageNum());
			rda.addFlashAttribute("msg", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Modify post method - Error occured [" + e.getMessage() + "]");
		}
		return "redirect:/board/listPage"; 
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rda) throws Exception {
		boardService.remove(bno);
		
		rda.addAttribute("page", cri.getPage());
		rda.addAttribute("perPageNum", cri.getPerPageNum());
		rda.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listPage";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerView(BoardVO board, Model model, HttpSession session) {
		logger.info("Regisrer get method called....");
		
		String loginId = (String) session.getAttribute("login");
		if(loginId == null || loginId.isEmpty())
			return "/login";
		return "/board/register";
	}
	
	/*@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(BoardVO vo, Model model) { 
		logger.info("Regisrer post method called....");
		
		try {
			boardService.regist(vo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Register post method - Error occured [" + e.getMessage() + "]");
		}
		
		model.addAttribute("result", "success");
		
		return "redirect:/board/listAll"; 
	}
*/		
	@RequestMapping(value="/filedown", method=RequestMethod.GET)
	public void getFile(BoardVO vo, HttpSession session, HttpServletResponse response) throws Exception {
	    try {
	    	System.out.println(vo.getContent());
	    	BoardVO board = boardService.get(vo.getBno());
	        String filePathToBeServed = "/test_file/upload/"+ board.getFilename();
	        File fileToDownload = new File(filePathToBeServed);
	        InputStream inputStream = new FileInputStream(fileToDownload);
	        
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+ board.getFilename()); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	    	logger.debug("Request could not be completed at this moment. Please try again.");
	        e.printStackTrace();
	    }
	}
	
	// upload file 파이리이이이---------------------------------------------------------------------

	public String uploadFile(String fileName, byte[] fileData, String userId) throws IOException {
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
	
	private String getNewFilename(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".")); //뒤에 확장자
		return UUID.randomUUID().toString() + ext;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(BoardVO board, MultipartFile file, Model model) throws Exception {
		logger.info("register get method called");
		System.out.println("***************************"+board.getTitle());
		
		if(file!=null) {
			if(!file.isEmpty()) {
				//String newFilename = UploadFileUtils.uploadFile(file.getOriginalFilename(),file.getBytes(),board.getWriter());
				String newFilename = uploadFile(file.getOriginalFilename(),file.getBytes(),board.getWriter());
				System.out.println(newFilename+"******************************///////////////////");
				board.setFilename(newFilename);
				board.setFilesize((int)file.getSize());
				board.setOldname(file.getOriginalFilename());
			}
		}
		
		//insert board작업하기
		System.out.println(board);
		try {
			boardService.regist(board);	
			//temp에 저장해뒀다가 잘 저장되면 user 폴더로 이동
		}catch(Exception e) {
			e.printStackTrace();
			//주기적으로 temp folder 안의 내용 삭제
		}
		
		logger.info("regist post method called");

		//DB insert -> move to success page
		model.addAttribute("result", "success");
		return "redirect:/board/listPage"; //WEB-INF/classes/views/success.jsp
	}
}





