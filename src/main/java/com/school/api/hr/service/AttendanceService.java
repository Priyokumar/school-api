package com.school.api.hr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.services.CommonService;
import com.school.api.common.utils.ScDateUtil;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;
import com.school.api.hr.dto.EmployeeAttendanceDTO;
import com.school.api.hr.dto.EmployeeAttendanceReportDTO;
import com.school.api.hr.dto.EmployeeAttendanceReportResponseDTO;
import com.school.api.hr.dto.EmployeeAttendanceRequestDTO;
import com.school.api.hr.dto.EmployeeAttendanceResponseDTO;
import com.school.api.hr.dto.EmployeeAttendancesRequestDTO;
import com.school.api.hr.entity.EmployeeAttendance;
import com.school.api.hr.repository.IEmployeeAttendanceRepository;
import com.school.api.hr.repository.IEmployeeRepository;

@Service
public class AttendanceService {

	@Autowired
	private IEmployeeAttendanceRepository attendanceRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Autowired
	private CommonService commonService;
	
	public EmployeeAttendanceResponseDTO findAttendance(String empId, String dateStr) {
		
		EmployeeAttendanceResponseDTO res = new EmployeeAttendanceResponseDTO();
		
		Date date = ScDateUtil.stringToDate(dateStr);

		Optional<EmployeeAttendance> attendanceOpt = attendanceRepository.findAttendanceByEmpAndDate(date, empId);

		if (!attendanceOpt.isPresent())
			throw new NotFoundException("No attendance can be found !");

		EmployeeAttendanceDTO attendanceDTO = setAttendanceToDTO(attendanceOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(attendanceDTO);

		return res;
	}

	public ActionResponseDTO createAttendance(EmployeeAttendancesRequestDTO attendancesRequestDTO) {

		ActionResponseDTO res = new ActionResponseDTO();

		Date date = ScDateUtil.stringToDate(attendancesRequestDTO.getDate());
		List<EmployeeAttendanceRequestDTO> attendanceRequestsDTO = attendancesRequestDTO.getAttendances();

		if (ScUtil.isAllPresent(attendanceRequestsDTO)) {
			attendanceRequestsDTO.forEach(attendanceRequestDTO -> {
				EmployeeAttendance attendence = setAttendanceDTOToAttendance(attendanceRequestDTO, date);
				commonService.save(attendence);
			});
		}

		String message = "";
		message = "Successfully created a Designation";
		res.setApiMessage(ApiUtilDTO.createdMessage(message));
		res.setActionMessage(message);

		return res;
	}
	
	public EmployeeAttendanceDTO setAttendanceToDTO(EmployeeAttendance attendance) {

		EmployeeAttendanceDTO attendanceDTO = new EmployeeAttendanceDTO();
		attendanceDTO.setId(attendance.getId());
		attendanceDTO.setAttendanceType(attendance.getAttendanceType());
		attendanceDTO.setDate(ScDateUtil.dateToString(attendance.getDate()));
		attendanceDTO.setNote(attendance.getNote());

		return attendanceDTO;
	}

	public EmployeeAttendance setAttendanceDTOToAttendance(EmployeeAttendanceRequestDTO attendanceRequestDTO, Date date) {

		EmployeeAttendance attendence = new EmployeeAttendance();
		attendence.setAttendanceType(attendanceRequestDTO.getAttendanceType());
		attendence.setDate(date);
		attendence.setEmployee(employeeRepository.findById(attendanceRequestDTO.getEmpId()).get());
		attendence.setNote(attendanceRequestDTO.getNote());

		return attendence;
	}
	
	public EmployeeAttendanceReportResponseDTO findAttendanceReport(String empType, String month, String year) {
		
		EmployeeAttendanceReportResponseDTO res = new EmployeeAttendanceReportResponseDTO();

		List<EmployeeAttendance> attendances = attendanceRepository.findAttendanceReport(empType, month, year);

		if (!ScUtil.isAllPresent(attendances))
			throw new NotFoundException("No attendances can be found !");
		
		System.out.println("\n\n Reports");
		System.out.println(attendances);
		
		
		List<EmployeeAttendanceReportDTO> reportsDTO = new ArrayList<EmployeeAttendanceReportDTO>();


		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(reportsDTO);

		return res;
	}
	
}
