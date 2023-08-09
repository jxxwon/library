package com.care.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.library.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired HttpSession session;
	@Autowired private UserService service;
	
	//마이 라이브러리(첫페이지) - container
	@GetMapping("/myLibrary")
	public String myLibrayMain() {
		return "user/myLibraryMain";
	}
	
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
	public String myInquiry(@RequestParam(value="currentPage", required = false)String cp, 
			@RequestParam(value="select", required = false)String select, @RequestParam(value="search", required = false)String search, 
			@RequestParam(value="replySelect", required = false) String replySelect, Model model) {
		
		String id = (String)session.getAttribute("id");
		if(id == null || id.equals("")) {
			return "redirect:main";
		}
		System.out.println("select : " + select);
		System.out.println("search : " + search);
		System.out.println("replySelect : " + replySelect);
		
		//초기 화면 및 검색조건에 제목으로 해놓고 검색어 입력 안 하면 전체 조회
		if(select == null || (select.equals("title") && search==null)) {
			service.selectInquiry(cp, id, model);
		} else if(select.equals("title") && search != null) {
			service.selectInquiry(cp, search, id, model);
		}
		
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
	
	// 회원정보 - container
	@GetMapping("/myLibrary/myInfo")
	public String myInfo() {
		String id = (String)session.getAttribute("id");
		return "user/myInfo";
	}
	
	// 회원정보 - 회원정보 수정
	@GetMapping("/myLibrary/updateInfo")
	public String updateInfo(Model model) {
		String id = (String)session.getAttribute("id");
		UserDTO myInfo = service.getMyInfo(id);
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
	
	@PostMapping("/myLibrary/changeMyInfoProc")
	public String changeMyInfoProc(UserDTO myInfo, RedirectAttributes ra) {
		String id = (String)session.getAttribute("id");
		myInfo.setId(id);
		
		String result = service.changeMyInfoProc(myInfo);
		if(result.equals("정보가 수정되었습니다.")) {
			ra.addFlashAttribute("updateMsg", result);
			return "redirect:/main";
		}
		ra.addFlashAttribute("updateMsg", result);
		return "user/myInfo";
	}
	
	// 회원정보 - 비밀번호 수정
	@GetMapping("/myLibrary/updatePW")
	public String updatePW() {
		return "user/updatePW";
	}
	
	@PostMapping("/myLibrary/updatePwProc")
	public String updatePwProc(String currentPW, String newPW, String newConfirmPW) {
		String id = (String)session.getAttribute("id");
		String result = service.updatePwProc(currentPW, newPW, newConfirmPW, id);
		if(result.equals("비밀번호가 변경되었습니다."))
			return "redirect:/main";
		return "user/myInfo";
	}
	
	// 회원정보 - 회원인증
	@GetMapping("/myLibrary/updateAuth")
	public String updateAuth() {
		return "user/updateAuth";
	}
	
	@PostMapping("/myLibrary/updateAuthProc")
	public String updateAuthProc(String pw) {
		String id = (String)session.getAttribute("id");
		String result = service.updateAuthProc(pw, id);
		if(result.equals("신청이 완료 되었습니다."))
			return "redirect:/main";
		return "user/myInfo";
	}
	
	// 회원정보 -  회원탈퇴
	@GetMapping("/myLibrary/withdraw")
	public String withdraw() {
		return "user/withdraw";
	}
	
	@PostMapping("withdrawProc")
	public String withdrawProc(String pw) {
		String id = (String)session.getAttribute("id");
		String result = service.deleteMember(id, pw);
		if(result.equals("회원 탈퇴가 완료되었습니다.")) {
			session.invalidate();
			return "redirect:main";
		}
		return "user/withdraw";
	}
	
}
