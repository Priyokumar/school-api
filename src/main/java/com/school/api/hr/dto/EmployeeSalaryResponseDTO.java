package com.school.api.hr.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeeSalaryResponseDTO {

	private EmployeeSalaryDTO data;

	private ApiMessageDTO apiMessage;

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

	public EmployeeSalaryDTO getData() {
		return data;
	}

	public void setData(EmployeeSalaryDTO data) {
		this.data = data;
	}

}
