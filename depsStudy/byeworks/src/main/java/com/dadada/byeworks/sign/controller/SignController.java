package com.dadada.byeworks.sign.controller;




import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.sign.model.dto.AppointmentDto;
import com.dadada.byeworks.sign.model.dto.DepartmentDto;
import com.dadada.byeworks.sign.model.dto.SignAndAnnualSign;
import com.dadada.byeworks.sign.model.dto.SignAndAppointment;
import com.dadada.byeworks.sign.model.dto.SignAndQuit;
import com.dadada.byeworks.sign.model.dto.SignLineDto;
import com.dadada.byeworks.sign.model.dto.SignReferDto;
import com.dadada.byeworks.sign.model.service.SignService;
import com.dadada.byeworks.sign.model.vo.Sign;
import com.dadada.byeworks.sign.model.vo.SignAttachment;
import com.dadada.byeworks.sign.model.vo.SignLine;
import com.dadada.byeworks.sign.model.vo.SignRefer;
import com.dadada.byeworks.work.model.vo.AnnualDto;
import com.google.gson.Gson;

@Controller
public class SignController {
	@Autowired
	private SignService sService;
	
	/**
	 * 결재 작성화면 이동
	 */
	@RequestMapping("enrollForm.si")
	public String enrollSignForm() {
		
		return "sign/enrollSignForm";
	}
	
	/**
	 * 결재문서 상세보기 이동
	 */
	@RequestMapping("signDetail.si")
	public ModelAndView signDetail(ModelAndView mv, int sno, String type, int mno) {
		
		ArrayList<SignReferDto> checkReferlist = sService.selectSignRefer(sno);
		for(int i=0;i<checkReferlist.size();i++) {
			if(checkReferlist.get(i).getMemberNo() == mno ) {
				int result = sService.checkRefer(sno, mno);
			}
		}
		
	if(type.equals("V")) {
		SignAndAnnualSign an = sService.selectSignAnnual(sno);

		mv.addObject("list",an);
	}else if(type.equals("Q")) {
		SignAndQuit sq = sService.selectSignQuit(sno);
		
		mv.addObject("list",sq);
	}else {
		SignAndAppointment sp = sService.selectSignAppointment(sno);
		
		mv.addObject("list",sp);
	}
		ArrayList<SignLineDto> slist = sService.selectSignLine(sno);
		
		mv.addObject("slist",slist);
		
		ArrayList<SignReferDto> rlist = sService.selectSignRefer(sno);
		if(!rlist.isEmpty()) {

			mv.addObject("rlist", rlist);

		}
		ArrayList<SignAttachment> alist = sService.selectAttachment(sno);
		if(!alist.isEmpty()) {
			mv.addObject("alist", alist);
		}
		
		
		mv.setViewName("sign/signDetail");
		return mv;
	}
	
	/**
	 * @param signAndAppointment
	 * @param slist
	 * @param rlist
	 * @param request
	 * @param file
	 * 승진/발령 결재 등록 
	 * 
	 */
	@RequestMapping("insertSignAppointment.si")
	public String insertSignAp(SignAndAppointment signAndAppointment, @ModelAttribute SignLine slist, @ModelAttribute SignRefer rlist, MultipartHttpServletRequest request, @RequestParam(value="upLoadFile", required=false) MultipartFile[] file) {
		
		ArrayList<SignAttachment> alist = new ArrayList<SignAttachment>();
		
		
		if(!file[0].getOriginalFilename().equals("")) {
			
			alist = saveFile(file, request);
		}
		
		int result = sService.insertSignAp(signAndAppointment, slist, rlist, alist);
		return "redirect:selectSignList.si?mno=" + signAndAppointment.getMemberNo() + "&type=2";
	}
	
	/**
	 * @param signAndQuit
	 * @param slist
	 * @param rlist
	 * @param request
	 * @param file
	 * @return
	 * 사직 결재 등록
	 */
	@RequestMapping("insertSignQuit.si")
	public String insertSignQ(SignAndQuit signAndQuit, @ModelAttribute SignLine slist, @ModelAttribute SignRefer rlist, MultipartHttpServletRequest request, @RequestParam(value="upLoadFile", required=false) MultipartFile[] file) {
		
		ArrayList<SignAttachment> alist = new ArrayList<SignAttachment>();
			
			if(!file[0].getOriginalFilename().equals("")) {
			
			alist = saveFile(file, request);
			
			
		}
			int result1 = sService.insertSignQ(signAndQuit);
			int result2 = 0;
			int result3 = 0;
			int result4 = 1;
			int result5 = 1;
			
			if(result1 == 1) {
			 result2 = sService.insertQuit(signAndQuit);
			 result3 = sService.insertSignLineList(slist);
			
			if(rlist.getRlist()!=null) {
			 result4 = sService.insertReferList(rlist); 
			}
			if(!alist.isEmpty()) {
				result5 = sService.insertAttachmentList(alist);
			}
		}
		return "redirect:selectSignList.si?mno=" + signAndQuit.getMemberNo() + "&type=2";
	}

