package com.dadada.byeworks.member.model.vo;

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
public class AddressFav {
	
	private int favMemberNo;	// 즐겨찾기 한 직원 번호
	private int memberNo;		// 즐겨찾기 된 직원 번호

}
