package com.school.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;
import com.school.hr.dto.EmployeeSalariesResponseDTO;
import com.school.hr.dto.EmployeeSalaryDTO;
import com.school.hr.dto.EmployeeSalaryRequestDTO;
import com.school.hr.dto.EmployeeSalaryResponseDTO;
import com.school.hr.entity.EmployeePayrollSalary;
import com.school.hr.repository.IEmployeeSalaryRepository;

@Service
public class EmployeeSalaryService {

	@Autowired
	private IEmployeeSalaryRepository salaryRepository;

	@Autowired
	private EmployeeService employeeService;

	public EmployeeSalariesResponseDTO findAllSalaries() {

		EmployeeSalariesResponseDTO res = new EmployeeSalariesResponseDTO();

		List<EmployeePayrollSalary> salaries = (List<EmployeePayrollSalary>) salaryRepository.findAll();

		if (!ScUtil.isAllPresent(salaries))
			throw new NotFoundException("No salary can be found !");

		List<EmployeeSalaryDTO> salariesDTO = new ArrayList<EmployeeSalaryDTO>();

		salaries.forEach(salary -> {
			salariesDTO.add(setSalaryToDTO(salary));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(salariesDTO);

		return res;
	}

	private EmployeeSalaryDTO setSalaryToDTO(EmployeePayrollSalary salary) {

		EmployeeSalaryDTO salaryDTO = new EmployeeSalaryDTO();
		salaryDTO.setId(salary.getId());
		salaryDTO.setSalaryAmount(salary.getSalaryAmount());

		salaryDTO.setEmployee(employeeService.setEmployeeToDto(salary.getEmployee()));

		return salaryDTO;
	}

	public ActionResponseDTO UpdateSalary(EmployeeSalaryRequestDTO salaryRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<EmployeePayrollSalary> salaryOpt = salaryRepository.findById(id);
		if (!salaryOpt.isPresent()) {
			throw new NotFoundException("Salary not found.");
		}
		EmployeePayrollSalary salary = salaryOpt.get();
		salary.setSalaryAmount(salaryRequestDTO.getSalaryAmount());
		salaryRepository.save(salary);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the Designation data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a Designation";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public EmployeeSalaryResponseDTO findSalary(String id) {

		EmployeeSalaryResponseDTO res = new EmployeeSalaryResponseDTO();

		Optional<EmployeePayrollSalary> salaryOpt = salaryRepository.findById(id);

		if (!salaryOpt.isPresent())
			throw new NotFoundException("No designation can be found !");

		EmployeeSalaryDTO designationDTO = setSalaryToDTO(salaryOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationDTO);

		return res;
	}

}
