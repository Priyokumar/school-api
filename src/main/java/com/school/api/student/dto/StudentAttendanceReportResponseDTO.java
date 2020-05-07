package com.school.api.student.dto;

import com.school.api.common.dto.ApiMessageDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentAttendanceReportResponseDTO {

	private List<StudentAttendanceReportDTO> data = new ArrayList<>();

	private ApiMessageDTO apiMessage;

	public List<StudentAttendanceReportDTO> getData() {
		return data;
	}

	public void setData(List<StudentAttendanceReportDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
}
