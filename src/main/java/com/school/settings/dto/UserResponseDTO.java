package com.school.settings.dto;

import com.school.common.dto.ApiMessageDTO;

public class UserResponseDTO {

	private UserDTO data;

	private ApiMessageDTO apiMessage;

	public UserDTO getData() {
		return data;
	}

	public void setData(UserDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
