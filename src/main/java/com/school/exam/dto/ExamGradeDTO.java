package com.school.exam.dto;

public class ExamGradeDTO {

	private String id;

	private String name;
	
	private String gpa;
	
	private Double pcFrom;
	
	private Double pcTo;
	
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGpa() {
		return gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public Double getPcFrom() {
		return pcFrom;
	}

	public void setPcFrom(Double pcFrom) {
		this.pcFrom = pcFrom;
	}

	public Double getPcTo() {
		return pcTo;
	}

	public void setPcTo(Double pcTo) {
		this.pcTo = pcTo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
