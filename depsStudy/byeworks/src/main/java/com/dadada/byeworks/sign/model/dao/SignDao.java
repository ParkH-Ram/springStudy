

package com.dadada.byeworks.sign.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.member.model.vo.Member;
import com.dadada.byeworks.sign.model.dto.AppointmentDto;
import com.dadada.byeworks.sign.model.dto.DepartmentDto;
import com.dadada.byeworks.sign.model.dto.SignAndAnnualSign;
import com.dadada.byeworks.sign.model.dto.SignAndAppointment;
import com.dadada.byeworks.sign.model.dto.SignAndQuit;
import com.dadada.byeworks.sign.model.dto.SignLineDto;
import com.dadada.byeworks.sign.model.dto.SignReferDto;
import com.dadada.byeworks.sign.model.dto.UpdateQuitDto;
import com.dadada.byeworks.sign.model.vo.Appointment;
import com.dadada.byeworks.sign.model.vo.Sign;
import com.dadada.byeworks.sign.model.vo.SignAttachment;
import com.dadada.byeworks.sign.model.vo.SignLine;
import com.dadada.byeworks.sign.model.vo.SignRefer;

@Repository //데이터 CRUD관련한 객체의 빈
public class SignDao {
	
	//조직도 관련 ajax 메소드-----------------------------------------------------------

	/**
	 *  전 부서 select 메소드 
	 */
	public ArrayList<DepartmentDto> selectDepartmentList(SqlSessionTemplate sqlSession) {
		
		return (ArrayList)sqlSession.selectList("signMapper.selectDepartmentList");
	}
	
	
	/**
	 *  부서별 직원 select 메소드
	 */
	public ArrayList<Member> selectEmpList(SqlSessionTemplate sqlSession, int departmentNo) {
		
		
		
		return (ArrayList)sqlSession.selectList("signMapper.selectEmpList", departmentNo);
	}
	
	//연차 결재 등록-------------------------------------------
		/**
		 * 연차 결재 등록 메소드(여러가지방법으로 해보는중. controller에서부터 각 요청을 쪼개서 하는지 아니면 Dao에서 쪼개서 하는지)
		 */
	public int insertSignAnnual(SqlSessionTemplate sqlSession, SignAndAnnualSign signAndAnnualSign, SignLine slist, SignRefer rlist,
			ArrayList<SignAttachment> alist) {
		
		int result1 = sqlSession.insert("signMapper.insertSignA", signAndAnnualSign);
		int result2 = sqlSession.insert("signMapper.insertAnnualSign", signAndAnnualSign);
		int result3 = sqlSession.insert("signMapper.insertSignLine", slist.getSlist());
		int result4 = 1;
			if(rlist.getRlist()!=null) {
		 result4 = sqlSession.insert("signMapper.insertSignRefer", rlist.getRlist());
			}
		int result5 = 1;
		if(!alist.isEmpty()) {
			
			for(SignAttachment sa : alist) {
		result5 = sqlSession.insert("signMapper.insertSignAttachment", sa);
			}
		}
		return result1*result2*result3*result4*result5;
	}

	// 사직 결재 등록----------------------------------------------------------
	
	/**
	 * 결재테이블 등록 메소드
	 */
	public int insertSignQ(SqlSessionTemplate sqlSession, SignAndQuit signAndQuit) {
		
		return sqlSession.insert("signMapper.insertSignQ", signAndQuit);
	}

	/**
	 * 사직테이블 등록 메소드
	 */
	public int insertQuit(SqlSessionTemplate sqlSession, SignAndQuit signAndQuit) {
		
		return sqlSession.insert("signMapper.insertQuit", signAndQuit);
	}
	
	/**
	 * 결재자테이블 등록 메소드
	 */
	public int insertSignLineList(SqlSessionTemplate sqlSession, SignLine slist) {
		
		return sqlSession.insert("signMapper.insertSignLineList", slist.getSlist());
	}
	
