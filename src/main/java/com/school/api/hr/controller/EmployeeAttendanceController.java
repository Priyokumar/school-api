package com.school.api.hr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.hr.dto.EmployeeAttendanceReportResponseDTO;
import com.school.api.hr.dto.EmployeeAttendanceResponseDTO;
import com.school.api.hr.dto.EmployeeAttendancesRequestDTO;
import com.school.api.hr.service.EmployeeAttendanceService;

@RestController
@RequestMapping("/v1/api/employeeAttendances")
public class EmployeeAttendanceController {

	@Autowired
	private EmployeeAttendanceService attendanceService;

	@GetMapping
	public EmployeeAttendanceResponseDTO findAttendance(@RequestParam(name = "empId", required = true) String empId,
			@RequestParam(name = "date", required = true) String date) {
		return attendanceService.findAttendance(empId, date);
	}

	@GetMapping(value = "/report")
	public EmployeeAttendanceReportResponseDTO findAttendanceReport(@RequestParam(name = "empType") String empType,
			@RequestParam(name = "month") String month, @RequestParam(name = "year") String year) {
		return attendanceService.findAttendanceReport(empType, month, year);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createAttendance(
			@Valid @RequestBody EmployeeAttendancesRequestDTO attendancesRequestDTO) {
		ActionResponseDTO response = attendanceService.createAttendance(attendancesRequestDTO);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}
}
