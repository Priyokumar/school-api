package com.school.api.hr.dto;

import java.util.List;

public class EmployeeAttendancesRequestDTO {

	private String date;

	private List<EmployeeAttendanceRequestDTO> attendances;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<EmployeeAttendanceRequestDTO> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<EmployeeAttendanceRequestDTO> attendances) {
		this.attendances = attendances;
	}

}
