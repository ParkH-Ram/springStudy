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
public class SignAndQuit {

	private int signNo;
	private Timestamp signUpDate;
	private String docuType;
	private int memberNo;
	private String memberName;
	private String signStatus;
	private String flag;
	private String title;
	private Date enrollDate;
	private Date quitDate;
	private String reason;
	private String department;
	
}
