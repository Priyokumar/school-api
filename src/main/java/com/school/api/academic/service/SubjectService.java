package com.school.api.academic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.academic.dto.SubjectDTO;
import com.school.api.academic.dto.SubjectResponseDTO;
import com.school.api.academic.dto.SubjectsResponseDTO;
import com.school.api.academic.entity.Subject;
import com.school.api.academic.repository.ISubjectRepository;
import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;

@Service
public class SubjectService {

	@Autowired
	ISubjectRepository subjectRepository;

	public SubjectsResponseDTO findAllSubjects(Boolean isTheory) {

		SubjectsResponseDTO res = new SubjectsResponseDTO();

		List<Subject> subjects = new ArrayList<Subject>();

		if (ScUtil.isAllPresent(isTheory)) {
			subjects = subjectRepository.findClassRoomsByIsTheory(isTheory);
		} else {
			subjects = (List<Subject>) subjectRepository.findAll();
		}

		if (!ScUtil.isAllPresent(subjects))
			throw new NotFoundException("No subject can be found !");

		List<SubjectDTO> subjectsDTO = new ArrayList<SubjectDTO>();

		subjects.forEach(subject -> {
			subjectsDTO.add(setSubjectToDTO(subject));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(subjectsDTO);

		return res;
	}

	private SubjectDTO setSubjectToDTO(Subject subject) {

		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(subject.getId());
		subjectDTO.setTitle(subject.getTitle());
		subjectDTO.setIsTheory(subject.getIsTheory());

		return subjectDTO;
	}

	public ActionResponseDTO createOrUpdateSubject(SubjectDTO subjectDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Subject subject = new Subject();
		if (ScUtil.isAllPresent(id)) {
			
			Optional<Subject> subjectOpt = subjectRepository.findById(id);
			if(!subjectOpt.isPresent()) {
				throw new NotFoundException("Subject not found.");
			}
			subject = subjectOpt.get();
		}
		subject.setIsTheory(subjectDTO.getIsTheory());
		subject.setTitle(subjectDTO.getTitle());
		subjectRepository.save(subject);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the Subject data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a Subject";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public SubjectResponseDTO findSubject(String id) {

		SubjectResponseDTO res = new SubjectResponseDTO();

		Optional<Subject> subjectOpt = subjectRepository.findById(id);

		if (!subjectOpt.isPresent())
			throw new NotFoundException("No subject can be found !");
		
		SubjectDTO subjectDTO = setSubjectToDTO(subjectOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(subjectDTO);

		return res;
	}

	public ActionResponseDTO deleteSubject(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Subject> subjectOpt = subjectRepository.findById(id);

		if (!subjectOpt.isPresent())
			throw new NotFoundException("No subject can be found !");

		subjectRepository.delete(subjectOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
