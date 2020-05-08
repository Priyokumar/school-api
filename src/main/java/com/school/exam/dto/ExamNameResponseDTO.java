package com.school.exam.dto;

import com.school.common.dto.ApiMessageDTO;

public class ExamNameResponseDTO {

	private ExamNameDTO data;

	private ApiMessageDTO apiMessage;

	public ExamNameDTO getData() {
		return data;
	}

	public void setData(ExamNameDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
