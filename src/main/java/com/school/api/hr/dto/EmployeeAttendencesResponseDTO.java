package com.school.api.hr.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeeAttendencesResponseDTO {

	private List<EmployeeAttendenceDTO> data;

	private ApiMessageDTO apiMessage;

	public List<EmployeeAttendenceDTO> getData() {
		return data;
	}

	public void setData(List<EmployeeAttendenceDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
