package com.school.hr.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.common.dto.ApiMessageDTO;

public class EmployeesResponseDTO {

	private List<EmployeeDTO> data = new ArrayList<>();

	private ApiMessageDTO apiMessage;

	public List<EmployeeDTO> getData() {
		return data;
	}

	public void setData(List<EmployeeDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
