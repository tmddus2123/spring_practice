package com.githrd.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.githrd.www.util.PageUtil;
import com.githrd.www.vo.BoardVO;
import com.githrd.www.vo.MemberVO;

/**
 * 	이 클래스를 댓글게시판 관련 데이터베이스 전담 처리함수들로 구성된 클래스
 *  @author tmddus
 *  @since	2022.06.08
 *  @version v.1.0
 *  
 *  		작업 이력 ]
 *  			2022.06.08	-	담당자 : 이승연
 *  								클래스 제작, 리스트 조회
 * 
 */

public class ReBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 총 게시글 수 조회 전담 처리함수
	public int getTotal() {
		return sqlSession.selectOne("rSQL.getTotal");
	}
	
	// 게시글 리스트 조회 전담 처리함수
	public List<BoardVO> getList(PageUtil page) {
		return sqlSession.selectList("rSQL.getList", page);
	}
	
	// 글 작성 회원 정보 처리함수
	public BoardVO getWriteInfo(String id) {
		return sqlSession.selectOne("rSQL.getWriteInfo", id);
	}
	
	// 댓글 작성 전담 처리함수
	public int addReBoard(BoardVO bVO) {
		return sqlSession.insert("rSQL.addReBoard", bVO);
	}
	
}
