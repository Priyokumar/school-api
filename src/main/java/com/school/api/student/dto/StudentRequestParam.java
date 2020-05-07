package com.school.api.student.dto;

public class StudentRequestParam {

	private String registrationNo;

	private String registrationDate;

	private String status;

	private String firstName;

	private String middleName;

	private String lastName;

	private String standard;

	private Integer rollNo;

	private String dob;

	private String joiningDate;

	private String gender;

	private String bloodGroup;

	private String religion;

	private String community;

	private String nationality;

	private String aadhaarNo;

	private Boolean physicallyChallenged;

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public Boolean getPhysicallyChallenged() {
		return physicallyChallenged;
	}

	public void setPhysicallyChallenged(Boolean physicallyChallenged) {
		this.physicallyChallenged = physicallyChallenged;
	}

	@Override
	public String toString() {
		return "StudentRequestParam [registrationNo=" + registrationNo + ", registrationDate=" + registrationDate
				+ ", status=" + status + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", standard=" + standard + ", rollNo=" + rollNo + ", dob=" + dob + ", joiningDate="
				+ joiningDate + ", gender=" + gender + ", bloodGroup=" + bloodGroup + ", religion=" + religion
				+ ", community=" + community + ", nationality=" + nationality + ", aadhaarNo=" + aadhaarNo
				+ ", physicallyChallenged=" + physicallyChallenged + "]";
	}
	
}
