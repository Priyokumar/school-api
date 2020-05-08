package com.school.student.dto;

import java.util.List;

import com.school.common.dto.ApiMessageDTO;

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
