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
}
