package com.school.api.common.dto;

import org.springframework.http.HttpStatus;

public class ApiUtilDTO {

	public static ApiMessageDTO okMessage(String detail) {

		return new ApiMessageDTO(false, HttpStatus.OK.value(), detail, HttpStatus.OK.name());
	}

	public static ApiMessageDTO createdMessage(String detail) {

		return new ApiMessageDTO(false, HttpStatus.CREATED.value(), detail, HttpStatus.CREATED.name());
	}

}
