package com.school.api.hr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.hr.dto.SalariesResponseDTO;
import com.school.api.hr.dto.SalaryRequestDTO;
import com.school.api.hr.dto.SalaryResponseDTO;
import com.school.api.hr.service.SalaryService;

@RestController
@RequestMapping("/v1/api/salaries")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;

	@GetMapping
	public SalariesResponseDTO findAllSalaries() {
		return salaryService.findAllSalaries();
	}

	@GetMapping(value = "/{id}")
	public SalaryResponseDTO findSalary(@PathVariable("id") String id) {
		return salaryService.findSalary(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateSalary(@Valid @RequestBody SalaryRequestDTO salaryRequestDTO, @PathVariable("id") String id) {
		return salaryService.UpdateSalary(salaryRequestDTO, id);
	}

}
