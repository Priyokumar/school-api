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
import com.school.exam.dto.ExamGradeDTO;
import com.school.exam.dto.ExamGradeResponseDTO;
import com.school.exam.dto.ExamGradesResponseDTO;
import com.school.exam.service.ExamGradeService;

@RestController
@RequestMapping("/v1/api/grades")
public class ExamGradeController {

	@Autowired
	private ExamGradeService examGradeService;

	@GetMapping
	public ExamGradesResponseDTO findExamGrades() {
		return examGradeService.findAllExamGrades();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createExamGrade(@Valid @RequestBody ExamGradeDTO examGradeDTO) {
		ActionResponseDTO response = examGradeService.createOrUpdateExamGrade(examGradeDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public ExamGradeResponseDTO findExamGrade(@PathVariable("id") String id) {
		return examGradeService.findExamGrade(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateExamGrade(@Valid @RequestBody ExamGradeDTO examGradeDTO,
			@PathVariable("id") String id) {
		return examGradeService.createOrUpdateExamGrade(examGradeDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteExamGrade(@PathVariable("id") String id) {
		return examGradeService.deleteExamGrade(id);
	}
}
