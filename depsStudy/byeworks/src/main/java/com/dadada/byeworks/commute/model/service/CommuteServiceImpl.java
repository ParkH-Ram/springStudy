package com.dadada.byeworks.commute.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.commute.model.dao.CommuteDao;
import com.dadada.byeworks.commute.model.vo.Commute;
import com.dadada.byeworks.commute.model.service.CommuteService;

@Service("cService")
public class CommuteServiceImpl implements CommuteService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private CommuteDao cDao;
	
	// 김다흰
	// 직원 출퇴근 조회하기
	@Override
	public ArrayList<Commute> commuteList(String commuteDateStr) {
		
		
		return cDao.commuteList(sqlSession, commuteDateStr);
	}

	// 김다흰
	// 내 출퇴근 조회하기
	@Override
	public ArrayList<Commute> myCommute(int commuteMember, String commuteDateStr) {

		
		return cDao.myCommute(sqlSession, commuteMember,commuteDateStr);
	}
	
	// 김다흰
	// 직원 지각 조회하기
	@Override
	public ArrayList<Commute> lateCommuteList(String commuteDateStr) {

		return cDao.lateCommuteList(sqlSession, commuteDateStr);
	}

	// 김다흰
	// 출근하기 버튼 클릭시 출근시간 입력
	@Override
	public int commuteWorkTime(HashMap map) {

		
		return cDao.commuteWorkTime(sqlSession, map);
	}

	
	// 김다흰
	// 출근여부 확인하는 메소드
	@Override
	public Commute wheterCommute(HashMap map) {
		
		
		return cDao.wheterCommute(sqlSession, map);
	}
	
	// 김다흰
	// 출근 여부 확인
	@Override
	public int commuteLeavedTime(HashMap map) {
		return cDao.commuteLeavedTime(sqlSession, map);
	}
	

}
