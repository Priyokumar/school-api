package com.school.academic.dto;

import java.util.List;

import com.school.academic.entity.AcademicYear;
import com.school.common.dto.ApiMessageDTO;

public class AcademicYearsResponseDTO {

	private List<AcademicYear> data;

	private ApiMessageDTO apiMessage;

	public List<AcademicYear> getData() {
		return data;
	}

	public void setData(List<AcademicYear> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
