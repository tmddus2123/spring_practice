package com.githrd.www.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.githrd.www.vo.SurveyVO;

public class SurveyDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 참여하지 않은 설문 갯수 조회 전담 처리함수
	public int getCount(SurveyVO sVO) {
		return sqlSession.selectOne("sSQL.remainSurvey", sVO);
	}
	
	public List<SurveyVO> getIngList(String id) {
		return sqlSession.selectList("sSQL.ingList", id);
	}
	
	public List<SurveyVO> getOldList() {
		return sqlSession.selectList("sSQL.oldList");
	}
	
	public List<SurveyVO> getQuestList(int sino) {
		return sqlSession.selectList("sSQL.questList", sino);
	}
	
	public List<SurveyVO> getBogiList(int upno) {
		return sqlSession.selectList("sSQL.bogiList", upno);
	}
	
	// 계층형 질의로 설문문항보기리스트 조회 전담 처리함수
	public List<SurveyVO> getQList(int sino) {
		return sqlSession.selectList("sSQL.qList", sino);
	}
	
	public int addSurvey(SurveyVO sVO) {
		return sqlSession.insert("sSQL.addSurvey", sVO);
	}
	
}
