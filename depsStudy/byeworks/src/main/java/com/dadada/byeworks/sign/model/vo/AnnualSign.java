package com.dadada.byeworks.sign.model.vo;

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
public class AnnualSign {

	private int signNo;
	private Date signUse;
	private int annualPeriod;
	private Date annualStartDay;
	private Date annualEndDay;
	private String annualContent;
	private int annualType;
}
