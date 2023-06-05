package com.dadada.byeworks.sign.model.vo;

import java.util.ArrayList;
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
public class SignAttachment {
	
	private List<SignAttachment> alist;
	
	private int fileNo;
	private String oaName;
	private String maName;
	private int signNo;

}
