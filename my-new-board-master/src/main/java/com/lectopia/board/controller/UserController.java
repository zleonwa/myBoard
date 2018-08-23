package com.lectopia.board.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lectopia.board.domain.UserVO;
import com.lectopia.board.service.UserService;
import com.lectopia.util.MD5Util;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping(value="/newlogin", method=RequestMethod.GET)
	public String login() {
		// 로그인 창으로 이동하는 거
		logger.info("Login get method called....");
		return "/newlogin";
	}
	
	@RequestMapping(value="/newlogin", method=RequestMethod.POST)
	public String loginPost(UserVO vo, Model model, HttpSession session){ 
		logger.info("Login post method called....");
		session.removeAttribute("login"); // 왜인지 모르겠지만 우선 로그인 시도하면 기존 세션에 있는 아이디 지워
		
		try {
			if(vo != null) {
				UserVO dbvo = service.login(vo.getUserid());
				
				if(dbvo.getUserpwd().equals(MD5Util.GET_CRYPTO_MD5(vo.getUserpwd()))) {
					session.setAttribute("login", dbvo.getUserid()); // session에 아이디 넣어야징
					return "redirect:/board/listPage"; // 리스트 페이지로 돌아가야징
					
				} else {
					model.addAttribute("error", "wrong_userpwd");
					return "/newlogin";
					
				}
			} else {
				model.addAttribute("error", "wrong_userid");
				return "/newlogin";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Login post method - Error occured [" + e.getMessage() + "]");
			return "/error";
		}
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes rda) {
		
		session.removeAttribute("login");
		rda.addAttribute("msg", "logout");
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		logger.info("Join get method called....");
		return "/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinPost(UserVO vo, Model model, HttpSession session, RedirectAttributes rda) {
		
		session.removeAttribute("login");
		
		try {
			if(vo != null && vo.getUserid() != null && vo.getUserpwd() != null) {
				if(service.login(vo.getUserid()) == null) {
					System.out.println(vo.getUserid()+"********************************************");
					vo.setUserpwd(MD5Util.GET_CRYPTO_MD5(vo.getUserpwd()));
					service.join(vo);
				} else {
					model.addAttribute("msg", "id");
					return "/join";
				}
			} else {
				model.addAttribute("msg", "try");
				return "/join";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Join post method - Error occured [" + e.getMessage() + "]");
			model.addAttribute("msg","try");
			return "/join";
		}

		rda.addAttribute("msg", "welcome");
		
		return "redirect:/board/listPage";
	}
	
}
