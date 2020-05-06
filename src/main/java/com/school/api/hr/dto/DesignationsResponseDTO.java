package com.school.api.hr.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class DesignationsResponseDTO {

	private List<DesignationDTO> data;

	private ApiMessageDTO apiMessage;

	public List<DesignationDTO> getData() {
		return data;
	}

	public void setData(List<DesignationDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
}
