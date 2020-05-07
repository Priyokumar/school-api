package com.school.api.hr.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;

public class EmployeeAttendanceReportResponseDTO {

	private List<EmployeeAttendanceReportDTO> data = new ArrayList<>();

	private ApiMessageDTO apiMessage;

	public List<EmployeeAttendanceReportDTO> getData() {
		return data;
	}

	public void setData(List<EmployeeAttendanceReportDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
}
