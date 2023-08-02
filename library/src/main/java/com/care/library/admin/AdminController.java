package com.care.library.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired HttpSession session;
	@Autowired AdminService service;
	
	@RequestMapping("subMenuAdmin")
	public String subMenuAdmin() {
		return "admin/subMenuAdmin";
	}
	
	@RequestMapping("adminMember")
	public String adminMember(Model model) {
		String id = (String)session.getAttribute("id");
		if(id.equals("admin") == false) {
			return "redirect:main";
		}
		service.selectMember(model);
		return "admin/adminMember";
	}
}
