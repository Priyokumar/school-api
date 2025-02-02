package com.school.settings.dto;

import com.school.common.dto.ApiMessageDTO;

public class MenuResponseDTO {

	private MenuDTO data;

	private ApiMessageDTO apiMessage;

	public MenuDTO getData() {
		return data;
	}

	public void setData(MenuDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
