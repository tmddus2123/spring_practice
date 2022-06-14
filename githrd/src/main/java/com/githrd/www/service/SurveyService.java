package com.githrd.www.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.githrd.www.dao.*;
import com.githrd.www.vo.*;

/**
 * 	 이 클래스는 설문조사에 관련된 부가적인 기능을 처리할 클래스
 * 	@author tmddu
 *	@since	22.06.13
 *	@version v.1.0
 *
 *			작업이력 ]
 *				2022.06.13	-	담당자 : 이승연
 *								클래스제작
 *
 */
public class SurveyService {
	@Autowired
	SurveyDao sDao;
	
	// 설문주제 번호로 설문데이터 작업 셋팅 함수
	public void setBogi(SurveyVO sVO) {
		int sino = sVO.getSino();
		List<SurveyVO> list = sDao.getQuestList(sino);
		
		sVO.setBogi(list);
		
		for(SurveyVO vo : list) {
			int qno = vo.getSqno();
			List<SurveyVO> bogi = sDao.getBogiList(qno);
			
			vo.setBogi(bogi);
		}
		
	}
	
	// 문항과 보기를 동시에 조회해서 분리 처리하는 전담 함수
	public void settingList(SurveyVO sVO) {
		int sino = sVO.getSino();
		List<SurveyVO> qlist = sDao.getQList(sino);
		
		ArrayList<SurveyVO> munjae = new ArrayList<SurveyVO>();
		
		for(SurveyVO vo : qlist) {
			if(vo.getSqno() == vo.getUpno()) {
				munjae.add(vo);
			}
		}
		
		for(SurveyVO vo : munjae) {
			
			ArrayList<SurveyVO> tmp = new ArrayList<SurveyVO>();
			for(SurveyVO bogi : qlist) {
				if(bogi.getSqno() != bogi.getUpno()) {
					tmp.add(bogi);
				}
			}
			vo.setBogi(tmp);
		}
		sVO.setBogi(munjae);
	}
	
	// 전체 응답 입력 처리 서비스 함수
	@Transactional
	public boolean addAllDap(SurveyVO sVO) {
		// 응답 번호를 기억하는 배열을 꺼낸다.
		int[] dapArr = sVO.getDap();
		
		
//		작동 확인용 테스트 카운트변수
//		int cnt = 0;
		
		for(int qno : dapArr) {
	/*
			// 트랜젝션 확인 테스트용 코드
			if(cnt++ == 2) {
				qno = 1111111;
			}
	*/
			sVO.setSqno(qno);
			sDao.addSurvey(sVO);
		}
		
		return true;
	}
	
	// 트랜잭션 적용 처리작업 호출 함수
		public boolean applyTx(SurveyVO sVO) {
			boolean bool = false;
			try {
				bool = addAllDap(sVO);
			} catch(Exception e) {
				bool = false;
			}
			
			return bool;
		}
}
