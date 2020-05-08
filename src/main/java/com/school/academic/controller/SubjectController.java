package com.school.academic.controller;

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

import com.school.academic.dto.SubjectDTO;
import com.school.academic.dto.SubjectResponseDTO;
import com.school.academic.dto.SubjectsResponseDTO;
import com.school.academic.service.SubjectService;
import com.school.common.dto.ActionResponseDTO;

@RestController
@RequestMapping("/v1/api/subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public SubjectsResponseDTO findAllSubjects(@RequestParam(name = "isTheory", required = false) Boolean isTheory) {
		return subjectService.findAllSubjects(isTheory);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createClassRoom(@Valid @RequestBody SubjectDTO subjectDTO) {
		ActionResponseDTO response = subjectService.createOrUpdateSubject(subjectDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public SubjectResponseDTO findSubject(@PathVariable("id") String id) {
		return subjectService.findSubject(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateClassRoom(@Valid @RequestBody SubjectDTO subjectDTO, @PathVariable("id") String id) {
		return subjectService.createOrUpdateSubject(subjectDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteSubject(@PathVariable("id") String id) {
		return subjectService.deleteSubject(id);
	}
}
