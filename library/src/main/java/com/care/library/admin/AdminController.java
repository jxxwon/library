package com.care.library.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.library.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired HttpSession session;
	@Autowired AdminService service;
	
	@RequestMapping("/admin")
	public String admin() {
		return "redirect:admin/member";
	}
	
	@RequestMapping("subMenuAdmin")
	public String subMenuAdmin() {
		return "admin/subMenuAdmin";
	}
	
	@RequestMapping("admin/member")
	public String adminMember(@RequestParam(value="currentPage", required = false)String cp, @RequestParam(value="memberSelect", required = false)String memberSelect, Model model) {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		if(id == null || id.equals("")||status.equals("M") == false) {
			return "redirect:main";
		}
		service.selectMember(cp, memberSelect, model);
		return "admin/member";
	}
	
	@GetMapping("/memberConfirm")
	public String memberConfirm(String id, Model model) {
		service.selectUser(id, model);
		return "admin/memberConfirm";
	}
	
	@PostMapping("/memberConfirmProc")
	public String memberConfirmProc(MemberDTO member, String group) {
		System.out.println(group);
//		service.memberConfirm(model);
		return "redirect:admin/member";
	}
}
