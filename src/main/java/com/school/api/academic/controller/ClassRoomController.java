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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.academic.dto.ClassRoomResponseDTO;
import com.school.api.academic.dto.ClassRoomsResponseDTO;
import com.school.api.academic.entity.ClassRoom;
import com.school.api.academic.service.ClassRoomService;
import com.school.api.common.dto.ActionResponseDTO;

@RestController
@RequestMapping("/v1/api/classRooms")
public class ClassRoomController {

	@Autowired
	private ClassRoomService classRoomService;

	@GetMapping
	public ClassRoomsResponseDTO findAllClassRooms(@RequestParam(name = "status", required = false) String status) {
		return classRoomService.findAllClassRooms(status);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createClassRoom(@Valid @RequestBody ClassRoom classRoom) {
		ActionResponseDTO response = classRoomService.createOrUpdateClassRoom(classRoom, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public ClassRoomResponseDTO findClassRoom(@PathVariable("id") String id) {
		return classRoomService.findClassRoom(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateClassRoom(@Valid @RequestBody ClassRoom classroom, @PathVariable("id") String id) {
		return classRoomService.createOrUpdateClassRoom(classroom, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteClassRoom(@PathVariable("id") String id) {
		return classRoomService.deleteClassRoom(id);
	}
}
