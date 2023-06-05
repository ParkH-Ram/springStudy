package com.dadada.byeworks.member.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.dadada.byeworks.member.model.dto.MemberAddress;
import com.dadada.byeworks.member.model.dto.MemberLogin;
import com.dadada.byeworks.member.model.service.MemberService;
import com.dadada.byeworks.member.model.vo.AddressFav;
import com.dadada.byeworks.member.model.vo.Member;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	// 로그인
	@RequestMapping("login.me")
	public ModelAndView loginMember(MemberLogin m, HttpSession session, HttpServletResponse response, ModelAndView mv) {
		
		// 기존에 loginUser가 세션 값에 존재한다면 지워준다
		if(session.getAttribute("loginUser") != null) {
			session.removeAttribute("loginUser");			
		}
		
		// dto가 아닌 vo를 사용해보기 위해 
		Member checkUser = new Member();
		checkUser.setMemberId(m.getMemberId());
		
		// 디비에 해당 값이 존재하는지 확인하기
		Member loginUser = mService.loginMember(checkUser);
		
		// 로그인이 성공하면
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getMemberPwd(), loginUser.getMemberPwd())) { // 로그인 성공
			// 세션에 설정해주기
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/main.do");
			
			// 로그인 유지 확인
			// 로그인 폼에서 자동로그인 체크를 했는지 확인한다
			if(m.isUseCookie()) { // 체크가 되어 있다면
				// 쿠키를 생성하여 세션의 id를 쿠키에 저장한다
				Cookie cookie = new Cookie("loginCookie", session.getId());
				// 쿠키를 찾을 경로를 컨텍스트 경로로 변경한다
				cookie.setPath("/");
				// 쿠키의 유효 시간을 7일 정도로 설정한다
				int amount = 60*60*24*7;
				cookie.setMaxAge(amount);
				// 쿠키를 적용한다
				response.addCookie(cookie);
				
				// 로그인 시 세션의 id값과 유효시간을 설정해주는 코드
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));
				mService.keepLogin(m.getMemberId(), session.getId(), sessionLimit);
			}
		} else { // 로그인을 실패하면
			session.setAttribute("msg", "로그인 실패");
			mv.setViewName("redirect:/");
		}
		
		return mv;
	}
	
	// 로그아웃
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		Member m = (Member)session.getAttribute("loginUser");
		
		// 로그인 된 친구가 있다면
		if(m != null) {
			session.removeAttribute("loginUser");
			session.invalidate();
			
			// 그리고 쿠키도 없애기
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			// 만약 쿠키가 존재한다면
			if(loginCookie != null) {
				loginCookie.setPath("/");
				// 쿠키 없앨 때는 유효시간을 0으로 변경하면 됨
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				
				// TB_MEMBER에서도 유효기간을 현재시간으로 다시 세팅
				Date date = new Date(System.currentTimeMillis());
				mService.keepLogin(m.getMemberId(), session.getId(), date);
			}
		}
		return "redirect:/";
	}
	
	/**
	 * 이슬희: findIdPwd.jsp로 이동하기 
	 * @return	전달 jsp명
	 */
	@RequestMapping("goFind.me")
	public String findIdPwd() {
		return "login/findIdPwd";
	}
	
	/**
	 * 이슬희: 아이디 찾기
	 * @param m			이름, 이메일 정보가 담겨있음
	 * @param request
	 * @param session
	 * @param mv
	 * @return
	 */
	@RequestMapping("findId.me")
	public ModelAndView findId(Member m, HttpServletRequest request, HttpSession session, ModelAndView mv) {

		// 이름과 이메일에 해당하는 데이터가 존재하는지 확인
		Member checkUser = mService.checkId(m);
		
		// 존재하는 경우
		if(checkUser != null) {
			try {
				// 아이디 정보를 담은 메일 보내주기
				mailSender(request, checkUser, 1, "");
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			// 메일 보내기가 성공했다면 로그인 화면으로 보내주기
			session.setAttribute("msg", "메일을 발송하였습니다. 메일을 확인해주세요.");
			mv.setViewName("redirect:/");
		} else { // 그런 데이터 여기 없어요
			session.setAttribute("msg", "잘못 입력하였습니다.");
			mv.setViewName("redirect:/goFind.me");
		}
		
		return mv;
	}
	
	/**
	 * 이슬희: 비밀번호 찾기
	 * @param request
	 * @param userId		입력한 아이디
	 * @param userEmail		입력한 이메일
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sendSecret.me")
	public String sendSecretCode(HttpServletRequest request, String userId, String userEmail, String ranCode) {
		
		// 아이디와 이메일에 해당하는 계정이 있으면 받아온다
		Member m = mService.checkPwd(userId, userEmail);
		String result;
		
		// 계정이 있다면 result를 1로 세팅하고 메일을 보내기 / 없다면 result를 2로 세팅
		if(m != null) {
			result = "1";
			
			// 메일 보내기
			try {
				mailSender(request, m, 2, ranCode);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
		} else {
			result = "2";
		}
		
		return result;
	}
	
	/**
	 * 이슬희 : 메일 보내는 메서드
	 * @param request				
	 * @param m						이름, 이메일, 아이디 등의 정보를 담아온다
	 * @param check					1: 아이디 찾기 / 2: 비밀번호 찾기
	 * @param ranCode				비밀번호 찾기일 때 받아오는 인증번호
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void mailSender(HttpServletRequest request, Member m, int check, String ranCode) throws AddressException, MessagingException {
		
		// 네이버 stmp.naver.com
		// google smtp.gmail.com
		String host = "smtp.naver.com";
		
		// 발신 계정 아이디, 비밀번호
		final String userName = "lsh9412"; 
		final String password = "howtos22";
		// 포트번호
		int port = 465;
		
		// 수신 메일 주소
		String receiveEmail = m.getEmail();
		
		// 메일 제목
		String subject = "";
		// 메일 내용
		String body = "";
		
		// 아이디 찾기면
		if(check == 1) {
			subject = "[Byeworks] 아이디 확인";
			body = m.getMemberName() + "님 아이디를 확인해주세요 : " + m.getMemberId();
		} else { // 비밀번호 찾기면
			subject = "[Byeworks] 인증번호를 보내드립니다";
			
			HttpSession session = request.getSession();
			
			session.setAttribute("ranCode", ranCode);	
			// 비밀번호 변경까지 가게 되면 필요한 memberNo을 session에 담기
			if(session.getAttribute("memberNo") != null) { // 기존에 존재하면 지워주기
				session.removeAttribute("memberNo");			
			}
			session.setAttribute("memberNo", m.getMemberNo());
			
			// 내용 설정
			body = "귀하의 인증코드는 " + ranCode + " 입니다.";			
		}
		
		// 정보를 담기 위한 객체 생성
		Properties props = System.getProperties();
		
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.trust", host);
		
		// Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = userName;
			String pw = password;
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(un, pw);
			}
		});
		
		// for debug
		session.setDebug(true);
		
		// MimeMessage 생성
		Message mimeMessage = new MimeMessage(session);
		// 발신자 세팅
		mimeMessage.setFrom(new InternetAddress("lsh9412@naver.com"));
		// 수신자 세팅
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));
		
		// 제목, 내용 세팅
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		
		Transport.send(mimeMessage);
	}
	
	/**
	 * 이슬희: 비밀번호 재설정 폼으로 이동하기 
	 * @return
	 */
	@RequestMapping("resetForm.me")
	public String goResetForm(HttpSession session) {
		
		Member m = (Member)session.getAttribute("loginUser");
		
		if(m != null) {
			session.setAttribute("memberNo", m.getMemberNo());
		}
		
		return "login/resetPwd";
	}
	
	/**
	 * 이슬희: 비밀번호 재설정
	 * @param session
	 * @param m	새로운 비밀번호 정보 담겨있음
	 * @return
	 */
	@RequestMapping("resetPwd.me")
	public String resetNewPwd(HttpSession session, Member m) {
		
		// 메일 발송 시 session에 담아온 memberNo
		int no = (int)session.getAttribute("memberNo");

		// Member에 memberNo, 암호화 된 memberPwd 담기
		m.setMemberNo(no);
		m.setMemberPwd(bcryptPasswordEncoder.encode(m.getMemberPwd()));
		
		// 비밀번호 업데이트
		int result = mService.resetNewPwd(m);
		
		if(result > 0) { // 업데이트 성공하면
			session.setAttribute("msg", "비밀번호를 수정하였습니다.");
			// 이제 이런 값 필요 읍다
			if(session.getAttribute("memberNo") != null) { 
				session.removeAttribute("memberNo");			
			}
			return "redirect:/";
		} else { // 실패하면
			session.setAttribute("msg", "수정에 실패하였습니다. 다시 시도해주세요.");
			return "redirect:/resetForm.me";
		}
	}
	
	/**
	 * 이슬희: 사내 주소록 전체 조회
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping("selectList.ad")
	public ModelAndView selectAddrList(ModelAndView mv, HttpSession session) {
		
		// 사원 번호
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		// 전체 주소록 조회
		ArrayList<MemberAddress> addrList = mService.selectAddrList(no);
		mv.addObject("addrList", addrList);
		
		// 즐겨찾기 주소록 조회
		ArrayList<MemberAddress> markList = mService.selectMarkList(no);
		mv.addObject("markList", markList);
		
		mv.setViewName("bizAddress/compAddress");
		return mv;
	}
	
	/**
	 * 이슬희: 사내 주소록 즐겨찾기 조회
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping("selectMarkList.ad")
	public ModelAndView selectMarkList(ModelAndView mv, HttpSession session) {
		// 사원 번호
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();

		// 즐겨찾기 주소록 조회
		ArrayList<MemberAddress> markList = mService.selectMarkList(no);
		mv.addObject("markList", markList);
		
		mv.setViewName("bizAddress/compAddressMark");
		
		return mv;
	}
	
	/**
	 * 이슬희: 즐겨찾기에 추가
	 * @param af	즐겨찾기 한 사람, 즐겨찾기 된 사람의 정보가 담겨 있음
	 * @return
	 */
	@ResponseBody
	@RequestMapping("bookmark.ad")
	public String bookmarkAddr(AddressFav af) {
		
		// 받아온 정보를 insert 해준다
		int result = mService.bookmarkAddr(af);
		
		return String.valueOf(result);
	}
	
	/**
	 * 이슬희: 즐겨찾기에서 삭제
	 * @param af	즐겨찾기 한 사람, 즐겨찾기 된 사람의 정보가 담겨 있음
	 * @return
	 */
	@ResponseBody
	@RequestMapping("removeBM.ad")
	public String removeBookmarkAddr(AddressFav af) {
		
		int result = mService.removeBookmarkAddr(af);
		
		return String.valueOf(result);
	}
	
	/** 김다흰 : 직원 전체 조회
	 * @param model
	 * @return jsp 파일명
	 */
	@RequestMapping("memberList.me")
	public String memberSelectList(Model model) {
		
		ArrayList<Member> list = mService.selectList();
		
		model.addAttribute("list", list);
		return "member/memberSelectList";
	}
	
	/** 김다흰 : 직원 상세 조회
	 * @param mno
	 * @param mv
	 * @return
	 */
	@RequestMapping("detail.me")
	public String memberDetailList(int mno, Model model, HttpSession session) {
		
		Member m = mService.memberDetailList(mno);
		
		
		model.addAttribute("m", m);
	
		
		return "member/memberDetailList";
		
	}
	
	/** 김다흰 : 직원 수정
	 * @param m
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("update.me")
	public String memberUpdate(Member m, Model model, HttpSession session) {
		
		int result = mService.updateMember(m);
		
		if(result >0) {
			session.setAttribute("memberNo", m.getMemberNo());
			return "member/memberDetailList";
		}else {
			
			model.addAttribute("msg", "수정 실패");
			return "member/memberDetailList";
		}
	}
	
	/** 김다흰 : 직원등록 페이지 이동
	 * @return
	 */
	@RequestMapping("memberInsertForm.me")
	public String memberInsert() {
		
		return "member/memberInsert";
	}
	
	/** 김다흰 : 직원 등록(인)
	 * @param m
	 * @param request
	 * @param model
	 * @param file
	 * @return
	 */
	@RequestMapping("memberInsert.me")
	public String memberInsert(Member m, HttpServletRequest request, Model model,
							   @RequestParam(value="profilePicture", required=true) MultipartFile file) {
		
		String profileName = memSaveFile(m, file, request);
		m.setProfileModify(profileName);
		m.setMemberPwd(bcryptPasswordEncoder.encode(m.getBirth()));
		
		int result = mService.memberInsert(m);
		
		
		if(result>0) {
			model.addAttribute("mno", m.getMemberNo());
			
			return "redirect:detail.me";
		}else {
			model.addAttribute("msg", "작성 실패");
			return "member/memberInsert";
		}
		

	}
	
	/** 김다흰 : 직원 프로필사진 등록
	 * @param m
	 * @param file
	 * @param request
	 * @return
	 */
	public String memSaveFile(Member m,  MultipartFile file, HttpServletRequest request) {
		
		String resources = request.getSession().getServletContext().getRealPath("resources");
		String savePath = resources + "\\profile_modify\\";
		
		String originName = file.getOriginalFilename();
		
	
		
		
		String changeName = m.getMemberName() + m.getPhone();
		
		String ext = originName.substring(originName.lastIndexOf("."));
		
		
		String profileModify = changeName + ext;
		
		
		try {
			file.transferTo(new File(savePath + profileModify));
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
		}
		
		
		return profileModify;
	}
	
	
	/** 김다흰 : 직원 등록 후 상세페이지 보기
	 * @param m
	 * @param model
	 * @param session
	 * @return
	 */
	/*
	@RequestMapping("memberdetail.me")
	public String memberDetail(Member m, Model model, HttpSession session) {
		
		int mno = m.getMemberNo();
		m = mService.memberDetailList(mno);
		System.out.println(m.getMemberNo());
		System.out.println(m);
		model.addAttribute("m", m);
	
	
		return "member/memberDetailList";
		
	}
	*/
	
	
	/** 김다흰 : 내정보 수정하기 페이지 이동
	 * @return
	 */
	@RequestMapping("myPage.me")
	public String myPage() {
		
		return "member/myInfo";
	}
	
	/** 김다흰 : 내정보 수정하기
	 * @param m
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("myUpdate.me")
	public String myUpdate(Member m, Model model, HttpSession session) {
		
		int result = mService.myUpdate(m);
		
		
		if(result > 0) {
			session.setAttribute("loginUser", mService.loginMember(m));
			session.setAttribute("myMsg", "내 정보 수정 성공!");
			
			return "redirect:myPage.me";
		}else {
			model.addAttribute("myMsg", "내 정보 수정 실패!");
			return "redirect:myPage.me";
		}
		
	}
	
	/** 김다흰 : 퇴사처리 직원 조회하기
	 * @param memberNo
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws JsonIOException 
	 */
	
	@RequestMapping(value="retireList.me")
	public void retireList(int mno, HttpServletResponse response) throws JsonIOException, IOException {
		
		Member m = mService.memberDetailList(mno);
	
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(m, response.getWriter());
		
	}
	
	/** 김다흰 : 퇴사처리 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("retire.me")
	public String retire(int mno, HttpSession session) {
		
		
		int result = mService.retireMember(mno);
	
		if(result > 0) {
			session.setAttribute("retire", "직원 퇴사처리 완료됐습니다.");
			
			return "redirect:memberList.me";
		}else {
			session.setAttribute("retire", "직원 퇴사처리 실패했습니다. 다시해주세요");
			
			return "redirct:memberList.me";
		}
		
	}
	
	/**
	 * 이슬희: 주소록 ajax로 조회
	 * @param session
	 * @param response
	 * @throws JsonIOException
	 * @throws IOException
	 */
	@RequestMapping("readBM.ad")
	public void readAddrBookmark(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		ArrayList<MemberAddress> markList = mService.selectMarkList(no);
				
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(markList, response.getWriter());
	}
	
	@RequestMapping("read.ad")
	public void readAddr(HttpSession session, HttpServletResponse response) throws JsonIOException, IOException {
		
		int no = ((Member)session.getAttribute("loginUser")).getMemberNo();
		
		ArrayList<MemberAddress> list = mService.selectAddrList(no);
				
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	}
}
