package com.care.library.search;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@EnableScheduling
@Controller
public class SearchController {
	
	@Autowired SearchService service;
	
	@RequestMapping("/datasearch")
	public String datasearch(Model model) {
		// 공공 API를 사용하여 xml형태의 데이터 받아오기. 매월 1일에 받아오기.
		
		//String popularParam = "&startDt=2023-07-10";
		//popularParam += "&endDt=2023-07-17";
		
		//String recentParam = "&libCode=111042"; //은평구립도서관
		//String tableName = "";
		
		//인기도서 대출
		String popParam = "&libCode=111042";
		String popUrl = service.reqUrlParam("extends/loanItemSrchByLib", popParam);
		System.out.println("popUrl : " + popUrl);
		service.showMainImages("popularBook", model, popUrl);
		
		//신착도서 대출
		String recentParam = "";
		String recentUrl = service.reqUrlParam("extends/libSrch", recentParam, 1, 10);
		System.out.println("recentUrl : " + recentUrl);
		service.showMainImages("recentBook", model, recentUrl);
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
	public String totalSearchProc(String totalSearch, RedirectAttributes ra, Model model ) {
		System.out.println("totalSearchProc 호출");
		//System.out.println("totalSearch : "+ totalSearch);
		
		//예시
		//http://data4library.kr/api/srchBooks?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641
		//&title="하늘"&author="하늘"&pageNo=1&pageSize=10 
		
		 String encodedSearch="";
		 ArrayList<BookDTO> searchResult = null;
		try {
			encodedSearch = URLEncoder.encode(totalSearch, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String totalParam = "&keyword="+encodedSearch;
		String totalParam = "&title="+encodedSearch;
		String totalUrl = service.reqUrlParam("srchBooks", totalParam, 1, 10);
		String apiResult = service.connAPI(totalUrl);
		if(apiResult != null) {
			 searchResult = service.totalXmlParse(apiResult);
			 //ra.addAttribute("searchResult",searchResult);
			 model.addAttribute("searchResult",searchResult);
		}
		
		System.out.println("totalUrl : " + totalUrl);
		System.out.println("totalSearch : " + totalSearch);
		System.out.println("apiResult : " + apiResult);
		return "/search/totalSearch";
	}
	
	@RequestMapping("/datasearch/subMenuSearch")
	public String subMenuSearch() {
		// TODO Auto-generated method stub
		return "search/subMenuSearch";
	}

}
