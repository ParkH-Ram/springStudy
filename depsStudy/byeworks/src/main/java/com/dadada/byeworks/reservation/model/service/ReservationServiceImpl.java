package com.dadada.byeworks.reservation.model.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.reservation.model.dao.ReservationDao;
import com.dadada.byeworks.reservation.model.vo.Reservation;
import com.dadada.byeworks.reservation.model.vo.ReservationDTO;

@Service("rService")
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private SqlSessionTemplate sqlSession; 
	@Autowired
	private ReservationDao rDao;
	
	//내가  회의실 예약한거 조회
	@Override
	public ArrayList<Reservation> selectList(int mno) {
		return rDao.selectList(sqlSession, mno);
	}

	//회의실 로그인 한 사람꺼 삭제
	@Override
	public int deleteResevation(int reservationNo, int mno) {
		
		return rDao.deleteReservation(sqlSession, reservationNo , mno);
	}
	
	//전체 예약 목록 보여주는거
	public List<ReservationDTO> showSchedule() {

		return rDao.showSchedule(sqlSession);
	}
	
	//예약
	@Override
	public int insertView(ReservationDTO reservation) throws Exception {

		return rDao.insertView(reservation,sqlSession);
		
	}

	@Override
	public ReservationDTO getReservationInfo(int rno) {
		return rDao.getReservationInfo(sqlSession, rno);
	}
	
	
	
}
