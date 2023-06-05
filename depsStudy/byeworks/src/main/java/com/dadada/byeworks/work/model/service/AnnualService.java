package com.dadada.byeworks.work.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.work.model.vo.Annual;

public interface AnnualService {

	// 김다흰 : 날짜별 직원 연차별 조회
	ArrayList<Annual> annualUseList(String annualUseDay);
	
	// 김다흰 : 내 연차 조회
	Annual annualInfo(int mno);
}
