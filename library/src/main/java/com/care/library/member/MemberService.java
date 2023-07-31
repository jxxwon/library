package com.care.library.member;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class MemberService {
	
	@Autowired MemberMapper mapper;

	public MemberDTO loginProc(String id) {
		return mapper.loginProc(id);
	}
	
	public String exists(String id) {
		if(id == null || id.isEmpty())
			return "아이디를 입력하세요.";
		
		Pattern pattern = Pattern.compile("^[a-z0-9]{1}[a-z0-9_-]{4,19}$");
		Matcher matcher = pattern.matcher(id);
		if(matcher.matches() == false)
			return "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		
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
		/*
		System.out.println("암호화된 비밀번호 : " + cryptPassword);
		System.out.println("암호화된 비밀번호 길이 : " + cryptPassword.length());
		System.out.println("평문 비밀번호 : " + member.getPw());
		
		 암호화된 비밀번호 : $2a$10$.EOushkIDT8Gnb33i6NOSuS32ymKWipIvLCKeVlwGR20UWJYRYWEm
		 암호화된 비밀번호 길이 : 60
		 평문 비밀번호 : 1111
		 ALTER TABLE session_quiz MODIFY pw varchar2(60);
		 */
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


}




