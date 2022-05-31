package com.githrd.www.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.githrd.www.dao.MemberDao;
import com.githrd.www.vo.MemberVO;

@Controller
public class Member {
	@Autowired
	MemberDao mDao;
	
	public String loginForm(HttpSession session, HttpServletResponse resp) {
		
		return "member/login";
		
	}
	
	public ModelAndView loginProc(MemberVO mVO, HttpServletResponse resp, ModelAndView mv) {
		
		return mv;
	}
}
