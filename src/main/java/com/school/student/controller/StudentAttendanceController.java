package com.school.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.school.common.dto.ActionResponseDTO;
import com.school.student.dto.StudentAttendanceReportResponseDTO;
import com.school.student.dto.StudentAttendanceResponseDTO;
import com.school.student.dto.StudentAttendancesRequestDTO;
import com.school.student.service.StudentAttendanceService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/studentAttendances")
public class StudentAttendanceController {

	@Autowired
	private StudentAttendanceService attendanceService;

	@GetMapping
	public StudentAttendanceResponseDTO findAttendance(@RequestParam(name = "studId", required = true) String studId,
			@RequestParam(name = "date", required = true) String date) {
		return attendanceService.findAttendance(studId, date);
	}

	@GetMapping(value = "/report")
	public StudentAttendanceReportResponseDTO findAttendanceReport(@RequestParam(name = "month") String month,
			@RequestParam(name = "year") String year) {
		return attendanceService.findAttendanceReport(month, year);
	}

	@PostMapping
	public ResponseEntity<ActionResponseDTO> createAttendance(
			@Valid @RequestBody StudentAttendancesRequestDTO attendancesRequestDTO) {
		ActionResponseDTO response = attendanceService.createAttendance(attendancesRequestDTO);
		ResponseEntity<ActionResponseDTO> responseEntity = new ResponseEntity<ActionResponseDTO>(response,
				HttpStatus.CREATED);
		return responseEntity;
	}
}
