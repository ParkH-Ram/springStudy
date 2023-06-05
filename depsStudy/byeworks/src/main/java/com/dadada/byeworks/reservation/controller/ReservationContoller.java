package com.dadada.byeworks.reservation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dadada.byeworks.reservation.model.service.ReservationService;
import com.dadada.byeworks.reservation.model.vo.Reservation;
import com.dadada.byeworks.reservation.model.vo.ReservationDTO;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class ReservationContoller {
	
	@Autowired
	private ReservationService rService;
	
	//달력에 뿌려주는거
	@RequestMapping("makeView.res")
	public String ReservationView(Model model) {
		List<ReservationDTO> list = rService.showSchedule();
		model.addAttribute("showSchedule" , list );
//		System.out.println(list);
		
		return "reservation/ReservationView";
	}
	
	//내가  회의실 예약한거 조회
	@RequestMapping("list.res")
	public String selectList(Model model, int mno) {
		
		ArrayList<Reservation> list = rService.selectList(mno);
		model.addAttribute("list",list);
		
//		System.out.println(list);
		
		return "reservation/ReservationDetail";
	}


	//회의실 로그인 한 사람꺼 삭제
	@RequestMapping("delteRe.res")
	public String deleteReservation(int reservationNo, int mno) {
		
		int result = rService.deleteResevation(reservationNo, mno);
		
		if(result>0) {
			return "redirect:list.res?mno="+mno;
		}else {
			return "common/errorPage";
		}
	
	}
	
	@RequestMapping("createView.res")
	public String insertView(ReservationDTO reservation,HttpServletRequest http , Model model) throws Exception {
		
		List<ReservationDTO> list = rService.showSchedule();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String []dateTile = reservation.getDateTime().split("-");
		//시간 문자열로 쭉 나열
		String startDt = reservation.getDateTime()+reservation.getStartTime()+":00";
		String endDt = reservation.getDateTime()+reservation.getEndTime()+":00";
		reservation.setStartDate(startDt.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
		reservation.setEndDate(endDt.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
		
		int resultNum = rService.insertView(reservation);

		model.addAttribute("showSchedule" , list );
		return "redirect:/makeView.res";
	}
	
	
	@RequestMapping(value = "getRinfo.res"/* , produces="application/json; charset=utf-8" */)
	public void getReservationInfo(int rno, HttpServletResponse response) throws JsonIOException, IOException {
		//System.out.println(rno);  << calEvent.event._def.publicId의 번호를 찍어주는거임 즉 예약 고유의 번호
		ReservationDTO rDto = rService.getReservationInfo(rno);
		
		Gson gson= new Gson();
		response.setContentType("application/json; charset=utf-8");
		gson.toJson(rDto, response.getWriter());
		
	}

}