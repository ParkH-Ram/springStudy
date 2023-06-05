package com.dadada.byeworks.bizAddress.model.vo;

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
public class BizAddress {
	
	private int addressNo;		// primary key
	private String name;		// 거래처 직원 이름
	private String bizName;		// 거래처명
	private String position;	// 직책
	private String email;		// 이메일
	private String phone;		// 연락처
	private String status;		// 삭제 여부
	private int groupNo;		// 그룹 번호
	private int memberNo;		// 등록한 직원 번호

	// 내 맘대로 DTO
	private String bookmark;	// 즐겨찾기 여부
	private String groupName;	// 그룹명
}
