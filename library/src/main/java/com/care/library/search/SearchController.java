package com.care.library.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SearchController {
	
	@Autowired SearchService service;
	
	@RequestMapping("/datasearch")
	public String datasearch() {
		String key = "";
		
		//인기도서 loanItemSrch
		String url = "http://data4library.kr/api/loanItemSrch";

		String urlParam = "?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641";
		urlParam += "&startDt=2023-08-10&endDt=2023-08-17";
		//urlParam += "&gender=1&age=20";
		urlParam += "&region=11;31"; /// 다중 선택 가능 / 서울: 11
		urlParam += "&dtl_region=11120"; // 은평
		//urlParam += "&addCode=0";
		//urlParam += "&kdc=6";
		urlParam += "&pageNo=1&pageSize=5";

		url = url + urlParam;
		
		// 공공 API를 사용하여 xml형태의 데이터 받아오기.
		service.connAPI(url);
		
		return "search/searchMain";
	}

	@PostMapping("/datasearchProc")
	public String datasearchProc() {

	

		return "search/searchMain";
	}

}
