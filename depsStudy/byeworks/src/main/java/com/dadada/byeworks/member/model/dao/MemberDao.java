package com.dadada.byeworks.member.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.member.model.dto.MemberAddress;
import com.dadada.byeworks.member.model.vo.AddressFav;
import com.dadada.byeworks.member.model.vo.Member;
import com.sun.javafx.collections.MappingChange.Map;

@Repository
public class MemberDao {
	
	// 로그인
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	
	// 이슬희
	// 자동로그인 체크한 경우 TB_MEMBER에 세션id와 유효시간을 저장하는 메서드
	public void keepLogin(SqlSessionTemplate sqlSession, String memberId, String sessionId, Date next) {
		
		// mapper에 전달하기 위해 map으로 전달한다
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("memberId", memberId);
		map.put("sessionId", sessionId);
		map.put("next", next);
		
		sqlSession.update("memberMapper.keepLogin", map);
		
	}
	
	// 이슬희
	// TB_MEMBER 테이블 내에 유효시간을 넘기지 않고 전달받은 세션id와 일치하는 사용자 정보를 가져오는 메서드
	public Member checkMemberWithSessionKey(SqlSessionTemplate sqlSession, String sessionId) {
		return sqlSession.selectOne("memberMapper.checkMemberWithSessionKey", sessionId);
	}
	
	// 이슬희
	// 이름과 이메일로 아이디 체크
	public Member checkId(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.checkId", m);
	}
	
	// 이슬희
	// 아이디와 이메일로 계정 체크
	public Member checkPwd(SqlSessionTemplate sqlSession, String userId, String userEmail) {
		Member m = new Member();
		m.setMemberId(userId);
		m.setEmail(userEmail);
		
		return sqlSession.selectOne("memberMapper.checkPwd", m);
	}
	
	// 이슬희
	// 비밀번호 업데이트
	public int resetNewPwd(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.resetNewPwd", m);
	}

	// 이슬희
	// 주소록 전체 조회
	public ArrayList<MemberAddress> selectAddrList(SqlSessionTemplate sqlSession, int no) {
		return (ArrayList)sqlSession.selectList("memberMapper.selectAddrList", no);
	}
	
	// 이슬희
	// 즐겨찾기 주소록 전체 조회
	public ArrayList<MemberAddress> selectMarkList(SqlSessionTemplate sqlSession, int no) {
		return (ArrayList)sqlSession.selectList("memberMapper.selectMarkList", no);
	}
	
	// 이슬희
	// 즐겨찾기 실행
	public int bookmarkAddr(SqlSessionTemplate sqlSession, AddressFav af) {
		return sqlSession.insert("memberMapper.bookmarkAddr", af);
	}
	
	// 이슬희
	// 즐겨찾기 제거 실행
	public int removeBookmarkAddr(SqlSessionTemplate sqlSession, AddressFav af) {
		return sqlSession.delete("memberMapper.removeBookmarkAddr", af);
	}
	
	// 김다흰
	// 직원 전체 조회
	public ArrayList<Member> selectList(SqlSessionTemplate sqlSession){
		
		return (ArrayList)sqlSession.selectList("memberMapper.memberList");
	}
	// 김다흰
	// 직원 상세조회
	public Member memberDetailList(SqlSessionTemplate sqlSession, int mno) {
		
		return sqlSession.selectOne("memberMapper.memberDetailList", mno);
		
	}
	// 김다흰
		// 직원 수정
	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}
	// 김다흰
		// 직원 등록
	public int memberInsert(SqlSessionTemplate sqlSession, Member m) {

		
		return sqlSession.insert("memberMapper.memberInsert", m);
	}
	
	// 김다흰
	// 내 정보 수정
	public int myUpdate(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.myUpdate", m);
	}
	
	//김다흰
	// 직원 퇴사처리
	public int retireMember(SqlSessionTemplate sqlSession, int memberNo) {
		return sqlSession.update("memberMapper.retireMember", memberNo);
	}
	
	// 김다흰
	// 직원 이름 검색
	public ArrayList<Member> searchMemberName(SqlSessionTemplate sqlSession, String memberName){
		
		return (ArrayList)sqlSession.selectList("memberMapper.searchMemberName", memberName);
	}
}
