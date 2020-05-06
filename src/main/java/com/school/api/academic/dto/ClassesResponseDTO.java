package com.school.api.academic.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class ClassesResponseDTO {

	private List<ClassDTO> data;

	private ApiMessageDTO apiMessage;

	public List<ClassDTO> getData() {
		return data;
	}

	public void setData(List<ClassDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
