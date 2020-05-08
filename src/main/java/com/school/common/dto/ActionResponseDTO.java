package com.school.common.dto;

public class ActionResponseDTO {

	private String actionMessage;

	private ApiMessageDTO apiMessage = new ApiMessageDTO();

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public ApiMessageDTO getApiMessage() {
		return apiMessage;
	}

	public void setApiMessage(ApiMessageDTO apiMessage) {
		this.apiMessage = apiMessage;
	}

}
