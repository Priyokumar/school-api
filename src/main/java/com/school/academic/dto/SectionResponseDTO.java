package com.school.academic.dto;

import com.school.common.dto.ApiMessageDTO;

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
