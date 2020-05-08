package com.school.error.exception;

import com.school.common.dto.ApiMessageDTO;

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
