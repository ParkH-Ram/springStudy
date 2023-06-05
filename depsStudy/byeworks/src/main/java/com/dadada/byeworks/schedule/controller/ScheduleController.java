package com.dadada.byeworks.schedule.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.schedule.model.service.ScheduleService;
import com.dadada.byeworks.schedule.model.vo.Schedule;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class ScheduleController {
	
	@Autowired
	private ScheduleService scService;
	
	// 보내주기
	@RequestMapping("my.sc")
	public ModelAndView goSchedule(ModelAndView mv, HttpSession session){
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		ArrayList<Schedule> list = scService.selectSchedule(no);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Schedule s : list) {
			// 일정이 당일 일정이 아닌 연이어진 일정인지 확인한다
			// 풀캘린더에서 구현 시에 당일 일정이 아닌 경우에 하루가 빠져서 구현되기때문에
			long calDate = s.getEndDate().getTime() - s.getBeginDate().getTime();
			long calDateDays = calDate / (24*60*60*1000);
			calDateDays = Math.abs(calDateDays);
			
			if(calDateDays >= 1) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(s.getEndDate());
				// 하루를 더해준다
				cal.add(Calendar.DATE, 1);
				
				// 더한 값을 세팅
				s.setEnd(sdf.format(cal.getTime()));
			}
			s.setBegin(s.getBegin().substring(0, 10));
			
		}
		mv.addObject("testL", list);
		
		mv.setViewName("schedule/MySchedule");
		return mv;
	}

	// 리스트 읽어오기
	@ResponseBody
	@RequestMapping(value="select.sc")
	public void selectSchedule(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		ArrayList<Schedule> list = scService.selectSchedule(no);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Schedule s : list) {
			// 일정이 당일 일정이 아닌 연이어진 일정인지 확인한다
			// 풀캘린더에서 구현 시에 당일 일정이 아닌 경우에 하루가 빠져서 구현되기때문에
			long calDate = s.getEndDate().getTime() - s.getBeginDate().getTime();
			long calDateDays = calDate / (24*60*60*1000);
			calDateDays = Math.abs(calDateDays);
			
			if(calDateDays >= 1) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(s.getEndDate());
				// 하루를 더해준다
				cal.add(Calendar.DATE, 1);
				
				// 더한 값을 세팅
				s.setEnd(sdf.format(cal.getTime()));
			}
			s.setBegin(s.getBegin().substring(0, 10));
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	}
	
	// insert
	@RequestMapping("insert.sc")
	public String insertSchedule(Schedule s) {
		
		int result = scService.insertSchedule(s);
		
		if(result > 0) {
			return "redirect:/my.sc";
		} else {
			return "common/errorPage";
		}
	}
	
	// 정보 하나 읽어오기
	@ResponseBody
	@RequestMapping("selectOne.sc")
	public void selectOne(HttpSession session, HttpServletResponse response, String no) throws JsonIOException, IOException {
		
		int schNo = Integer.parseInt(no);
		
		Schedule s = scService.selectOne(schNo);
		
		s.setBegin(s.getBegin().substring(0, 10));
		s.setEnd(s.getEnd().substring(0, 10));
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(s, response.getWriter());
	}
	
	@RequestMapping("update.sc")
	public String updateSchedule(Schedule s) {
		
		int result = scService.updateSchedule(s);
		
		if(result > 0) {
			return "redirect:/my.sc";
		}
		
		return "common/errorPage";
	}
	
	@RequestMapping("delete.sc")
	public String deleteSchedule(int schNo) {
		
		int result = scService.deleteSchedule(schNo);
		
		if(result > 0) {
			return "redirect:/my.sc";
		}
		
		return "common/errorPage";
	}
}
