package com.dadada.byeworks.commute.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.dadada.byeworks.commute.model.vo.Commute;

public interface CommuteService {

	// 날짜별 직원 출퇴근 조회하기
	ArrayList<Commute> commuteList(String commuteDateStr);
	
	// 내 출퇴근 조회하기
	ArrayList<Commute> myCommute(int commuteMember, String commuteDateStr);
	
	// 지각 조회하기
	ArrayList<Commute> lateCommuteList(String commuteDateStr);
	
	// 내출근 입력하기
	int commuteWorkTime(HashMap map);
	
	// 출근여부 확인 후 값 입력
	Commute wheterCommute(HashMap map);
	
	// 내 퇴근 입력하기
	int commuteLeavedTime(HashMap map);
	
	
}