	/**
	 * @param signAndAnnualSign
	 * @param slist
	 * @param rlist
	 * @param request
	 * @param file
	 * @return
	 * 연차 결재 등록
	 */
	@RequestMapping("insertSignAnnual.si")
	public String insertSingAn(SignAndAnnualSign signAndAnnualSign, @ModelAttribute SignLine slist, @ModelAttribute SignRefer rlist, MultipartHttpServletRequest request, @RequestParam(value="upLoadFile", required=false) MultipartFile[] file ) {
		
		
		ArrayList<SignAttachment> alist = new ArrayList<SignAttachment>();
		
		System.out.println(signAndAnnualSign);
		
		if(!file[0].getOriginalFilename().equals("")) {
			
			alist = saveFile(file, request);	
			System.out.println(alist);
		}	
		int result = sService.insertSignAnnual(signAndAnnualSign, slist, rlist, alist);
		
		return "redirect:selectSignList.si?mno=" + signAndAnnualSign.getMemberNo() + "&type=2";
	}
	

	@RequestMapping("selectSignList.si")
	public ModelAndView selectSignList(ModelAndView mv, int mno, int type) {
		
		ArrayList<Sign> list = sService.selectSignList(mno,type);
		
		
		mv.addObject("list", list).addObject("type", type).setViewName("sign/totalSignList");

		
//		switch(type) {
//		case 1 : mv.addObject("list", list).setViewName("sign/totalSignList"); break;
//		case 2 : mv.addObject("list", list).setViewName("sign/waitingSignList"); break;
//		case 3 : mv.addObject("list", list).setViewName("sign/progressingSignList"); break;
//		case 4 : mv.addObject("list", list).setViewName("sign/confirmedSignList"); break;
//		case 5 : mv.addObject("list", list).setViewName("sign/returnSignList"); break;
//		case 6 : mv.addObject("list", list).setViewName("sign/cancelSignList"); break;
//		
//		}
		return mv;
	
	}
	
	/**
	 * @param sno
	 * @return
	 * 결재 대기에서 결재 진행으로 변경 (N to O)
	 */
	@RequestMapping("signUp.si")
	public String signUp(int sno, int mno) {
		
		int result = sService.signUp(sno);
		
		if(result>0) {
			return "redirect:selectSignList.si?mno=" + mno + "&type=3";
		}else {
			return "common/errorPage";
		}
	}
	
	/**
	 * @param sno
	 * @param mno
	 * @return
	 * 결재 회수 처리
	 */
	@RequestMapping("signCancel.si")
	public String signCancel(int sno, int mno) {
		
		int result = sService.signCancel(sno);
		
		if(result>0) {
			
			return "redirect:selectSignList.si?mno=" + mno + "&type=6";
		}else {
			
			return "common/errorPage";
			
		}
		
	}
	
	/**
	 * @param sno
	 * @param mno
	 * @return
	 * 
	 * 결재 승인
	 */
	@RequestMapping("signConfirm.si")
	public String signConfirm(int sno, int mno, int length, int updateMno) {
		
		int result = sService.signConfirm(sno, mno, length, updateMno);
		
		if(result>0) {
			
			return "redirect:selectSignList.si?mno="+ mno +"&type=8";
		}else {
			
			return "common/errorPage";
			
			
		}
		
	}
	
