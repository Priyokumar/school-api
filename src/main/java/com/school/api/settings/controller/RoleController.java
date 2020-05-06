package com.school.api.settings.controller;

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
import com.school.api.settings.dto.RoleRequestDTO;
import com.school.api.settings.dto.RoleResponseDTO;
import com.school.api.settings.dto.RolesResponseDTO;
import com.school.api.settings.services.RoleService;

@RestController
@RequestMapping("/v1/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public RolesResponseDTO findAllRoles() {
		return roleService.findAllRoles();
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {

		ActionResponseDTO response = roleService.createOrUpdateRole(roleRequestDTO, null);

		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public RoleResponseDTO findRole(@PathVariable("id") String id) {
		return roleService.findRole(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateRole(@RequestBody RoleRequestDTO role, @PathVariable("id") String id) {
		return roleService.createOrUpdateRole(role, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteRole(@PathVariable("id") String id) {
		return roleService.deleteRole(id);
	}
}
