package com.school.hr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.services.CommonService;
import com.school.common.utils.ScDateUtil;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;
import com.school.hr.dto.EmployeeAttendanceDTO;
import com.school.hr.dto.EmployeeAttendanceReportDTO;
import com.school.hr.dto.EmployeeAttendanceReportResponseDTO;
import com.school.hr.dto.EmployeeAttendanceRequestDTO;
import com.school.hr.dto.EmployeeAttendanceResponseDTO;
import com.school.hr.dto.EmployeeAttendancesRequestDTO;
import com.school.hr.entity.EmployeeAttendance;
import com.school.hr.repository.IEmployeeAttendanceRepository;
import com.school.hr.repository.IEmployeeRepository;

@Service
public class EmployeeAttendanceService {

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

	public EmployeeAttendance setAttendanceDTOToAttendance(EmployeeAttendanceRequestDTO attendanceRequestDTO,
			Date date) {

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
