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
public class Appointment {
	
	private int signNo;
	private int positionBefore;
	private int positionAfter;
	private int depBefore;
	private int depAfter;
	private int appointmentLev;
	private int appointmentMem;
	private Date appointmentDate;

}
