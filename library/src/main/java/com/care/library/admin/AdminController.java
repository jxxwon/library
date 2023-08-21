package com.care.library.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.library.member.MemberDTO;
import com.care.library.user.InquiryDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired HttpSession session;
	@Autowired AdminService service;
	
	@RequestMapping("/admin/member")
	public String admin() {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		if(id == null || id.equals("")||status.equals("M") == false) {
			return "redirect:main";
		}
		return "admin/member";
	}
	
	@RequestMapping("subMenuAdmin")
	public String subMenuAdmin() {
		return "admin/subMenuAdmin";
	}
	
	@RequestMapping("/admin/memberAuth")
	public String adminMember(@RequestParam(value="currentPage", required = false)String cp, @RequestParam(value="memberSelect", required = false)String memberSelect, Model model) {
		service.selectMember(cp, memberSelect, model);
		return "admin/memberAuth";
	}
	
	@RequestMapping("/admin/memberConfirm")
	public String memberConfirm(String id, Model model) {
		service.selectUser(id, model);
		return "admin/memberConfirm";
	}
	
	@PostMapping("/admin/memberConfirmProc")
	public String memberConfirmProc(String id, String userGroup, String paper, String reject) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String authDate = sdf.format(new Date());
		service.memberConfirm(id, userGroup, paper, authDate, reject);
		return "redirect:/admin/member";
	}
	
	// 1:1문의 - 목록
	@RequestMapping("/admin/inquiry")
	public String inquiryList(@RequestParam(value="currentPage", required = false)String cp, 
			@RequestParam(value="select", required = false)String select, @RequestParam(value="search", required = false)String search, 
			@RequestParam(value="replySelect", required = false) String replySelect, Model model) {
		
		String id = (String)session.getAttribute("id");
		String status = service.statusChk(id).getStatus();
		
		if(id == null || id.equals("") || (status.equals("M")==false)) {
			return "redirect:main";
		}
				
		//초기 화면 및 검색조건에 제목으로 해놓고 검색어 입력 안 하면 전체 조회
		if(select == null){
			replySelect = "N";
			service.selectInquiryReply(cp, select, replySelect, model);
		} else if(select.equals("title")) {
			service.selectInquiryTitle(cp, select, search, model);
		} else if(select.equals("reply")) {
			service.selectInquiryReply(cp, select, replySelect, model);
		} else if(select.equals("writer")) {
			service.selectInquiryWriter(cp, select, search, model);
		}
		return "admin/inquiry";
	}
	
	// 1:1문의 - 내용
	@RequestMapping("/admin/inquiryContent")
	public String inquiryContent(int no, Model model) {
		InquiryDTO inquiry = service.inquiryContent(no);
		model.addAttribute("inquiry", inquiry);
		return "admin/inquiryContent";
	}
	
	//1:1문의 - 답변 작성
	@RequestMapping("/admin/inquiryReplyWrite")
	public String replyWrite(int no, Model model) {
		InquiryDTO inquiry = service.inquiryContent(no);
		model.addAttribute("inquiry", inquiry);
		return "admin/inquiryReplyWrite";
	}
	
	
	@PostMapping("/admin/inquiryReplyWriteProc")
	public String replyWriteProc(int no, String content) {
		service.replyWrite(no, content);
		return "redirect:/admin/inquiry";
	}
}
