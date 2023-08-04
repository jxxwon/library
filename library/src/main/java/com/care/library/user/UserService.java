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
		int check = userMapper.changeMyInfoProc(myInfo);
		if(check == 1)
			return "정보가 수정되었습니다.";
		return "수정에 실패했습니다.";
	}
	
	public String updatePwProc(UserDTO myInfo, String newConfirmPW) {
		// 기존 비밀번호랑 적은 비밀번호가 맞는지 확인하기
		int currentPw = userMapper.updatePwProc(myInfo);
			// O => 적은 비밀번호랑 confirmPW가 같은지 확인
				// O => "비밀번호가 변경되었습니다."
				// X => "비밀번호 변경에 실패했습니다."
			// X => "비밀번호를 확인해주세요."
		
		
		if(check == 1)
			return "정보가 수정되었습니다.";
		return "수정에 실패했습니다.";
	}

}




