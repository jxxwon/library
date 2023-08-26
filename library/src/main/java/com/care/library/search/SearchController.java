package com.care.library.search;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@EnableScheduling
@EnableAsync
@Controller
public class SearchController {

	@Autowired
	SearchService service;
	@Autowired
	SearchMapper mapper;

	@RequestMapping("/datasearch")
	public String datasearch(Model model) {
		// 공공 API를 사용하여 xml형태의 데이터 받아오기. 매월 1일에 받아오기.

		// 인기도서 대출
		String popParam = "&libCode=111042";
		String popUrl = service.reqUrlParam("extends/loanItemSrchByLib", popParam);
		String popTable = "popularBook";
		String popXmlTagName = "loanBooks";
		String paintPop = service.showMainImages(popTable, model, popUrl, popXmlTagName);
		System.out.println("paintPop : " + paintPop);

		// 신착도서 대출
		String recentParam = "&libCode=111042";
		String recentUrl = service.reqUrlParam("extends/libSrch", recentParam, 1, 10);
		System.out.println("recentUrl" + recentUrl);
		String recentTable = "recentBook";
		String recentXmlTagName = "newBooks";
		String paintRecent = service.showMainImages(recentTable, model, recentUrl, recentXmlTagName);
		System.out.println("paintRecent : " + paintRecent);

		// 전체 도서 db만들기
		service.checkTotalDB();
		return "search/searchMain";
	}

	@PostMapping("/datasearchProc")
	public String datasearchProc() {

		return "search/searchMain";
	}

	@GetMapping("/datasearch/totalSearch")
	public String totalSearch(@RequestParam(value = "currentPage", required = false) String cp,
			@RequestParam(required = false) String totalSearch, Model model) {
		// 모든 책을 보여줌
		if(totalSearch != null)
			service.totalSearch(totalSearch, cp, model);
		else
			service.getAllTotal(cp, model);
		return "search/totalSearch";
	}

	@PostMapping("/datasearch/totalSearchProc")
	public String totalSearchProc(String totalSearch, RedirectAttributes ra) {
		ra.addAttribute("totalSearch", totalSearch);

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
