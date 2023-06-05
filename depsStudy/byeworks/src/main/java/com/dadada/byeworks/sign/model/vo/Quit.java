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
public class Quit {
	
	private int signNo;
	private Date enrollDate;
	private Date quitDate;
	private String reason;
	
	
	
}
