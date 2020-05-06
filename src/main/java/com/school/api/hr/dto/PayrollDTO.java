package com.school.api.hr.dto;

import java.util.ArrayList;
import java.util.List;

public class PayrollDTO {

	private String id;

	private String year;
	
	private String status;

	private String month;

	private Double totalEarning;

	private Double totalDeduction;

	private Double grossSalary;

	private Double netSalary;

	private EmployeeDTO employee;

	private List<EarningDTO> earnings = new ArrayList<>();

	private List<DeductionDTO> deductions = new ArrayList<>();

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

	public List<EarningDTO> getEarnings() {
		return earnings;
	}

	public void setEarnings(List<EarningDTO> earnings) {
		this.earnings = earnings;
	}

	public List<DeductionDTO> getDeductions() {
		return deductions;
	}

	public void setDeductions(List<DeductionDTO> deductions) {
		this.deductions = deductions;
	}

}
