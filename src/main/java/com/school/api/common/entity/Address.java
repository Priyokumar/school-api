package com.school.api.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.school.api.hr.entity.Employee;

@Entity
@Table(name = "SC_ADDRESS")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", nullable = false, length = 100)
	private String id;

	@Column(name = "FIRST_LINE")
	private String firstLine;

	@Column(name = "SECOND_LINE")
	private String secondLine;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "STATE")
	private String state;

	@Column(name = "DISTRICT")
	private String district;

	@OneToOne
	@JoinColumn(name = "EMP_CORR_ADDRESS")
	private Employee empCorrespondentAddress;

	@OneToOne
	@JoinColumn(name = "EMP_PERM_ADDRESS")
	private Employee empPermanentAddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Employee getEmpCorrespondentAddress() {
		return empCorrespondentAddress;
	}

	public void setEmpCorrespondentAddress(Employee empCorrespondentAddress) {
		this.empCorrespondentAddress = empCorrespondentAddress;
	}

	public Employee getEmpPermanentAddress() {
		return empPermanentAddress;
	}

	public void setEmpPermanentAddress(Employee empPermanentAddress) {
		this.empPermanentAddress = empPermanentAddress;
	}

}
