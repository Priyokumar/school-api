package com.school.api.academic.dto;

import java.util.ArrayList;
import java.util.List;

public class ClassDTO {

	private String id;

	private String title;

	private List<SectionDTO> sections = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SectionDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
	}

}
