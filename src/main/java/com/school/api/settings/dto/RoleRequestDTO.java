package com.school.api.settings.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleRequestDTO {

	private String id;

	private String name;

	private String desc;

	private List<String> menuIDs = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getMenuIDs() {
		return menuIDs;
	}

	public void setMenuIDs(List<String> menuIDs) {
		this.menuIDs = menuIDs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
