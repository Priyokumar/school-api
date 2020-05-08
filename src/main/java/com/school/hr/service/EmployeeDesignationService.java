package com.school.hr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;
import com.school.hr.dto.EmployeeDesignationDTO;
import com.school.hr.dto.EmployeeDesignationResponseDTO;
import com.school.hr.dto.EmployeeDesignationsResponseDTO;
import com.school.hr.entity.EmployeeDesignation;
import com.school.hr.repository.IEmployeeDesignationRepository;

@Service
public class EmployeeDesignationService {

	@Autowired
	private IEmployeeDesignationRepository designationRepository;

	public EmployeeDesignationsResponseDTO findAllDesignations() {

		EmployeeDesignationsResponseDTO res = new EmployeeDesignationsResponseDTO();

		List<EmployeeDesignation> designations = (List<EmployeeDesignation>) designationRepository.findAll();

		if (!ScUtil.isAllPresent(designations))
			throw new NotFoundException("No designation can be found !");

		List<EmployeeDesignationDTO> designationsDTO = new ArrayList<EmployeeDesignationDTO>();

		designations.forEach(designation -> {
			designationsDTO.add(setDesignationToDTO(designation));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationsDTO);

		return res;
	}

	private EmployeeDesignationDTO setDesignationToDTO(EmployeeDesignation designation) {

		EmployeeDesignationDTO designationDTO = new EmployeeDesignationDTO();
		designationDTO.setId(designation.getId());
		designationDTO.setTitle(designation.getTitle());

		return designationDTO;
	}

	public ActionResponseDTO createOrUpdateDesignation(EmployeeDesignationDTO designationDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		EmployeeDesignation designation = new EmployeeDesignation();
		if (ScUtil.isAllPresent(id)) {

			Optional<EmployeeDesignation> designationOpt = designationRepository.findById(id);
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

	public EmployeeDesignationResponseDTO findDesignation(String id) {

		EmployeeDesignationResponseDTO res = new EmployeeDesignationResponseDTO();

		Optional<EmployeeDesignation> designationOpt = designationRepository.findById(id);

		if (!designationOpt.isPresent())
			throw new NotFoundException("No designation can be found !");

		EmployeeDesignationDTO designationDTO = setDesignationToDTO(designationOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(designationDTO);

		return res;
	}

	public ActionResponseDTO deleteDesignation(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<EmployeeDesignation> designationOpt = designationRepository.findById(id);

		if (!designationOpt.isPresent())
			throw new NotFoundException("No designation can be found !");

		designationRepository.delete(designationOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
