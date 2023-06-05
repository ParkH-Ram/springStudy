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
public class BizGroup {
	
	private int groupNo;		// 그룹 primary key
	private String groupName;	// 그룹명
	private int memberNo;		// 작성자 번호
	
	// 나 혼자 쓰니까 dto 안 쓰고 여기다가 때려박기~
	private String empNo;
	private String memberName;	// 작성자 명
}
