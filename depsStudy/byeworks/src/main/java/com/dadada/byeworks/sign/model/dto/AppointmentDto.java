package com.dadada.byeworks.sign.model.dto;

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
public class AppointmentDto {

	private int signNo;					// 결재 번호
	private int positionAfter;			// 발령 후 직급
	private int positionBefore;			// 발령 전 직급 
	private String positionName;		// 직급명
	private int deptAfter;				// 발령 후 부서
	private int deptBefore;				// 발령 전 부서
	private String departmentName;		// 부서명
	private int appointmentMem;			// 발령받은 직원
	private String appEmpno;			// 직원 사번
	private String appointmentLev;		// 발령 구분 0:부서이동 1:승진  2: 둘다 
	private String appointmentName;		// 발령 받은 직원 이름
	private Date appEnrollday;
	private int appointLev;
	private String docType;
}
