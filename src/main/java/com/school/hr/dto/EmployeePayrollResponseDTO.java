package com.school.hr.dto;

import com.school.common.dto.ApiMessageDTO;

public class EmployeePayrollResponseDTO {

	private EmployeePayrollDTO data;

	private ApiMessageDTO apiMessage;

	public EmployeePayrollDTO getData() {
		return data;
	}

	public void setData(EmployeePayrollDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
