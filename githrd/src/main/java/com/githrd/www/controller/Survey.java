package com.githrd.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;

@Controller
@RequestMapping("/survey")
public class Survey {
	@Autowired
	SurveyDao sDao;
	
	@RequestMapping("/surveyInfo.blp")
	public ModelAndView surveyInfo(ModelAndView mv, SurveyVO sVO) {
		List<SurveyVO> list = sDao.getIngList(sVO.getId());
		List<SurveyVO> old = sDao.getOldList();
		
		// 데이터심고
		mv.addObject("ING", list);
		mv.addObject("OLD", old);
		
		// 뷰 정하고
		mv.setViewName("survey/surveyInfo");
		return mv;
	}
}
