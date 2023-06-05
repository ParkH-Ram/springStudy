package com.dadada.byeworks.member.model.service;

import java.sql.Date;
import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.member.model.dao.MemberDao;
import com.dadada.byeworks.member.model.dto.MemberAddress;
import com.dadada.byeworks.member.model.vo.AddressFav;
import com.dadada.byeworks.member.model.vo.Member;

@Service("mService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDao mDao;
	
	@Override
	public Member loginMember(Member m) {
		return mDao.loginMember(sqlSession, m);
	}

	// 이슬희
	@Override
	public void keepLogin(String memberId, String sessionId, Date next) {
		mDao.keepLogin(sqlSession, memberId, sessionId, next);
	}

	// 이슬희
	@Override
	public Member checkMemberWithSessionKey(String sessionId) {
		return mDao.checkMemberWithSessionKey(sqlSession, sessionId);
	}

	// 이슬희
	@Override
	public Member checkId(Member m) {
		return mDao.checkId(sqlSession, m);
	}

	// 이슬희
	@Override
	public Member checkPwd(String userId, String userEmail) {
		return mDao.checkPwd(sqlSession, userId, userEmail);
	}

	// 이슬희
	@Override
	public int resetNewPwd(Member m) {
		return mDao.resetNewPwd(sqlSession, m);
	}

	// 이슬희
	@Override
	public ArrayList<MemberAddress> selectAddrList(int no) {
		return mDao.selectAddrList(sqlSession, no);
	}

	// 이슬희
	@Override
	public ArrayList<MemberAddress> selectMarkList(int no) {
		return mDao.selectMarkList(sqlSession, no);
	}

	// 이슬희
	@Override
	public int bookmarkAddr(AddressFav af) {
		return mDao.bookmarkAddr(sqlSession, af);
	}

	// 이슬희
	@Override
	public int removeBookmarkAddr(AddressFav af) {
		return mDao.removeBookmarkAddr(sqlSession, af);
	}
	
	// 김다흰
	@Override
	public ArrayList<Member> selectList() {
		
		
		return mDao.selectList(sqlSession);
	}
	// 김다흰
	@Override
	public Member memberDetailList(int mno) {
		
		
		return mDao.memberDetailList(sqlSession, mno);
	}
	// 김다흰
	@Override
	public int updateMember(Member m) {

		return mDao.updateMember(sqlSession, m);
	}
	// 김다흰
	@Override
	public int memberInsert(Member m) {
		
		
		int memberNo = mDao.memberInsert(sqlSession, m);
		
		return memberNo;
	}
	// 김다흰
	@Override
	public int myUpdate(Member m) {

		
		return mDao.myUpdate(sqlSession, m);
	}

	// 김다흰
	@Override
	public int retireMember(int memberNo) {

		return mDao.retireMember(sqlSession, memberNo);
	}

	// 김다흰
	@Override
	public ArrayList<Member> searchMemberName(String memberName) {

		return mDao.searchMemberName(sqlSession, memberName);
	}
}
