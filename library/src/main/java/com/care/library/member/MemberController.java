package com.care.library.member;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.library.reservation.ReserveMapper;
import com.care.library.reservation.ReserveService;
import com.care.library.search.SearchMapper;
import com.care.library.search.SearchService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class MemberController {
	@Autowired private MemberService service;
	@Autowired private KakaoService kakaoService;
	@Autowired private ReserveMapper reserveMapper;
	@Autowired private SearchService searchService;
	@Autowired private HttpSession session;
	
	@RequestMapping("header")
	public String header() {
		int closedRoom = reserveMapper.closeRoomStatus();
		session.setAttribute("closedRoom", closedRoom); //닫혔다면 1이 들어갈것
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

	@RequestMapping("subMenuCultural")
	public String subMenuCultural() {
		return "cultural/subMenuCultural";
	}
	
	@RequestMapping("main")
	public String main(Model model) {
		service.mainNotice(model);
		service.mainReadingRoom(model);
		searchService.getBookImages(model,"popularBook");
		searchService.getBookImages(model,"recentBook");
		
		return "default/main";
	}
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("loginProc")
	public String loginProc(String id, String pw, @RequestBody(required = false)String checkbox, RedirectAttributes ra, Model model){
		String result = service.loginProc(id, pw);
		if(result.equals("로그인 성공")) {
			String name = service.getNameById(id);
			if(checkbox.contains("on")) { // 아이디 저장에 체크하면, checkbox가 "on"으로 넘어옴.(하지만 다른 파라미터 같이옴.)
				session.setAttribute("savedId", id);
			}else {
				session.removeAttribute("savedId");
			}
			ra.addFlashAttribute("msg", result);
//			session.setAttribute("id", id);
//			session.setAttribute("name", name);
			return "redirect:main";
		}
		model.addAttribute("msg", result);
		System.out.println(result);
		return "member/login";
	}
	
	@RequestMapping("logout")
	public String logout() {
		//session.invalidate();
		if(session.getAttribute("savedId") == null || session.getAttribute("savedId").equals(""))
			session.removeAttribute("savedId");
		session.removeAttribute("id");
		session.removeAttribute("name");
		session.removeAttribute("kakaoID");
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
	
	@ResponseBody
	@PostMapping(value="sendEmail", produces = "text/plain; charset=utf-8")
	public String sendEmail(@RequestBody(required = false) String email) {
		return service.sendEmail(email);
	}
	
	@ResponseBody
	@PostMapping(value="sendAuth", produces = "text/plain; charset=utf-8")
	public String sendAuth(@RequestBody(required = false) String authNum) {
		String result = service.sendAuth(authNum);
		return result;
	}
	
	//회원가입(정보입력)
	@GetMapping("register3")
	public String register3() {
		return "member/register3";
	}

	@RequestMapping("register4")
	public String register4() {
		return "member/register4";
	}
	
	@PostMapping("registerProc")
	public String registerProc(MemberDTO member, String confirm, Model model) {
		String result = service.registerProc(member, confirm);
		if(result.equals("회원 등록 완료")) {
			System.out.println("회원 등록 완료 된듯");
			return "redirect:login";
		}
		System.out.println("회원 등록 안됨");
		return "member/register";
	}
	
	@ResponseBody //return을 jsp가 아닌 응답 데이터를 주는 것이다.
	@PostMapping(value="exist", produces = "text/plain; charset=UTF-8")
	public String idCheck(@RequestBody(required = false) String id) {
		//System.out.println("입력한 아이디 : " + id);
		return service.exists(id);
	}
	
	
	//카카오 로그인
	@Autowired private KakaoService kakao;
	
	@GetMapping("kakaoRegister")
	public String kakaoRegister() {
		// 일반 회원 여부 확인(이메일 중복 여부로 확인)
		//String kakaoEmail = (String)session.getAttribute("kakaoEmail");
		//MemberDTO existedMember = service.emailExists(kakaoEmail);
//		if(existedMember != null) { //일반 회원
//			//일반 회원이면 카카오 연동할꺼냐고 물어봐야지
//			 //String alertScript = "<script>confirm('이미 일반 회원으로 등록되어 있습니다.');</script>";
//			 //System.out.println(alertScript);
//			 //카카오 연동한다. =>
//			 //카카오 연동 안한다.
//			return "redirect:main";
//		}
		 //일반 회원 아니다. => 회원가입 진행시켜!
		return "member/kakaoRegister";
	}
	
	@PostMapping("kakaoRegisterProc")
	public String kakaoRegisterProc(MemberDTO member, String confirm, RedirectAttributes ra) {
		String result = kakaoService.kakaoRegisterProc(member, confirm);
		
		if(result.equals("회원 등록 완료")) {
			ra.addFlashAttribute("msg", result);
			return "redirect:login";
		}
		return "member/kakaoRegister";
	}
	
	@GetMapping("kakaoLogin")
	public String kakaoLogin(String code,  RedirectAttributes ra) {
		System.out.println("code : " + code);
		kakao.getAccessToken(code); //AccessToken으로 접근 권한 받기
		MemberDTO kakaoMember = kakao.kakaoExist();
		if(kakaoMember == null) 
			return "redirect:kakaoRegister";
		
		System.out.println(kakaoMember.getKakaoid());
		//카카오 아이디로 로그인을 해야죠?
		ra.addFlashAttribute("msg", "카카오 로그인 완료");
		session.setAttribute("kakaoID", kakaoMember.getKakaoid());
		session.setAttribute("id", kakaoMember.getId());
		session.setAttribute("name", kakaoMember.getName());
		return "redirect:main";
	}
	
	@GetMapping("kakaoLogout")
	public String kakaoLogout(RedirectAttributes ra) {
		kakao.unLink();
		return "redirect:main";
	}
	
	// 아이디 찾기
	@RequestMapping("findMemberId")
	public String findMemberId() {
		return "member/findMemberId";
	}
	
	// 아이디 찾기 - 이메일 인증
	@GetMapping("findMemberIdMail")
	public String findMemberIdMail() {
		return "member/findMemberIdMail";
	}
	
	// 아이디 찾기 - 이메일 인증 결과
	@PostMapping("findMemberIdMailResult")
	public String findMemberIdMailResult(String authEmail, Model model) {
		MemberDTO result = service.findMember(authEmail);
		if(result != null) {
			String name = result.getName();
			String id = result.getId();
			String email = result.getEmail();
			model.addAttribute("name", name);
			model.addAttribute("id", id);
			model.addAttribute("email", email);
		}
		return "member/findMemberIdMailResult";
	}
	
	// 비밀번호 찾기
	@RequestMapping("findMemberPw")
	public String findMemberPw() {
		return "member/findMemberPw";
	}
	
	@RequestMapping("findMemberPwMail")
	public String findMemberPwMail() {
		return "member/findMemberPwMail";
	}
	
	// 비밀번호 찾기 - 아이디 여부 체크
	@ResponseBody 
	@PostMapping(value="findPw", produces = "text/plain; charset=UTF-8")
	public String findPw(@RequestBody(required = false) String authId) {
		return service.findPw(authId);
	}

	// 비밀번호 찾기 - 이메일 인증 결과
	@PostMapping("findMemberPwMailResult")
	public String findMemberPwMailResult(String authId, String authEmail, Model model) {
		model.addAttribute("authId", authId);
		return "member/findMemberPwMailResult";
	}
	
	@PostMapping("changePw")
	public String changePw(String authId, String changePw, String confirmChangePw) {
		String result = service.changePw(authId, changePw);
		if(result.equals("비밀번호 변경이 완료되었습니다.")) {
			return "redirect:login";
		}
		return "member/findMemberPwMailResult";
	}
	
	// 회원탈퇴
	@GetMapping("deleteMember")
	public String deleteMember() {
		String id = (String)session.getAttribute("id");
		if(id == null) {
			return "redirect:login";
		}
		return "member/deleteMember";
	}
	
	@PostMapping("deleteMemberProc")
	public String deleteMemberProc(String pw) {
		String id = (String)session.getAttribute("id");
		String result = service.deleteMember(id, pw);
		if(result.equals("회원 탈퇴가 완료되었습니다.")) {
			session.invalidate();
			return "redirect:main";
		}
		return "member/deleteMember";
	}

}

