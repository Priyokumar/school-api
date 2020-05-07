package com.school.api.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.AddressDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.dto.DocumentBodyDTO;
import com.school.api.common.dto.DocumentDTO;
import com.school.api.common.entity.Address;
import com.school.api.common.entity.Document;
import com.school.api.common.services.CommonService;
import com.school.api.common.services.DocUploadService;
import com.school.api.common.utils.ScDateUtil;
import com.school.api.common.utils.ScUtil;
import com.school.api.common.vo.FieldType;
import com.school.api.common.vo.Filter;
import com.school.api.common.vo.Operator;
import com.school.api.common.vo.RefType;
import com.school.api.error.exception.InternalServerException;
import com.school.api.error.exception.NotFoundException;
import com.school.api.hr.dto.EmployeeAcademicBackgroundDTO;
import com.school.api.hr.dto.EmployeeDTO;
import com.school.api.hr.dto.EmployeeDesignationDTO;
import com.school.api.hr.dto.EmployeePersonalInfoDTO;
import com.school.api.hr.dto.EmployeeResponseDTO;
import com.school.api.hr.dto.EmployeesResponseDTO;
import com.school.api.hr.entity.Employee;
import com.school.api.hr.entity.EmployeeAcademicBackground;
import com.school.api.hr.entity.EmployeeDesignation;
import com.school.api.hr.entity.EmployeePayrollSalary;
import com.school.api.hr.entity.EmployeePersonalInfo;
import com.school.api.hr.repository.IEmployeeRepository;
import com.school.api.hr.repository.IEmployeeSalaryRepository;

@Service
public class EmployeeService {

	@Autowired
	private CommonService commonService;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Autowired
	private IEmployeeSalaryRepository employeeSalaryRepository;

	@Autowired
	private DocUploadService docUploadService;

	public EmployeesResponseDTO findAllEmployees(String type) {

		EmployeesResponseDTO res = new EmployeesResponseDTO();

		List<Employee> employees = new ArrayList<Employee>();

		if (ScUtil.isAllPresent(type)) {
			employees = employeeRepository.findEmployeeByType(type);
		} else {
			employees = (List<Employee>) employeeRepository.findAll();
		}

		if (!ScUtil.isAllPresent(employees)) {
			throw new NotFoundException("No employee found.");
		}

		List<EmployeeDTO> employeesDTO = setEmployeesToDto(employees);

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(employeesDTO);
		return res;
	}

	public List<EmployeeDTO> setEmployeesToDto(List<Employee> employees) {

		if (!ScUtil.isAllPresent(employees))
			return null;

		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		employees.forEach(employee -> {

			EmployeeDTO employeeDTO = setEmployeeToDto(employee);
			employeesDTO.add(employeeDTO);

		});
		return employeesDTO;
	}

