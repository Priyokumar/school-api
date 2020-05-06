package com.school.api.hr.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class DesignationResponseDTO {

	private DesignationDTO data;

	private ApiMessageDTO apiMessage;

	public DesignationDTO getData() {
		return data;
	}

	public void setData(DesignationDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
}
