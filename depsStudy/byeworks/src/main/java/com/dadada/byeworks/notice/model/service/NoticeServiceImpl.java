package com.dadada.byeworks.notice.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.notice.model.dao.NoticeDao;
import com.dadada.byeworks.notice.model.vo.Notice;


@Service("nService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private NoticeDao nDao;
	
//	@Override
//	public int getListCount() { 
//		return nDao.getListCount(sqlSession);
//	}

	//공지사항 총개수만 가져와주는거 ( 페이지info 등 필요없음)
	@Override
	public ArrayList<Notice> selectList() {
		return nDao.selectList(sqlSession);
	}

	@Override
	public int insertNotice(Notice n) {
		return nDao.insertNotice(sqlSession, n);
	}

	@Override
	public int increaseCount(int nno) {
		return nDao.increaseCount(sqlSession, nno);
	}
	
	//상세조회
	@Override
	public Notice selectNotice(int nno) {
		return nDao.selectNotice(sqlSession, nno);
	}

	@Override
	public int deleteNotice(int nno) {
		return nDao.deleteNotice(sqlSession, nno);
	}

	@Override
	public int updateNotice(Notice n) {
		
		return nDao.updateNotice(sqlSession, n);
	}

	// 이슬희 // 메인에서 공지사항 조회용 서비스
	@Override
	public ArrayList<Notice> selectMainList() {
		return nDao.selectMainList(sqlSession);
	}

}
