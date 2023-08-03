package com.care.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.library.member.MemberDTO;
import com.care.library.member.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired HttpSession session;
	@Autowired private MemberService service;
	
	@RequestMapping("/myLibrary/myInfo")
	public String myInfo(Model model) {
		String id = (String)session.getAttribute("id");
		//MemberDTO myInfo = service.getMyInfo(id);
		/*
		 * if(myInfo != null) { model.addAttribute("name", myInfo.getName());
		 * model.addAttribute("id", myInfo.getId()); model.addAttribute("email",
		 * myInfo.getEmail()); }
		 */
		return "user/myInfo";
	}
	
	@RequestMapping("subMenuMyLibrary")
	public String subMenuMyLibrary() {
		return "user/subMenuMyLibrary";
	}
	
}
