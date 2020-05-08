package com.school.exam.dto;

import com.school.common.dto.ApiMessageDTO;

public class ExamGradeResponseDTO {

	private ExamGradeDTO data;

	private ApiMessageDTO apiMessage;

	public ExamGradeDTO getData() {
		return data;
	}

	public void setData(ExamGradeDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
