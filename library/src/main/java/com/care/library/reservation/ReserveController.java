package com.care.library.reservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.library.member.MemberService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Extension.Parameter;

@Controller
public class ReserveController {
	@Autowired HttpSession session;
	@Autowired private ReserveService service;
	
	//마이 라이브러리(첫페이지) - container
	@GetMapping("/reservation")
	public String reservation() {
		return "reservation/reservationMain";
	}
	
	@GetMapping("/reservation/readingRoom1")
	public String readingRoom1() {
		return "reservation/readingRoom1";
	}
	
	@GetMapping("/reservation/readingRoom2")
	public String readingRoom2() {
		return "reservation/readingRoom2";
	}
	
	@GetMapping("/reservation/studyRoom")
	public String studyRoom() {
		return "reservation/studyRoom";
	}
	@GetMapping("/reservation/roomPopUp")
	public String roomPopUp() {

		return "reservation/roomPopUp";
	}
	
	@ResponseBody
	@PostMapping("/reservation/reserveProc")
	public void reserveProc(@RequestBody ReserveDTO reqData) {
		String id = (String)session.getAttribute("id");
		String name = (String)session.getAttribute("name");
		System.out.println("여기"+reqData.getRoom());
		
		 
		 Date nowDate = new Date();
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
     	 //원하는 데이터 포맷 지정
		 String strNowDate = simpleDateFormat.format(nowDate); 
     	 //지정한 포맷으로 변환 
		 reqData.setReserveDate(strNowDate);
		 
		 reqData.setUserId(id);
		 
		 String result = service.reservation(reqData);
		 
	}

}
