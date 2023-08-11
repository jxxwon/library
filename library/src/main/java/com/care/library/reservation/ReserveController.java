package com.care.library.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.library.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReserveController {
	@Autowired HttpSession session;
	@Autowired private ReserveService service;
	
	//마이 라이브러리(첫페이지) - container
	@RequestMapping("/reservation")
	public String reservation() {
		return "reservation/reservationMain";
	}

//	@PostMapping("/myLibrary/withdrawProc")
//	public String withdrawProc(String pw) {
//		String id = (String)session.getAttribute("id");
//		String result = service.deleteMember(id, pw);
//		System.out.println("result"+ result);
//		if(result.equals("회원 탈퇴가 완료되었습니다.")) {
//			session.invalidate();
//			return "redirect:/main";
//		}
//		return "user/withdraw";
//	}
	
}
