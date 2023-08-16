package com.care.library.common;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.library.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Service
public class NotifyService {
	@Autowired NotifyMapper mapper;
	@Autowired HttpSession session;

	public void register(NotifyDTO notification) {
		int no;
		try {
			no = mapper.findMaxNum();
		} catch (Exception e) {
			no = 0;
		}
		notification.setNo(no+1);
		mapper.insert(notification);
	}
	
	public void add(NotifyDTO notification) {
		int no;
		try {
			no = mapper.findMaxNum();
		} catch (Exception e) {
			no = 0;
		}
		notification.setNo(no+1);
		mapper.insert(notification);
		
		/*status가 M인 아이디에도 알림 추가*/
		MemberDTO admin = mapper.findAdmin(); 
		String category = notification.getCategory();
		NotifyDTO adminNoti = new NotifyDTO();
		adminNoti.setId(admin.getId());
		adminNoti.setNo(no+1);
		adminNoti.setCategory(notification.getCategory());
		adminNoti.setTitle(notification.getTitle());
		if(category.equals("회원")) {
			adminNoti.setUrl("member");
		}
		mapper.insert(adminNoti);
	}
	
	public void selectAll(Model model) {
		String id = (String)session.getAttribute("id");
		if (id != null) {
			ArrayList<NotifyDTO> notify = mapper.selectAll(id);
			if(notify.isEmpty() == false) {
				model.addAttribute("notify", notify);
			}
		}
	}

}
