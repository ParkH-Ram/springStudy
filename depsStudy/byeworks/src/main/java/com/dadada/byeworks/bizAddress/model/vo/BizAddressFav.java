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
public class BizAddressFav {
	
	private int addressNo;	// 즐겨찾기 되는 주소 번호
	private int memberNo;	// 즐겨찾는 직원 번호

}
