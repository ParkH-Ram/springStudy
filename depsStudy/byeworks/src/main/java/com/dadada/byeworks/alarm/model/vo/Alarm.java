package com.dadada.byeworks.alarm.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Alarm {
	
	private int alarmNo;			// 알람 번호
	private	String readFlag;		// 읽음 여부 default N
	private String status;			// 삭제 여부 default Y (얘도 n으로 했어야 하는데 흠)
	private Date alarmDate;			// 알람 받은 일자
	private int alarmGroup;			// 동작 구분 no (0:아무것도아님/1:쪽지/2:전자결재...)
	private int groupNo; 			// 구분의 번호 (0:ㄴㄴ/1:쪽지pk/2:결재pk)
	private int sendNo;				// 받은 직원 pk
	private int receiveNo;			// 보낸 직원 pk

	// 추가
	private String sendName;		// 보낸 사람 이름
	private String profileModify;	// 보낸 사람 프로필 사진..
	private String sendMonth;		// 보낸 달
	private String sendDate;		// 보낸 일
	//
	private String noteTitle;		// 쪽지 제목
}
