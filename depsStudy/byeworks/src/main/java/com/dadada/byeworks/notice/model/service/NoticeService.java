package com.dadada.byeworks.notice.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.notice.model.vo.Notice;

public interface NoticeService {
	
	//미완성된 메소드만 만들수잇음 반환형 만들것
	// 1_1 공지사항 총 갯수 조회용 서비스
	// 카운트 써서 
//	int getListCount();
	//현재요청한 페이제 보여져야할 리스트 조회용 서비스
	ArrayList<Notice> selectList();
	
	// 2. 공지사항 작성용 서비스
	int insertNotice(Notice n);
	
	// 3. 공지사항 상세조회용 서비스
	// 3_1. 공지사항 조회수 증가용서비스
	int increaseCount(int nno);
	// 3_2. 공지사항 상세조회용 서비스
	Notice selectNotice(int nno);
	
	// 4. 공지사항 삭제용 서비스
	int deleteNotice(int nno);
	
	// 5. 공지사항 수정용 서비스
	int updateNotice(Notice n);
	
	// 이슬희
	// Main) 메인에서 공지사항 조회용 서비스
	ArrayList<Notice> selectMainList();
}
