package com.school.api.hr.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class SalaryResponseDTO {

	private SalaryDTO data;

	private ApiMessageDTO apiMessage;

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

	public SalaryDTO getData() {
		return data;
	}

	public void setData(SalaryDTO data) {
		this.data = data;
	}

}
