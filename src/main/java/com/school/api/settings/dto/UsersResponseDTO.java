package com.school.api.settings.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class UsersResponseDTO {

	private List<UserDTO> data;

	private ApiMessageDTO apiMessage;

	public List<UserDTO> getData() {
		return data;
	}

	public void setData(List<UserDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
