package com.githrd.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.githrd.www.dao.BoardDao;
import com.githrd.www.service.BoardService;
import com.githrd.www.util.PageUtil;
import com.githrd.www.vo.BoardVO;
import com.githrd.www.vo.FileVO;

/**
 * 이 클래스는 파일게시판에 관련된 요청을 처리할 컨트롤러 클래스
 * @author tmddus
 * @since  2022.06.17
 * @version v.1.0
 * 
 *			작업이력 ] 
 *		
 *				2022.06.17	-	담당자 : 이승연
 *								클래스제작
 *								1) 게시글 리스트보기 요청 처리함수 제작
 *
 *				2022.06.22 -	담당자 : 이승연
 *								게시글 등록 요청 처리함수 추가
 */

@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDao bDao;
	@Autowired
	BoardService bSrvc;
	
	@RequestMapping("/boardList.blp")
	public ModelAndView boardList(ModelAndView mv, PageUtil page) {
		// 할 일
		// 1. 총 게시글 수 가져오고
		int total = bDao.getTotal();
		// 2. 해당페이지의 페이지 정보 만들고
		page.setPage(total);
		// 3. 해당페이지의 글 목록 가져오고..
		List<BoardVO> list = bDao.getList(page);
		// 4. 데이터 심고
		mv.addObject("LIST", list);
		// 5. 뷰 부르고
		mv.setViewName("board/boardList");
		// 6. mv 반환하고
		return mv;
	}
	
	// 게시글 상세보기 요청 처리함수
	@RequestMapping(path="/boardDetail", method=RequestMethod.POST, params= {"bno", "nowPage"})
	public ModelAndView boardDetail(ModelAndView mv, BoardVO bVO) {
		List<FileVO> list = bDao.getFileList(bVO.getBno());
		
		bVO = bDao.getDetail(bVO.getBno());
		
		mv.addObject("DATA", bVO);
		mv.addObject("LIST", list);
		
		mv.setViewName("board/boardDetail");
		
		return mv;
	}
	
	// 게시글 글쓰기 폼보기요청 처리함수
	@RequestMapping("/boardWrite.blp")
	public ModelAndView boardWrite(ModelAndView mv) {
		mv.setViewName("board/boardWrite");
		
		return mv;
	}
	
	// 게시글 등록 요청 처리함수
		@RequestMapping("/boardWriteProc.blp")
		public ModelAndView boardWriteProc(ModelAndView mv, BoardVO bVO, String nowPage) {
			String view = "/www/board/boardList.blp";
			System.out.println("************ " + bVO);
			try {
				bSrvc.addBoardData(bVO);
				nowPage = "1";
			} catch(Exception e) {
				// 게시글 등록에 실패한 경우
				// 결과적으로 롤백된 경우....
//				view = "/www/board/boardWrite.blp?nowPage=" + nowPage;
				view = "/www/board/boardWrite.blp";
				e.printStackTrace();
			}
		mv.addObject("NOWPAGE", nowPage);
		mv.addObject("VIEW", view);
		
		mv.setViewName("board/redirect");
		
		return mv;
	}

}
