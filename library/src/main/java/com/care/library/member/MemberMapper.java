package com.care.library.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	MemberDTO loginProc(String id);
	MemberDTO emailCheck(String email); //카카오 로그인시, 기존 회원 여부를 알기 위해.
	
	//카카오 로그인 시도시, 카카오 회원가입 여부 확인을 위해.
	MemberDTO kakaoIDCheck(String kakaoID); 
	void kakaoRegisterProc(MemberDTO member);
}