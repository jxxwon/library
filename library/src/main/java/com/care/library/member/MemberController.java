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
	@Autowired private KakaoService kakaoService;
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
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("loginProc")
	public String loginProc(String id){
		MemberDTO result = service.loginProc(id);
		if(result == null) {
			return "redirect:login";
		}
		session.setAttribute("id", result.getId());
		session.setAttribute("name", result.getName());
		return "redirect:main";
	}
	
	@RequestMapping("register")
	public String register() {
		return "member/register";
	}
	
	@RequestMapping("register1")
	public String register1() {
		return "member/register1";
	}

	@RequestMapping("register2")
	public String register2() {
		return "member/register2";
	}

	// 이메일 인증 화면
	@RequestMapping("register2_2")
	public String register2_2() {
		return "member/register2_2";
	}
	
	// 이메일 인증 프로세스
	@PostMapping("authProc")
	public String authProc() {
		return "member/register3";
	}
	
	@RequestMapping("register3")
	public String register3() {
		return "member/register3";
	}
	
	//카카오 로그인
	@Autowired private KakaoService kakao;
	
	@GetMapping("kakaoRegister")
	public String kakaoRegister() {
		return "member/kakaoRegister";
	}
	
	@PostMapping("kakaoRegisterProc")
	public String kakaoRegisterProc(MemberDTO member, String confirm) {
		String result = kakaoService.kakaoRegisterProc(member, confirm);
		if(result.equals("회원 등록 완료")) {
			return "redirect:main";
		}
		return "member/kakaoRegister";
	}
	
	@GetMapping("kakaoLogin")
	public String kakaoLogin(String code) {
		System.out.println("code : " + code);
		kakao.getAccessToken(code);
		String res = kakao.getUserInfo();
		if(res.equals("카카오 연동 회원가입"))
			return "redirect:kakaoRegister";
		return "redirect:main";
	}
	
	@GetMapping("kakaoLogout")
	public String kakaoLogout() {
		kakao.unLink();
		return "redirect:main";
	}
}
