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
import org.springframework.web.bind.annotation.RestController;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.hr.dto.EmployeeDesignationDTO;
import com.school.api.hr.dto.EmployeeDesignationResponseDTO;
import com.school.api.hr.dto.EmployeeDesignationsResponseDTO;
import com.school.api.hr.service.EmployeeDesignationService;

@RestController
@RequestMapping("/v1/api/designations")
public class EmployeeDesignationController {

	@Autowired
	private EmployeeDesignationService designationService;

	@GetMapping
	public EmployeeDesignationsResponseDTO findAllDesignations() {
		return designationService.findAllDesignations();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createDesignation(
			@Valid @RequestBody EmployeeDesignationDTO designationDTO) {
		ActionResponseDTO response = designationService.createOrUpdateDesignation(designationDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public EmployeeDesignationResponseDTO findDesignation(@PathVariable("id") String id) {
		return designationService.findDesignation(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateDesignation(@Valid @RequestBody EmployeeDesignationDTO designationDTO,
			@PathVariable("id") String id) {
		return designationService.createOrUpdateDesignation(designationDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteDesignation(@PathVariable("id") String id) {
		return designationService.deleteDesignation(id);
	}
}
