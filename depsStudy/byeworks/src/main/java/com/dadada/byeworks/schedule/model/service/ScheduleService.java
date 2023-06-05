package com.dadada.byeworks.schedule.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.schedule.model.vo.Schedule;

public interface ScheduleService {
	
	// 내 일정 생성하기
	int insertSchedule(Schedule s);

	// 일정 조회하기
	ArrayList<Schedule> selectSchedule(int no);
	
	// 일정 하나만 조회학
	Schedule selectOne(int schNo);
	
	// 수정하기
	int updateSchedule(Schedule s);
	
	// 삭제하기
	int deleteSchedule(int schNo);
	
	// 메인에서 조회하기
	ArrayList<Schedule> selectMainList(int no);
}
