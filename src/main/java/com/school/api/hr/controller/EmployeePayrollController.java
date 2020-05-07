package com.school.api.hr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.hr.dto.EmployeePayrollRequestDTO;
import com.school.api.hr.dto.EmployeePayrollResponseDTO;
import com.school.api.hr.dto.EmployeePayrollsResponseDTO;
import com.school.api.hr.service.PayrollService;

@RestController
@RequestMapping("/v1/api/payrolls")
public class EmployeePayrollController {

	@Autowired
	private PayrollService payrollService;

	@GetMapping
	public EmployeePayrollsResponseDTO findPayrolls(@RequestParam(name = "empId", required = false) String empId,
			@RequestParam(name = "status", required = false) String status) {
		return payrollService.findPayrolls(empId, status);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createPayroll(@Valid @RequestBody EmployeePayrollRequestDTO payrollRequestDTO) {
		ActionResponseDTO response = payrollService.createOrUpdatePayroll(payrollRequestDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public EmployeePayrollResponseDTO findPayroll(@PathVariable("id") String id) {
		return payrollService.findPayroll(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updatePayroll(@Valid @RequestBody EmployeePayrollRequestDTO payrollRequestDTO, @PathVariable("id") String id) {
		return payrollService.createOrUpdatePayroll(payrollRequestDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deletePayroll(@PathVariable("id") String id) {
		return payrollService.deletePayroll(id);
	}
}
