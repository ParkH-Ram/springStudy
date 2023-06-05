package com.dadada.byeworks.schedule.model.vo;

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
public class Schedule {
	
	private int schNo;			// 일정 번호
	private String title;		// 일정 제목
	private Date beginDate;		// 시작일
	private Date endDate;		// 종료일
	private String location;	// 위치
	private String note;		// 메모
	private String deleted;		// 삭제 여부
	private int memberNo;		// 등록한 직원 번호
	private String color;		// 일정 색상
	
	private String begin;
	private String end;
}
