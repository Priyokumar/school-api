package com.school.api.academic.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class SubjectResponseDTO {

	private SubjectDTO data;

	private ApiMessageDTO apiMessage;

	public SubjectDTO getData() {
		return data;
	}

	public void setData(SubjectDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
}
