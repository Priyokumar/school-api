package com.school.api.hr.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class PayrollsResponseDTO {

	private List<PayrollDTO> data = new ArrayList<>();

	private ApiMessageDTO apiMessage;

	public List<PayrollDTO> getData() {
		return data;
	}

	public void setData(List<PayrollDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
