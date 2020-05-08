package com.school.academic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.academic.dto.ClassDTO;
import com.school.academic.dto.ClassRequestDTO;
import com.school.academic.dto.ClassResponseDTO;
import com.school.academic.dto.ClassesResponseDTO;
import com.school.academic.dto.SectionDTO;
import com.school.academic.entity.Class;
import com.school.academic.entity.Section;
import com.school.academic.repository.IClassRepository;
import com.school.academic.repository.ISectionRepository;
import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.services.CommonService;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;

@Service
public class ClassService {

	@Autowired
	IClassRepository classRepository;

	@Autowired
	CommonService commonService;

	@Autowired
	ISectionRepository sectionRepository;

	public ClassesResponseDTO findAllClasses() {

		ClassesResponseDTO res = new ClassesResponseDTO();

		List<Class> classes = (List<Class>) classRepository.findAll();

		if (!ScUtil.isAllPresent(classes))
			throw new NotFoundException("No class can be found !");

		List<ClassDTO> classesDTO = new ArrayList<ClassDTO>();

		classes.forEach(clazz -> {
			classesDTO.add(setClassToDTO(clazz));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(classesDTO);

		return res;
	}

	private ClassDTO setClassToDTO(Class clazz) {

		ClassDTO classDTO = new ClassDTO();
		classDTO.setId(clazz.getId());
		classDTO.setTitle(clazz.getTitle());

		List<Section> sections = clazz.getSections();

		if (ScUtil.isAllPresent(sections)) {

			List<SectionDTO> sectionsDTO = new ArrayList<SectionDTO>();

			sections.forEach(section -> {

				SectionDTO sectionDTO = new SectionDTO();
				sectionDTO.setId(section.getId());
				sectionDTO.setTitle(section.getTitle());

				sectionsDTO.add(sectionDTO);
			});
			classDTO.setSections(sectionsDTO);
		}
		return classDTO;
	}

	public ActionResponseDTO createOrUpdateClass(ClassRequestDTO classRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Class clazz = new Class();

		if (ScUtil.isAllPresent(id)) {
			Optional<Class> classOpt = classRepository.findById(id);

			if (classOpt.isPresent()) {
				throw new NotFoundException("Class not found.");
			}
			clazz = classOpt.get();
		}

		clazz.setTitle(classRequestDTO.getTitle());

		List<String> sectionIds = classRequestDTO.getSections();

		if (ScUtil.isAllPresent(sectionIds)) {
			List<Section> sections = new ArrayList<Section>();
			sectionIds.forEach(sectionId -> {
				Optional<Section> sectionOpt = sectionRepository.findById(sectionId);
				if (sectionOpt.isPresent()) {
					sections.add(sectionOpt.get());
				} else {
					System.out.println("Section with id " + sectionId + " could not found.");
				}
			});
			clazz.setSections(sections);
		}

		commonService.save(clazz);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the class data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a class";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}
		return res;
	}

	public ClassResponseDTO findClass(String id) {

		ClassResponseDTO res = new ClassResponseDTO();

		Optional<Class> classOpt = classRepository.findById(id);

		if (!classOpt.isPresent())
			throw new NotFoundException("No class can be found !");

		ClassDTO classToDTO = setClassToDTO(classOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(classToDTO);

		return res;
	}

	public ActionResponseDTO deleteClass(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Class> classOpt = classRepository.findById(id);

		if (!classOpt.isPresent())
			throw new NotFoundException("No class can be found !");

		commonService.delete(classOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
