package com.githrd.www.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.githrd.www.util.*;
import com.githrd.www.vo.*;

public class GBoardDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getMyCount(String id) {
		return sqlSession.selectOne("gSQL.myCount", id);
	}
	
	public int getTotal() {
		return sqlSession.selectOne("gSQL.totalCount");
	}
	
	public List<BoardVO> getList(PageUtil page) {
		return sqlSession.selectList("gSQL.gBoardList", page);
	}
	
}
