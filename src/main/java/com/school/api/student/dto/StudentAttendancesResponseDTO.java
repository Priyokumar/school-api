package com.school.api.student.dto;

import com.school.api.common.dto.ApiMessageDTO;

import java.util.List;

public class StudentAttendancesResponseDTO {

	private List<StudentAttendanceDTO> data;

	private ApiMessageDTO apiMessage;

	public List<StudentAttendanceDTO> getData() {
		return data;
	}

	public void setData(List<StudentAttendanceDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
