package com.school.api.hr.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeePayrollsResponseDTO {

	private List<EmployeePayrollDTO> data = new ArrayList<>();

	private ApiMessageDTO apiMessage;

	public List<EmployeePayrollDTO> getData() {
		return data;
	}

	public void setData(List<EmployeePayrollDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
