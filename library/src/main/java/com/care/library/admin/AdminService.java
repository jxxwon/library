package com.care.library.admin;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.library.member.MemberDTO;

@Service
public class AdminService {
	@Autowired AdminMapper mapper;

	public void selectMember(Model model) {
		ArrayList<MemberDTO> members = mapper.selectMember();
		model.addAttribute("members", members);
	}

}
