package com.example.exthymeleaf.domain;

import lombok.Data;
@Data
public class PageDTO {
	//페이지처리용
	private int startPage;
	private int endPage;
	
	//검색용
	private String search;
	private String key;
	
}
