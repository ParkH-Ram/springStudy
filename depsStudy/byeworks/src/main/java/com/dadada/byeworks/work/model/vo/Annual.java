package com.dadada.byeworks.work.model.vo;

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
public class Annual {

	private int annualNo;			// 연차번호
	private int memberNo;			// 직원 번호
	private String annualCreate;	// 연차 총생성일
	private double annualRemain;	// 연차 잔여일
	private String annName;			// 직원 이름
	private String annDept;			// 직원 부서	
	private String annPos;			// 직원 직급
	private String annEnrollday;	// 직원 입사일
	private int annualType;			// 연차 사용	0 :연차사용  1:오전반차  2:오후반차annE
	private String annualUseDay;	// 연차 사용일
	private String annualSign;		// 연차 상태
	private String annualContent;	// 연차 사유
	
}
