package com.care.library.cultural;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.library.cultural.CulturalDTO;
import com.care.library.cultural.CulturalMapper;
import com.care.library.common.PageService;

@Service
public class CulturalService {
    @Autowired
    private CulturalMapper culturalMapper;

    // 페이지 넘기기
    public void culturalForm(String cp, Model model) {
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(cp);
        } catch (Exception e) {
            currentPage = 1;
        }

        int pagePerBlock = 3; // 한 페이지에 보일 데이터의 수
        int pageBlock = 5; // 한 번에 보여줄 페이지 번호들의 그룹 개수
        int end = pagePerBlock * currentPage; // 테이블에서 가져올 마지막 행번호
        int begin = end - pagePerBlock + 1; // 테이블에서 가져올 시작 행번호

        ArrayList<CulturalDTO> culturals = culturalMapper.culForm(begin, end);
        int totalCount = culturalMapper.count();
        String url = "culForm?currentPage=";
        String result = PageService.printPage(url, currentPage, totalCount, pageBlock);

        model.addAttribute("culturals", culturals);
        model.addAttribute("result", result);
        model.addAttribute("currentPage", currentPage);
    }
}