	/**
	 * 참조자테이블 등록 메소드
	 */
	public int insertReferList(SqlSessionTemplate sqlSession, SignRefer rlist) {
		int result=1;
		
		if( (rlist.getRlist())!=null) {
		  result = sqlSession.insert("signMapper.insertReferList", rlist.getRlist());
		}
		return result;
	}
	
	/**
	 * 첨부파일 테이블 등록 메소드
	 */
	public int insertAttachmentList(SqlSessionTemplate sqlSession, ArrayList<SignAttachment> alist) {
		
		int result = 0;
		for(SignAttachment sa : alist) {
			sqlSession.insert("signMapper.insertAttachmentList", sa);
		}
		return result;
	}


	public int insertSignAp(SqlSessionTemplate sqlSession, SignAndAppointment signAndAppointment) {
		int result = sqlSession.insert("signMapper.insertSignAp", signAndAppointment);
		return result;
	}
	
	public int insertAppointment(SqlSessionTemplate sqlSession, SignAndAppointment signAndAppointment) {
		int result = sqlSession.insert("signMapper.insertAppointment", signAndAppointment);
		
		return result;
	}


	public ArrayList<Sign> selectSignList(SqlSessionTemplate sqlSession, int mno, int type) {
		
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		
		data.put("memberNo",mno);
		data.put("type",type);
		
		return (ArrayList)sqlSession.selectList("signMapper.selectSignList", data);
	}


	public SignAndAnnualSign selectSignAnnual(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.selectOne("signMapper.selectSignAnnual", sno);
	}


	public SignAndQuit selectSignQuit(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.selectOne("signMapper.selectSignQuit", sno);
	}


	public SignAndAppointment selectSignAppointment(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.selectOne("signMapper.selectSignAppointment", sno);
	}


	public ArrayList<SignLineDto> selectSignLine(SqlSessionTemplate sqlSession, int sno) {
		
		return (ArrayList)sqlSession.selectList("signMapper.selectSignLine", sno);
	}


	public ArrayList<SignReferDto> selectSignRefer(SqlSessionTemplate sqlSession, int sno) {
		
		return (ArrayList)sqlSession.selectList("signMapper.selectSignRefer", sno);
	}


	public ArrayList<SignAttachment> selectAttachment(SqlSessionTemplate sqlSession, int sno) {
		
		return (ArrayList)sqlSession.selectList("signMapper.selectAttachment", sno);
	}

// 김다흰


	public int signUp(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.update("signMapper.signUp", sno);
	}


	public int signLineUp(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.update("signMapper.signLineUp", sno);
	}


	public int updateSignQ(SqlSessionTemplate sqlSession, SignAndQuit signAndQuit) {
		
		return sqlSession.update("signMapper.updateSignQ", signAndQuit);
	}


	public int updateSignQuit(SqlSessionTemplate sqlSession, SignAndQuit signAndQuit) {
		
		return sqlSession.update("signMapper.updateSignQuit", signAndQuit);
	}


	public int deleteSignLineList(SqlSessionTemplate sqlSession, int signNo) {
		
		return sqlSession.delete("signMapper.deleteSignLine", signNo);
	}


	public int deleteSignReferList(SqlSessionTemplate sqlSession, int signNo) {
		
		return sqlSession.delete("signMapper.deleteSignRefer", signNo);
	}


	public int deleteSignAttachmentList(SqlSessionTemplate sqlSession, int signNo) {
		
		return sqlSession.delete("signMapper.deleteSignAttachment", signNo);
	}


	public int updateSignLineList(SqlSessionTemplate sqlSession, List<SignLine> list) {
		
		return sqlSession.insert("signMapper.updateSignLine", list);
	}


	public int updateReferList(SqlSessionTemplate sqlSession, List<SignRefer> list) {
		
		return sqlSession.insert("signMapper.updateSignRefer", list);
	}


	public int updateAttachmentList(SqlSessionTemplate sqlSession, ArrayList<SignAttachment> alist) {
		
		int result = 1;
		for(SignAttachment sa : alist) {
			
			result = sqlSession.insert("signMapper.updateAttachmentList", sa);
		}
		return result;
	}


