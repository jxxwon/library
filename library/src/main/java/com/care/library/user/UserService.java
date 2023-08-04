package com.care.library.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.library.common.PageService;
import com.care.library.member.MailService;
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
		return "수정에 실패했습니다.";
	}

	public void myInquiryWriteProc(String id, String title, String content2) {
		int no;
		try {
			no = userMapper.findMaxNum();
		} catch (Exception e) {
			no = 0;
		}
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String writeDate = sdf.format(new Date());
	
		InquiryDTO inquiry = new InquiryDTO();
		inquiry.setNo(no+1);
		inquiry.setTitle(title);
		inquiry.setContent(content2);
		inquiry.setId(id);
		inquiry.setWriteDate(writeDate);
		inquiry.setReply("N");
		
		userMapper.myInquiryWrite(inquiry);
	}


	public void selectInquiry(String cp, String id, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<InquiryDTO> inquiries = userMapper.selectInqiry(id, begin, end);
		
		String url = "myInquiry?currentPage=";
		int totalCount = userMapper.count();
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);

		model.addAttribute("inquiries", inquiries);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}
	
	public void selectInquiry(String cp, String search, String id, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		System.out.println(search);
//		ArrayList<InquiryDTO> inquiries = userMapper.selectInqiry(id, search, begin, end);
//		
//		String url = "myInquiry?currentPage=";
//		int totalCount = userMapper.count();
//		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
//		
//		model.addAttribute("inquiries", inquiries);
//		model.addAttribute("result", result);
//		model.addAttribute("currentPage", currentPage);
	}
	
}




