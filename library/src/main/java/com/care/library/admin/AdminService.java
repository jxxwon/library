package com.care.library.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.library.common.PageService;
import com.care.library.member.MemberDTO;

@Service
public class AdminService {
	@Autowired AdminMapper mapper;

	public void selectMember(String cp, String memberSelect, Model model) {
		if(memberSelect == null) {
			memberSelect = "R";
		}
		
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<MemberDTO> members = mapper.selectMember(memberSelect, begin, end);
		
		String url = "member?currentPage=";
		int totalCount = mapper.count();
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("members", members);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}

	public void selectUser(String id, Model model) {
		MemberDTO result = mapper.selectUser(id);
		
		model.addAttribute("member", result);
	}

}
