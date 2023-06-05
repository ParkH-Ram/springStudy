package com.dadada.byeworks.member.model.dto;

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
public class MemberAddress {
	
	private int memberNo;			// 직원 번호
	private String memberName;		// 직원 이름
	private String position;		// 직원 직책
	private String department;		// 직원 부서
	private String extension;		// 내선 번호
	private String email;			// 직원 이메일
	
	private String bookmark;		// 즐겨찾기 여부
	private String memberId;		// 직원 아이디
}
