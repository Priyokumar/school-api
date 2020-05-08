package com.school.student.dto;

import java.util.List;

import com.school.common.dto.ApiMessageDTO;

public class StudentsResponseDTO {

	private List<StudentDTO> data;

	private ApiMessageDTO apiMessage;

	public List<StudentDTO> getData() {
		return data;
	}

	public void setData(List<StudentDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
