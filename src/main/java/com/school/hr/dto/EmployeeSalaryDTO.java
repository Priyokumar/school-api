package com.school.hr.dto;

public class EmployeeSalaryDTO {

	private String id;

	private Double salaryAmount;

	private EmployeeDTO employee;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getSalaryAmount() {
		return salaryAmount;
	}

	public void setSalaryAmount(Double salaryAmount) {
		this.salaryAmount = salaryAmount;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

}
