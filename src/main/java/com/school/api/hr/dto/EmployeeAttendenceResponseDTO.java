package com.school.api.hr.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeeAttendenceResponseDTO {

	private EmployeeAttendenceDTO data;

	private ApiMessageDTO apiMessage;

	public EmployeeAttendenceDTO getData() {
		return data;
	}

	public void setData(EmployeeAttendenceDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
