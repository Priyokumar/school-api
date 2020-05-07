package com.school.api.student.controller;

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
import com.school.api.student.dto.StudentDTO;
import com.school.api.student.dto.StudentRequestParam;
import com.school.api.student.dto.StudentResponseDTO;
import com.school.api.student.dto.StudentsResponseDTO;
import com.school.api.student.service.StudentService;

@RestController
@RequestMapping("/v1/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping()
	public StudentsResponseDTO findStudents(StudentRequestParam param) {
		return studentService.findStudents(param);
	}

	@GetMapping(value = "/{id}")
	public StudentResponseDTO findStudent(@PathVariable("id") Long id) {
		return studentService.findStudent(id);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createStudent(@RequestBody StudentDTO studentDTO) {
		final ActionResponseDTO response = studentService.createOrUpdateStudent(studentDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable("id") String id) {
		return studentService.createOrUpdateStudent(studentDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteStudent(@PathVariable("id") Long id) {
		return studentService.deleteStudent(id);
	}

}
