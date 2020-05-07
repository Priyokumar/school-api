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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.settings.dto.MenuDTO;
import com.school.api.settings.dto.MenuResponseDTO;
import com.school.api.settings.dto.MenusResponseDTO;
import com.school.api.settings.services.MenuService;

@RestController
@RequestMapping("/v1/api/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping
	public MenusResponseDTO findAllMenus(@RequestParam(name = "role", required = false) String role) {
		return menuService.findAllMenus(role);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createRole(@RequestBody MenuDTO menuDTO) {
		ActionResponseDTO response = menuService.createOrUpdateMenu(menuDTO, null);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping(value = "/{id}")
	public MenuResponseDTO findMenu(@PathVariable("id") String id) {
		return menuService.findMenu(id);
	}

	@PutMapping(value = "/{id}")
	public ActionResponseDTO updateMenu(@RequestBody MenuDTO menuDTO, @PathVariable("id") String id) {
		return menuService.createOrUpdateMenu(menuDTO, id);
	}

	@DeleteMapping(value = "/{id}")
	public ActionResponseDTO deleteMenu(@PathVariable("id") String id) {
		return menuService.deleteMenu(id);
	}

}
