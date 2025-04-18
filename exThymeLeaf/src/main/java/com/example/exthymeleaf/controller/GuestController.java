package com.example.exthymeleaf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exthymeleaf.domain.GuestDTO;
import com.example.exthymeleaf.domain.PageDTO;
import com.example.exthymeleaf.mapper.GuestMapper;
import com.example.exthymeleaf.util.PageIndex;

@Controller
@RequestMapping("/Guest")
public class GuestController {
	private static final Logger log = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	private GuestMapper mapper;

	/*
	// 방명록 리스트
	@GetMapping("guest_list")
	public void guest_list(Model model) {
		log.info("guest_list() 호출");

		model.addAttribute("list", mapper.guestList());
	}
	*/
	
	// 방명록 등록 폼
	@GetMapping("guest_write")
	public void guest_write(@RequestParam("page") int page, Model model) {
		log.info("guest_write() 호출");
		model.addAttribute("page", page);
	}
	
	// 방명록 등록
	@PostMapping("guest_write")
	public String guest_write(GuestDTO dto, @RequestParam("page") int page, Model model) {
        log.info("guest_write() 호출");

        int row = mapper.guestWrite(dto);
        model.addAttribute("row", row);
        model.addAttribute("page", page);

        return "/Guest/guest_write_pro";
	}

	// 방명록 목록
	@RequestMapping(value = "/guest_list", method = { RequestMethod.GET, RequestMethod.POST })
	public void guest_list(@RequestParam("page") int page, PageDTO dto, Model model) {
		log.info("guest_view() 호출");

		String url = "guest_list";
		int nowpage = 1;// 현재페이지 설정
		int maxlist = 10;// 페이지당글수
		int totpage = 1;// 총페이지

		int totcount = 0;
		if (dto.getKey() != null) {
			totcount = mapper.guestCountSearch(dto);	//총 게시글수
		} else {
			totcount = mapper.guestCount();	// 총 게시글수
		}

		// 총 페이지수 계산
		if (totcount % 10 == 0) {
			totpage = totcount / maxlist;
		} else {
			totpage = totcount / maxlist + 1;
		}

		if (page != 0)
			nowpage = page;

		// 현재 페이지 구하기
		int startpage = (nowpage - 1) * maxlist + 1;
		int endpage = nowpage * maxlist;

		dto.setStartPage(startpage);
		dto.setEndPage(endpage);

		// 게시글 번호 출력용
		int listcount = totcount - ((nowpage - 1) * maxlist);

		// 모델이 담기
		model.addAttribute("page", nowpage);
		model.addAttribute("totcount", totcount);
		model.addAttribute("totpage", totpage);
		model.addAttribute("listcount", listcount);
		if (dto.getKey() != null) {
			model.addAttribute("list", mapper.guestListPageSearch(dto));
			model.addAttribute("pageList",
					PageIndex.pageListHan(nowpage, totpage, url, maxlist, dto.getKey(), dto.getSearch()));
		} else {
			model.addAttribute("list", mapper.guestListPage(dto));
			model.addAttribute("pageList", PageIndex.pageList(nowpage, totpage, url, maxlist));
		}
	}

	// 방명록 조회
	@GetMapping("guest_view")
	public void guest_view(@RequestParam("IDX") int IDX, @RequestParam("page") int page, Model model) {
		log.info("guest_view() 호출");

		model.addAttribute("page", page);

		GuestDTO dto = mapper.guestView(IDX);
		dto.setCONTENTS(dto.getCONTENTS().replace("\n", "<br>"));
		dto.setCONTENTS(dto.getCONTENTS().replace(" ", "&nbsp;"));

		model.addAttribute("dto", dto);
	}

	// 방명록 삭제
	@GetMapping("guest_delete")
	public String guest_delete(@RequestParam("IDX") int IDX, @RequestParam("page") int page, Model model) {
		log.info("guest_delete() 호출");

		mapper.guestDelete(IDX);

		return "redirect:guest_list?page=" + page;
	}

	// 방명록 수정 폼
	@GetMapping("guest_modify")
	public void guest_modify(@RequestParam("IDX") int IDX, @RequestParam("page") int page, Model model) {
		log.info("guest_modify() 호출");

		model.addAttribute("page", page);

		GuestDTO dto = mapper.guestView(IDX);
		model.addAttribute("dto", dto);
	}

	// 방명록 수정
	@PostMapping("guest_modify")
	public String guest_modify(GuestDTO dto, @RequestParam("page") int page, Model model) {
		log.info("guest_modify() 호출");

		int row = mapper.guestModify(dto);
		model.addAttribute("row", row);
		model.addAttribute("page", page);

		return "/Guest/guest_modify_pro";
	}
}
