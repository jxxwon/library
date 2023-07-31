package com.care.library.member;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}




