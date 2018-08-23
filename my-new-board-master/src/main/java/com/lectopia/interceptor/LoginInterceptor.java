package com.lectopia.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		HttpSession session = request.getSession();
		
		String loginId = (String) session.getAttribute("login");
		if(loginId == null || loginId.isEmpty()) {
			response.sendRedirect("/newlogin");
			return false;
		}
		return true;
	}

		/**
		 * This implementation is empty.
		 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
}
