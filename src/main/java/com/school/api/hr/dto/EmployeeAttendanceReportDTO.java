package com.school.api.hr.dto;

import java.util.List;

public class EmployeeAttendanceReportDTO {

	private EmployeeDTO employee;

	private int totalLate;

	private int totalAbsent;

	private int totalHalfDay;

	private int totalHoliday;

	private int totalPresent;

	private List<EmployeeAttendanceDTO> attendances;

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

	public List<EmployeeAttendanceDTO> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<EmployeeAttendanceDTO> attendances) {
		this.attendances = attendances;
	}

}
