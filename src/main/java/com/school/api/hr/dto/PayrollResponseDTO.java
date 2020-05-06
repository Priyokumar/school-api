package com.school.api.hr.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class PayrollResponseDTO {

	private PayrollDTO data;

	private ApiMessageDTO apiMessage;

	public PayrollDTO getData() {
		return data;
	}

	public void setData(PayrollDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
