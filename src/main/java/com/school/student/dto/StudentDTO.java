package com.school.student.dto;

import com.school.common.dto.AddressDTO;
import com.school.common.dto.DocumentDTO;

public class StudentDTO {

	private String id;

	private String registrationNo;

	private String registrationDate;

	private String status;

	private String firstName;

	private String middleName;

	private String lastName;

	private String standard;

	private Integer rollNo;

	private String dob;

	private Long age;

	private String joiningDate;

	private DocumentDTO profilePic;

	private String gender;

	private String bloodGroup;

	private String religion;

	private String community;

	private String nationality;

	private String aadhaarNo;
	
	private AdmissionDTO admission = new AdmissionDTO();

	private Boolean physicallyChallenged;

	private Boolean sameAsPermAddr;

	private AddressDTO correspondentAddress;

	private AddressDTO permanentAddress;

	private StudentGuardianDTO fatherInfo;

	private StudentGuardianDTO motherInfo;

	private StudentGuardianDTO guardianInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public AdmissionDTO getAdmission() {
		return admission;
	}

	public void setAdmission(AdmissionDTO admission) {
		this.admission = admission;
	}

	public StudentGuardianDTO getFatherInfo() {
		return fatherInfo;
	}

	public void setFatherInfo(StudentGuardianDTO fatherInfo) {
		this.fatherInfo = fatherInfo;
	}

	public StudentGuardianDTO getMotherInfo() {
		return motherInfo;
	}

	public void setMotherInfo(StudentGuardianDTO motherInfo) {
		this.motherInfo = motherInfo;
	}

	public StudentGuardianDTO getGuardianInfo() {
		return guardianInfo;
	}

	public void setGuardianInfo(StudentGuardianDTO guardianInfo) {
		this.guardianInfo = guardianInfo;
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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
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

	public Boolean getSameAsPermAddr() {
		return sameAsPermAddr;
	}

	public void setSameAsPermAddr(Boolean sameAsPermAddr) {
		this.sameAsPermAddr = sameAsPermAddr;
	}

	public DocumentDTO getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(DocumentDTO profilePic) {
		this.profilePic = profilePic;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
