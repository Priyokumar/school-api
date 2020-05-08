package com.school.academic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.academic.dto.AcademicYearResponseDTO;
import com.school.academic.dto.AcademicYearsResponseDTO;
import com.school.academic.entity.AcademicYear;
import com.school.academic.repository.IAcademicYearRepository;
import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;

@Service
public class AcademicYearService {

	@Autowired
	IAcademicYearRepository academicYearRepository;

	public AcademicYearsResponseDTO findAllAcademicYears() {

		AcademicYearsResponseDTO res = new AcademicYearsResponseDTO();

		List<AcademicYear> academicYears = (List<AcademicYear>) academicYearRepository.findAll();

		if (!ScUtil.isAllPresent(academicYears))
			throw new NotFoundException("No academic years can be found !");

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(academicYears);

		return res;
	}

	public ActionResponseDTO createOrUpdateAcademicYear(AcademicYear academicYear, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		if (ScUtil.isAllPresent(id)) {
			academicYear.setId(id);
		}

		academicYear.setStatus(academicYear.getStatus().toLowerCase());
		academicYearRepository.save(academicYear);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the academicYear data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a academicYear";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public AcademicYearResponseDTO findAcademicYear(String id) {

		AcademicYearResponseDTO res = new AcademicYearResponseDTO();

		Optional<AcademicYear> academicYearOpt = academicYearRepository.findById(id);

		if (!academicYearOpt.isPresent())
			throw new NotFoundException("No academic year can be found !");

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(academicYearOpt.get());

		return res;
	}

	public AcademicYearResponseDTO findActiveAcademicYear() {

		AcademicYearResponseDTO res = new AcademicYearResponseDTO();

		Optional<AcademicYear> academicYearOpt = academicYearRepository.findActiveAcademicYear();

		if (!academicYearOpt.isPresent())
			throw new NotFoundException("No academic year can be found !");

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(academicYearOpt.get());

		return res;
	}

	public ActionResponseDTO deleteMenu(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<AcademicYear> academicYearOpt = academicYearRepository.findById(id);

		if (!academicYearOpt.isPresent())
			throw new NotFoundException("No academic year can be found !");

		academicYearRepository.delete(academicYearOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}

}
