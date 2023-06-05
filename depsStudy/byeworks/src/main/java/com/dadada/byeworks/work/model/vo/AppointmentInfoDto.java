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
public class AppointmentInfoDto {
	
	private int memberNo;
	private int depBefore;
	private int depAfter;
	private int positionBefore;
	private int positionAfter;
	private int appointLev;
	private String docType;
	private String appointmentMemName;

}
