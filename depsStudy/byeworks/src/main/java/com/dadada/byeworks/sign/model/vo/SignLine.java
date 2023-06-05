package com.dadada.byeworks.sign.model.vo;

import java.sql.Timestamp;
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
public class SignLine {
	
	private List<SignLine> slist;
	
	private int signLineMemberNo;
	private int signNo;
	private Timestamp approvalDate;
	private String status;
	private int memberOrder;
	

}
