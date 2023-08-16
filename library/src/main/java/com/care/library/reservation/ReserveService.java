package com.care.library.reservation;

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

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ReserveService {
	
	@Autowired ReserveMapper userMapper;
	@Autowired HttpSession session;
	  public String reservation(ReserveDTO resevedData) {
		  if(resevedData.getRoom().equals("자율 학습실2")) {
			  resevedData.setRoom("R2"); 
		  } else if(resevedData.getRoom().equals("자율 학습실1")) {
			  resevedData.setRoom("R1");
		  }else {
			  resevedData.setRoom("SR"); //Study Room
		  }
		  int result = userMapper.reservation(resevedData); 
		  if(result == 1) {
			  
			  return "예약이 완료되었습니다.";
		  }
		  return "예약이 정상적으로 이루어지지 않았습니다.";
	  }
	  
	  public void getReservedSeat(Model model, String whichRoom) {
		  System.out.println("getRecentSeat : "+ whichRoom);
		  ArrayList<String> reservedSeat = userMapper.getReservedSeat(whichRoom); 
		  for(String seat : reservedSeat) {
			  System.out.println(seat);
		  }
		  model.addAttribute("reservedSeat", reservedSeat);
	  }
	  
	  
//	  public String changeMyInfoProc(UserDTO myInfo) {
//		  int check = userMapper.changeMyInfoProc(myInfo);
//		  if(check == 1) return "정보가 수정되었습니다.";
//	  return "수정에 실패했습니다."; 
//	  }
	  
	
}




