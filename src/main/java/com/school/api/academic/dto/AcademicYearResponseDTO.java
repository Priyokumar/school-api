package com.school.api.academic.dto;

import com.school.api.academic.entity.AcademicYear;
import com.school.api.common.dto.ApiMessageDTO;

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