	public EmployeeResponseDTO findEmployee(String id) {

		EmployeeResponseDTO res = new EmployeeResponseDTO();

		Optional<Employee> employeeOpt = employeeRepository.findById(id);

		if (!employeeOpt.isPresent())
			throw new NotFoundException("No employee can be found !");

		EmployeeDTO employeeDTO = setEmployeeToDto(employeeOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(employeeDTO);

		return res;
	}

	public EmployeeDTO setEmployeeToDto(Employee employee) {

		if (!ScUtil.isAllPresent(employee))
			return null;

		EmployeeDTO employeeDTO = new EmployeeDTO();

		employeeDTO.setId(employee.getId());
		employeeDTO.setEmpCode(employee.getEmpCode());
		employeeDTO.setProfilePic(setDocDtoDoc(employee.getProfilePic()));

		EmployeeDesignation designation = employee.getDesignation();
		if (ScUtil.isAllPresent(designation)) {
			employeeDTO.setDesignation(new EmployeeDesignationDTO());
			employeeDTO.getDesignation().setId(designation.getId());
			employeeDTO.getDesignation().setTitle(designation.getTitle());
		}

		employeeDTO.setDob(ScDateUtil.dateToString(employee.getDob()));
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setMobileNo(employee.getMobileNo());
		employeeDTO.setEmployeeType(employee.getEmployeeType());
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setJoiningDate(ScDateUtil.dateToString(employee.getJoiningDate()));
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setMiddleName(employee.getMiddleName());
		employeeDTO.setStatus(employee.getStatus());
		employeeDTO.setGender(employee.getGender());
		employeeDTO.setSameAsPermanentAddress(employee.getSameAsPermanentAddress());

		Address permanentAddress = employee.getPermanentAddress();
		if (ScUtil.isAllPresent(permanentAddress)) {

			AddressDTO permAddDto = new AddressDTO();
			permAddDto.setCountry(permanentAddress.getCountry());
			permAddDto.setDistrict(permanentAddress.getDistrict());
			permAddDto.setFirstLine(permanentAddress.getFirstLine());
			permAddDto.setId(permanentAddress.getId());
			permAddDto.setSecondLine(permanentAddress.getSecondLine());
			permAddDto.setState(permanentAddress.getState());
			employeeDTO.setPermanentAddress(permAddDto);

		}

		Address correspondentAddress = employee.getCorrespondentAddress();
		if (ScUtil.isAllPresent(correspondentAddress)) {

			AddressDTO corrAddrDto = new AddressDTO();

			corrAddrDto.setCountry(correspondentAddress.getCountry());
			corrAddrDto.setDistrict(correspondentAddress.getDistrict());
			corrAddrDto.setFirstLine(correspondentAddress.getFirstLine());
			corrAddrDto.setId(correspondentAddress.getId());
			corrAddrDto.setSecondLine(correspondentAddress.getSecondLine());
			corrAddrDto.setState(correspondentAddress.getState());

			employeeDTO.setCorrespondentAddress(corrAddrDto);
		}

		EmployeePersonalInfo personalInfo = employee.getPersonalInfo();
		if (ScUtil.isAllPresent(personalInfo)) {

			EmployeePersonalInfoDTO pcInfoDto = new EmployeePersonalInfoDTO();

			pcInfoDto.setAadharCard(personalInfo.getAadharCard());
			pcInfoDto.setDrivingLicence(personalInfo.getDrivingLicence());
			pcInfoDto.setId(personalInfo.getId());
			pcInfoDto.setPanCard(personalInfo.getPanCard());
			pcInfoDto.setVoterId(personalInfo.getVoterId());

			employeeDTO.setPersonalInfo(pcInfoDto);
		}

		EmployeeAcademicBackground highestQualification = employee.getHighestQualification();
		if (ScUtil.isAllPresent(highestQualification)) {

			EmployeeAcademicBackgroundDTO acaBgDTO = new EmployeeAcademicBackgroundDTO();
			acaBgDTO.setBoard(highestQualification.getBoard());
			acaBgDTO.setId(highestQualification.getId());
			acaBgDTO.setHighestQualification(highestQualification.getHighestQualification());
			acaBgDTO.setName(highestQualification.getName());
			acaBgDTO.setPassOutYear(ScDateUtil.dateToString(highestQualification.getPassOutYear()));
			acaBgDTO.setSchoolInstitue(highestQualification.getSchoolInstitue());
			acaBgDTO.setScore(highestQualification.getScore());
			acaBgDTO.setStartYear(ScDateUtil.dateToString(highestQualification.getStartYear()));

			employeeDTO.setHighestQualification(acaBgDTO);
		}
		return employeeDTO;
	}

	private DocumentDTO setDocDtoDoc(Document aadharCardDoc) {
		if (ScUtil.isAllPresent(aadharCardDoc)) {
			DocumentDTO doc = new DocumentDTO();
			doc.setId(aadharCardDoc.getId());
			String docUrl = "/document/" + doc.getId() + "/view";
			doc.setDocUrl(docUrl);
			return doc;
		}
		return null;
	}

	public ActionResponseDTO createOrUpdateEmployee(EmployeeDTO employeeDTO, String id) {
		ActionResponseDTO res = new ActionResponseDTO();

		Employee employee = new Employee();

		if (ScUtil.isAllPresent(id)) {
			Optional<Employee> employeeOpt = employeeRepository.findById(id);
			if (!employeeOpt.isPresent())
				throw new NotFoundException("No employee can be found !");

			employee = employeeOpt.get();
		}

		if (ScUtil.isAllPresent(id)) {
			employee.setStatus(employeeDTO.getStatus());
		} else {
			employee.setStatus("Active");
		}

		EmployeeDesignationDTO designationDTO = employeeDTO.getDesignation();
		if (ScUtil.isAllPresent(designationDTO)) {
			employee.setDesignation(commonService.findById(designationDTO.getId(), EmployeeDesignation.class));
		}
		employee.setDob(ScDateUtil.stringToDate(employeeDTO.getDob()));
		employee.setEmail(employeeDTO.getEmail());
		employee.setMobileNo(employeeDTO.getMobileNo());
		employee.setEmployeeType(employeeDTO.getEmployeeType());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setJoiningDate(ScDateUtil.stringToDate(employeeDTO.getJoiningDate()));
		employee.setLastName(employeeDTO.getLastName());
		employee.setMiddleName(employeeDTO.getMiddleName());
		employee.setGender(employeeDTO.getGender());
		employee.setSameAsPermanentAddress(employeeDTO.getSameAsPermanentAddress());
		employee.setEmployeeType(employeeDTO.getEmployeeType());

		if (employee.getSameAsPermanentAddress() == true) {
			if (ScUtil.isAllPresent(employee.getPermanentAddress())) {
				employee.setPermanentAddress(null);
			}
		}

		AddressDTO permAddDto = employeeDTO.getPermanentAddress();
		if (ScUtil.isAllPresent(permAddDto) && employeeDTO.getSameAsPermanentAddress() == false) {

			Address permanentAddress = new Address();
			permanentAddress.setCountry(permAddDto.getCountry());
			permanentAddress.setDistrict(permAddDto.getDistrict());
			permanentAddress.setFirstLine(permAddDto.getFirstLine());
			permanentAddress.setId(permAddDto.getId());
			permanentAddress.setSecondLine(permAddDto.getSecondLine());
			permanentAddress.setState(permAddDto.getState());
			employee.setPermanentAddress(permanentAddress);

		}

		AddressDTO corrAddrDto = employeeDTO.getCorrespondentAddress();
		if (ScUtil.isAllPresent(corrAddrDto)) {

			Address correspondentAddress = new Address();
			correspondentAddress.setCountry(corrAddrDto.getCountry());
			correspondentAddress.setDistrict(corrAddrDto.getDistrict());
			correspondentAddress.setFirstLine(corrAddrDto.getFirstLine());
			correspondentAddress.setId(corrAddrDto.getId());
			correspondentAddress.setSecondLine(corrAddrDto.getSecondLine());
			correspondentAddress.setState(corrAddrDto.getState());
			employee.setCorrespondentAddress(correspondentAddress);
		}

		EmployeePersonalInfoDTO pcInfoDto = employeeDTO.getPersonalInfo();
		if (ScUtil.isAllPresent(pcInfoDto)) {

			EmployeePersonalInfo personalInfo = employee.getPersonalInfo();

			if (!ScUtil.isAllPresent(personalInfo))
				personalInfo = new EmployeePersonalInfo();

			personalInfo.setAadharCard(pcInfoDto.getAadharCard());
			personalInfo.setDrivingLicence(pcInfoDto.getDrivingLicence());
			personalInfo.setId(pcInfoDto.getId());
			personalInfo.setPanCard(pcInfoDto.getPanCard());
			personalInfo.setVoterId(pcInfoDto.getVoterId());

			employee.setPersonalInfo(personalInfo);
			employee.getPersonalInfo().setEmployee(employee);
		}

		EmployeeAcademicBackgroundDTO highestQualificationDto = employeeDTO.getHighestQualification();
		if (ScUtil.isAllPresent(highestQualificationDto)) {

			EmployeeAcademicBackground academicBackground = new EmployeeAcademicBackground();
			academicBackground.setBoard(highestQualificationDto.getBoard());
			academicBackground.setId(highestQualificationDto.getId());
			academicBackground.setHighestQualification(highestQualificationDto.getHighestQualification());
			academicBackground.setName(highestQualificationDto.getName());
			academicBackground.setPassOutYear(ScDateUtil.stringToDate(highestQualificationDto.getPassOutYear()));
			academicBackground.setSchoolInstitue(highestQualificationDto.getSchoolInstitue());
			academicBackground.setScore(highestQualificationDto.getScore());
			academicBackground.setStartYear(ScDateUtil.stringToDate(highestQualificationDto.getStartYear()));

			employee.setHighestQualification(academicBackground);
			employee.getHighestQualification().setEmployee(employee);
		}
		employee = commonService.save(employee);
		createOrUpdateEmployeeSalary(employee);

		if (!ScUtil.isAllPresent(employee.getEmpCode())) {

			String empCode = RefType.EMPLOYEE + employee.getId();
			employee.setEmpCode(empCode);
			employee = commonService.save(employee);
		}

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Employee has been updated successfully.";
		} else {
			message = "Employee has been created successfully.";
		}

		res.setApiMessage(ApiUtilDTO.okMessage(message));
		res.setActionMessage(employee.getId().toString());
		return res;
	}

