package com.school.hr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.common.dto.ActionResponseDTO;
import com.school.hr.dto.EmployeeSalariesResponseDTO;
import com.school.hr.dto.EmployeeSalaryRequestDTO;
import com.school.hr.dto.EmployeeSalaryResponseDTO;
import com.school.hr.service.EmployeeSalaryService;

@RestController
@RequestMapping("/v1/api/salaries")
public class EmployeePayrollSalaryController {

	@Autowired
	private EmployeeSalaryService salaryService;

	@GetMapping
	public EmployeeSalariesResponseDTO findAllSalaries() {
		return salaryService.findAllSalaries();
	}

	@GetMapping(value = "/{id}")
	public EmployeeSalaryResponseDTO findSalary(@PathVariable("id") String id) {
		return salaryService.findSalary(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateSalary(@Valid @RequestBody EmployeeSalaryRequestDTO salaryRequestDTO,
			@PathVariable("id") String id) {
		return salaryService.UpdateSalary(salaryRequestDTO, id);
	}

}
