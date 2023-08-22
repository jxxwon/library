package com.care.library.reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReserveController {
	@Autowired HttpSession session;
	@Autowired private ReserveService service;
	
	//마이 라이브러리(첫페이지) - container
	@RequestMapping("/reservation")
	public String reservation() {
	    
	    return "reservation/reservationMain";
	}
	
	@ResponseBody//return을 jsp가 아닌 응답 데이터를 주는 것이다.
	 @PostMapping(value = "reservation/room", produces = "application/json; charset=UTF-8")
    public Object room(@RequestBody(required = false) String whichRoom) {
        System.out.println("reservation : " + whichRoom);
        	ArrayList<String> reservedSeat = service.getReservedSeat(whichRoom);
        	for(String seat: reservedSeat) {
        		//System.out.println(seat);
        	}
        	return reservedSeat;
    }
	
	@GetMapping("/reservation/readingRoom1")
	public String readingRoom1(Model model, RedirectAttributes ra) {
		String whichRoom = "R1";
		service.reservedSeatNum(model, whichRoom, ra);
		
		return "reservation/readingRoom1";
	}
	
	@GetMapping("/reservation/readingRoom2")
	public String readingRoom2(Model model,  RedirectAttributes ra) {
		String whichRoom = "R2";
		service.reservedSeatNum(model,whichRoom, ra);
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
	public String reserveProc(@RequestBody ReserveDTO reqData,  RedirectAttributes ra) {
		//ReserveDTO에 예약 정보 입력.
		String id = (String)session.getAttribute("id");
		reqData.setUserId(id);
		
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
		String strNowDate = simpleDateFormat.format(nowDate); 
		reqData.setReserveDate(strNowDate);
		
		//이미 예약한 좌석이 있는 회원인지 체크.
		String userCheck = service.userCheck(id);
		
		if(userCheck.equals("이미 다른 좌석을 예약하셨습니다.")) {
			return userCheck;
		}
		 
		 String result = service.reservation(reqData);
		 return result;
	}

}
