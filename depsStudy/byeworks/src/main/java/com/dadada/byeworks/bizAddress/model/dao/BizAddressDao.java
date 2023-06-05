package com.dadada.byeworks.bizAddress.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.bizAddress.model.vo.BizAddress;
import com.dadada.byeworks.bizAddress.model.vo.BizAddressFav;
import com.dadada.byeworks.bizAddress.model.vo.BizGroup;

@Repository
public class BizAddressDao {
	
	// 그룹 전체 조회
	public ArrayList<BizGroup> selectGroupList(SqlSessionTemplate sqlSession) {
		return (ArrayList)sqlSession.selectList("bizAddressMapper.selectGroupList");
	}
	
	// 그룹 추가
	public int insertGroup(SqlSessionTemplate sqlSession, int memberNo, String groupName) {
		BizGroup bg = new BizGroup();
		bg.setMemberNo(memberNo);
		bg.setGroupName(groupName);
		
		return sqlSession.insert("bizAddressMapper.insertGroup", bg);
	}
	
	// 그룹명 수정
	public int updateGroup(SqlSessionTemplate sqlSession, int gno, String groupName) {
		BizGroup bg = new BizGroup();
		bg.setGroupNo(gno);
		bg.setGroupName(groupName);
		
		System.out.println(gno + ", " + groupName);
		
		return sqlSession.update("bizAddressMapper.updateGroup", bg);
	}
	
	// 그룹 삭제
	public int deleteGroup(SqlSessionTemplate sqlSession, int gno) {
		return sqlSession.update("bizAddressMapper.deleteGroup", gno);
	}
	
	// 업체 주소록 전체 조회
	public ArrayList<BizAddress> selectBizAddrList(SqlSessionTemplate sqlSession, int memberNo) {
		return (ArrayList)sqlSession.selectList("bizAddressMapper.selectBizAddrList", memberNo);
	}

	// 업체 주소록 그룹 별 조회
	public ArrayList<BizAddress> selectBizListGroup(SqlSessionTemplate sqlSession, int memberNo, int groupNo){
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("memberNo", memberNo);
		map.put("groupNo", groupNo);
		
		return (ArrayList)sqlSession.selectList("bizAddressMapper.selectBizListGroup", map);
	}
	
	// 업체 주소록 즐겨찾기
	public int bookmarkBizAddr(SqlSessionTemplate sqlSession, BizAddressFav af) {
		return sqlSession.insert("bizAddressMapper.bookmarkBizAddr", af);
	}
	
	// 업체 주소록 즐겨찾기 해제
	public int removeBookmarkBizAddr(SqlSessionTemplate sqlSession, BizAddressFav af) {
		return sqlSession.delete("bizAddressMapper.removeBookmarkBizAddr", af);
	}
	
	// 업체 주소록 추가
	public int insertBizAddr(SqlSessionTemplate sqlSession, BizAddress addr) {
		return sqlSession.insert("bizAddressMapper.insertBizAddr", addr);
	}
	
	// 업체 주소록 수정
	public int updateBizAddr(SqlSessionTemplate sqlSession, BizAddress addr) {
		return sqlSession.update("bizAddressMapper.updateBizAddr", addr);
	}
	
	// 업체 주소록 삭제
	public int deleteBizAddr(SqlSessionTemplate sqlSession, ArrayList<Integer> list) {
		return sqlSession.update("bizAddressMapper.deleteBizAddr", list);
	}
	
	// 업체 주소록 북마크 조회
	public ArrayList<BizAddress> selectBizMarkList(SqlSessionTemplate sqlSession, int memberNo) {
		return (ArrayList)sqlSession.selectList("bizAddressMapper.selectBizMarkList", memberNo);
	}
	
	// 업체 휴지통 조회
	public ArrayList<BizAddress> selectWBList(SqlSessionTemplate sqlSession, int memberNo) {
		return (ArrayList)sqlSession.selectList("bizAddressMapper.selectWBList", memberNo);
	}
	
	// 주소록 삭제 복원
	public int restoreBizAddr(SqlSessionTemplate sqlSession, ArrayList<Integer> list) {
		return sqlSession.update("bizAddressMapper.restoreBizAddr", list); 
	}
	
	// 주소록 즐겨찾기 삭제 (영구삭제 전용)
	public int removeBookmarkMany(SqlSessionTemplate sqlSession, ArrayList<Integer> list) {
		return sqlSession.delete("bizAddressMapper.removeBookmarkMany", list);
	}
	
	// 주소 영구 삭제
	public int purgeBizAddr(SqlSessionTemplate sqlSession, ArrayList<Integer> list) {
		return sqlSession.delete("bizAddressMapper.purgeBizAddr", list);
	}
}
