package com.school.api.academic.dto;

import java.util.List;

import com.school.api.academic.entity.ClassRoom;
import com.school.api.common.dto.ApiMessageDTO;

public class ClassRoomsResponseDTO {

	private List<ClassRoom> data;

	private ApiMessageDTO apiMessage;

	public List<ClassRoom> getData() {
		return data;
	}

	public void setData(List<ClassRoom> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	
}
