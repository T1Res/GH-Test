package com.example.exthymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exthymeleaf.domain.ExamDTO;

@Controller
public class ThymeleafController {
	private static final Logger log 
		= LoggerFactory.getLogger(ThymeleafController.class);
	
	@GetMapping("/")
	public String index() {
		log.info("index() 호출");
		
		return "index";
	}
	
	@RequestMapping("/exam_01")
	public void exam_01(Model model) {
		log.info("exam_01() 호출");
		model.addAttribute("data", "exam_01");
		model.addAttribute("age", 28);
	}
	
	@RequestMapping("/exam_02")
	public void exam_02(Model model) {
		log.info("exam_02() 호출");
		
		ExamDTO dto = new ExamDTO();
		dto.setBUN(1);
		dto.setNAME("홍길동");
		dto.setGENDER(true);
		
		model.addAttribute("dto", dto);
	}
	
	@RequestMapping("/exam_03")
	public void exam_03(Model model) {
		log.info("exam_03() 호출");
		
		model.addAttribute("id", "user01");
		model.addAttribute("name", "홍길동");
		model.addAttribute("score", 95);
		model.addAttribute("gender", true);
	}
	
	@RequestMapping("/exam_04")
	public void exam_04(Model model) {
		log.info("exam_04() 호출");
		
		model.addAttribute("id", "user01");
		model.addAttribute("name", "홍길동");
		model.addAttribute("score", 95);
		model.addAttribute("gender", true);
	}
	
	@RequestMapping("/exam_05")
	public void exam_05(Model model) {
		log.info("exam_05() 호출");
		
		List<ExamDTO> list = new ArrayList<>();
		// 생성자가 존재하기에 ExamDTO() 안에 값을 넣어서 객체 생성 가능
		ExamDTO dto = new ExamDTO();
		dto.setBUN(1);
		dto.setNAME("홍길동");
		dto.setGENDER(true);
		
		list.add(dto);
		
		list.add(new ExamDTO(2, "강감찬", false));
		list.add(new ExamDTO(3, "이순신", false));
		list.add(new ExamDTO(4, "유관순", true));
		list.add(new ExamDTO(5, "김유신", false));
		
		model.addAttribute("list", list);
	}
}
