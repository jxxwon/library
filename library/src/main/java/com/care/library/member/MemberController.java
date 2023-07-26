package com.care.library.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
@Controller
public class MemberController {
	@Autowired private MemberService service;
	@Autowired private HttpSession session;
	
	@RequestMapping("header")
	public String header() {
		return "default/header";
	}

	@RequestMapping("subMenuLogin")
	public String subMenu() {
		return "default/subMenuLogin";
	}

	@RequestMapping("footer")
	public String footer() {
		return "default/footer";
	}

	@RequestMapping("main")
	public String main() {
		return "default/main";
	}
	
	@PostMapping("login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("register")
	public String register() {
		return "member/register";
	}
	
}
