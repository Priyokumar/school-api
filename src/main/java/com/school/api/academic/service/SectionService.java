package com.school.api.academic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.academic.dto.SectionDTO;
import com.school.api.academic.dto.SectionResponseDTO;
import com.school.api.academic.dto.SectionsResponseDTO;
import com.school.api.academic.entity.Section;
import com.school.api.academic.repository.ISectionRepository;
import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;

@Service
public class SectionService {

	@Autowired
	ISectionRepository sectionRepository;

	public SectionsResponseDTO findAllSections() {

		SectionsResponseDTO res = new SectionsResponseDTO();

		List<Section> sections = (List<Section>) sectionRepository.findAll();

		if (!ScUtil.isAllPresent(sections))
			throw new NotFoundException("No sections can be found !");

		List<SectionDTO> sectionsDTO = new ArrayList<SectionDTO>();

		sections.forEach(section -> {
			sectionsDTO.add(setSectionToDTO(section));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(sectionsDTO);

		return res;
	}

	private SectionDTO setSectionToDTO(Section section) {

		SectionDTO sectionDTO = new SectionDTO();
		sectionDTO.setId(section.getId());
		sectionDTO.setTitle(section.getTitle());

		return sectionDTO;
	}

	public ActionResponseDTO createOrUpdateSection(SectionDTO sectionDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Section section = new Section();

		if (ScUtil.isAllPresent(id)) {
			Optional<Section> sectionOpt = sectionRepository.findById(id);
			if (sectionOpt.isPresent()) {
				throw new NotFoundException("Section not found.");
			}
			section = sectionOpt.get();
		}

		section.setTitle(sectionDTO.getTitle());

		sectionRepository.save(section);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the section data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a section";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public SectionResponseDTO findSection(String id) {

		SectionResponseDTO res = new SectionResponseDTO();

		Optional<Section> sectionOpt = sectionRepository.findById(id);

		if (!sectionOpt.isPresent())
			throw new NotFoundException("No section can be found !");

		SectionDTO sectionToDTO = setSectionToDTO(sectionOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(sectionToDTO);

		return res;
	}

	public ActionResponseDTO deleteSection(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Section> sectionOpt = sectionRepository.findById(id);

		if (!sectionOpt.isPresent())
			throw new NotFoundException("No section can be found !");

		sectionRepository.delete(sectionOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
