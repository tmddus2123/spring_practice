package com.githrd.www.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.RedirectView;

import com.githrd.www.dao.GBoardDao;
import com.githrd.www.dao.MemberDao;
import com.githrd.www.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class Member {
	@Autowired
	MemberDao mDao;
	@Autowired
	GBoardDao gDao;
	
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
			session.setAttribute("MSG_CHECK", "OK");
			int count = gDao.getMyCount(mVO.getId());
			session.setAttribute("CNT", count);
			
			if(count == 0) {
				rv.setUrl("/www/gBoard/gBoardList.blp");
			} else {
				rv.setUrl("/www/main.blp");
			}
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
	public ModelAndView adminProc(MemberVO mVO, HttpSession session, ModelAndView mv, RedirectView rv) {
		System.out.println("### 관리자 ###");
		
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
		int cnt = mDao.getLogin(mVO);
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			session.setAttribute("MSG_CHECK", "OK");
			int count = gDao.getMyCount(mVO.getId());
			session.setAttribute("CNT", count);
			
			if(count == 0) {
				rv.setUrl("/www/gBoard/gBoardList.blp");
			} else {
				rv.setUrl("/www/main.blp");
			}
		} else {
			rv.setUrl("/www/member/login.blp");
		}
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping(path="/myInfo.blp", params="id")
	public ModelAndView myInfo(String id, ModelAndView mv) {
		// 데이터 가져오고
		MemberVO mVO = mDao.getIdInfo(id);
		// 뷰에 데이터 심고
		mv.addObject("DATA", mVO);
		// 뷰 정하고
		mv.setViewName("member/memberInfo");
		return mv;
	}
	
	@RequestMapping("/memberInfo.blp")
	public ModelAndView memberInfo(ModelAndView mv, int mno) {
		// 데이터 가져오고
		MemberVO mVO = mDao.getMnoInfo(mno);
		System.out.println(mVO);
		// 뷰에 데이터 심고
		mv.addObject("DATA", mVO);
		// 뷰 정하고
		mv.setViewName("member/memberInfo");
		return mv;
	}
	
	@RequestMapping("/memberList.blp")
	public ModelAndView memberList(ModelAndView mv) {
		List<MemberVO> list = mDao.getMemberList();
		mv.addObject("LIST", list);
		mv.setViewName("member/memberList");
		
		return mv;
	}
	
	@RequestMapping("/delMember.blp")
	public ModelAndView delMember(ModelAndView mv, String id, RedirectView rv, HttpSession session) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/www/member/login.blp");
			mv.setView(rv);
			return mv;
		}
		
		if(!id.equals(sid)) {
			rv.setUrl("/www/member/myInfo.blp");
			mv.setView(rv);
			return mv;
		}
		
		int cnt = mDao.delMember(id);
		
		if(cnt == 1) {
			// 세션에 기억시켜둔 데이터를 삭제하고
			session.removeAttribute("SID");
			rv.setUrl("/www/");
		} else {
			rv.setUrl("/www/member/myInfo.blp");
		}
		
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/myInfoEdit.blp")
	public ModelAndView editInfo(ModelAndView mv, String id, HttpSession session, RedirectView rv) {
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			rv.setUrl("/www/member/login.blp");
			mv.setView(rv);
			return mv;
		}
		
		if(!id.equals(sid)) {
			rv.setUrl("/www/");
			mv.setView(rv);
			return mv;			
		}
		
		// 데이터베이스 조회
		MemberVO mVO = mDao.getIdInfo(id);
		List<MemberVO> list = mDao.getAvtList(id);
		
		mv.addObject("DATA", mVO);
		mv.addObject("LIST", list);
		
		mv.setViewName("member/editInfo");
		return mv;
	}
	
	// 내정보 수정 처리요청 처리 함수
	@RequestMapping("/infoEditProc.blp")
	public ModelAndView infoEditProc(ModelAndView mv, MemberVO mVO, RedirectView rv) {
		int cnt = mDao.editMyInfo(mVO);
		String view = "member/redirect";
		if(cnt == 0) {
			mv.addObject("VIEW","/www/member/myInfoEdit.blp");
		} else {
			mv.addObject("VIEW","/www/member/myInfo.blp");			
		}
		mv.setViewName(view);
		return mv;
	}
}