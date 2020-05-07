package com.school.api.hr.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeeDesignationsResponseDTO {

	private List<EmployeeDesignationDTO> data;

	private ApiMessageDTO apiMessage;

	public List<EmployeeDesignationDTO> getData() {
		return data;
	}

	public void setData(List<EmployeeDesignationDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
