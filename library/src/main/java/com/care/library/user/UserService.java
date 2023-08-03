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
	
	@Autowired UserMapper userMapper;

	public UserDTO getMyInfo(String id) { 
		return userMapper.getMyInfo(id); 
		}
	

	public String changeMyInfoProc(UserDTO myInfo) {
		System.out.println("4");
		int check = userMapper.changeMyInfoProc(myInfo);
//		MemberDTO check = mapper.loginProc(authId);
//		if(check != null) {
//			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
//			String cryptPassword = bpe.encode(changePw);
//			int result = mapper.changePw(authId, cryptPassword);
//			if(result == 1) {
//				return "비밀번호 변경이 완료되었습니다.";
//			} else {
//				return "비밀번호 변경에 실패하였습니다.";
//			}
//		}
		if(check == 1)
			return "정보가 수정되었습니다.";
		System.out.println("5");
		return "수정에 실패했습니다.";
	}

}




