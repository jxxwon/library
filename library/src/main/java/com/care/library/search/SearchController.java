package com.care.library.search;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@EnableScheduling
@EnableAsync
@Controller

public class SearchController {
	
	@Autowired SearchService service;
	@Autowired HttpSession session;
	
	@RequestMapping("/datasearch")
	public String datasearch(Model model) {
		// 공공 API를 사용하여 xml형태의 데이터 받아오기. 매월 1일에 받아오기.
		
		//인기도서 대출
		String popParam = "&libCode=111042"; //은평구립도서관
		String popUrl = service.reqUrlParam("extends/loanItemSrchByLib", popParam);
		System.out.println("popUrl : " + popUrl);
		service.showMainImages("popularBook", model, popUrl);
		
		//신착도서 대출
		String recentParam = "&libCode=111042";
		String recentUrl = service.reqUrlParam("extends/libSrch", recentParam, 1, 10);
		System.out.println("recentUrl : " + recentUrl);
		service.showMainImages("recentBook", model, recentUrl);
		
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
	public String totalSearch(Model model) {
	    ArrayList<BookDTO> searchResult = (ArrayList<BookDTO>) session.getAttribute("searchResult");
	    model.addAttribute("searchResult", searchResult);
	    return "search/totalSearch";
	}

	@RequestMapping("/datasearch/totalSearchProc")
	public String totalSearchProc(String totalSearch) {
	    System.out.println("totalSearchProc 호출");

	    String encodedSearch = "";
	    ArrayList<BookDTO> searchResult = null;
	    try {
	        encodedSearch = URLEncoder.encode(totalSearch, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    String totalParam = "&title=" + encodedSearch;
	    String totalUrl = service.reqUrlParam("srchBooks", totalParam, 1, 10);
	    String apiResult = service.connAPI(totalUrl);
	    if (apiResult != null) {
	        searchResult = service.totalXmlParse(apiResult);
	        // 세션에 데이터 저장
	        session.setAttribute("searchResult", searchResult);
	    }

	    System.out.println("totalUrl : " + totalUrl);
	    System.out.println("totalSearch : " + totalSearch);
	    System.out.println("apiResult : " + apiResult);
	    
	    // 검색 결과 페이지로 리디렉션
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
