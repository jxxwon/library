package com.care.library.reservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Service
public class ReserveService {
	
	@Autowired ReserveMapper userMapper;
	@Autowired HttpSession session;
	
	public String userCheck(String id) {
		 int usingUser = userMapper.usingUser(id); 
		 if(usingUser >= 1) 
			 return "이미 예약한 좌석이 존재합니다.";
		 return "예약 가능한 회원입니다.";
	}
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
	  
//	  public void getReservedSeat(Model model, String whichRoom) {
//		  //System.out.println("getRecentSeat : "+ whichRoom);
//		  ArrayList<String> reservedSeat = userMapper.getReservedSeat(whichRoom); 
//		  for(String seat: reservedSeat) {
//			  //System.out.println("seat : " + seat);
//		  }
//		  //System.out.println();
//		  model.addAttribute("reservedSeat", reservedSeat);
//	  }
	  
	  public ArrayList<String> getReservedSeat(String whichRoom) {
		  //System.out.println("getRecentSeat : "+ whichRoom);
		  ArrayList<String> reservedSeat = userMapper.getReservedSeat(whichRoom); 
		  for(String seat: reservedSeat) {
			  System.out.println("seat : " + seat);
		  }
		  System.out.println();
		  return reservedSeat;
	  }
	  
	  public void reservedSeatNum(Model model, String whichRoom) {
		  System.out.println("getRecentSeat : "+ whichRoom);
		  int reservedNum = userMapper.reservedNum(whichRoom);
		  model.addAttribute("reservedNum", reservedNum);
	  }
	  
	
}