	public int updateSignAN(SqlSessionTemplate sqlSession, SignAndAnnualSign signAndAnnualSign) {
		
		return sqlSession.update("signMapper.updateSignAN", signAndAnnualSign);
	}



	public int updateSignAnnual(SqlSessionTemplate sqlSession, SignAndAnnualSign signAndAnnualSign) {
		
		return sqlSession.update("signMapper.updateSignAnnual", signAndAnnualSign);
	}


	public int updateSignAP(SqlSessionTemplate sqlSession, SignAndAppointment signAndAppointment) {
		
		return sqlSession.update("signMapper.updateSignAP", signAndAppointment);
	}


	public int updateSignAppointment(SqlSessionTemplate sqlSession, SignAndAppointment signAndAppointment) {
		
		return sqlSession.update("signMapper.updateSignAppointment", signAndAppointment);
	}


	public int signCancel(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.update("signMapper.signCancel", sno);
	}


	public int signConfirm(SqlSessionTemplate sqlSession, int sno, int mno) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("sno", sno);
		map.put("mno", mno);
		
		return sqlSession.update("signMapper.signConfirm", map);
	}


	public int signReturn(SqlSessionTemplate sqlSession, int sno, int mno) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("sno", sno);
		map.put("mno", mno);
		
		return sqlSession.update("signMapper.signReturn", map);
	}

	public int totalReturn(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.update("signMapper.totalReturn", sno);
	}

	public int finalConfirm(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.update("signMapper.finalConfirm", sno);

	}
	public ArrayList<AppointmentDto> selectAppointmentList(SqlSessionTemplate sqlSession, ArrayList<Integer> list){
		return (ArrayList)sqlSession.selectList("signMapper.selectAppointmentList", list);

	}
// 김다흰
	public Member appointmentList(SqlSessionTemplate sqlSession, int appEmpno) {
		
		return sqlSession.selectOne("signMapper.appointmentList", appEmpno);
	}



	public int orderCheck(SqlSessionTemplate sqlSession, int sno, int mno) {
		

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("sno", sno);
		map.put("mno", mno);
		int result = sqlSession.selectOne("signMapper.orderCheck", map);
		
		return result;
	}


	public int checkRefer(SqlSessionTemplate sqlSession, int sno, int mno) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("sno", sno);
		map.put("mno", mno);
		
		return sqlSession.update("signMapper.checkRefer", map);
	}


	public String checkDocType(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.selectOne("signMapper.checkDocType", sno);
	}


	public double getAnnualPeriod(SqlSessionTemplate sqlSession, int sno) {
		
		return sqlSession.selectOne("signMapper.getAnnualPeriod", sno);
	}


	public int changeAnnualRemain(SqlSessionTemplate sqlSession, int updateMno, double period) {
		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("mno", (double) updateMno);
		map.put("period", period);
		
		return sqlSession.update("signMapper.changeAnnualRemain", map);
	}


	public ArrayList<UpdateQuitDto> selectQuitMember(SqlSessionTemplate sqlSession, String day) {
		
		ArrayList<UpdateQuitDto> list = (ArrayList) sqlSession.selectList("signMapper.selectQuitMember", day);
		
		 return list;
	}


	public int updateMemberStatus(SqlSessionTemplate sqlSession, ArrayList<Integer> memberlist) {
		int result = 0;
		
		for(Integer mno : memberlist) {
		 result = sqlSession.update("signMapper.updateMemberStatus", mno);
		}
		
		return result;

	}


	public ArrayList<Appointment> selectList(SqlSessionTemplate sqlSession, String day) {
		
		return (ArrayList) sqlSession.selectList("signMapper.selectAppointmentMember", day);
	}


	public int updateMemberStatus2(SqlSessionTemplate sqlSession, ArrayList<Appointment> list) {
		HashMap<String, ArrayList<Appointment>> map = new HashMap<String, ArrayList<Appointment>>();
		
		map.put("AppointmentList", list);
		return sqlSession.update("signMapper.updateMemberStatus2", map);
	}
}
