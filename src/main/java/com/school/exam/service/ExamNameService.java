package com.school.exam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;
import com.school.exam.dto.ExamNameDTO;
import com.school.exam.dto.ExamNameResponseDTO;
import com.school.exam.dto.ExamNamesResponseDTO;
import com.school.exam.entity.ExamName;
import com.school.exam.repository.IExamNameRepository;

@Service
public class ExamNameService {

	@Autowired
	private IExamNameRepository examNameRepository;

	public ExamNamesResponseDTO findAllExamNames() {

		ExamNamesResponseDTO res = new ExamNamesResponseDTO();

		List<ExamName> examNames = (List<ExamName>) examNameRepository.findAll();

		if (!ScUtil.isAllPresent(examNames))
			throw new NotFoundException("No examName can be found !");

		List<ExamNameDTO> examNamesDTO = new ArrayList<ExamNameDTO>();

		examNames.forEach(examName -> {
			examNamesDTO.add(setExamNameToDTO(examName));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(examNamesDTO);

		return res;
	}

	private ExamNameDTO setExamNameToDTO(ExamName examName) {

		ExamNameDTO examNameDTO = new ExamNameDTO();
		examNameDTO.setId(examName.getId());
		examNameDTO.setCode(examName.getCode());
		examNameDTO.setName(examName.getName());

		return examNameDTO;
	}

	public ActionResponseDTO createOrUpdateExamName(ExamNameDTO examNameDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		ExamName examName = new ExamName();
		if (ScUtil.isAllPresent(id)) {

			Optional<ExamName> examNameOpt = examNameRepository.findById(id);
			if (!examNameOpt.isPresent()) {
				throw new NotFoundException("ExamName not found.");
			}
			examName = examNameOpt.get();
		}
		examName.setCode(examNameDTO.getCode());
		examName.setName(examNameDTO.getName());
		examNameRepository.save(examName);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the examName data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a examName";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public ExamNameResponseDTO findExamName(String id) {

		ExamNameResponseDTO res = new ExamNameResponseDTO();

		Optional<ExamName> examNameOpt = examNameRepository.findById(id);

		if (!examNameOpt.isPresent())
			throw new NotFoundException("No examName can be found !");

		ExamNameDTO examNameDTO = setExamNameToDTO(examNameOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(examNameDTO);

		return res;
	}

	public ActionResponseDTO deleteExamName(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<ExamName> examNameOpt = examNameRepository.findById(id);

		if (!examNameOpt.isPresent())
			throw new NotFoundException("No examName can be found !");

		examNameRepository.delete(examNameOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}

}
