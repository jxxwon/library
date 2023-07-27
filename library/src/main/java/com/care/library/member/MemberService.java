package com.care.library.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired MemberMapper mapper;

	public MemberDTO loginProc(String id) {
		return mapper.loginProc(id);
	}
	


}




