package com.dadada.byeworks.work.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.work.model.service.AnnualService;
import com.dadada.byeworks.work.model.vo.Annual;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class AnnualController {

	@Autowired
	private AnnualService annService;
	
	/** 김다흰 : 페이지 이동
	 * @return
	 */
	@RequestMapping("annualList.ann")
	public String annualList() {
		return "work/annualList";
	}
	
	/** 김다흰 : 직원 연차 리스트 조회
	 * @param annualUseDay
	 * @param response
	 * @throws IOException 
	 * @throws JsonIOException 
	 */
	@RequestMapping(value="annualUseList.ann")
	public void annualUseList(String annualUseDay, HttpServletResponse response) throws JsonIOException, IOException {
		
		ArrayList<Annual> list = annService.annualUseList(annualUseDay);
	
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
		
		
	}
	
	/** 김다흰 : 페이지 이동과 연차 정보 조회
	 * @return
	 */
	@RequestMapping("annualApp.ann")
	public String annualApp(HttpSession session, Model model) {
		
		int mno = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		Annual ann = annService.annualInfo(mno);
		
		model.addAttribute("ann", ann);
		return "work/annualInsert";
	}
	
	
	

}
