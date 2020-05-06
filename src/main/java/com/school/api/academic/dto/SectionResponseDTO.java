package com.school.api.academic.dto;

import com.school.api.common.dto.ApiMessageDTO;

public class SectionResponseDTO {

	private SectionDTO data;

	private ApiMessageDTO apiMessage;

	public SectionDTO getData() {
		return data;
	}

	public void setData(SectionDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
