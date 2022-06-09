package com.githrd.www.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;
import com.githrd.www.util.*;

/**
 * 
 * @author tmddus
 * @since	22.06.08
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				22.06.08	-	담당자 : 이승연
 * 								클래스 제작, 리스트 보기
 * 
 */								

@Controller
@RequestMapping("/reBoard")
public class ReBoard {
	@Autowired
	ReBoardDao rDao;
	
	// 댓글게시판 리스트보기 요청
	@RequestMapping("/reBoardList.blp")
	public ModelAndView reBoardList(ModelAndView mv, PageUtil page) {
		/*
		 할 일
		 데이터베이스에서 데이터 가져오고
		 PageUtil이 먼저 만들어져야하기 때무에
		 총 게시글 수부터 가져온다.
		 */
		int total = rDao.getTotal();
		
		// PageUtil 을 셋팅해준다.
		page.setPage(total);
		
		List<BoardVO> list = rDao.getList(page);
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		mv.setViewName("reBoard/reBoardList");
		
		return mv;
	}
	
	@RequestMapping(path="/reBoardWrite.blp", method=RequestMethod.POST, params= {"id", "nowPage"})
	public ModelAndView reBoardWrite(ModelAndView mv, String id, String nowPage) {		
		BoardVO bVO = rDao.getWriteInfo(id);
		
		mv.addObject("DATA", bVO);
		
		mv.setViewName("reBoard/reBoardWrite");
		
		return mv;
	}
	
	@RequestMapping("/writeProc.blp")
	public ModelAndView writeProc(ModelAndView mv, BoardVO bVO, String nowPage) {
		int cnt = rDao.addReBoard(bVO);
		
		String view = "/www/reBoard/reBoardList.blp";
		if(cnt == 0) {
			view = "/www/reBoard/reBoardWrite.blp";
			mv.addObject("NOWPAGE", nowPage);
		} else {
			nowPage = "1";
			mv.addObject("MSG", "게시글 등록에 성공했습니다.");
		}
		mv.addObject("VIEW", view);
		mv.addObject("NOWPAGE", nowPage);
		
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	// 댓글 작성 폼보기 처리함수
	@RequestMapping("/commentWrite.blp")
	public ModelAndView commentWrite(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		bVO = rDao.getCommentData(bVO);
		
		// 데이터 셋팅하고 
		mv.addObject("DATA", bVO);
		
		mv.setViewName("reBoard/reBoardComment");
		
		return mv;
	}
	
	// 댓글 등록 요청 처리함수
	@RequestMapping("/commentProc.blp")
	public ModelAndView writeProc(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		
		//int result
		
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	// 게시글 수정 폼보기 요청 처리함수
	@RequestMapping("/reBoardEdit.blp")
	public ModelAndView reBoardEdit(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		bVO = rDao.getEditData(bVO);
		
		mv.addObject("DATA", bVO);
		mv.setViewName("reBoard/reBoardEdit");
		return mv;
	}
	
	// 게시글 수정 요청 처리함수
	@RequestMapping("/editProc.blp")
	public ModelAndView editProc(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		int result = rDao.editProc(bVO);
		
		if(result == 1) {
			mv.addObject("VIEW", "/www/reBoard/reBoardList.blp");
		} else {
			mv.addObject("VIEW", "/www/reBoard/reBoardEdit.blp");
		}
		mv.addObject("NOWPAGE", nowPage);
		
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
	@RequestMapping("/delReBoard.blp")
	public ModelAndView delReBoard(ModelAndView mv, BoardVO bVO, String nowPage, String vw) {
		rDao.delReBoard(bVO);
		
		mv.addObject("VIEW", vw);
		mv.addObject("NOWPAGE", nowPage);		
		
		mv.setViewName("reBoard/redirect");
		return mv;
	}
	
}
