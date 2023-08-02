package com.care.library.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired HttpSession session;
	
	@RequestMapping("subMenuAdmin")
	public String subMenuAdmin() {
		return "admin/subMenuAdmin";
	}
	
	@RequestMapping("adminMember")
	public String adminMember() {
		String id = (String)session.getAttribute("id");
		if(id.equals("admin") == false) {
			return "member/main";
		}
		return "admin/adminMember";
	}
}
