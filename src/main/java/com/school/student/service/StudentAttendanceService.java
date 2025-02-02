package com.school.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.services.CommonService;
import com.school.common.utils.ScDateUtil;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;
import com.school.hr.dto.*;
import com.school.hr.entity.EmployeeAttendance;
import com.school.hr.repository.IEmployeeRepository;
import com.school.student.dto.*;
import com.school.student.entity.Student;
import com.school.student.entity.StudentAttendance;
import com.school.student.repository.IStudentAttendanceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentAttendanceService {

	@Autowired
	private IStudentAttendanceRepository attendanceRepository;

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Autowired
	private CommonService commonService;

	public StudentAttendanceResponseDTO findAttendance(String empId, String dateStr) {

		StudentAttendanceResponseDTO res = new StudentAttendanceResponseDTO();

		Date date = ScDateUtil.stringToDate(dateStr);

		Optional<StudentAttendance> studentOpt = attendanceRepository.findAttendanceByStudentAndDate(date, empId);

		if (!studentOpt.isPresent())
			throw new NotFoundException("No attendance can be found !");

		StudentAttendanceDTO studentDTO = setAttendanceToDTO(studentOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(studentDTO);

		return res;
	}

	public ActionResponseDTO createAttendance(StudentAttendancesRequestDTO attendancesRequestDTO) {

		ActionResponseDTO res = new ActionResponseDTO();

		Date date = ScDateUtil.stringToDate(attendancesRequestDTO.getDate());
		List<StudentAttendanceRequestDTO> attendanceRequestsDTO = attendancesRequestDTO.getAttendances();

		if (ScUtil.isAllPresent(attendanceRequestsDTO)) {
			attendanceRequestsDTO.forEach(attendanceRequestDTO -> {
				StudentAttendance attendence = setAttendanceDTOToAttendance(attendanceRequestDTO, date);
				commonService.save(attendence);
			});
		}

		String message = "Successfully created a Designation";
		res.setApiMessage(ApiUtilDTO.createdMessage(message));
		res.setActionMessage(message);

		return res;
	}

	public StudentAttendanceDTO setAttendanceToDTO(StudentAttendance attendance) {

		StudentAttendanceDTO attendanceDTO = new StudentAttendanceDTO();
		attendanceDTO.setId(attendance.getId());
		attendanceDTO.setAttendanceType(attendance.getAttendanceType());
		attendanceDTO.setDate(ScDateUtil.dateToString(attendance.getDate()));
		attendanceDTO.setNote(attendance.getNote());

		return attendanceDTO;
	}

	public StudentAttendance setAttendanceDTOToAttendance(StudentAttendanceRequestDTO attendanceRequestDTO, Date date) {

		StudentAttendance attendence = new StudentAttendance();
		attendence.setAttendanceType(attendanceRequestDTO.getAttendanceType());
		attendence.setDate(date);
		attendence.setStudent(commonService.findById(attendanceRequestDTO.getStudId(), Student.class));
		attendence.setNote(attendanceRequestDTO.getNote());

		return attendence;
	}

	public StudentAttendanceReportResponseDTO findAttendanceReport(String month, String year) {

		StudentAttendanceReportResponseDTO res = new StudentAttendanceReportResponseDTO();

		List<StudentAttendance> attendances = attendanceRepository.findAttendanceReport(month, year);

		if (!ScUtil.isAllPresent(attendances))
			throw new NotFoundException("No attendances can be found !");

		System.out.println("\n\n Reports");
		System.out.println(attendances);

		List<StudentAttendanceReportDTO> reportsDTO = new ArrayList<StudentAttendanceReportDTO>();

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(reportsDTO);

		return res;
	}

}
