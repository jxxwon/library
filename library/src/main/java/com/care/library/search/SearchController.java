package com.care.library.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableScheduling
@Controller
public class SearchController {
	
	@Autowired SearchService service;
	
	@RequestMapping("/datasearch")
	public String datasearch(Model model) {
		// 공공 API를 사용하여 xml형태의 데이터 받아오기. 매월 1일에 받아오기.
		
		String popularParam = "&startDt=2023-07-10";
		popularParam += "&endDt=2023-07-17";
		
		String recentParam = "&libCode=111042"; //은평구립도서관
		String tableName = "";
		
		String popularResult = service.getBookImages(model, "popularBook");
		tableName = "popularBook";
		if(popularResult.equals("이미지 가져오기 실패")) {
			String popApiResult = service.connAPI("loanItemSrch", popularParam, tableName);
				System.out.println("popApiResult" + popApiResult);
				service.getBookImages(model, tableName);
		}
				
		String recentResult = service.getBookImages(model, "recentBook");
		tableName = "recentBook";
		if(recentResult.equals("이미지 가져오기 실패")) {
			String recApiResult = service.connAPI("extends/libSrch", recentParam, tableName);
				System.out.println("recApiResult" + recApiResult);
				service.getBookImages(model, tableName);
		}
		
		System.out.println("popularResult : "+ popularResult);
		System.out.println("recentResult : "+ recentResult);
		
//		if(popularResult.equals("이미지 가져오기 완료") && recentResult.equals("이미지 가져오기 완료"))
//			return "search/searchMain";
		
			//return "redirect:/main"; //데이터 받아오는 속도가 너무 느려서 넣어야할지...
		return "search/searchMain";
	}

	@PostMapping("/datasearchProc")
	public String datasearchProc() {

		return "search/searchMain";
	}
	
	@GetMapping("/datasearch/totalSearch")
	public String totalSearch() {
		// TODO Auto-generated method stub
		return "search/totalSearch";
	}
	
	@PostMapping("/datasearch/totalSearchProc")
	public String totalSearchProc(String totalSearch) {
		System.out.println("totalSearch : "+ totalSearch);
		return "search/totalSearch";
	}
	
	@RequestMapping("/datasearch/subMenuSearch")
	public String subMenuSearch() {
		// TODO Auto-generated method stub
		return "search/subMenuSearch";
	}

}
