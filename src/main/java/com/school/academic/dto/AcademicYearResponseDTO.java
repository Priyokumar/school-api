package com.school.academic.dto;

import com.school.academic.entity.AcademicYear;
import com.school.common.dto.ApiMessageDTO;

public class AcademicYearResponseDTO {

	private AcademicYear data;

	private ApiMessageDTO apiMessage;

	public AcademicYear getData() {
		return data;
	}

	public void setData(AcademicYear data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
