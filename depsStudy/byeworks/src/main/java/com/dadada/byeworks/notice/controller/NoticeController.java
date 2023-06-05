package com.dadada.byeworks.notice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dadada.byeworks.notice.model.service.NoticeService;
import com.dadada.byeworks.notice.model.vo.Notice;


@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService nService;
	
	// @RequestParam(value="", required=false, defaultValue="")
	@RequestMapping("list.not")
	public String selectList(Model model) {
		ArrayList<Notice> list = nService.selectList();

		
		model.addAttribute("list",list);
		
		
		return "notice/NoticeListView";
	}

	/**
	 * @공지사항 작성하기 시작부분
	 *
	 */
	@RequestMapping("enrollForm.no")
	public String enrollForm() {
		return "notice/NoticeEnrollForm";
	}
	
	@RequestMapping("insert.not")
	public String insertNotice(Notice n, HttpServletRequest request,
							   @RequestParam(value="uploadFile", required=false) MultipartFile file) {
		
//		System.out.println(n);
//		System.out.println(file.getOriginalFilename());
		// 파일 업로드를 위한 라이브러리 추가해야됨
		
		//전달되는 파일이 있을 경우 --> 서버에 전달된 파일업로드 처리
		if(!file.getOriginalFilename().equals("")) {
			String changeName = saveFile(file, request);
			String originName = file.getOriginalFilename();
			n.setFRename(changeName);//changeName
			n.setFileName(originName);// originName
		}
		
		int result = nService.insertNotice(n);
		
		if(result > 0) { // 게시판 등록성공
			return "redirect:list.not";
		}else { // 게시판 등록 실패
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping("detail.not")
	public ModelAndView selectNotice(int nno, ModelAndView mv, Model model) {
		
		ArrayList<Notice> list = nService.selectList();
		model.addAttribute("list",list);

		int result = nService.increaseCount(nno);
		
		if(result > 0) { //조회수 증가 성공 --> 조회
			
			Notice n = nService.selectNotice(nno);
			
			mv.addObject("n",n).setViewName("notice/NoticeDetailView");
		}else {
			mv.setViewName("common/errorPage");
		}
		return mv;
		
	}
	
	@RequestMapping("delete.not")
	public String deleteNotice(int nno, String fileName, HttpServletRequest request) {
		
		int result = nService.deleteNotice(nno);
		//System.out.println(nno);
		//System.out.println("nno");
		if(result > 0) {
			
			if(!fileName.equals("")) { //기존에 첨부파일이 있었을 경우
				deleteFile(fileName, request);
			}
				
			return "redirect:list.not";
		}else {
			
			return "common/errorPage";
		}
	}
	
	@RequestMapping("updateForm.not")
	public ModelAndView updateForm(int nno, ModelAndView mv) {
		
		Notice n = nService.selectNotice(nno);
		
		mv.addObject("n",n).setViewName("notice/NoticeUpdateForm");
		
		return mv;
	}
	
	public void deleteFile(String fileName, HttpServletRequest request) {
		String resources = request.getSession().getServletContext().getRealPath("resources");
		String savePath = resources + "\\upload_files\\";
		
		File deleteFile = new File(savePath + fileName);
		deleteFile.delete();
	}
	
	@RequestMapping("update.not")
	public ModelAndView updateNotice(Notice n, HttpServletRequest request, 	ModelAndView mv,
										@RequestParam(value="reUploadFile", required=false) MultipartFile file) {
		//n : 게시글 번호, 수정된제목, 작성자, 수정된낸용 [기존의 첨부파일의 원본명, 수정명]
		
		if(!file.getOriginalFilename().equals("")) {
			
			 if(n.getFileName() != null) {
				 deleteFile(n.getFRename(), request);
			 }
			 
			 //새로 넘어온 파일 서버에 업로드 처리해야됨
			String changeName = saveFile(file, request);
			String originName = file.getOriginalFilename();
			n.setFRename(changeName);//changeName
			n.setFileName(originName);// originName
		}
		
		int result = nService.updateNotice(n);
		
		if(result > 0) {
			mv.addObject("nno",n.getNoticeNo())
				.setViewName("redirect:detail.not");
		}else {
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 전달받은 파일을 서버에 업로드 후 수정된 파일명을 리턴 시키는 메소드 (공유해서 쓸수 있게끔 따로 빼줌)이라고 강사님 강의
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		
		// 1.전달받은 파일을 업로드할 폴더 경로
		String resources = request.getSession().getServletContext().getRealPath("resources");
		String savePath = resources + "\\upload_files\\";
		
		String originName = file.getOriginalFilename();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(new Date());
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + ext;
		
		try {
			file.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return changeName;
	}
	//공지사항 작성하기 끝 부분

}
