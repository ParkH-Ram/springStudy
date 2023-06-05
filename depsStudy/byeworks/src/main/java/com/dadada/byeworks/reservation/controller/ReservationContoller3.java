/*
package com.dadada.byeworks.reservation.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dadada.byeworks.reservation.model.service.ReservationService;
import com.dadada.byeworks.reservation.model.vo.ReservationDTO;
*/
/*
@Controller
public class ReservationContoller {
	
	@Autowired
	private ReservationService rService;
	
	@RequestMapping("makeView.res")
	public String selectList(Model model) {
//		
//		ArrayList<Reservation> list = rService.selectList();
//		model.addAttribute("list",list);
		
		List<ReservationDTO> list = rService.showSchedule();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//for(ReservationDTO dto : list) {
			// string 타입의 변수에 sdf.format을 사용해서 startDate, endDate
			//dto.setStartDate(); //그변수...
//			String reStartDate = sdf.format(dto.getStartDate());
//			String reEndDate = sdf.format(dto.getEndDate());
			
//			dto.setReStartDate(reStartDate);
//			dto.setReEndDate(reEndDate);
			
			// System.out.println(dto.getStartDate());
			// System.out.println(dto.getEndDate());
		//}
		
		
		
		model.addAttribute("showSchedule" , list );
		//System.out.println(list);
		
		return "reservation/ReservationView";
	}
	
	@RequestMapping("createView.res")
	public String insertView(ReservationDTO reservation,HttpServletRequest http , Model model) throws Exception {
		
		
//		System.out.println(reservation.toString());
		
		List<ReservationDTO> list = rService.showSchedule();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		
		String []dateTile = reservation.getDateTime().split("-");
	
		
		
		String startDt = reservation.getDateTime()+reservation.getStartTime()+":00";
		String endDt = reservation.getDateTime()+reservation.getEndTime()+":00";
		reservation.setStartDate(startDt.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
		reservation.setEndDate(endDt.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
		
//		System.out.println(reservation.getStartDate());
//		System.out.println(reservation.getEndDate());
		
		int resultNum = rService.insertView(reservation);
//		System.out.println(resultNum);
		//List<ReservationDTO> list = rService.showSchedule();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//model.addAttribute("showSchedule" , list );
		model.addAttribute("showSchedule" , list );
		return "reservation/ReservationView";
	}
	
	@RequestMapping(value = "/ReservationView")
	public String ReservationView(Model model) {
		List<ReservationDTO> list = rService.showSchedule();
		model.addAttribute("showSchedule" , list );
		System.out.println(list);
		return "reservation/ReservationView";
	}
}
*//*
*/
/*

*/
