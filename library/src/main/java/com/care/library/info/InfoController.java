package com.care.library.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpSession;

@Controller
public class InfoController {

	@Autowired HttpSession session;
	@Autowired InfoService service;
	
	@RequestMapping("subMenuInfo")
	public String subMenuInfo() {
		return "info/subMenuInfo";
	}
	
	@RequestMapping("/info/notice")
	public String notice() {
		return "info/notice";
	}
	
	@RequestMapping("/info/noticeWriteForm")
	public String noticeWriteForm() {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		
		if(id.isEmpty() || id == null || (status.equals("M")==false)) {
			return "redirect:/info/notice";
		}
		
		return "info/noticeWriteForm";
	}
	
	@PostMapping("/info/noticeWriteProc")
	public String noticeWriteProc(MultipartHttpServletRequest multi) {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		
		if(id.isEmpty() || id == null || (status.equals("M")==false)) {
			return "redirect:/info/notice";
		}
		
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		MultipartFile file = multi.getFile("upfile");
		
		service.noticeWrite(id, title, content, file);
		
		return "redirect:/info/notice";
	}
}
