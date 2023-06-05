package com.dadada.byeworks.alarm.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dadada.byeworks.alarm.model.service.AlarmService;
import com.dadada.byeworks.alarm.model.vo.Alarm;
import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.note.model.service.NoteService;
import com.dadada.byeworks.note.model.vo.Note;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class AlarmController {
	
	@Autowired
	private AlarmService alService;
	
	@Autowired
	private NoteService ntService; 

	@RequestMapping("go.al")
	public ModelAndView selectList(HttpSession session, ModelAndView mv) {
				
		// 나한테 온 알람만 봐야 하니까 ^^
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();

		// 모든 알람을 봤으니까 그 알람에 대한 건 읽음처리 하자
		alService.updateRead(no);
		
		ArrayList<Alarm> aList = alService.selectNoteList(no);
		
		// 받아온 쪽지의 날짜를 영어로 표현하기 위해
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd", Locale.ENGLISH);
		for(Alarm a : aList) {
			String newDate = sdf.format(a.getAlarmDate());
			
			a.setSendMonth(newDate.substring(0, newDate.lastIndexOf("-")));
			a.setSendDate(newDate.substring(newDate.lastIndexOf("-")+1));			
		}
		
		mv.addObject("aList", aList);
		mv.setViewName("alarm/alarm");
		
		return mv;
	}
	
	@RequestMapping("deleteAll.al")
	public String deleteAllNote(HttpSession session) {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		int result = alService.deleteAllNote(no);
		
		if(result > 0) {
			return "redirect:/go.al";
		} else {
			return "common/errorPage";
		}
	}
	
	@RequestMapping("deleteOne.al")
	public String deleteOneNote(HttpSession session, String alarmNo) {
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		int result = alService.deleteOneNote(Integer.parseInt(alarmNo));
		
		if(result > 0) {
			return "redirect:/go.al";
		} else {
			return "common/errorPage";
		}
	}
	
	// 알람을 눌렀을 때 확인하지 않은 모든 알람 보여주기
	@ResponseBody
	@RequestMapping("show.al")
	public void showAlarm(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		// 모든 알람 보기
		ArrayList<Alarm> list = alService.showAlarm(no);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	}
	
	@ResponseBody
	@RequestMapping("read.al")
	public String readAlarm(String alarmNo, HttpSession session) {
		
		// 일단 확인 여부를 바꿔준다
		int result = alService.readAlarm(Integer.parseInt(alarmNo));
		
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping("count.al")
	public String countAlarm(HttpSession session) {
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		int count = alService.countAlarm(no);
		
		return String.valueOf(count);
	}
	
	// 쪽지에서 보낸 후에 insert 해주기
	@RequestMapping("insert.al")
	public String insertAlarm(Note n, String alarmGroupNo, HttpSession session) {
		
		// 이미 ajax로 note에 insert한 뒤기 때문에 가장 큰 번호를 받아온다
		int noteNo = ntService.returnSequence();
		
		// 넣어줄 alarm 객체
		Alarm a = new Alarm();
		// 날짜 받아오기
		Calendar cal = new GregorianCalendar();
		Date date = new Date(cal.getTimeInMillis());
		a.setAlarmDate(date); 
		// 쪽지면 1
		a.setAlarmGroup(Integer.parseInt(alarmGroupNo));
		// 위에서 긁어온 가장 큰... 그거
		a.setGroupNo(noteNo);
		a.setSendNo(n.getSendNo());
		a.setReceiveNo(n.getReceiveNo());
		
		// 알람 넣어주기
		int result = alService.insertAlarm(a);
		
		session.setAttribute("sendMsg", "전송되었습니다.");
		
		if(result > 0) {
			return "redirect:selectList.nt";
		} else {
			return "common/errorPage";
		}
	}
}
