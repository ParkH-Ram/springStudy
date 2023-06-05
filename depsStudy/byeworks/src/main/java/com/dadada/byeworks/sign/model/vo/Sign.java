package com.dadada.byeworks.sign.model.vo;

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
public class Sign {
	
	private int signNo;
	private Timestamp signUpDate;
	private String docuType;
	private int memberNo;
	private String signStatus;
	private String flag;
	private String title;

}
