package com.school.hr.dto;

import com.school.common.dto.ApiMessageDTO;

public class EmployeeDesignationResponseDTO {

	private EmployeeDesignationDTO data;

	private ApiMessageDTO apiMessage;

	public EmployeeDesignationDTO getData() {
		return data;
	}

	public void setData(EmployeeDesignationDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
