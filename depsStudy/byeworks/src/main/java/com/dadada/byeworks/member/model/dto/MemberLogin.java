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
public class MemberLogin {

	private int memberNo;			// 직원 번호
	private String memberId;		// 직원 아이디
	private String memberName;		// 직원 이름
	private String memberPwd;		// 직원 비밀번호
	private String position;		// 직원 직책
	private String department;		// 직원 부서
	private String phone;			// 직원 핸드폰 번호
	private String extension;		// 내선 번호
	private String email;			// 직원 이메일
	private String address;			// 직원 주소
	private String birth;			// 직원 생년월일
	private Date enrollday;			// 입사일
	private String memberStatus;	// 직원 상태
	private Date retireday;			// 퇴사일
	private String profileModify;	// 프로필 수정명
	private String empNo;			// 직원 사번
	
	// 로그인 자동 로그인(=유지) 관련 컬럼
	private boolean useCookie;		// 로그인 시 자동로그인인지 체크
}