	private void createOrUpdateEmployeeSalary(Employee employee) {
		String empId = employee.getId();

		ArrayList<Filter> filters = new ArrayList<>();
		filters.add(new Filter("employee", Operator.EQUAL, FieldType.STRING, empId));

		Optional<EmployeePayrollSalary> employeeOpt = employeeSalaryRepository.findByEmployee(empId);

		EmployeePayrollSalary employeeSalary = null;
		if (!employeeOpt.isPresent()) {
			employeeSalary = new EmployeePayrollSalary();
			employeeSalary.setEmployee(employee);
		}

		commonService.save(employeeSalary);
	}

	public ActionResponseDTO deleteEmployee(String id) {
		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Employee> employeeOpt = employeeRepository.findById(id);

		if (!employeeOpt.isPresent())
			throw new NotFoundException("No employee can be found !");

		deleteEmployeeSalary(id);
		commonService.delete(employeeOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Employee has been deleted successfully"));
		return res;
	}

	private void deleteEmployeeSalary(String id) {
		ArrayList<Filter> filters = new ArrayList<>();
		filters.add(new Filter("employee", Operator.EQUAL, FieldType.NUMBER, id));

		Optional<EmployeePayrollSalary> employeeOpt = employeeSalaryRepository.findByEmployee(id);
		if (employeeOpt.isPresent())
			commonService.delete(employeeOpt.get());
	}

	public ActionResponseDTO updateDocument(String id, MultipartFile file, String documentBodyStr) {

		ActionResponseDTO res = new ActionResponseDTO();

		try {

			ObjectMapper objMapper = new ObjectMapper();
			DocumentBodyDTO documentBody = objMapper.readValue(documentBodyStr, DocumentBodyDTO.class);

			Document store = docUploadService.store(file, documentBody, id);
			res.setActionMessage(store.getId().toString());

		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));

		return res;
	}

}
