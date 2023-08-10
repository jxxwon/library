package com.care.library.cultural;

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
    
	/*
	 * @PostMapping("/submit-date") // 해당 URL로 클라이언트에서 POST 요청을 보냈을 때 처리
	 * 
	 * @ResponseBody public String submitDate(@RequestBody CulturalDTO culturalDTO,
	 * MultipartHttpServletRequest multi) { // 날짜 데이터 처리를 위해 서비스 클래스의 메서드 호출 String
	 * result = service.culFormWriteProc(culturalDTO, multi); return result; }
	 */
    
	@PostMapping("culFormWriteProc")
	public String culFormWriteProc(Model model, MultipartHttpServletRequest multi) {
		String msg = service.culFormWriteProc(multi);
		if(msg.equals("로그인"))
			return "redirect:login";
		
		if(msg.equals("게시글 작성 완료"))
			return "redirect:culForm";
		
		model.addAttribute("msg", msg);
		return "culForm/culSubmit";
	}

}
