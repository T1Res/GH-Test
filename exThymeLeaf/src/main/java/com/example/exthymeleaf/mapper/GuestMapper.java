package com.example.exthymeleaf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.exthymeleaf.domain.GuestDTO;
import com.example.exthymeleaf.domain.PageDTO;

@Mapper
public interface GuestMapper {
	// public List<GuestDTO> guestList();

	// 전체목록(PageO, 검색 X)
	public List<GuestDTO> guestListPage(PageDTO dto);

	// 전체목록(PageO, 검색 O)
	public List<GuestDTO> guestListPageSearch(PageDTO dto);

	// 전체 글수 카운트(검색 X)
	public int guestCount();

	// 전체 글수 카운트(검색 O)
	public int guestCountSearch(PageDTO dto);

	public int guestWrite(GuestDTO dto);

	public GuestDTO guestView(int IDX);

	public int guestDelete(int IDX);

	public int guestModify(GuestDTO dto);
}
