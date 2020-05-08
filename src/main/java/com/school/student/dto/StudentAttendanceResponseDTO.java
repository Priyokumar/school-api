package com.school.student.dto;

import com.school.common.dto.ApiMessageDTO;

public class StudentAttendanceResponseDTO {

	private StudentAttendanceDTO data;

	private ApiMessageDTO apiMessage;

	public StudentAttendanceDTO getData() {
		return data;
	}

	public void setData(StudentAttendanceDTO data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
