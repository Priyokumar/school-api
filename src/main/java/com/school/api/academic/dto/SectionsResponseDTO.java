package com.school.api.academic.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class SectionsResponseDTO {

	private List<SectionDTO> data;

	private ApiMessageDTO apiMessage;

	public List<SectionDTO> getData() {
		return data;
	}

	public void setData(List<SectionDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
