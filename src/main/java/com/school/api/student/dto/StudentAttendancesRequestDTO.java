package com.school.api.student.dto;

import java.util.List;

public class StudentAttendancesRequestDTO {

	private String date;
	
	private List<StudentAttendanceRequestDTO> attendances;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<StudentAttendanceRequestDTO> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<StudentAttendanceRequestDTO> attendances) {
		this.attendances = attendances;
	}
	
 }
