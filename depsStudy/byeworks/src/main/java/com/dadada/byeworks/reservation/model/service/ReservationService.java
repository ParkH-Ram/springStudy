package com.dadada.byeworks.reservation.model.service;

import java.util.ArrayList;
import java.util.List;

import com.dadada.byeworks.reservation.model.vo.Reservation;
import com.dadada.byeworks.reservation.model.vo.ReservationDTO;

public interface ReservationService {
	
	//내가  회의실 예약한거 조회
	ArrayList<Reservation> selectList(int mno); 

	//회의실 로그인 한 사람꺼 삭제
	int deleteResevation(int reservationNo, int mno);
	
	// 전체 회의실 예약 목록
	List<ReservationDTO> showSchedule();
	
	//회의실예약
	int insertView(ReservationDTO reservation) throws Exception;
	
	ReservationDTO getReservationInfo(int rno);
	
}
