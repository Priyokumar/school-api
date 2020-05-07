package com.school.api.academic.dto;

import com.school.api.academic.entity.ClassRoom;
import com.school.api.common.dto.ApiMessageDTO;

public class ClassRoomResponseDTO {

	private ClassRoom data;

	private ApiMessageDTO apiMessage;

	public ClassRoom getData() {
		return data;
	}

	public void setData(ClassRoom data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
