package com.care.library.cultural;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpSession;

@Controller
public class CulturalController {
    @Autowired
    private CulturalService service;
    @Autowired 
    private HttpSession session;

    @RequestMapping("culForm")
    public String culForm(
            @RequestParam(value="currentPage", required = false) String cp,
            Model model) {
    	System.out.println("culFrom호출");
        service.culturalForm(cp, model);
        return "cultural/culForm"; // 뷰 이름 설정
    }
    
    @RequestMapping("cultural")
	public String cultural() {
		return "cultural/cultural";
	}
	
	@RequestMapping("culResult")
	public String culResult() {
		return "cultural/culResult";
	}
	
    @RequestMapping("culSubmit")
	public String culSubmit() {
		return "cultural/culSubmit";
	}
    
    @PostMapping("culFormWriteProc")
    public String culFormWriteProc(Model model, MultipartHttpServletRequest multi,
                                   @RequestParam("lectureStart") String lectureStart,
                                   @RequestParam("lectureEnd") String lectureEnd,
                                   @RequestParam("registrationStart") String registrationStart,
                                   @RequestParam("registrationEnd") String registrationEnd) {
        String msg = service.culFormWriteProc(multi, lectureStart, lectureEnd, registrationStart, registrationEnd);
        
		System.out.println("culFormWriteProc_Controller_Start");
		if(msg.equals("로그인"))
			return "redirect:login";
		
		if(msg.equals("게시글 작성 완료"))
			return "redirect:culForm";
		
		model.addAttribute("msg", msg);
		System.out.println("culFormWriteProc_Controller_End");
		return "cultural/culSubmit";
	}
    
}
