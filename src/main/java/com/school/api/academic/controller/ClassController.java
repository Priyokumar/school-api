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

import com.school.api.academic.dto.ClassRequestDTO;
import com.school.api.academic.dto.ClassResponseDTO;
import com.school.api.academic.dto.ClassesResponseDTO;
import com.school.api.academic.service.ClassService;
import com.school.api.common.dto.ActionResponseDTO;

@RestController
@RequestMapping("/v1/api/classes")
public class ClassController {

	@Autowired
	private ClassService classService;

	@GetMapping
	public ClassesResponseDTO findAllClasses() {
		return classService.findAllClasses();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createClass(@Valid @RequestBody ClassRequestDTO classRequestDTO) {
		ActionResponseDTO response = classService.createOrUpdateClass(classRequestDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public ClassResponseDTO findClass(@PathVariable("id") String id) {
		return classService.findClass(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateClass(@Valid @RequestBody ClassRequestDTO classRequestDTO, @PathVariable("id") String id) {
		return classService.createOrUpdateClass(classRequestDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteClass(@PathVariable("id") String id) {
		return classService.deleteClass(id);
	}
}
