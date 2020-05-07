package com.school.api.settings.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class RoleResponseDTO {

	private RoleDTO data;

	private ApiMessageDTO apiMessage;

	public RoleDTO getData() {
		return data;
	}

	public void setData(RoleDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
