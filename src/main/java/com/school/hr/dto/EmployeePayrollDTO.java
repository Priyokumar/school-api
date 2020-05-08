package com.school.hr.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDTO {

	private String id;

	private String year;

	private String status;

	private String month;

	private Double totalEarning;

	private Double totalDeduction;

	private Double grossSalary;

	private Double netSalary;

	private EmployeeDTO employee;

	private List<EmployeePayrollEarningDTO> earnings = new ArrayList<>();

	private List<EmployeePayrollDeductionDTO> deductions = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getTotalEarning() {
		return totalEarning;
	}

	public void setTotalEarning(Double totalEarning) {
		this.totalEarning = totalEarning;
	}

	public Double getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(Double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public List<EmployeePayrollEarningDTO> getEarnings() {
		return earnings;
	}

	public void setEarnings(List<EmployeePayrollEarningDTO> earnings) {
		this.earnings = earnings;
	}

	public List<EmployeePayrollDeductionDTO> getDeductions() {
		return deductions;
	}

	public void setDeductions(List<EmployeePayrollDeductionDTO> deductions) {
		this.deductions = deductions;
	}

}
