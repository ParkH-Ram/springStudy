package com.dadada.byeworks.common.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dadada.byeworks.alarm.model.service.AlarmService;
import com.dadada.byeworks.commute.model.service.CommuteService;
import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.notice.model.service.NoticeService;
import com.dadada.byeworks.notice.model.vo.Notice;
import com.dadada.byeworks.project.model.service.ProjectService;
import com.dadada.byeworks.project.model.vo.ProjectMain;
import com.dadada.byeworks.schedule.model.service.ScheduleService;
import com.dadada.byeworks.schedule.model.vo.Schedule;

@Controller
public class MainController {

	// 공지사항 조회를 하기 위해
	@Autowired
	private NoticeService nService;
	
	@Autowired
	private ScheduleService sService;
	
	// 출퇴근 위해
	@Autowired
	private CommuteService cService;
	@Autowired
	private AlarmService alService;
	
	// 프로젝트를 조회하기 위해
	@Autowired
	private ProjectService pService;
	
	/**
	 * 이슬희: 메인 가는 메서드
	 * @return
	 */
	@RequestMapping("main.do")
	public ModelAndView goMain(ModelAndView mv, HttpSession session) {
		
		// 공지사항 조회
		ArrayList<Notice> nList = nService.selectMainList();
		mv.addObject("nList", nList);
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		// 일정 조회
		ArrayList<Schedule> sList = sService.selectMainList(no);
		mv.addObject("sList", sList);
		
		// 알람 개수 조회
		int alarmCount = alService.countAlarm(no);
		session.setAttribute("alarmCount", alarmCount);
		
		// 프로젝트 조회
		ProjectMain pm = pService.selectProject(no);
		session.setAttribute("projectMain", pm);
		
		mv.setViewName("main");
		
		return mv;
	}
	
	/**
	 * 이슬희: 로그인 화면 가는 메서드
	 * @return
	 */
	@RequestMapping("login.do")
	public String goLogin() {
		return "login/login";   
	}
	
	/**
	 * 이슬희: 에러페이지 여는 서블릿
	 * @return
	 */
	@RequestMapping("error.do")
	public String goError() {
		return "common/errorPage";
	}
	
	
	/**
	 * 김다흰 : 출근 값 넣는 메서드
	 * @param sysdate
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("commuteWorkTime.do")
	public void commuteWorkTime(Date sysdate, int commuteMember) throws ParseException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		String commuteDate = sdf.format(sysdate);
		String commuteWork = time.format(sysdate);
		
		Date lateTime = time.parse("09:01");
		Date lateTimeAfter = time.parse("18:00");
		Date commuteWorkFormat = time.parse(commuteWork);
		Date annualLateTime = time.parse("14:01");
		
		int commuteStatus = 0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("commuteDate", commuteDate);
		map.put("commuteWork", commuteWork);
		map.put("commuteMember", commuteMember);
		
		if(commuteWorkFormat.getTime() > lateTimeAfter.getTime()) {	// 18:00 이후 출근 무단결근
			commuteStatus=5;
			map.put("commuteStatus", commuteStatus);
		}else if(commuteWorkFormat.getTime() >= lateTime.getTime()){	// 9:00 이후 출근 지각
//			if(commuteMember) { // commuteMember의  tb_sign dType=V 이고  tb_annual_sign startday sysdate이고 annualType=1번일때
//				// 오전반차 사용했을경우 지각아니고 
//			}
			commuteStatus = 4;
			map.put("commuteStatus", commuteStatus);
		}else {	// 정상출근
			commuteStatus=0;
			map.put("commuteStatus", commuteStatus);
		}
	
	
		int result = cService.commuteWorkTime(map);
		
	}
	
	@ResponseBody
	@RequestMapping("commuteLeavedTime.do")
	public void commuteLeavedTime(Date sysdate, int commuteMember) {
		SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm");
		String commuteDate = sdf.format(sysdate);
		String commuteLeaved = time.format(sysdate);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("commuteDate", commuteDate);
		map.put("commuteLeaved", commuteLeaved);
		map.put("commuteMember", commuteMember);
		
		int result = cService.commuteLeavedTime(map);
		
	}
	
}
