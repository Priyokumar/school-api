package com.school.api.settings.dto;

import java.util.List;

import com.school.api.common.dto.ApiMessageDTO;


public class RolesResponseDTO {

	private List<RoleShortDTO> data;

	private ApiMessageDTO apiMessage;

	public List<RoleShortDTO> getData() {
		return data;
	}

	public void setData(List<RoleShortDTO> data) {
		this.data = data;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}
	
	

}
