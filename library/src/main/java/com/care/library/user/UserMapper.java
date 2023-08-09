package com.care.library.user;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	UserDTO getMyInfo(String id);
	
	int changeMyInfoProc(UserDTO myInfo);
	
	int findMaxNum();

	int myInquiryWrite(InquiryDTO inquiry);

	ArrayList<InquiryDTO> selectInqiry(String id, int begin, int end);

	int count();
	
	String currentPwCheck(String id);
	
	int updatePw(String newPW, String id);
	
	int updateAuth(String id);
	
	int updateId(String id, String delId);
}