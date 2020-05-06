package com.school.api.settings.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {

	private String id;

	private String title;

	private String order;
	
	private String icon;

	private String path;
	
	private Boolean hasSubmenu;
	
	private List<SubMenuDTO> subMenus = new ArrayList<>();

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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getHasSubmenu() {
		return hasSubmenu;
	}

	public void setHasSubmenu(Boolean hasSubmenu) {
		this.hasSubmenu = hasSubmenu;
	}

	public List<SubMenuDTO> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenuDTO> subMenus) {
		this.subMenus = subMenus;
	}
	
	
}
