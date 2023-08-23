package com.care.library.admin;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.care.library.member.MemberDTO;
import com.care.library.user.InquiryDTO;

@Mapper
public interface AdminMapper {

	int count();

	int countUser(String memberSelect);

	ArrayList<MemberDTO> selectMember(String memberSelect, int begin, int end);

	ArrayList<MemberDTO> selectAllMember(int begin, int end);

	int countMember(@Param("searchSelect")String searchSelect, @Param("search")String search);

	MemberDTO selectUser(String id);

	void memberConfirm(String id, String userGroup, String paper, String authDate);

	void memberReject(String id, String reject);

	ArrayList<MemberDTO> searchMember(@Param("searchSelect")String searchSelect, @Param("search")String search, int begin, int end);
	
	ArrayList<MemberDTO> searchMemberDiv(@Param("memberSelect")String memberSelect, @Param("searchSelect")String searchSelect, @Param("search")String search, int begin, int end);
	
	int countMemberDiv(@Param("memberSelect")String memberSelect, @Param("searchSelect")String searchSelect, @Param("search")String search);

	ArrayList<InquiryDTO> selectInquiry(int begin, int end);

	int countInquiry();

	InquiryDTO selectInquiryContent(int no);

	void replyWrite(int no, String content);

	int countInquiryReply(String replySelect);

	ArrayList<InquiryDTO> selectInquiryReply(String replySelect, int begin, int end);

	int countInquiryTitle(String search);

	ArrayList<InquiryDTO> selectInquiryTitle(String search, int begin, int end);

	int countInquiryWriter(String search);

	ArrayList<InquiryDTO> selectInquiryWriter(String search, int begin, int end);



}
