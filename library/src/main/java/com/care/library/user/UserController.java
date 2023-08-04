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
	@Autowired private MemberService service;
	@Autowired private UserService userService;
	
	@RequestMapping("subMenuMyLibrary")
	public String subMenuMyLibrary() {
		return "user/subMenuMyLibrary";
	}
	
	//대출/예약/연장 현황
	@RequestMapping("myLibrary/myBookStatus")
	public String myBookStatus() {
		return "user/myBookStatus";
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
	
	// 회원정보 수정
	@GetMapping("/myLibrary/updateInfo")
	public String updateInfo(Model model) {
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
		return "user/updateInfo";
	}
	
	// 비밀번호 수정
	@GetMapping("/myLibrary/updatePW")
	public String updatePW() {
		return "user/updatePW";
	}
	
	// 회원인증
	@GetMapping("/myLibrary/updateAuth")
	public String updateAuth() {
		return "user/updateAuth";
	}
	
	// 회원탈퇴
	@GetMapping("/myLibrary/withdraw")
	public String withdraw() {
		return "user/withdraw";
	}
	
	@PostMapping("/myLibrary/myInquiryWriteProc")
	public String myInquiryWriteProc(String title, String content) {
		String id = (String)session.getAttribute("id");
		service.myInquiryWriteProc(id, title, content);
		return "redirect:myInquiry";
	}
	
	@GetMapping("/myLibrary/myInfo")
	public String myInfo() {
		String id = (String)session.getAttribute("id");
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
	
	@PostMapping("/myLibrary/updatePwProc")
	public String updatePwProc(String currentPW, String newPW, String newConfirmPW) {
		String id = (String)session.getAttribute("id");
		String result = userService.updatePwProc(currentPW, newPW, newConfirmPW, id);
		if(result.equals("비밀번호가 변경되었습니다."))
			return "redirect:/main";
		return "user/myInfo";
	}
	
	@PostMapping("/myLibrary/updateAuthProc")
	public String updateAuthProc(String pw) {
		String id = (String)session.getAttribute("id");
		String result = userService.updateAuthProc(pw, id);
		if(result.equals("신청이 완료 되었습니다."))
			return "redirect:/main";
		return "user/myInfo";
	}
	
}
