package com.school.api.student.dto;

import com.school.api.hr.dto.EmployeeDTO;

import java.util.List;

public class StudentAttendanceReportDTO {

	private EmployeeDTO employee;
	
	private int totalLate;
	
	private int totalAbsent;
	
	private int totalHalfDay;
	
	private int totalHoliday;
	
	private int totalPresent;
	
	private List<StudentAttendanceDTO> attendances;

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public int getTotalLate() {
		return totalLate;
	}

	public void setTotalLate(int totalLate) {
		this.totalLate = totalLate;
	}

	public int getTotalAbsent() {
		return totalAbsent;
	}

	public void setTotalAbsent(int totalAbsent) {
		this.totalAbsent = totalAbsent;
	}

	public int getTotalHalfDay() {
		return totalHalfDay;
	}

	public void setTotalHalfDay(int totalHalfDay) {
		this.totalHalfDay = totalHalfDay;
	}

	public int getTotalHoliday() {
		return totalHoliday;
	}

	public void setTotalHoliday(int totalHoliday) {
		this.totalHoliday = totalHoliday;
	}

	public int getTotalPresent() {
		return totalPresent;
	}

	public void setTotalPresent(int totalPresent) {
		this.totalPresent = totalPresent;
	}

	public List<StudentAttendanceDTO> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<StudentAttendanceDTO> attendances) {
		this.attendances = attendances;
	}
	
	
	
}
