package com.school.hr.dto;

import com.school.common.dto.ApiMessageDTO;

public class EmployeeAttendanceResponseDTO {

	private EmployeeAttendanceDTO data;

	private ApiMessageDTO apiMessage;

	public EmployeeAttendanceDTO getData() {
		return data;
	}

	public void setData(EmployeeAttendanceDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
