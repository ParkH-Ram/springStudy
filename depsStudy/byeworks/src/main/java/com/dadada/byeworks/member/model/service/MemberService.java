package com.dadada.byeworks.member.model.service;

import java.sql.Date;
import java.util.ArrayList;

import com.dadada.byeworks.member.model.dto.MemberAddress;
import com.dadada.byeworks.member.model.vo.AddressFav;
import com.dadada.byeworks.member.model.vo.Member;

public interface MemberService {

	// 로그인
	Member loginMember(Member m);
	
	// 자동 로그인 시 세션 값 업데이트
	void keepLogin(String memberId, String sessionId, Date next);
	
	// 자동 로그인 관련 세션 값이 있는지 조회
	Member checkMemberWithSessionKey(String sessionId);
	
	// 아이디 찾기 (이름, 아이디에 해당하는 값이 있는지 체크하여 아이디와 같이 반환)
	Member checkId(Member m);
	
	// 비밀번호 찾기 (아이디, 이메일에 해당하는 값이 있는지 체크하여 반환)
	Member checkPwd(String userId, String userEmail);
	
	// 비밀번호 업데이트
	int resetNewPwd(Member m);
	
	// 사내 주소록 전체 조회
	ArrayList<MemberAddress> selectAddrList(int no);
	
	// 사내 주소록 즐겨찾기 조회
	ArrayList<MemberAddress> selectMarkList(int no);
	
	// 사내 주소록 즐겨찾기 실행
	int bookmarkAddr(AddressFav af);
	
	// 사내 주소록 즐겨찾기 제거 실행
	int removeBookmarkAddr(AddressFav af);
	
	// 전체 조회(인)
	ArrayList<Member> selectList();
		
	// 직원 상세조회(인)
	Member memberDetailList(int mno);
	
	// 직원 수정하기(인)
	int updateMember(Member m);
	
	// 직원 등록하기(인)
	int memberInsert(Member m);
	
	// 내 정보 수정하기
	int myUpdate(Member m);
	
	// 직원 퇴사처리
	int retireMember(int memberNo);
	
	// 직원 이름으로 검색하기
	ArrayList<Member> searchMemberName(String memberName);
}
