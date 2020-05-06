package com.school.api.academic.controller;

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

import com.school.api.academic.dto.AcademicYearResponseDTO;
import com.school.api.academic.dto.AcademicYearsResponseDTO;
import com.school.api.academic.entity.AcademicYear;
import com.school.api.academic.service.AcademicYearService;
import com.school.api.common.dto.ActionResponseDTO;

@RestController
@RequestMapping("/v1/api/academicYear")
public class AcademicYearController {

	@Autowired
	private AcademicYearService academicYearService;

	@GetMapping
	public AcademicYearsResponseDTO findAllAcademicYears() {
		return academicYearService.findAllAcademicYears();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createAcademicYear(@Valid @RequestBody AcademicYear academicYear) {
		ActionResponseDTO response = academicYearService.createOrUpdateAcademicYear(academicYear, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public AcademicYearResponseDTO findAcademicYear(@PathVariable("id") String id) {
		return academicYearService.findAcademicYear(id);
	}

	@GetMapping(value = "/active/academicYear")
	public AcademicYearResponseDTO findActiveAcademicYear() {
		return academicYearService.findActiveAcademicYear();
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateMenu(@Valid @RequestBody AcademicYear academicYear, @PathVariable("id") String id) {
		return academicYearService.createOrUpdateAcademicYear(academicYear, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteAcademicYear(@PathVariable("id") String id) {
		return academicYearService.deleteMenu(id);
	}
}
