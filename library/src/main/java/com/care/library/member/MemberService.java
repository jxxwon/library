package com.care.library.member;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class MemberService {
	
	@Autowired MemberMapper mapper;

	public MemberDTO loginProc(String id) {
		return mapper.loginProc(id);
	}
	
	@Autowired private MailService mailService;
	public String sendEmail(String email) {
		if(email == null || email.isEmpty())
			return "이메일을 확인 후 다시 입력하세요.";
		Random r = new Random();
	
		content = String.format("%06d", r.nextInt(1000000));
		System.out.println("인증번호 : " + content);
		String msg = mailService.sendMail(email, "인증번호가 도착했습니다", content);
		if(msg.equals("입력한 이메일에서 인증번호를 확인하세요.") == false) {
			content = "";
		}
		return msg;
	}
	private String content;
	
	public String sendAuth(String authNum) {
		if(authNum == null || authNum.isEmpty())
			return "인증번호를 입력 후 다시 시도하세요.";
		
		if(content == null || content.isEmpty())
			return "인증번호를 입력 후 다시 시도하세요.";
		
		if(authNum.equals(content)) {
			return "인증 성공";
		} 
			return "인증번호를 확인해주세요.";
	}
	
	public String exists(String id) {
		if(id == null || id.isEmpty())
			return "아이디를 입력하세요.";
		
		Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$");
		Matcher matcher = pattern.matcher(id);
		if(matcher.matches() == false)
			return "아이디는 6~12자리의 영문 또는 숫자 혼용, 특수 문자 제외";
		
		MemberDTO result = mapper.idCheck(id);
		if(result == null)
			return "사용 가능한 아이디 입니다.";
		return "사용 할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.";
	}
	
	public String registerProc(MemberDTO member, String confirm) {
		if(member.getId() == null || member.getId().isEmpty()) {
			return "아이디를 입력하세요.";
		}
		
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(member.getPw().equals(confirm) == false) {
			return "두 비밀번호를 일치하여 입력하세요.";
		}
		
		if(member.getName() == null || member.getName().isEmpty()) {
			return "이름을 입력하세요.";
		}

		MemberDTO result = mapper.loginProc(member.getId());
		if(result == null) {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			String cryptPassword = bpe.encode(member.getPw());
			member.setPw(cryptPassword);
			member.setStatus("D");
			mapper.registerProc(member);
			return "회원 등록 완료";
		}
		
		return "이미 가입된 아이디 입니다.";
	}

	public MemberDTO findMember(String authEmail) {
		return mapper.emailCheck(authEmail);
	}

	public String findPw(String id) {
		MemberDTO result = mapper.idCheck(id);
		if(result != null) {
			return "아이디가 확인되었습니다.";
		}
		return "등록되지 않은 아이디입니다.";
	}

	public String changePw(String authId, String changePw) {
		MemberDTO check = mapper.loginProc(authId);
		if(check != null) {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			String cryptPassword = bpe.encode(changePw);
			int result = mapper.changePw(authId, cryptPassword);
			if(result == 1) {
				return "비밀번호 변경이 완료되었습니다.";
			} else {
				return "비밀번호 변경에 실패하였습니다.";
			}
		}
		return "등록되지 않은 아이디입니다.";
	}

	public String deleteMember(String id, String pw) {
		if(pw == null || pw.equals("")) {
			return "비밀번호를 입력하세요.";
		}

		MemberDTO check = mapper.loginProc(id);
		if(check == null) {
			return "등록되지 않은 회원입니다.";
		}
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		if(bpe.matches(pw, check.getPw())) {
			Random r = new Random();
			String delId = String.format("%06d", r.nextInt(1000000));
			MemberDTO delChk = mapper.loginProc(delId);
			if(delChk != null) {
				return "탈퇴 중 오류가 발생하였습니다. 관리자에게 문의하세요.";
			} else {
				int result = mapper.updateId(id, delId);
				if(result != 1) {
					return "탈퇴 중 오류가 발생하였습니다. 관리자에게 문의하세요.";
				}
			}
		}
		return "회원 탈퇴가 완료되었습니다.";
	}
	public MemberDTO emailExists(String kakaoEmail) {
		return mapper.emailCheck(kakaoEmail);
	}

	public void myInquiryWriteProc(String id, String title, String content2) {
		int no;
		
	}
	

}




