package com.school.api.hr.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class SalariesResponseDTO {

	private List<SalaryDTO> data = new ArrayList<SalaryDTO>();

	private ApiMessageDTO apiMessage;

	public List<SalaryDTO> getData() {
		return data;
	}

	public void setData(List<SalaryDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
