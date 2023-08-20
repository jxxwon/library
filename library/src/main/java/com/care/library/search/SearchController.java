package com.care.library.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableScheduling
@Controller
public class SearchController {
	
	@Autowired SearchService service;
	
	@RequestMapping("/datasearch")
	public String datasearch(Model popularBook) {
		
		// 공공 API를 사용하여 xml형태의 데이터 받아오기. 매월 1일에 받아오기.
		
		String result = service.getBookImages(popularBook);
		if(result.equals("이미지 가져오기 완료"))
			return "search/searchMain";
		
		String apiResult = service.connAPI();
		if(apiResult.equals("API 호출 성공")) {
			service.getBookImages(popularBook);
		}
		return "search/searchMain";
	}

	@PostMapping("/datasearchProc")
	public String datasearchProc() {

	

		return "search/searchMain";
	}

}
