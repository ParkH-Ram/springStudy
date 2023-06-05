package com.dadada.byeworks.bizAddress.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dadada.byeworks.bizAddress.model.service.BizAddressService;
import com.dadada.byeworks.bizAddress.model.vo.BizAddress;
import com.dadada.byeworks.bizAddress.model.vo.BizAddressFav;
import com.dadada.byeworks.bizAddress.model.vo.BizGroup;
import com.dadada.byeworks.member.model.vo.Member;

@Controller
public class AddrController {
	
	@Autowired
	private BizAddressService bService;
	
	@RequestMapping("selectList.bzad")
	public ModelAndView selectBizAddrList(HttpSession session, ModelAndView mv) {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		// 그룹 전체 조회
		ArrayList<BizGroup> gList = bService.selectGroupList();
		mv.addObject("gList", gList);
		
		// 혹시 그룹으로 조회된 값인지 확인하기
		ArrayList<BizAddress> list = (ArrayList<BizAddress>)session.getAttribute("groupList");
		
		// 있으면
		if(list != null) {
			mv.addObject("list", list);
			// 일단 걘 지워주고
			session.removeAttribute("groupList");
		} else { // 없으면 전체 주소록을 조회해준다
			list = bService.selectBizAddrList(no);
			mv.addObject("list", list);
		}
		
		mv.setViewName("bizAddress/bizAddress");
		
		return mv;
	}
	
	/**
	 * 그룹 전체 조회
	 * @param mv
	 * @return
	 */
	@RequestMapping("selectList.bzgr")
	public ModelAndView selectGroupList(ModelAndView mv) {
		
		ArrayList<BizGroup> list = bService.selectGroupList();
		
		mv.addObject("list", list);
		
		mv.setViewName("bizAddress/bizGroup");
		
		return mv;
	}
	
	/**
	 * 그룹 추가
	 * @param memberNo
	 * @param groupName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insert.bzgr")
	public String insertGroup(String memberNo, String groupName) {
		
		int result = bService.insertGroup(Integer.parseInt(memberNo), groupName);
		
		return String.valueOf(result);
	}
	
	/**
	 * 그룹명 수정
	 * @param gno
	 * @param groupName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update.bzgr")
	public String updateGroup(String gno, String groupName) {
		
		int result = bService.updateGroup(Integer.parseInt(gno), groupName);
		
		return String.valueOf(result);
	}
	
	/**
	 * 그룹 삭제
	 * @param gno
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete.bzgr")
	public String deleteGroup(String gno) {
		System.out.println(gno);
		int result = bService.deleteGroup(Integer.parseInt(gno));
		
		return String.valueOf(result);
	}
	
	/**
	 * 업체 주소록 그룹 별 조회
	 * @param session
	 * @param groupNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("group.bzad")
	public String selectBizListGroup(HttpSession session, String groupNo) {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		if(groupNo.equals("")) {
			groupNo = "0";
		}
		ArrayList<BizAddress> groupList = bService.selectBizListGroup(no, Integer.parseInt(groupNo));
		
		// 조회 시에 읽어오려고 넣어준다
		session.setAttribute("groupList", groupList);
		session.setAttribute("groupNo", groupNo);
		
		return "";
	}
	
	/**
	 * 업체 주소록 즐겨찾기
	 * @param af
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bookmark.bzad")
	public String bookmarkBizAddr(BizAddressFav af) {
		// 받아온 정보를 insert 해준다
		int result = bService.bookmarkBizAddr(af);
		
		return String.valueOf(result);
	}
	
	/**
	 * 업체 주소록 즐겨찾기 해제
	 * @param af
	 * @return
	 */
	@ResponseBody
	@RequestMapping("removeBM.bzad")
	public String removeBookmarkBizAddr(BizAddressFav af) {
		
		int result = bService.removeBookmarkBizAddr(af);
		
		return String.valueOf(result);
	}
	
	/**
	 * 주소 추가
	 * @param addr
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insert.bzad")
	public String insertBizAddr(BizAddress addr) {
		
		int result = bService.insertBizAddr(addr);
		
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping("update.bzad")
	public String updateBizAddr(BizAddress addr) {
		
		int result = bService.updateBizAddr(addr);
		
		return String.valueOf(result);
	}

	/**
	 * 주소 삭제
	 * @param no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("delete.bzad")
	public String deleteBizAddr(String no) {
		// 리스트로 변환
		List<String> list = Arrays.asList(no.split("\\s*,\\s*"));
		
		ArrayList<Integer> intList = new ArrayList<>();
		
		for(String s : list) {
			intList.add(Integer.parseInt(s));
		}
		
		int result = bService.deleteBizAddr(intList);
		
		return String.valueOf(result);
	}
	
	/**
	 * 즐겨찾기 주소록 조회
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping("markList.bzad")
	public ModelAndView selectBizMarkList(ModelAndView mv, HttpSession session) {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		ArrayList<BizAddress> list = bService.selectBizMarkList(no);
		
		mv.addObject("list", list);
		mv.setViewName("bizAddress/bizAddressMark");
		
		return mv;
	}
	
	/**
	 * 휴지통 조회
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping("wasteBasket.bzad")
	public ModelAndView selectWBList(ModelAndView mv, HttpSession session) {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		ArrayList<BizAddress> list = bService.selectWBList(no);
		
		mv.addObject("list", list);
		mv.setViewName("bizAddress/wastebasketAddr");
		
		return mv;
	}
	
	/**
	 * 삭제된 주소 복원
	 * @param no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("restore.bzad")
	public String restoreBizAddr(String no) {
		// 리스트로 변환
		List<String> list = Arrays.asList(no.split("\\s*,\\s*"));
		
		ArrayList<Integer> intList = new ArrayList<>();
		
		for(String s : list) {
			intList.add(Integer.parseInt(s));
		}
		
		int result = bService.restoreBizAddr(intList);
		
		return String.valueOf(result);
	}
	
	/**
	 * 주소 영구 삭제
	 * @param no
	 * @return
	 */
	@ResponseBody
	@RequestMapping("purge.bzad")
	public String purgeBizAddr(String no) {
		// 리스트로 변환
		List<String> list = Arrays.asList(no.split("\\s*,\\s*"));
		
		ArrayList<Integer> intList = new ArrayList<>();
		
		for(String s : list) {
			intList.add(Integer.parseInt(s));
		}
		
		// 우선 참조하고 있는 즐겨찾기부터 삭제
		bService.removeBookmarkMany(intList);
		int result = 0;
		
		result = bService.purgeBizAddr(intList);
		
		
		return String.valueOf(result);
	}
}
