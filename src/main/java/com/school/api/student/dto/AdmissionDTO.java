package com.school.api.student.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdmissionDTO {

	private String id;

	private String admissionRefNo;

	private String status;

	@NotBlank(message = "admissionDate is required")
	private String admissionDate;

	@NotNull(message = "admissionAmount is required")
	private Double admissionAmount;

	@NotNull(message = "paidAmount is required")
	private Double paidAmount;

	@NotNull(message = "dueAmount is required")
	private Double dueAmount;

	@NotBlank(message = "promiseToPayDate is required")
	private String promiseToPayDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAdmissionRefNo() {
		return admissionRefNo;
	}

	public void setAdmissionRefNo(String admissionRefNo) {
		this.admissionRefNo = admissionRefNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Double getAdmissionAmount() {
		return admissionAmount;
	}

	public void setAdmissionAmount(Double admissionAmount) {
		this.admissionAmount = admissionAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getPromiseToPayDate() {
		return promiseToPayDate;
	}

	public void setPromiseToPayDate(String promiseToPayDate) {
		this.promiseToPayDate = promiseToPayDate;
	}
	
}
