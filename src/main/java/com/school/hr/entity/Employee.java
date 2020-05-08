package com.school.hr.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.school.common.entity.Address;
import com.school.common.entity.Document;

@Entity
@Table(name = "SC_EMPLOYEE")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", nullable = false, length = 100)
	private String id;

	@Column(name = "EMP_CODE")
	private String empCode;

	@Column(name = "EMPLOYEE_TYPE")
	private String employeeType;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MOBILE_NO")
	private String mobileNo;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "JOINING_DATE")
	private Date joiningDate;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "DESIGNATION")
	private EmployeeDesignation designation;

	@Column(name = "SAMEASPERMANENT_ADDRESS")
	private Boolean sameAsPermanentAddress = false;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFILE_PIC")
	private Document profilePic;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "empCorrespondentAddress")
	private Address correspondentAddress;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "empPermanentAddress")
	private Address permanentAddress;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private EmployeePersonalInfo personalInfo = new EmployeePersonalInfo();

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	private EmployeeAcademicBackground highestQualification = new EmployeeAcademicBackground();

	@OneToMany(targetEntity = EmployeeDocumentDetails.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<EmployeeDocumentDetails> documents = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeeDesignation getDesignation() {
		return designation;
	}

	public void setDesignation(EmployeeDesignation designation) {
		this.designation = designation;
	}

	public Boolean getSameAsPermanentAddress() {
		return sameAsPermanentAddress;
	}

	public void setSameAsPermanentAddress(Boolean sameAsPermanentAddress) {
		this.sameAsPermanentAddress = sameAsPermanentAddress;
	}

	public Address getCorrespondentAddress() {
		return correspondentAddress;
	}

	public void setCorrespondentAddress(Address correspondentAddress) {
		this.correspondentAddress = correspondentAddress;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public EmployeePersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(EmployeePersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public EmployeeAcademicBackground getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(EmployeeAcademicBackground highestQualification) {
		this.highestQualification = highestQualification;
	}

	public List<EmployeeDocumentDetails> getDocuments() {
		return documents;
	}

	public void setDocuments(List<EmployeeDocumentDetails> documents) {
		this.documents = documents;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Document getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Document profilePic) {
		this.profilePic = profilePic;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

}