	/**
	 * @param sno
	 * @param mno
	 * @return
	 * 
	 * 결재 반려
	 */
	@RequestMapping("signReturn.si")
	public String signReturn(int sno, int mno) {
		
		int result = sService.signReturn(sno, mno);
		
		if(result>0) {
			return "redirect:selectSignList.si?mno=" + mno + "&type=8";
		}else {
			return "common/errorPage";
		}
	}
	
	
	/**
	 * @param mv
	 * @param sno
	 * @param mno
	 * @param type
	 * @return
	 * 결재 수정폼 이동
	 */
	@RequestMapping("signFormUpdate.si")
	public ModelAndView signUpdateForm(ModelAndView mv, int sno, int mno, String type) {
		
		if(type.equals("V")) {
			SignAndAnnualSign an = sService.selectSignAnnual(sno);

			mv.addObject("list",an);
		}else if(type.equals("Q")) {
			SignAndQuit sq = sService.selectSignQuit(sno);
			
			mv.addObject("list",sq);
		}else {
			SignAndAppointment sp = sService.selectSignAppointment(sno);
			
			mv.addObject("list",sp);
		}
			ArrayList<SignLineDto> slist = sService.selectSignLine(sno);
			
			mv.addObject("slist",slist);
			
			ArrayList<SignReferDto> rlist = sService.selectSignRefer(sno);
			
			if(!rlist.isEmpty()) {
				mv.addObject("rlist", rlist);
			}
			ArrayList<SignAttachment> alist = sService.selectAttachment(sno);
			if(!alist.isEmpty()) {
				mv.addObject("alist", alist);
			}
			
			
			mv.setViewName("sign/signUpdateForm");
		
		
			return mv;
	}
	
	@RequestMapping("updateSignQuit.si")
	public String updateSignQuit(SignAndQuit signAndQuit, @ModelAttribute SignLine slist, @ModelAttribute SignRefer rlist, @ModelAttribute SignAttachment atList, MultipartHttpServletRequest request, @RequestParam(value="reUpLoadFile", required=false) MultipartFile[] file) {
		
			int signNo=signAndQuit.getSignNo();
			ArrayList<SignAttachment> alist = new ArrayList<SignAttachment>();
					
					
					if(!file[0].getOriginalFilename().equals("")) {
						
						if(atList.getAlist()!=null) {
						
							deleteFile(atList.getAlist(),request);
							alist = saveFile(file, request);
							for(int i=0; i<alist.size();i++) {
								alist.get(i).setSignNo(signNo);

							}
						}
			
						}
					int result = sService.updateSignQuit(signAndQuit, slist, rlist, alist);
					
					String ad = "";
					if(result>0) {
						ad = "redirect:selectSignList.si?mno=" + signAndQuit.getMemberNo() + "&type=2";
					}else {
						ad = "common/errorPage";
					}
					
					return ad;
	}
	
	@RequestMapping("updateSignAnnual.si")
	public String updateSignAnnual(SignAndAnnualSign signAndAnnualSign, @ModelAttribute SignLine slist, @ModelAttribute SignRefer rlist, @ModelAttribute SignAttachment atList, MultipartHttpServletRequest request, @RequestParam(value="reUpLoadFile", required=false) MultipartFile[] file ) {
		
		int signNo=signAndAnnualSign.getSignNo();
		
		
		ArrayList<SignAttachment> alist = new ArrayList<SignAttachment>();
	
		if(!file[0].getOriginalFilename().equals("")) {
			
			if(atList.getAlist()!=null) {
			
				deleteFile(atList.getAlist(),request);
				alist = saveFile(file, request);
				for(int i=0; i<alist.size();i++) {
					alist.get(i).setSignNo(signNo);

				}
				
				
			}

			}
		int result = sService.updateSignAnnual(signAndAnnualSign, slist, rlist, alist);
		
		String ad = "";
		if(result>0) {
			ad = "redirect:selectSignList.si?mno=" + signAndAnnualSign.getMemberNo() + "&type=2";
		}else {
			ad = "common/errorPage";
		}
		
		return ad;
		
	}
	
	@RequestMapping("updateSignAppointment.si")
	public String updateSignAppointment(SignAndAppointment signAndAppointment, @ModelAttribute SignLine slist, @ModelAttribute SignRefer rlist, @ModelAttribute SignAttachment atList, MultipartHttpServletRequest request, @RequestParam(value="reUpLoadFile", required=false) MultipartFile[] file ) {
		
		int signNo=signAndAppointment.getSignNo();
		
		ArrayList<SignAttachment> alist = new ArrayList<SignAttachment>();

		if(!file[0].getOriginalFilename().equals("")) {
			
			if(atList.getAlist()!=null) {
			
				deleteFile(atList.getAlist(),request);
				alist = saveFile(file, request);
				for(int i=0; i<alist.size();i++) {
					alist.get(i).setSignNo(signNo);

				}
			}

			}
		int result = sService.updateSignAnnual(signAndAppointment, slist, rlist, alist);
		
		String ad = "";
		if(result>0) {
			ad = "redirect:selectSignList.si?mno=" + signAndAppointment.getMemberNo() + "&type=2";
		}else {
			ad = "common/errorPage";
		}
		
		return ad;
	}
	
//	@RequestMapping("signLineConfirm.si")
//	public String signLineConfirm(int mno, int sno) {
//		
//		SignLine signLine = new SignLine();
//		
//		int result = sService.signLineConfirm();
//		
//		return "";		
//	}
//					
					
	
	//@RequestMapping("updateSignAnnual.si")
	
