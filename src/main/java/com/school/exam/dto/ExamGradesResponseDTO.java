package com.school.exam.dto;

import java.util.List;

import com.school.common.dto.ApiMessageDTO;

public class ExamGradesResponseDTO {

	private List<ExamGradeDTO> data;

	private ApiMessageDTO apiMessage;

	public List<ExamGradeDTO> getData() {
		return data;
	}

	public void setData(List<ExamGradeDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
