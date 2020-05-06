package com.school.api.error.exception;

import com.school.api.common.dto.ApiMessageDTO;

public class ErrorDetails {

	private ApiMessageDTO apiMessage;

	public ErrorDetails(int code, String detail, String status) {

		apiMessage = new ApiMessageDTO(true, code, detail, status);
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
