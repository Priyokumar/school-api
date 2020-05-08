package com.school.student.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.AddressDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.dto.DocumentDTO;
import com.school.common.entity.Address;
import com.school.common.entity.Document;
import com.school.common.services.CommonService;
import com.school.common.utils.ScDateUtil;
import com.school.common.utils.ScUtil;
import com.school.common.vo.AdmissionStatuses;
import com.school.common.vo.FieldType;
import com.school.common.vo.Filter;
import com.school.common.vo.Operator;
import com.school.common.vo.StudentStatus;
import com.school.error.exception.NotFoundException;
import com.school.student.dto.AdmissionDTO;
import com.school.student.dto.StudentDTO;
import com.school.student.dto.StudentGuardianDTO;
import com.school.student.dto.StudentRequestParam;
import com.school.student.dto.StudentResponseDTO;
import com.school.student.dto.StudentsResponseDTO;
import com.school.student.entity.Admission;
import com.school.student.entity.Student;
import com.school.student.entity.StudentGuardian;

@Service
public class StudentService {

	@Autowired
	private CommonService commonService;

	public StudentsResponseDTO findStudents(StudentRequestParam param) {

		StudentsResponseDTO res = new StudentsResponseDTO();
		Map<String, Object> params = getParamObjectMap(param);
		List<Filter> filters = null;
		if (params != null && !params.isEmpty()) {
			filters = new ArrayList<>();
			for (Entry<String, Object> entry : params.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				filters.add(new Filter(key, Operator.EQUAL, FieldType.STRING, value));
			}
		}
		List<Student> students = null;
		if (!ScUtil.isAllPresent(filters))
			students = commonService.findAll(Student.class);
		else
			students = commonService.find(filters, Student.class);

		List<StudentDTO> dtoStudents = new ArrayList<>();
		students.forEach(student -> {
			dtoStudents.add(setStudentToDto(student));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(dtoStudents);
		return res;
	}

	private Map<String, Object> getParamObjectMap(StudentRequestParam param) {
		Map<String, Object> params = new HashMap<String, Object>();

		final String aadhaarNo = param.getAadhaarNo();
		final String bloodGroup = param.getBloodGroup();
		final String community = param.getCommunity();
		final String dob = param.getDob();
		final String firstName = param.getFirstName();
		final String gender = param.getGender();
		final String joiningDate = param.getJoiningDate();
		final String lastName = param.getLastName();
		final String middleName = param.getMiddleName();
		final String nationality = param.getNationality();
		final Boolean physicallyChallenged = param.getPhysicallyChallenged();
		final String registrationDate = param.getRegistrationDate();
		final String registrationNo = param.getRegistrationNo();

		if (ScUtil.isAllPresent(aadhaarNo))
			params.put("aadhaarNo", aadhaarNo);

		if (ScUtil.isAllPresent(bloodGroup))
			params.put("bloodGroup", bloodGroup);

		if (ScUtil.isAllPresent(community))
			params.put("community", community);

		if (ScUtil.isAllPresent(dob))
			params.put("dob", dob);

		if (ScUtil.isAllPresent(firstName))
			params.put("firstName", firstName);

		if (ScUtil.isAllPresent(gender))
			params.put("gender", gender);

		if (ScUtil.isAllPresent(joiningDate))
			params.put("joiningDate", joiningDate);

		if (ScUtil.isAllPresent(lastName))
			params.put("lastName", lastName);

		if (ScUtil.isAllPresent(middleName))
			params.put("middleName", middleName);

		if (ScUtil.isAllPresent(nationality))
			params.put("nationality", nationality);

		if (ScUtil.isAllPresent(physicallyChallenged))
			params.put("physicallyChallenged", physicallyChallenged);

		if (ScUtil.isAllPresent(registrationDate))
			params.put("registrationDate", registrationDate);

		if (ScUtil.isAllPresent(registrationNo))
			params.put("registrationNo", registrationNo);
		return params;
	}

	public StudentsResponseDTO findStudentsByStandard(String standard) {

		StudentsResponseDTO res = new StudentsResponseDTO();

		List<Filter> filters = Arrays.asList(new Filter("standard", Operator.EQUAL, FieldType.STRING, standard));
		List<Student> students = commonService.find(filters, Student.class);

		List<StudentDTO> dtoStudents = new ArrayList<>();
		students.forEach(student -> {
			dtoStudents.add(setStudentToDto(student));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(dtoStudents);
		return res;
	}

	public StudentResponseDTO findStudent(Long id) {

		StudentResponseDTO res = new StudentResponseDTO();
		Student student = commonService.findById(id, Student.class);

		if (!ScUtil.isAllPresent(student))
			throw new NotFoundException("No Student can be found !");

		StudentDTO studentDto = setStudentToDto(student);

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(studentDto);

		return res;
	}

	public ActionResponseDTO createOrUpdateStudent(StudentDTO dtoStudent, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Student student = setDtoToStudent(dtoStudent, id);

		commonService.save(student);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated.";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully registered.";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public ActionResponseDTO deleteStudent(Long id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Student student = commonService.findById(id, Student.class);

		if (!ScUtil.isAllPresent(student))
			throw new NotFoundException("No student can be found !");

		commonService.delete(student);

		res.setActionMessage("Student has been deleted successfully");
		res.setApiMessage(ApiUtilDTO.okMessage("Student has been deleted successfully"));
		return res;
	}

	public StudentDTO setStudentToDto(Student student) {

		StudentDTO studentDto = new StudentDTO();

		studentDto.setRegistrationDate(ScDateUtil.dateToString(student.getRegistrationDate()));
		studentDto.setRegistrationNo(student.getRegistrationNo());
		studentDto.setStatus(student.getStatus());
		studentDto.setAge(student.getAge());
		studentDto.setDob(ScDateUtil.dateToString(student.getDob()));
		studentDto.setFirstName(student.getFirstName());
		studentDto.setId(student.getId());
		studentDto.setJoiningDate(ScDateUtil.dateToString(student.getJoiningDate()));
		studentDto.setLastName(student.getLastName());
		studentDto.setMiddleName(student.getMiddleName());
		studentDto.setRollNo(student.getRollNo());
		studentDto.setStandard(student.getStandard());
		studentDto.setBloodGroup(student.getBloodGroup());
		studentDto.setCommunity(student.getCommunity());
		studentDto.setGender(student.getGender());
		studentDto.setNationality(student.getNationality());
		studentDto.setPhysicallyChallenged(student.getPhysicallyChallenged());
		studentDto.setReligion(student.getReligion());
		studentDto.setSameAsPermAddr(student.getSameAsPermAddr());
		studentDto.setProfilePic(setDocDtoDoc(student.getProfilePic()));
		
		Admission admission = student.getAdmission();
		if(ScUtil.isAllPresent(admission)) {
			AdmissionDTO admissionDTO = new AdmissionDTO();
			admissionDTO.setAdmissionAmount(admission.getAdmissionAmount());
			admissionDTO.setAdmissionDate(ScDateUtil.dateToString(admission.getAdmissionDate()));
			admissionDTO.setAdmissionRefNo(admission.getAdmissionRefNo());
			admissionDTO.setDueAmount(admission.getDueAmount());
			admissionDTO.setId(admission.getId());
			admissionDTO.setPaidAmount(admission.getPaidAmount());
			admissionDTO.setPromiseToPayDate(ScDateUtil.dateToString(admission.getPromiseToPayDate()));
			admissionDTO.setStatus(admission.getStatus());
			studentDto.setAdmission(admissionDTO);
		}

		Address permanentAddress = student.getPermanentAddress();
		if (ScUtil.isAllPresent(permanentAddress)) {
			AddressDTO permAddDto = new AddressDTO();
			permAddDto.setCountry(permanentAddress.getCountry());
			permAddDto.setDistrict(permanentAddress.getDistrict());
			permAddDto.setFirstLine(permanentAddress.getFirstLine());
			permAddDto.setId(permanentAddress.getId());
			permAddDto.setSecondLine(permanentAddress.getSecondLine());
			permAddDto.setState(permanentAddress.getState());
			studentDto.setPermanentAddress(permAddDto);
		}

		Address correspondentAddress = student.getCorrespondentAddress();
		if (ScUtil.isAllPresent(correspondentAddress)) {
			AddressDTO corrAddrDto = new AddressDTO();
			corrAddrDto.setCountry(correspondentAddress.getCountry());
			corrAddrDto.setDistrict(correspondentAddress.getDistrict());
			corrAddrDto.setFirstLine(correspondentAddress.getFirstLine());
			corrAddrDto.setId(correspondentAddress.getId());
			corrAddrDto.setSecondLine(correspondentAddress.getSecondLine());
			corrAddrDto.setState(correspondentAddress.getState());
			studentDto.setCorrespondentAddress(corrAddrDto);
		}

		StudentGuardian fatherInfo = student.getFatherInfo();
		if (ScUtil.isAllPresent(fatherInfo)) {
			StudentGuardianDTO fatherDto = new StudentGuardianDTO();
			fatherDto.setContactNo(fatherInfo.getContactNo());
			fatherDto.setDob(ScDateUtil.dateToString(fatherInfo.getDob()));
			fatherDto.setId(fatherInfo.getId());
			fatherDto.setName(fatherInfo.getName());
			fatherDto.setEduQualification(fatherInfo.getEduQualification());
			fatherDto.setOccupation(fatherInfo.getOccupation());
			fatherDto.setIncome(fatherInfo.getIncome());
			studentDto.setFatherInfo(fatherDto);

		}

		StudentGuardian motherInfo = student.getMotherInfo();
		if (ScUtil.isAllPresent(motherInfo)) {
			StudentGuardianDTO motherInfoDto = new StudentGuardianDTO();
			motherInfoDto.setContactNo(motherInfo.getContactNo());
			motherInfoDto.setDob(ScDateUtil.dateToString(motherInfo.getDob()));
			motherInfoDto.setId(motherInfo.getId());
			motherInfoDto.setName(fatherInfo.getName());
			motherInfoDto.setEduQualification(motherInfo.getEduQualification());
			motherInfoDto.setOccupation(motherInfo.getOccupation());
			motherInfoDto.setIncome(motherInfo.getIncome());
			studentDto.setMotherInfo(motherInfoDto);
		}

		StudentGuardian guardianInfo = student.getGuardianInfo();
		if (ScUtil.isAllPresent(guardianInfo)) {
			StudentGuardianDTO guardianInfoDto = new StudentGuardianDTO();
			guardianInfoDto.setContactNo(guardianInfo.getContactNo());
			guardianInfoDto.setDob(ScDateUtil.dateToString(guardianInfo.getDob()));
			guardianInfoDto.setId(guardianInfo.getId());
			guardianInfoDto.setName(guardianInfo.getName());
			guardianInfoDto.setEduQualification(guardianInfo.getEduQualification());
			guardianInfoDto.setOccupation(guardianInfo.getOccupation());
			guardianInfoDto.setIncome(guardianInfo.getIncome());
			studentDto.setGuardianInfo(guardianInfoDto);
		}

		return studentDto;
	}

	public Student setDtoToStudent(StudentDTO studentDto, String id) {

		Student student = new Student();

		if (ScUtil.isAllPresent(id))
			student = commonService.findById(id, Student.class);

		if (!ScUtil.isAllPresent(student))
			throw new NotFoundException("No student can be found !");

		if (!ScUtil.isAllPresent(student.getId())) {
			student.setRegistrationNo(ScUtil.getGeneratedNumber("REG"));
			student.setRegistrationDate(new Date());
			student.setStatus(StudentStatus.ACTIVE);
		} else {
			student.setStatus(studentDto.getStatus());
		}

		student.setDob(ScDateUtil.stringToDate(studentDto.getDob()));
		student.setFirstName(studentDto.getFirstName());
		student.setId(studentDto.getId());
		student.setJoiningDate(ScDateUtil.stringToDate(studentDto.getJoiningDate()));
		student.setLastName(studentDto.getLastName());
		student.setMiddleName(studentDto.getMiddleName());
		student.setRollNo(studentDto.getRollNo());
		student.setStandard(studentDto.getStandard());
		student.setReligion(studentDto.getReligion());

		student.setBloodGroup(studentDto.getBloodGroup());
		student.setCommunity(studentDto.getCommunity());
		student.setGender(studentDto.getGender());
		student.setNationality(studentDto.getNationality());
		student.setPhysicallyChallenged(studentDto.getPhysicallyChallenged());

		student.setSameAsPermAddr(studentDto.getSameAsPermAddr());

		if (student.getSameAsPermAddr() == true) {
			if (ScUtil.isAllPresent(student.getPermanentAddress())) {
				student.setPermanentAddress(null);
			}
		}
		
		AdmissionDTO admissionDTO = studentDto.getAdmission();
		if(ScUtil.isAllPresent(admissionDTO)) {
			Admission admission= new Admission();
			admission.setAdmissionAmount(admissionDTO.getAdmissionAmount());
			admission.setAdmissionDate(ScDateUtil.stringToDate(admissionDTO.getAdmissionDate()));
			admission.setAdmissionRefNo(admissionDTO.getAdmissionRefNo());
			admission.setDueAmount(admissionDTO.getDueAmount());
			admission.setId(admissionDTO.getId());
			admission.setPaidAmount(admissionDTO.getPaidAmount());
			admission.setPromiseToPayDate(ScDateUtil.stringToDate(admissionDTO.getPromiseToPayDate()));
			if(!ScUtil.isAllPresent(id)) {
				admission.setAdmissionRefNo(ScUtil.getGeneratedNumber("ADM"));
				admission.setStatus(AdmissionStatuses.ADMITTED);
			} else {
				admission.setStatus(admissionDTO.getStatus());	
			}
			admission.setStudent(student);
			student.setAdmission(admission);
		}

		AddressDTO permAddDto = studentDto.getPermanentAddress();
		if (ScUtil.isAllPresent(permAddDto) && studentDto.getSameAsPermAddr() == false) {
			if (ScUtil.isAllPresent(permAddDto)) {

				Address permanentAddress = new Address();
				if (ScUtil.isAllPresent(student.getPermanentAddress()))
					permanentAddress = student.getPermanentAddress();
				permanentAddress.setCountry(permAddDto.getCountry());
				permanentAddress.setDistrict(permAddDto.getDistrict());
				permanentAddress.setFirstLine(permAddDto.getFirstLine());
				// permanentAddress.setId(permAddDto.getId());
				permanentAddress.setSecondLine(permAddDto.getSecondLine());
				permanentAddress.setState(permAddDto.getState());

				if (!ScUtil.isAllPresent(student.getPermanentAddress()))
					student.setPermanentAddress(permanentAddress);
			}
		}

		AddressDTO corrAddrDto = studentDto.getCorrespondentAddress();
		if (ScUtil.isAllPresent(corrAddrDto)) {

			Address correspondentAddress = new Address();
			if (ScUtil.isAllPresent(student.getCorrespondentAddress()))
				correspondentAddress = student.getCorrespondentAddress();
			correspondentAddress.setCountry(corrAddrDto.getCountry());
			correspondentAddress.setDistrict(corrAddrDto.getDistrict());
			correspondentAddress.setFirstLine(corrAddrDto.getFirstLine());
			correspondentAddress.setSecondLine(corrAddrDto.getSecondLine());
			correspondentAddress.setState(corrAddrDto.getState());

			if (!ScUtil.isAllPresent(student.getCorrespondentAddress()))
				student.setCorrespondentAddress(correspondentAddress);
		}

		StudentGuardianDTO fatherInfoDto = studentDto.getFatherInfo();
		if (ScUtil.isAllPresent(fatherInfoDto)) {

			StudentGuardian guardian = new StudentGuardian();
			if (ScUtil.isAllPresent(student.getFatherInfo()))
				guardian = student.getFatherInfo();
			guardian.setContactNo(fatherInfoDto.getContactNo());
			guardian.setDob(ScDateUtil.stringToDate(fatherInfoDto.getDob()));
			// guardian.setId(fatherInfoDto.getId());
			guardian.setName(fatherInfoDto.getName());
			guardian.setEduQualification(fatherInfoDto.getEduQualification());
			guardian.setOccupation(fatherInfoDto.getOccupation());
			guardian.setIncome(fatherInfoDto.getIncome());

			if (!ScUtil.isAllPresent(student.getFatherInfo()))
				student.setFatherInfo(guardian);
		}

		StudentGuardianDTO motherInfoDto = studentDto.getMotherInfo();
		if (ScUtil.isAllPresent(motherInfoDto)) {

			StudentGuardian guardian = new StudentGuardian();
			if (ScUtil.isAllPresent(student.getMotherInfo()))
				guardian = student.getMotherInfo();
			guardian.setContactNo(motherInfoDto.getContactNo());
			guardian.setDob(ScDateUtil.stringToDate(motherInfoDto.getDob()));
			// guardian.setId(motherInfoDto.getId());
			guardian.setName(motherInfoDto.getName());
			guardian.setEduQualification(motherInfoDto.getEduQualification());
			guardian.setOccupation(motherInfoDto.getOccupation());
			guardian.setIncome(motherInfoDto.getIncome());

			if (!ScUtil.isAllPresent(student.getMotherInfo()))
				student.setMotherInfo(guardian);
		}

		StudentGuardianDTO guardianInfoDto = studentDto.getGuardianInfo();
		if (ScUtil.isAllPresent(guardianInfoDto)) {

			StudentGuardian guardian = new StudentGuardian();
			if (ScUtil.isAllPresent(student.getGuardianInfo()))
				guardian = student.getGuardianInfo();
			guardian.setContactNo(guardianInfoDto.getContactNo());
			guardian.setDob(ScDateUtil.stringToDate(guardianInfoDto.getDob()));
			// guardian.setId(guardianInfoDto.getId());
			guardian.setName(guardianInfoDto.getName());
			guardian.setEduQualification(guardianInfoDto.getEduQualification());
			guardian.setOccupation(guardianInfoDto.getOccupation());
			guardian.setIncome(guardianInfoDto.getIncome());

			if (!ScUtil.isAllPresent(student.getGuardianInfo()))
				student.setGuardianInfo(guardian);
		}

		return student;
	}

	private DocumentDTO setDocDtoDoc(Document scDoc) {
		if (ScUtil.isAllPresent(scDoc)) {
			DocumentDTO doc = new DocumentDTO();
			doc.setId(scDoc.getId());
			String docUrl = "/document/" + doc.getId() + "/view";
			doc.setDocUrl(docUrl);
			return doc;
		}
		return null;
	}

}
