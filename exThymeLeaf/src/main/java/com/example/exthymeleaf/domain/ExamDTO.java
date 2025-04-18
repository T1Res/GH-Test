package com.example.exthymeleaf.domain;

@lombok.Data
public class ExamDTO {
	// 생성자
	public ExamDTO(int BUN, String NAME, boolean GENDER) {
        this.BUN = BUN;
        this.NAME = NAME;
        this.GENDER = GENDER;
	}
	
	// 기본 생성자
	public ExamDTO() {}

	private int BUN;
	private String NAME;
	private Boolean GENDER;
}
