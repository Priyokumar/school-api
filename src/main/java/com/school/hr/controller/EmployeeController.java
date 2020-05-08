package com.school.hr.controller;

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

import com.school.common.dto.ActionResponseDTO;
import com.school.hr.dto.EmployeeDTO;
import com.school.hr.dto.EmployeeResponseDTO;
import com.school.hr.dto.EmployeesResponseDTO;
import com.school.hr.service.EmployeeService;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public EmployeesResponseDTO findAllEmployees(@RequestParam(name = "type", required = false) String type) {
		return employeeService.findAllEmployees(type);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		ActionResponseDTO response = employeeService.createOrUpdateEmployee(employeeDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public EmployeeResponseDTO findEmployee(@PathVariable("id") String id) {
		return employeeService.findEmployee(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO,
			@PathVariable("id") String id) {
		return employeeService.createOrUpdateEmployee(employeeDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteEmployee(@PathVariable("id") String id) {
		return employeeService.deleteEmployee(id);
	}
}
