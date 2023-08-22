package com.care.library.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.library.common.NotifyDTO;
import com.care.library.common.NotifyService;
import com.care.library.common.PageService;
import com.care.library.member.MemberDTO;
import com.care.library.user.InquiryDTO;

@Service
public class AdminService {
	@Autowired AdminMapper mapper;
	@Autowired NotifyService notiService;
	
	public void selectMember(String cp, String memberSelect, Model model) {
		if(memberSelect == null) {
			memberSelect = "R";
		}
		
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<MemberDTO> members = mapper.selectMember(memberSelect, begin, end);
		
		String url = "member?currentPage=";
		int totalCount = mapper.count();
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("members", members);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}

	public void selectUser(String id, Model model) {
		MemberDTO result = mapper.selectUser(id);
		model.addAttribute("member", result);
	}

	public String memberConfirm(String id, String userGroup, String paper, String authDate, String reject) {
		String result = "";
		if (reject.equals("")) {
			mapper.memberConfirm(id, userGroup, paper, authDate);
			result ="정회원이 되셨습니다.";
			NotifyDTO notification = new NotifyDTO();
			notification.setId(id);
			notification.setCategory("회원");
			notification.setTitle(result);
			notification.setUrl("/myLibrary/myInfo");
			notiService.register(notification);
		} else {
			mapper.memberReject(id, reject);
			result ="인증 신청이 반려되었습니다.";
			NotifyDTO notification = new NotifyDTO();
			notification.setId(id);
			notification.setCategory("회원");
			notification.setTitle(result);
			notification.setUrl("/myLibrary/myInfo");
			notiService.register(notification);
		}
		return result;
	}

	public MemberDTO statusChk(String id) {
		return mapper.selectUser(id);
	}

	public void selectInquiryTitle(String cp, String select, String search, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<InquiryDTO> inquiries = mapper.selectInquiryTitle(search, begin, end);
		
		String url = "inquiry?select=" + select + "&search=" + search + "&currentPage=";
		int totalCount = mapper.countInquiryTitle(search);
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("inquiries", inquiries);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}
	
	
	public void selectInquiryWriter(String cp, String select, String search, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<InquiryDTO> inquiries = mapper.selectInquiryWriter(search, begin, end);
		
		String url = "inquiry?select=" + select + "&search=" + search + "&currentPage=";
		int totalCount = mapper.countInquiryWriter(search);
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("inquiries", inquiries);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
		
	}
	
	public void selectInquiryReply(String cp, String select, String replySelect, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<InquiryDTO> inquiries;
		int totalCount;
		
		if(replySelect.equals("A")) {
			inquiries = mapper.selectInquiry(begin, end);
			totalCount = mapper.countInquiry();
		} else {
			inquiries = mapper.selectInquiryReply(replySelect, begin, end);
			totalCount = mapper.countInquiryReply(replySelect);
		}

		String url = "inquiry?select=reply&replySelect="+replySelect +"&currentPage=";
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("inquiries", inquiries);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}

	public InquiryDTO inquiryContent(int no) {
		return mapper.selectInquiryContent(no);
	}

	public void replyWrite(int no, String content) {
		mapper.replyWrite(no, content);
		String result = "1:1문의 답변이 등록되었습니다.";
		
		InquiryDTO writer = mapper.selectInquiryContent(no);
		String id = writer.getId();
		
		NotifyDTO notification = new NotifyDTO();
		notification.setId(id);
		notification.setCategory("문의");
		notification.setTitle(result);
		notification.setUrl("/myLibrary/myInquiry");
		notiService.register(notification);
	}





}
