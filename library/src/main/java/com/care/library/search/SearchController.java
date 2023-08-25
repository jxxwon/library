package com.care.library.search;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@EnableScheduling
@EnableAsync
@Controller
public class SearchController {
	
	@Autowired SearchService service;
	
	@RequestMapping("/datasearch")
	public String datasearch(Model model) {
		// 공공 API를 사용하여 xml형태의 데이터 받아오기. 매월 1일에 받아오기.
		
		//인기도서 대출
		String popParam = "&libCode=111042";
		String popUrl = service.reqUrlParam("extends/loanItemSrchByLib", popParam);
		String popTable = "popularBook";
		String popXmlTagName = "loanBooks";
		service.showMainImages(popTable, model, popUrl, popXmlTagName);
		
		
		//신착도서 대출
		String recentParam = "&libCode=111042";
		String recentUrl = service.reqUrlParam("extends/libSrch", recentParam, 1, 10);
		System.out.println("recentUrl"+ recentUrl);
		String recentTable = "recentBook";
		String recentXmlTagName = "newBooks";
		service.showMainImages(recentTable, model, recentUrl, recentXmlTagName);
		
		//도서관 전체 정보(일단 50권만 넣을 예정)
		CompletableFuture<String> future = service.asyncMethod();

        future.thenAccept(result -> {
            // 작업이 완료되었을 때 처리할 내용
            System.out.println("Async result: " + result);
        });
		
		
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
			 ra.addFlashAttribute("searchResult",searchResult);
			 //model.addAttribute("searchResult",searchResult);
		}
		
		return "redirect:/datasearch/totalSearch";
	}
	
	@RequestMapping("/datasearch/subMenuSearch")
	public String subMenuSearch() {
		// TODO Auto-generated method stub
		return "search/subMenuSearch";
	}
	
	@GetMapping("/datasearch/searchModal")
	public String searchModal() {
		// TODO Auto-generated method stub
		return "search/searchModal";
	}

}
