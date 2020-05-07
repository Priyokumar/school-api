package com.school.api.hr.dto;

import com.school.api.common.dto.AddressDTO;
import com.school.api.common.dto.DocumentDTO;

public class EmployeeDTO {

	private String id;

	private String empCode;

	private String firstName;

	private String middleName;

	private String lastName;

	private String gender;

	private String email;

	private String mobileNo;

	private String status;

	private String dob;

	private String joiningDate;

	private String employeeType;

	private EmployeeDesignationDTO designation;

	private Boolean sameAsPermanentAddress = false;

	private DocumentDTO profilePic;

	private AddressDTO correspondentAddress;

	private AddressDTO permanentAddress;

	private EmployeePersonalInfoDTO personalInfo;

	private EmployeeAcademicBackgroundDTO highestQualification;

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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Boolean getSameAsPermanentAddress() {
		return sameAsPermanentAddress;
	}

	public void setSameAsPermanentAddress(Boolean sameAsPermanentAddress) {
		this.sameAsPermanentAddress = sameAsPermanentAddress;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public EmployeeDesignationDTO getDesignation() {
		return designation;
	}

	public void setDesignation(EmployeeDesignationDTO designation) {
		this.designation = designation;
	}

	public AddressDTO getCorrespondentAddress() {
		return correspondentAddress;
	}

	public void setCorrespondentAddress(AddressDTO correspondentAddress) {
		this.correspondentAddress = correspondentAddress;
	}

	public AddressDTO getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(AddressDTO permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public EmployeePersonalInfoDTO getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(EmployeePersonalInfoDTO personalInfo) {
		this.personalInfo = personalInfo;
	}

	public EmployeeAcademicBackgroundDTO getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(EmployeeAcademicBackgroundDTO highestQualification) {
		this.highestQualification = highestQualification;
	}

	public DocumentDTO getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(DocumentDTO profilePic) {
		this.profilePic = profilePic;
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
