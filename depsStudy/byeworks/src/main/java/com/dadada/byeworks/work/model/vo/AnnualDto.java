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
public class AnnualDto {

	
	private int signNo;
	private Date signUse;
	private double annualPeriod;
	private String annualStartDay;
	private String annualEndDay;
	private String annualContent;
	private int annualType;
	private String docType;
	
}
