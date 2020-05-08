package com.school.student.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.common.dto.ApiMessageDTO;

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
