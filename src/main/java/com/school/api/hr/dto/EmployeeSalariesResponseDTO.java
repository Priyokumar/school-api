package com.school.api.hr.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeeSalariesResponseDTO {

	private List<EmployeeSalaryDTO> data = new ArrayList<EmployeeSalaryDTO>();

	private ApiMessageDTO apiMessage;

	public List<EmployeeSalaryDTO> getData() {
		return data;
	}

	public void setData(List<EmployeeSalaryDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
