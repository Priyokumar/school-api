package com.school.api.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;
import com.school.api.hr.dto.DesignationDTO;
import com.school.api.hr.dto.DesignationResponseDTO;
import com.school.api.hr.dto.DesignationsResponseDTO;
import com.school.api.hr.entity.Designation;
import com.school.api.hr.repository.IDesignationRepository;

@Service
public class DesignationService {

	@Autowired
	IDesignationRepository designationRepository;

	public DesignationsResponseDTO findAllDesignations() {

		DesignationsResponseDTO res = new DesignationsResponseDTO();

		List<Designation> designations = (List<Designation>) designationRepository.findAll();

		if (!ScUtil.isAllPresent(designations))
			throw new NotFoundException("No designation can be found !");

		List<DesignationDTO> designationsDTO = new ArrayList<DesignationDTO>();

		designations.forEach(designation -> {
			designationsDTO.add(setDesignationToDTO(designation));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationsDTO);

		return res;
	}

	private DesignationDTO setDesignationToDTO(Designation designation) {

		DesignationDTO designationDTO = new DesignationDTO();
		designationDTO.setId(designation.getId());
		designationDTO.setTitle(designation.getTitle());

		return designationDTO;
	}

	public ActionResponseDTO createOrUpdateDesignation(DesignationDTO designationDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Designation designation = new Designation();
		if (ScUtil.isAllPresent(id)) {

			Optional<Designation> designationOpt = designationRepository.findById(id);
			if (!designationOpt.isPresent()) {
				throw new NotFoundException("Designation not found.");
			}
			designation = designationOpt.get();
		}
		designation.setTitle(designationDTO.getTitle());
		designationRepository.save(designation);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the Designation data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a Designation";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public DesignationResponseDTO findDesignation(String id) {

		DesignationResponseDTO res = new DesignationResponseDTO();

		Optional<Designation> designationOpt = designationRepository.findById(id);

		if (!designationOpt.isPresent())
			throw new NotFoundException("No designation can be found !");

		DesignationDTO designationDTO = setDesignationToDTO(designationOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationDTO);

		return res;
	}

	public ActionResponseDTO deleteDesignation(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Designation> designationOpt = designationRepository.findById(id);

		if (!designationOpt.isPresent())
			throw new NotFoundException("No designation can be found !");

		designationRepository.delete(designationOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
