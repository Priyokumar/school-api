package com.school.api.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;
import com.school.api.hr.dto.SalariesResponseDTO;
import com.school.api.hr.dto.SalaryDTO;
import com.school.api.hr.dto.SalaryRequestDTO;
import com.school.api.hr.dto.SalaryResponseDTO;
import com.school.api.hr.entity.Salary;
import com.school.api.hr.repository.ISalaryRepository;

@Service
public class SalaryService {

	@Autowired
	ISalaryRepository salaryRepository;

	@Autowired
	EmployeeService employeeService;

	public SalariesResponseDTO findAllSalaries() {

		SalariesResponseDTO res = new SalariesResponseDTO();

		List<Salary> salaries = (List<Salary>) salaryRepository.findAll();

		if (!ScUtil.isAllPresent(salaries))
			throw new NotFoundException("No salary can be found !");

		List<SalaryDTO> salariesDTO = new ArrayList<SalaryDTO>();

		salaries.forEach(salary -> {
			salariesDTO.add(setSalaryToDTO(salary));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(salariesDTO);

		return res;
	}

	private SalaryDTO setSalaryToDTO(Salary salary) {

		SalaryDTO salaryDTO = new SalaryDTO();
		salaryDTO.setId(salary.getId());
		salaryDTO.setSalaryAmount(salary.getSalaryAmount());

		salaryDTO.setEmployee(employeeService.setEmployeeToDto(salary.getEmployee()));

		return salaryDTO;
	}

	public ActionResponseDTO UpdateSalary(SalaryRequestDTO salaryRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Salary> salaryOpt = salaryRepository.findById(id);
		if (!salaryOpt.isPresent()) {
			throw new NotFoundException("Salary not found.");
		}
		Salary salary = salaryOpt.get();
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

	public SalaryResponseDTO findSalary(String id) {

		SalaryResponseDTO res = new SalaryResponseDTO();

		Optional<Salary> salaryOpt = salaryRepository.findById(id);

		if (!salaryOpt.isPresent())
			throw new NotFoundException("No designation can be found !");

		SalaryDTO designationDTO = setSalaryToDTO(salaryOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationDTO);

		return res;
	}

}
