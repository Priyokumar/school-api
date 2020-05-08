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
import org.springframework.web.bind.annotation.RestController;

import com.school.academic.dto.SectionDTO;
import com.school.academic.dto.SectionResponseDTO;
import com.school.academic.dto.SectionsResponseDTO;
import com.school.academic.service.SectionService;
import com.school.common.dto.ActionResponseDTO;

@RestController
@RequestMapping("/v1/api/sections")
public class SectionController {

	@Autowired
	private SectionService sectionService;

	@GetMapping
	public SectionsResponseDTO findAllSections() {
		return sectionService.findAllSections();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createSection(@Valid @RequestBody SectionDTO sectionDTO) {
		ActionResponseDTO response = sectionService.createOrUpdateSection(sectionDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public SectionResponseDTO findSection(@PathVariable("id") String id) {
		return sectionService.findSection(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateSection(@Valid @RequestBody SectionDTO sectionDTO, @PathVariable("id") String id) {
		return sectionService.createOrUpdateSection(sectionDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteSection(@PathVariable("id") String id) {
		return sectionService.deleteSection(id);
	}
}
