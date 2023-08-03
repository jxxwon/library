package com.care.library.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	UserDTO getMyInfo(String id);
	
	int changeMyInfoProc(UserDTO myInfo);

	int findMaxNum();

	int myInquiryWrite(InquiryDTO inquiry);
}