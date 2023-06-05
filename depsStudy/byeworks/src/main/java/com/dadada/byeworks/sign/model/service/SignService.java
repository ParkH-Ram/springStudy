package com.dadada.byeworks.sign.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.sign.model.dto.AppointmentDto;
import com.dadada.byeworks.sign.model.dto.DepartmentDto;
import com.dadada.byeworks.sign.model.dto.SignAndAnnualSign;
import com.dadada.byeworks.sign.model.dto.SignAndAppointment;
import com.dadada.byeworks.sign.model.dto.SignAndQuit;
import com.dadada.byeworks.sign.model.dto.SignLineDto;
import com.dadada.byeworks.sign.model.dto.SignReferDto;
import com.dadada.byeworks.sign.model.vo.Sign;
import com.dadada.byeworks.sign.model.vo.SignAttachment;
import com.dadada.byeworks.sign.model.vo.SignLine;
import com.dadada.byeworks.sign.model.vo.SignRefer;

public interface SignService {

	ArrayList<DepartmentDto> selectDepartmentList();

	ArrayList<Member> selectEmpList(int departmentNo);

	int insertSignAnnual(SignAndAnnualSign signAndAnnualSign, SignLine slist, SignRefer rlist,
			ArrayList<SignAttachment> alist);

	int insertSignQ(SignAndQuit signAndQuit);

	int insertQuit(SignAndQuit signAndQuit);

	int insertSignLineList(SignLine slist);

	int insertReferList(SignRefer rlist);

	int insertAttachmentList(ArrayList<SignAttachment> alist);

	int insertSignAp(SignAndAppointment signAndAppointment, SignLine slist, SignRefer rlist,
			ArrayList<SignAttachment> alist);

	ArrayList<Sign> selectSignList(int mno, int type);

	SignAndAnnualSign selectSignAnnual(int sno);

	SignAndQuit selectSignQuit(int sno);

	SignAndAppointment selectSignAppointment(int sno);

	ArrayList<SignLineDto> selectSignLine(int sno);

	ArrayList<SignReferDto> selectSignRefer(int sno);

	ArrayList<SignAttachment> selectAttachment(int sno);

	int signUp(int sno);

	int updateSignQuit(SignAndQuit signAndQuit, SignLine slist, SignRefer rlist, ArrayList<SignAttachment> alist);

	int updateSignAnnual(SignAndAnnualSign signAndAnnualSign, SignLine slist, SignRefer rlist,
			ArrayList<SignAttachment> alist);

	int updateSignAnnual(SignAndAppointment signAndAppointment, SignLine slist, SignRefer rlist,
			ArrayList<SignAttachment> alist);

	int signCancel(int sno);

	int signConfirm(int sno, int mno, int length, int updateMno);

	int signReturn(int sno, int mno);

	int checkRefer(int sno, int mno);

	int updateEmpInfo(String day);

	int updateEmpInfo2(String day);


	// 김다흰 발령 내역 조회
	ArrayList<AppointmentDto> selectAppointmentList(ArrayList<Integer> list);

	// 김다흰 발령 사원 조회
	Member appointmentList(int appEmpno);


}
