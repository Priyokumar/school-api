package com.school.hr.dto;

import com.school.common.dto.ApiMessageDTO;

public class EmployeeResponseDTO {

	private EmployeeDTO data;

	private ApiMessageDTO apiMessage;

	public EmployeeDTO getData() {
		return data;
	}

	public void setData(EmployeeDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
