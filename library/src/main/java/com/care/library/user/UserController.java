package com.care.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.library.member.MemberDTO;
import com.care.library.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired HttpSession session;
	@Autowired private UserService service;

	@RequestMapping("subMenuMyLibrary")
	public String subMenuMyLibrary() {
		return "user/subMenuMyLibrary";
	}
	
	// 1:1문의 - 목록
	@RequestMapping("/myLibrary/myInquiry")
	public String myInquiry() {
		return "user/myInquiry";
	}
	
	// 1:1문의 - 글작성
	@GetMapping("/myLibrary/myInquiryWriteForm")
	public String myInquiryWriteForm() {
		String id = (String)session.getAttribute("id");
		if(id == null || id.equals("")) {
			return "redirect:/login";
		}
		return "user/myInquiryWriteForm";
	}
	
	@PostMapping("/myLibrary/myInquiryWriteProc")
	public String myInquiryWriteProc(String title, String content) {
		String id = (String)session.getAttribute("id");
		service.myInquiryWriteProc(id, title, content);
		return "redirect:myInquiry";
	}
	@Autowired private UserService userService;
	
	@GetMapping("/myLibrary/myInfo")
	public String myInfo(Model model) {
		String id = (String)session.getAttribute("id");
		UserDTO myInfo = userService.getMyInfo(id);
		
		  if(myInfo != null) { 
			  model.addAttribute("email", myInfo.getEmail()); 
			  model.addAttribute("mobile", myInfo.getMobile()); 
			  model.addAttribute("birth", myInfo.getBirth()); 
			  model.addAttribute("address", myInfo.getAddress()); 
			  model.addAttribute("postCode", myInfo.getPostCode()); 
			  model.addAttribute("detailAddress", myInfo.getDetailAddress());
		  }
		 
		return "user/myInfo";
	}
	
	@PostMapping("/myLibrary/changeMyInfoProc")
	public String changeMyInfoProc(UserDTO myInfo, RedirectAttributes ra) {
		String id = (String)session.getAttribute("id");
		myInfo.setId(id);
		
		String result = userService.changeMyInfoProc(myInfo);
		if(result.equals("정보가 수정되었습니다.")) {
			ra.addFlashAttribute("updateMsg", result);
			return "redirect:/main";
		}
		ra.addFlashAttribute("updateMsg", result);
		return "user/myInfo";
	}
	
}
