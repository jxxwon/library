package com.care.library.common;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Service
public class NotifyService {
	@Autowired NotifyMapper mapper;
	@Autowired HttpSession session;
	
	public void noticitation(Model model) {
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
