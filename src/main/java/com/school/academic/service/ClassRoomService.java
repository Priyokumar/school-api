package com.school.academic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.academic.dto.ClassRoomResponseDTO;
import com.school.academic.dto.ClassRoomsResponseDTO;
import com.school.academic.entity.ClassRoom;
import com.school.academic.repository.IClassroomRepository;
import com.school.common.dto.ActionResponseDTO;
import com.school.common.dto.ApiUtilDTO;
import com.school.common.utils.ScUtil;
import com.school.error.exception.NotFoundException;

@Service
public class ClassRoomService {

	@Autowired
	IClassroomRepository classroomRepository;

	public ClassRoomsResponseDTO findAllClassRooms(String status) {

		ClassRoomsResponseDTO res = new ClassRoomsResponseDTO();

		List<ClassRoom> classRooms = new ArrayList<ClassRoom>();

		if (ScUtil.isAllPresent(status)) {
			classRooms = classroomRepository.findClassRoomsByStatus(status);
		} else {
			classRooms = (List<ClassRoom>) classroomRepository.findAll();
		}

		if (!ScUtil.isAllPresent(classRooms))
			throw new NotFoundException("No classroom can be found !");

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(classRooms);

		return res;
	}

	public ActionResponseDTO createOrUpdateClassRoom(ClassRoom classRoom, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		if (ScUtil.isAllPresent(id)) {
			classRoom.setId(id);
		}

		classRoom.setStatus(classRoom.getStatus().toLowerCase());
		classroomRepository.save(classRoom);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the classRoom data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a classRoom";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public ClassRoomResponseDTO findClassRoom(String id) {

		ClassRoomResponseDTO res = new ClassRoomResponseDTO();

		Optional<ClassRoom> classRoomOpt = classroomRepository.findById(id);

		if (!classRoomOpt.isPresent())
			throw new NotFoundException("No classRoom can be found !");

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(classRoomOpt.get());

		return res;
	}

	public ActionResponseDTO deleteClassRoom(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<ClassRoom> classRoomOpt = classroomRepository.findById(id);

		if (!classRoomOpt.isPresent())
			throw new NotFoundException("No classroom can be found !");

		classroomRepository.delete(classRoomOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
