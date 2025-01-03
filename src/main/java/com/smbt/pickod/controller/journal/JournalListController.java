package com.smbt.pickod.controller.journal;

import com.smbt.pickod.dto.journal.JournalDTO;
import com.smbt.pickod.mapper.journal.JournalMapper;
import com.smbt.pickod.service.journal.JournalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/journal")
@Slf4j
@RequiredArgsConstructor
public class JournalListController {

    private final JournalMapper journalMapper;
    private JournalService journalService;

    @Autowired
    public JournalListController(JournalMapper journalMapper, JournalService journalService) {
        this.journalMapper = journalMapper;
        this.journalService = journalService;
    }

    @GetMapping("/list")
    public String getFilteredPlaces(@RequestParam(value= "sort", defaultValue = "orderByDate") String sort, Model model) {
        List<JournalDTO> journalList = journalService.getJournalBySort(sort);
        model.addAttribute("journalList", journalList);
        model.addAttribute("sort", sort);
        log.info(sort + "확인=======");
        return "journal/journalSearch";
    }

    @GetMapping("/list/area")
    public String searchJournalByArea(@RequestParam(defaultValue = "") String area, Model model) {
        List<JournalDTO> journals = journalService.searchByArea(area);  // Service에서 지역별 필터링된 결과 가져오기
        model.addAttribute("journalList", journals);  // 필터링된 결과를 모델에 추가
        model.addAttribute("area", area);  // 현재 선택된 지역을 모델에 추가
        return "journal/journalSearch";  // 결과를 보여줄 페이지로 리턴
    }

    @GetMapping("/search")
    public String getJournalBySearch(@RequestParam(defaultValue = "") String keyword, Model model) {
        if (keyword.isEmpty()) {
            return "redirect:/journal/list?sort=orderByDate";
        } else {
            List<JournalDTO> journals = journalService.getJournalBySearch(keyword); // 검색 결과를 가져옴
            model.addAttribute("journalList", journals);
            model.addAttribute("keyword", keyword);
            return "journal/journalSearch";
        }
    }

//    @GetMapping("/search")
//    public String searchJournal(@RequestParam("searchKeyword") String searchKeyword, Model model) {
//        List<JournalDTO> journals = journalService.searchJournal(searchKeyword);
//        model.addAttribute("journals", journals);
//        return "journalSearch :: #journalResults";  // 검색 결과를 부분적으로 업데이트
//    }

}