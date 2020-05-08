package com.school.settings.dto;

import java.util.List;

import com.school.common.dto.ApiMessageDTO;

public class MenusResponseDTO {

	private List<MenuDTO> data;

	private ApiMessageDTO apiMessage;

	public List<MenuDTO> getData() {
		return data;
	}

	public void setData(List<MenuDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
