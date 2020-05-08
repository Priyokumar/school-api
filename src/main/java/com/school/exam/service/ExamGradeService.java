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
import com.school.exam.dto.ExamGradeDTO;
import com.school.exam.dto.ExamGradeResponseDTO;
import com.school.exam.dto.ExamGradesResponseDTO;
import com.school.exam.entity.ExamGrade;
import com.school.exam.repository.IExamGradeRepository;

@Service
public class ExamGradeService {

	@Autowired
	private IExamGradeRepository examGradeRepository;
	
	public ExamGradesResponseDTO findAllExamGrades() {

		ExamGradesResponseDTO res = new ExamGradesResponseDTO();

		List<ExamGrade> examGrades = (List<ExamGrade>) examGradeRepository.findAll();

		if (!ScUtil.isAllPresent(examGrades))
			throw new NotFoundException("No examGrade can be found !");

		List<ExamGradeDTO> examGradesDTO = new ArrayList<ExamGradeDTO>();

		examGrades.forEach(examGrade -> {
			examGradesDTO.add(setExamGradeToDTO(examGrade));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(examGradesDTO);

		return res;
	}

	private ExamGradeDTO setExamGradeToDTO(ExamGrade examGrade) {

		ExamGradeDTO examGradeDTO = new ExamGradeDTO();
		examGradeDTO.setId(examGrade.getId());
		examGradeDTO.setDesc(examGradeDTO.getDesc());
		examGradeDTO.setGpa(examGrade.getGpa());
		examGradeDTO.setName(examGrade.getName());
		examGradeDTO.setPcFrom(examGrade.getPcFrom());
		examGradeDTO.setPcTo(examGrade.getPcTo());

		return examGradeDTO;
	}

	public ActionResponseDTO createOrUpdateExamGrade(ExamGradeDTO examGradeDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		ExamGrade examGrade = new ExamGrade();
		if (ScUtil.isAllPresent(id)) {

			Optional<ExamGrade> examGradeOpt = examGradeRepository.findById(id);
			if (!examGradeOpt.isPresent()) {
				throw new NotFoundException("ExamGrade not found.");
			}
			examGrade = examGradeOpt.get();
		}
		examGrade.setDesc(examGradeDTO.getDesc());
		examGrade.setGpa(examGradeDTO.getGpa());
		examGrade.setName(examGradeDTO.getName());
		examGrade.setPcFrom(examGradeDTO.getPcFrom());
		examGrade.setPcTo(examGradeDTO.getPcTo());
		examGradeRepository.save(examGrade);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the examGrade data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a examGrade";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public ExamGradeResponseDTO findExamGrade(String id) {

		ExamGradeResponseDTO res = new ExamGradeResponseDTO();

		Optional<ExamGrade> examGradeOpt = examGradeRepository.findById(id);

		if (!examGradeOpt.isPresent())
			throw new NotFoundException("No examGrade can be found !");

		ExamGradeDTO examGradeDTO = setExamGradeToDTO(examGradeOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(examGradeDTO);

		return res;
	}

	public ActionResponseDTO deleteExamGrade(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<ExamGrade> examGradeOpt = examGradeRepository.findById(id);

		if (!examGradeOpt.isPresent())
			throw new NotFoundException("No examGrade can be found !");

		examGradeRepository.delete(examGradeOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
	
}
