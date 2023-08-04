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
	
	public String updatePwProc(String currentPW, String newPW, String newConfirmPW, String id) {
		// 기존 비밀번호랑 currentPW가 맞는지 확인하기
		String dataCurrentPw = userMapper.currentPwCheck(id);
		if(dataCurrentPw != null) {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			if(bpe.matches(currentPW, dataCurrentPw)) {
				// O => newPW랑 newConfirmPW가 같은지 확인
				if(newPW.equals(newConfirmPW)) {
					// O => "비밀번호가 변경되었습니다."
					String cryptPassword = bpe.encode(newPW);
					int result = userMapper.updatePw(cryptPassword, id);
					if(result == 1 )
						return "비밀번호가 변경되었습니다.";
					return "비밀번호 변경에 실패했습니다.";
				}
				// X => "비밀번호를 확인해주세요."
				return "비밀번호를 확인해주세요.";
			}
		}
		// X => "비밀번호 변경에 실패했습니다."
		return "비밀번호 변경에 실패했습니다.";
	}
	
	public String updateAuthProc(String pw, String id) {
		String dataPw = userMapper.currentPwCheck(id);
		if(dataPw != null) {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			if(bpe.matches(pw, dataPw)) {
				int result = userMapper.updateAuth(id);
				if(result == 1 )
					return "신청이 완료 되었습니다.";
			}
		}
		return "요청을 실패했습니다.";
	}

}




