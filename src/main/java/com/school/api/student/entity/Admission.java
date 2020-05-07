package com.school.api.student.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SC_ADMISSION")
public class Admission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", nullable = false, length = 100)
	private String id;

	@Column(name = "ADMISSION_REF_NO")
	private String admissionRefNo;

	@Column(name = "status")
	private String status;

	@Column(name = "ADMISSION_DATE")
	private Date admissionDate;

	@Column(name = "ADMISSION_AMOUNT")
	private Double admissionAmount;

	@Column(name = "PAID_AMOUNT")
	private Double paidAmount;

	@Column(name = "DUE_AMOUNT")
	private Double dueAmount;

	@Column(name = "PROMISE_TO_PAY_DATE")
	private Date promiseToPayDate;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	private Student student;

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

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
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

	public Date getPromiseToPayDate() {
		return promiseToPayDate;
	}

	public void setPromiseToPayDate(Date promiseToPayDate) {
		this.promiseToPayDate = promiseToPayDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
