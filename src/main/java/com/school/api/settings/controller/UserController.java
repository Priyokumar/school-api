package com.school.api.settings.controller;

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
import com.school.api.settings.dto.UserRequestDTO;
import com.school.api.settings.dto.UserResponseDTO;
import com.school.api.settings.dto.UsersResponseDTO;
import com.school.api.settings.services.UserService;
	
@RestController
@RequestMapping("v1/api/users")
public class UserController {

	@Autowired	
	private UserService userService;

	@GetMapping
	public UsersResponseDTO findAllUser() {
		return userService.findAllUsers();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		
		ActionResponseDTO response = userService.createOrUpdateUser(userRequestDTO, null);

		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public UserResponseDTO findUser(@PathVariable("id") String id) {
		return userService.findUser(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO, @PathVariable("id") String id) {
		return userService.createOrUpdateUser(userRequestDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteUser(@PathVariable("id") String id) {
		return userService.deleteUser(id);
	}
}
