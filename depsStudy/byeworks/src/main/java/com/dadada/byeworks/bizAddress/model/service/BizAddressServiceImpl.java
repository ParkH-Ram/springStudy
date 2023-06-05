package com.dadada.byeworks.bizAddress.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.bizAddress.model.dao.BizAddressDao;
import com.dadada.byeworks.bizAddress.model.vo.BizAddress;
import com.dadada.byeworks.bizAddress.model.vo.BizAddressFav;
import com.dadada.byeworks.bizAddress.model.vo.BizGroup;

@Service("bService")
public class BizAddressServiceImpl implements BizAddressService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BizAddressDao bDao;

	/**
	 * 그룹 전체 조회
	 */
	@Override
	public ArrayList<BizGroup> selectGroupList() {
		return bDao.selectGroupList(sqlSession);
	}

	/**
	 * 그룹 추가
	 */
	@Override
	public int insertGroup(int memberNo, String groupName) {
		return bDao.insertGroup(sqlSession, memberNo, groupName);
	}

	/**
	 * 그룹명 수정
	 */
	@Override
	public int updateGroup(int gno, String groupName) {
		return bDao.updateGroup(sqlSession, gno, groupName);
	}

	/**
	 * 그룹 삭제
	 */
	@Override
	public int deleteGroup(int gno) {
		return bDao.deleteGroup(sqlSession, gno);
	}

	/**
	 * 업체 주소록 전체 조회
	 */
	@Override
	public ArrayList<BizAddress> selectBizAddrList(int memberNo) {
		return bDao.selectBizAddrList(sqlSession, memberNo);
	}

	/**
	 * 그룹 별 업체 주소록 조회
	 */
	@Override
	public ArrayList<BizAddress> selectBizListGroup(int memberNo, int groupNo) {
		return bDao.selectBizListGroup(sqlSession, memberNo, groupNo);
	}

	/**
	 * 업체 주소록 즐겨찾기
	 */
	@Override
	public int bookmarkBizAddr(BizAddressFav af) {
		return bDao.bookmarkBizAddr(sqlSession, af);
	}

	/**
	 * 업체 주소록 
	 */
	@Override
	public int removeBookmarkBizAddr(BizAddressFav af) {
		return bDao.removeBookmarkBizAddr(sqlSession, af);
	}
	
	/**
	 * 주소 추가
	 */
	@Override
	public int insertBizAddr(BizAddress addr) {
		return bDao.insertBizAddr(sqlSession, addr);
	}
	
	/**
	 * 주소 수정
	 */
	@Override
	public int updateBizAddr(BizAddress addr) {
		return bDao.updateBizAddr(sqlSession, addr);
	}

	/**
	 * 주소 삭제
	 */
	@Override
	public int deleteBizAddr(ArrayList<Integer> list) {
		return bDao.deleteBizAddr(sqlSession, list);
	}

	/**
	 * 업체 주소록 북마크 조회
	 */
	@Override
	public ArrayList<BizAddress> selectBizMarkList(int memberNo) {
		return bDao.selectBizMarkList(sqlSession, memberNo);
	}

	/**
	 * 휴지통 조회
	 */
	@Override
	public ArrayList<BizAddress> selectWBList(int memberNo) {
		return bDao.selectWBList(sqlSession, memberNo);
	}

	/**
	 * 주소 삭제 복원
	 */
	@Override
	public int restoreBizAddr(ArrayList<Integer> list) {
		return bDao.restoreBizAddr(sqlSession, list);
	}

	/**
	 * 즐겨찾기 삭제 (영구 삭제 때문에)
	 */
	@Override
	public int removeBookmarkMany(ArrayList<Integer> list) {
		return bDao.removeBookmarkMany(sqlSession, list);
	}

	/**
	 * 주소 영구 삭제
	 */
	@Override
	public int purgeBizAddr(ArrayList<Integer> list) {
		return bDao.purgeBizAddr(sqlSession, list);
	}
	
	
}
