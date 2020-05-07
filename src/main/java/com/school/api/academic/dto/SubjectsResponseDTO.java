package com.school.api.academic.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class SubjectsResponseDTO {

	private List<SubjectDTO> data;

	private ApiMessageDTO apiMessage;

	public List<SubjectDTO> getData() {
		return data;
	}

	public void setData(List<SubjectDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
