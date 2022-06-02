package com.githrd.www.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.githrd.www.dao.MemberDao;
import com.githrd.www.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class Member {
	@Autowired
	MemberDao mDao;
	
	@RequestMapping("/login.blp")
	public ModelAndView loginForm(ModelAndView mv, HttpSession session) {
		mv.setViewName("member/login");
		return mv;
	}
	
	/*
	 * @RequestMapping("/login.blp") public String loginForm(HttpSession session,
	 * HttpServletResponse resp) {
	 * 
	 * return "member/login"; }
	 */
	
	@RequestMapping(path="/loginProc.blp", method=RequestMethod.POST, params= {"id", "pw"})
	public ModelAndView loginProc(MemberVO mVO, HttpSession session, ModelAndView mv, RedirectView rv) {
//		System.out.println("### 일반 사용자 ###");
		int cnt = mDao.getLogin(mVO);
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			rv.setUrl("/www/main.blp");
		} else {
			rv.setUrl("/www/member/login.blp");
		}
		mv.setView(rv);
		return mv;
	}

	/*
	 * @RequestMapping(path="/loginProc.blp", method=RequestMethod.POST, params=
	 * {"id", "pw"}) public ModelAndView loginProc(String sid, String spw, MemberVO
	 * mVO, HttpSession session, ModelAndView mv) { //
	 * System.out.println("### 일반 사용자 ###"); int cnt = mDao.getLogin(mVO); String
	 * view = ""; if(cnt == 1) { session.setAttribute("SID", mVO.getId()); view =
	 * "redirect:../main.blp"; } else { view = "redirect:login.blp"; }
	 * mv.setViewName(view); return mv; }
	 */	
	@RequestMapping(path="/loginProc.blp", params="id=admin")
	public ModelAndView adminProc(MemberVO mVO, HttpSession session, ModelAndView mv) {
		System.out.println("### 관리자 ###");
		
		int cnt = mDao.getLogin(mVO);
		String view = "";
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			view = "redirect:../main.blp";
		} else {
			view = "redirect:login.blp";
		}
		mv.setViewName(view);
		return mv;
	}
	
	@RequestMapping("/logout.blp")
	public ModelAndView logout(ModelAndView mv, HttpSession session, RedirectView rv) {
		session.removeAttribute("SID");
		rv.setUrl("/www/");
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping(path="/idCheck.blp", params="id")
	@ResponseBody
	public Map<String, String> idCheck(String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "NO";
		
		int cnt = mDao.getIdCnt(id);
		if(cnt == 0) {
			result = "OK";
		}
		map.put("result",  result);
		return map;
	}
	// 회원가입 폼보기 요청
	@RequestMapping("/join.blp")
	public ModelAndView joinForm(ModelAndView mv, RedirectView rv) {
		List<MemberVO> list = mDao.getAvtList();
		// 데이터 심고
		mv.addObject("LIST", list);
		
		mv.setViewName("member/join");
		return mv;
	}
	
	/* // 회원가입 폼보기 요청
	 * 
	 * @RequestMapping("/join.blp") public String joinForm() { return "member/join";
	 * }
	 */
	
	@RequestMapping(path="/joinProc.blp", method=RequestMethod.POST)
	public ModelAndView joinProc(MemberVO mVO, ModelAndView mv, RedirectView rv, HttpSession session) {
		int cnt = mDao.addMember(mVO);
		if(cnt == 1) {
			// 성공한 경우
			session.setAttribute("SID", mVO.getId());
			rv.setUrl("/www/");
		} else {
			rv.setUrl("/www/member/join.blp");			
		}
		mv.setView(rv);
		
		return mv;
	}
	
	//@RequestMapping(path="/myInfo.blp", params="id")
	
}