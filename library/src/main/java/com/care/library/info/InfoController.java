package com.care.library.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String notice(@RequestParam(value="currentPage", required = false)String cp, @RequestParam(value="select", required = false)String select, @RequestParam(value="search", required = false)String search, Model model) {
		if(select == null && search == null) {
			service.selectAllNotice(cp, model);
		} else {
			service.selectNotice(cp, select, search, model);
		}
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
	
	@RequestMapping("/info/noticeContent")
	public String noticeContent(int no, Model model) {
		service.noticeContent(no, model);
		return "info/noticeContent";
	}
	
	@RequestMapping("/info/noticeFileDownload")
	public String noticeFileDownload(int no) {
		service.noticeFileDownload(no);
		return "info/noticeContent";
	}
	
	@RequestMapping("/info/noticeUpdate")
	public String noticeUpdate(int no, Model model) {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		
		if(id.isEmpty() || id == null || (status.equals("M")==false)) {
			return "redirect:/info/notice";
		}
		
		service.noticeUpdate(no, model);
		return "info/noticeUpdate";
	}

	@GetMapping("/info/noticeDelete")
	public String noticeDelete(int no) {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		
		if(id.isEmpty() || id == null || (status.equals("M")==false)) {
			return "redirect:/info/notice";
		}
		
		service.noticeDelete(no);
		return "redirect:/info/notice";
	}
	
	@RequestMapping("/info/faq")
	public String faq() {
		return "info/faq";
	}
	
	@RequestMapping("/info/faqList")
	public String faqList(String category, Model model) {
		if(category == null) {
			category = "member";
		}
		service.selectFaq(category, model);
		return "info/faqList";
	}
	
	@RequestMapping("/info/faqWriteForm")
	public String faqWriteForm() {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		
		if(id.isEmpty() || id == null || (status.equals("M")==false)) {
			return "redirect:/info/faq";
		}
		return "info/faqWriteForm";
	}
	
	@RequestMapping("/info/faqWriteProc")
	public String faqWriteProc(String category, String title, String content) {
		String id = (String)session.getAttribute("id");
		String status = (String)session.getAttribute("status");
		
		if(id.isEmpty() || id == null || (status.equals("M")==false)) {
			return "redirect:/info/faq";
		}
		
		service.faqWrite(category, title, content);
		return "redirect:/info/faq";
	}
	
	@RequestMapping("/info/faqContent")
	public String faqContent(int no, Model model) {
		service.faqContent(no, model);
		return "info/faqContent";
	}
	
	@RequestMapping("/info/faqUpdate")
	public String faqUpdate(int no, Model model) {
		service.faqContent(no, model);
		return "info/faqUpdate";
	}
	
	@PostMapping("/info/faqUpdateProc")
	public String faqUpdateProc(int no, String category, String title, String content) {
		service.faqUpdate(no, category, title, content);
		return "redirect:/info/faq";
	}
	
	@RequestMapping("/info/faqDelete")
	public String faqDelete(int no) {
		service.faqDelete(no);
		return "redirect:/info/faq";
	}
}
