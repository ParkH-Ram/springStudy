package com.dadada.byeworks.commute.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.commute.model.vo.Commute;

@Repository
public class CommuteDao {

	// 김다흰
	// 직원 일별 출퇴근 조회
	public ArrayList<Commute> commuteList(SqlSessionTemplate sqlSession, String commuteDateStr){
		
		return (ArrayList)sqlSession.selectList("commuteMapper.commuteList", commuteDateStr);
	}
	
	
	// 김다흰
	// 내 출퇴근 조회
	public ArrayList<Commute> myCommute(SqlSessionTemplate sqlSession, int commuteMember, String commuteDateStr){
	  HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("commuteMember", commuteMember);
		map.put("commuteDateStr", commuteDateStr);
		
		return (ArrayList)sqlSession.selectList("commuteMapper.myCommute", map);
	}
	
	// 김다흰
	// 지각 조회
	public ArrayList<Commute> lateCommuteList(SqlSessionTemplate sqlSession, String commuteDateStr){
		
		return (ArrayList)sqlSession.selectList("commuteMapper.lateCommuteList", commuteDateStr);
		
		
	}
	
	// 김다흰
	// 출근시간 넣기
	public int commuteWorkTime(SqlSessionTemplate sqlSession, HashMap map) {
	
		return sqlSession.insert("commuteMapper.commuteWorkTime", map);
	}
	
	// 김다흰
	// 출근여부 확인 
	public Commute wheterCommute(SqlSessionTemplate sqlSession, HashMap map) {
		
		return sqlSession.selectOne("commuteMapper.wheterCommute", map);
	}

	// 김다흰
	// 퇴근 시간넣기
	public int commuteLeavedTime(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.update("commuteMapper.commuteLeavedTime", map);
	}
	
	// 김다흰
	// 출근여부 확인
	public Commute checkTime(SqlSessionTemplate sqlSession, HashMap map) {
		return sqlSession.selectOne("commuteMapper.checkTime", map);
	}
}
