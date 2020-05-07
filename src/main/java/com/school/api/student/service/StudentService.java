package com.school.api.student.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.AddressDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.dto.DocumentDTO;
import com.school.api.common.entity.Address;
import com.school.api.common.entity.Document;
import com.school.api.common.services.CommonService;
import com.school.api.common.utils.ScDateUtil;
import com.school.api.common.utils.ScUtil;
import com.school.api.common.vo.FieldType;
import com.school.api.common.vo.Filter;
import com.school.api.common.vo.Operator;
import com.school.api.common.vo.StudentStatus;
import com.school.api.error.exception.NotFoundException;
import com.school.api.student.dto.StudentDTO;
import com.school.api.student.dto.StudentGuardianDTO;
import com.school.api.student.dto.StudentRequestParam;
import com.school.api.student.dto.StudentResponseDTO;
import com.school.api.student.dto.StudentsResponseDTO;
import com.school.api.student.entity.Student;
import com.school.api.student.entity.StudentGuardian;

@Service
public class StudentService {

	@Autowired
	private CommonService commonService;

	public StudentsResponseDTO findAllStudents(StudentRequestParam param) {

		StudentsResponseDTO res = new StudentsResponseDTO();
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		param.getAadhaarNo();
		param.getBloodGroup();
		param.getClass();
		param.getCommunity();
		param.getDob();
		param.getFirstName();
		param.getGender();
		param.getJoiningDate();
		param.getLastName();
		param.getMiddleName();
		param.getNationality();
		param.getPhysicallyChallenged();
		param.getRegistrationDate();
		param.getRegistrationNo();
		if(ScUtil.isAllPresent())
		
		 /* List<Filter> filters = null; if (allParams != null && !allParams.isEmpty()) {
		  
		  filters = new ArrayList<Filter>();
		  
		  for (Entry<String, String> entry : allParams.entrySet()) { String key =
		  entry.getKey(); String value = entry.getValue(); filters.add(new Filter(key,
		  Operator.EQUAL, FieldType.STRING, value)); } }
		  
		  
		  
		  List<Student> students = null; if (!ScUtil.isAllPresent(filters)) students =
		  commonService.findAll(Student.class); else students =
		  commonService.find(filters, Student.class);
		  
		  List<StudentDTO> dtoStudents = new ArrayList<>(); students.forEach(student ->
		  { dtoStudents.add(setStudentToDto(student)); });*/
		 
		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(null);
		return res;
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
