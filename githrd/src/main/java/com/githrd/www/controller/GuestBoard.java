package com.githrd.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.githrd.www.dao.GBoardDao;
import com.githrd.www.util.*;
import com.githrd.www.vo.BoardVO;

@Controller
@RequestMapping("/gBoard")
public class GuestBoard {
	@Autowired(required=false)
	GBoardDao gDao;
	
	@RequestMapping("/gBoardList.blp")
	public ModelAndView gBoardList(ModelAndView mv, PageUtil page) {
		int total = gDao.getTotal();
		page.setPage(page.getNowPage(), total);
		
		List<BoardVO> list = gDao.getList(page);
		
		mv.addObject("LIST", list);
		mv.addObject("PAGE", page);
		
		mv.setViewName("gBoard/gBoardList");
		
		return mv;
	}
}