package com.dadada.byeworks.sign.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

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
public class SignAndAnnualSign {
	
	private int signNo;
	private Timestamp signUpDate;
	private String docuType;
	private int memberNo;
	private String memberName;
	private String signStatus;
	private String flag;
	private String title;
	private Date signUse;
	private double annualPeriod;
	private Date annualStartDay;
	private Date annualEndDay;
	private String annualContent;
	private int annualType;
	private String department;

}
