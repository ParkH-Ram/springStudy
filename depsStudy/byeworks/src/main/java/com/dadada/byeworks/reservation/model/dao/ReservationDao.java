package com.dadada.byeworks.reservation.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.reservation.model.vo.Reservation;
import com.dadada.byeworks.reservation.model.vo.ReservationDTO;

@Repository("rDao")
public class ReservationDao { 
	
	//내가  회의실 예약한거 조회
	public ArrayList<Reservation> selectList(SqlSession sqlSession, int mno) {
		//System.out.println(mno);
		return (ArrayList)sqlSession.selectList("reservationMapper.selectList",mno);
	}
	
	//회의실 로그인 한 사람꺼 삭제
	public int deleteReservation(SqlSessionTemplate sqlSession, int reservationNo , int mno) {
		//System.out.println(mno);
		//System.out.println(reservationNo);
		Reservation r = new Reservation();
		r.setReservationNo(reservationNo);
		r.setMemberNo(mno);
		
		return sqlSession.update("reservationMapper.deleteReservation", r);
	}
	
	//전체목록
	public List<ReservationDTO> showSchedule(SqlSession sqlSession) {
		return sqlSession.selectList("reservationMapper.showSchedule");
	}
	
	//예약하기
	public int insertView(ReservationDTO reservation,SqlSession sqlSession) {
		return sqlSession.insert("reservationMapper.insertView", reservation);	
	}
	
	//상세조회
	public ReservationDTO getReservationInfo(SqlSessionTemplate sqlSession, int rno) {
		return sqlSession.selectOne("reservationMapper.getRinfo", rno);
	}

}
