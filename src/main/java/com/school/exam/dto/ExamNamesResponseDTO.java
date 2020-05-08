package com.school.exam.dto;

import java.util.List;

import com.school.common.dto.ApiMessageDTO;

public class ExamNamesResponseDTO {

	private List<ExamNameDTO> data;

	private ApiMessageDTO apiMessage;

	public List<ExamNameDTO> getData() {
		return data;
	}

	public void setData(List<ExamNameDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
