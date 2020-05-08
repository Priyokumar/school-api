package com.school.exam.controller;

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

import com.school.common.dto.ActionResponseDTO;
import com.school.exam.dto.ExamNameDTO;
import com.school.exam.dto.ExamNameResponseDTO;
import com.school.exam.dto.ExamNamesResponseDTO;
import com.school.exam.service.ExamNameService;

@RestController
@RequestMapping("/v1/api/names")
public class ExamNameController {

	@Autowired
	private ExamNameService examNameService;

	@GetMapping
	public ExamNamesResponseDTO findExamNames() {
		return examNameService.findAllExamNames();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createExamName(@Valid @RequestBody ExamNameDTO examNameDTO) {
		ActionResponseDTO response = examNameService.createOrUpdateExamName(examNameDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public ExamNameResponseDTO findExamName(@PathVariable("id") String id) {
		return examNameService.findExamName(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateExamName(@Valid @RequestBody ExamNameDTO examNameDTO,
			@PathVariable("id") String id) {
		return examNameService.createOrUpdateExamName(examNameDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteExamName(@PathVariable("id") String id) {
		return examNameService.deleteExamName(id);
	}
}
