package com.school.student.dto;

import com.school.common.dto.ApiMessageDTO;

public class StudentResponseDTO {

	private StudentDTO data;

	private ApiMessageDTO apiMessage;

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

	public StudentDTO getData() {
		return data;
	}

	public void setData(StudentDTO data) {
		this.data = data;
	}

}
