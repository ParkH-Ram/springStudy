package com.dadada.byeworks.sign.model.vo;

import java.sql.Date;
import java.util.List;

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
public class SignRefer {
	
	private List<SignRefer> rlist;

	private int signNo;
	private int referMemberNo;
	private String status;
	private Date checkDate;
}
