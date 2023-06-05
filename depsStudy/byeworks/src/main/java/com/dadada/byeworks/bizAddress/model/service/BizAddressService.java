package com.dadada.byeworks.bizAddress.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.bizAddress.model.vo.BizAddress;
import com.dadada.byeworks.bizAddress.model.vo.BizAddressFav;
import com.dadada.byeworks.bizAddress.model.vo.BizGroup;

public interface BizAddressService {
	
	// 그룹 리스트 조회
	ArrayList<BizGroup> selectGroupList();

	// 그룹 추가
	int insertGroup(int memberNo, String groupName);
	
	// 그룹명 수정
	int updateGroup(int gno, String groupName);
	
	// 그룹 삭제
	int deleteGroup(int gno);
	
	// 업체 주소 전체 조회
	ArrayList<BizAddress> selectBizAddrList(int memberNo);
	
	// 그룹 별 업체 주소 조회
	ArrayList<BizAddress> selectBizListGroup(int memberNo, int groupNo);
	
	// 업체 주소 북마크
	int bookmarkBizAddr(BizAddressFav af);
	
	// 업체 주소 북마크 제거
	int removeBookmarkBizAddr(BizAddressFav af);
	
	// 주소 추가
	int insertBizAddr(BizAddress addr);
	
	// 주소 수정
	int updateBizAddr(BizAddress addr);
	
	// 주소 삭제
	int deleteBizAddr(ArrayList<Integer> list);
	
	// 업체 주소 북마크 조회
	ArrayList<BizAddress> selectBizMarkList(int memberNo);
	
	// 휴지통 조회
	ArrayList<BizAddress> selectWBList(int memberNo);
	
	// 주소록 삭제 복원
	int restoreBizAddr(ArrayList<Integer> list);
	
	// 즐겨찾기 여러 개 삭제
	int removeBookmarkMany(ArrayList<Integer> list);
	
	// 주소 영구 삭제
	int purgeBizAddr(ArrayList<Integer> list);
}
