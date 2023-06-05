package com.dadada.byeworks.alarm.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.alarm.model.vo.Alarm;

public interface AlarmService {
	
	// 쪽지 알람 조회
	ArrayList<Alarm> selectNoteList(int no);

	// 쪽지 알람 전체 삭제
	int deleteAllNote(int no);
	
	// 쪽지 한 개 삭제
	int deleteOneNote(int no);
	
	// 쪽지 알람 insert
	int insertAlarm(Alarm a);
	
	// 쪽지 확인 여부 업데이트
	int updateRead(int no);
	
	// 쪽지 확인 안 된 거 다 보여주기
	ArrayList<Alarm> showAlarm(int no);
	
	// 쪽지 남은 알람 세기
	int countAlarm(int no);
	
	// 해당 알람 확인 처리
	int readAlarm(int alarmNo);
}
