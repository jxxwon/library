package com.care.library.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.library.member.MailService;
import com.care.library.member.MemberDTO;
import com.care.library.member.MemberMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
	
	@Autowired MemberMapper mapper;

	public MemberDTO loginProc(String id) {
		return mapper.loginProc(id);
	}
	/*
	 * public MemberDTO getMyInfo(String id) { return mapper.getMyInfo(id); }
	 */

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



	

}




