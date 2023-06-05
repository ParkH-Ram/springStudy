package com.dadada.byeworks.commute.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dadada.byeworks.commute.model.service.CommuteService;
import com.dadada.byeworks.commute.model.vo.Commute;
import com.dadada.byeworks.member.model.vo.Member;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class CommuteController {
	
	@Autowired
	private CommuteService cService;

	/** 김다흰 : 직원 출퇴근 페이지 이동
	 * @param model
	 * @return
	 */
	@RequestMapping("commuteList.co")
	public String commuteList() {
		
		return "commute/employeeCommute";
	}
	
	/** 김다흰 : 날짜 선택 전사원 출퇴근 조회
	 * @param commuteDateStr
	 * @param response
	 * @throws IOException 
	 * @throws JsonIOException 
	 */
	@RequestMapping(value="commuteDate.co")
	public void commuteDate(String commuteDateStr, HttpServletResponse response) throws JsonIOException, IOException {
		
		ArrayList<Commute> list = cService.commuteList(commuteDateStr);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
		
	}
	
	
	/** 김다흰 : 내 출퇴근 페이지 이동
	 * @return
	 */
	@RequestMapping("myCommuteList.co")
	public String myCommuteList() {
		
		return "commute/myCommute";
	}
	
	/** 김다흰 : 내 출퇴근 조회 보기
	 * @param mv
	 * @param session
	 * @return
	 * @throws IOException 
	 * @throws JsonIOException 
	 */
	@ResponseBody
	@RequestMapping(value="myCommute.co", produces="application/json; charset=utf-8")
	public String myCommute(int commuteMember, String commuteDateStr){
		ArrayList<Commute> myCommuteList = cService.myCommute(commuteMember, commuteDateStr);
	
		return new Gson().toJson(myCommuteList);
	
	}
	
	/** 김다흰 : 월별 지각 조회하기 페이지 이동
	 * @return
	 */
	@RequestMapping("lateCommute.co")
	public String lateCommute() {
		
		return "commute/lateCommute";
	}
	/** 김다흰 : 월별 지각 사원 리스트
	 * @param commuteDateStr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value= "lateCommuteList.co", produces="application/json; charset=utf-8")
	public String lateCommuteList(String commuteDateStr) {
		ArrayList<Commute> lateCommuteList = cService.lateCommuteList(commuteDateStr);
		
		return new Gson().toJson(lateCommuteList);
		
	}
	
	/** 김다흰 : 출근여부 확인후 result 값에 따른 실행되는 메소드 위한 메소드
	 * @param session
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("wheterCommute.co")
	public String wheterCommute(HttpSession session, Model model) {
		
		int commuteMember = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		Date sysdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
		String commuteDate = sdf.format(sysdate);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("commuteDate", commuteDate);
		map.put("commuteMember", commuteMember);
		
		
		Commute c = cService.wheterCommute(map);
		
		int result = 0;
		
		if(c == null) {	// 출근 안했을 경우 --> 0 리턴
			return String.valueOf(result);
			
		}else {	// 출근 했을 경우
			result = 1;
			return String.valueOf(result);
		}
		
	}
	
	
	
	
}