	//@RequestMapping("updateSignAppointment.si")
	

	

//	@RequestMapping("selectSignList.si")
//	public String selectSignList(int status) {
//		
//		String url = "";
//		
//		if(status == 0) {
//			
//			url = "sign/confirmedSignList";
//			
//		}else if(status == 1){
//			
//			url = "sign/returnSignList";
//			
//		}else if(status == 2){
//			
//			url = "sign/cancelSignList";
//			
//		}
//		
//		return url;
		

//	} --> 이렇게 하게되면 메뉴바에 누른 상태 화면 표시 안됨. 매개변수넘겨서 안하고 각각의 url mapping 값 따로줌.
	
	
	/**
	 * 부서목록 가져오는 ajax
	 */
	@ResponseBody
	@RequestMapping(value="selectDepartmentList.si", produces="application/json; charset=utf-8")
	public String selectDepartmentList() {
		
		ArrayList<DepartmentDto> list = sService.selectDepartmentList();
		
	
		
		Gson gson= new Gson();
		
		return gson.toJson(list);
		
	}
	
	/**
	 *  해당부서에 포함된 사원 가져오는 ajax
	 */
	@ResponseBody
	@RequestMapping(value="selectEmpList.si", produces="application/json; charset=utf-8")
	public String selectEmpList(int departmentNo) {
		
		
		ArrayList<Member> list = sService.selectEmpList(departmentNo);
		
		
		Gson gson = new Gson();
		
		return gson.toJson(list);
	}
	
	//서버에 전달받은 파일들 업로드하는 메소드(공유해서 쓸수있음)
	/**
	 *  파일 업로드하는 메소드
	 */
	public ArrayList<SignAttachment> saveFile(MultipartFile[] file, MultipartHttpServletRequest request) {
		
		String resources = request.getSession().getServletContext().getRealPath("resources");
		String savePath = resources + "\\sign_files\\";
		
		ArrayList<SignAttachment> list = new ArrayList<SignAttachment>();
		
		String originName = "";
		String changeName = "";
		for(int i=0;i<file.length;i++) {
			originName = file[i].getOriginalFilename();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String currentTime = sdf.format(new Date());
			String ext = originName.substring(originName.lastIndexOf("."));
			
			changeName = originName + currentTime + ext;
			
			SignAttachment sa = new SignAttachment();
			sa.setMaName(changeName);
			sa.setOaName(originName);
			
			list.add(sa);
			try {
				file[i].transferTo(new File(savePath + changeName));
			} catch (IllegalStateException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	

	// 전달받은 파일명을 찾아서 삭제시키는 메소드(공유해서 쓸수 있게끔 따로 빼줌)
		public void deleteFile(List<SignAttachment> list, HttpServletRequest request) {
			String resources = request.getSession().getServletContext().getRealPath("resources");
			
			String savePath = resources + "\\sign_files\\";
			
			for(int i=0;i<list.size();i++) {
			
			File deleteFile = new File(savePath + list.get(i).getMaName());
			
			deleteFile.delete();
			}
		}
		
	//ajax로 오늘 날짜 가지고와서 QUIT 테이블과 APPOINTMENT 테이블에 있는 날짜 비교후 업데이트 처리	
		@RequestMapping("updateChange.si")
		public void updateEmpInfo(Date today) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
			
			String day = sdf.format(today);

			int result = sService.updateEmpInfo(day);
			
			
		}
	//ajax로 오늘 날짜 가지고와서 QUIT 테이블과 APPOINTMENT 테이블에 있는 날짜 비교후 업데이트 처리		
		@RequestMapping("updateChange2.si")
		public void updateChange2(Date today) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");

			String day = sdf.format(today);
			
			int result = sService.updateEmpInfo2(day);
		}
	

	
	/** 김다흰 : 연차 작성 후 전자결재로 이동
	 * @param mv
	 * @param annualSign
	 * @return
	 */
	@RequestMapping("annualInsert.ann")

	public ModelAndView annualInsertSign(ModelAndView mv, AnnualDto annualSign) {


		mv.addObject("type","V").setViewName("work/directEnrollForm");
		
	

		mv.addObject("annual", annualSign).setViewName("work/directEnrollForm");
		

		return mv;
	}
	
	}

