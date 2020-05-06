package com.school.api.academic.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class ClassResponseDTO {

	private ClassDTO data;

	private ApiMessageDTO apiMessage;

	public ClassDTO getData() {
		return data;
	}

	public void setData(ClassDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
