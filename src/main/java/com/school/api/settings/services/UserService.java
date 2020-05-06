package com.school.api.settings.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.services.CommonService;
import com.school.api.common.utils.ScUtil;
import com.school.api.common.vo.FieldType;
import com.school.api.common.vo.Filter;
import com.school.api.common.vo.Operator;
import com.school.api.error.exception.NotFoundException;
import com.school.api.settings.dto.RoleShortDTO;
import com.school.api.settings.dto.UserDTO;
import com.school.api.settings.dto.UserRequestDTO;
import com.school.api.settings.dto.UserResponseDTO;
import com.school.api.settings.dto.UsersResponseDTO;
import com.school.api.settings.entity.Role;
import com.school.api.settings.entity.User;
import com.school.api.sms.service.DataGenSmsService;

@Service
public class UserService {

	@Autowired
	private CommonService commonService;

	@Autowired
	private DataGenSmsService dataGenSmsService;

	public UsersResponseDTO findAllUsers() {

		UsersResponseDTO res = new UsersResponseDTO();

		List<User> users = commonService.findAll(User.class);

		if (!ScUtil.isAllPresent(users))
			throw new NotFoundException("No users can be found !");

		List<UserDTO> usersDTO = new ArrayList<>();

		users.forEach(user -> {
			usersDTO.add(setUserToDTO(user));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(usersDTO);
		return res;
	}

	public UserDTO setUserToDTO(User user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setEmail(user.getEmail());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setId(user.getId());
		userDTO.setLastName(user.getLastName());
		userDTO.setLinkedId(user.getLinkedId());
		userDTO.setMobile(user.getMobile());
		userDTO.setStatus(user.getStatus());
		userDTO.setUserType(user.getUserType());

		Role role = user.getRole();
		if (ScUtil.isAllPresent(role)) {

			RoleShortDTO roleShortDTO = new RoleShortDTO();
			roleShortDTO.setDesc(role.getDesc());
			roleShortDTO.setId(role.getId());
			roleShortDTO.setName(role.getName());
			userDTO.setRole(roleShortDTO);

		}

		return userDTO;
	}

	public User setDTOToUser(UserRequestDTO userRequestDTO, String id) {

		User user = new User();
		if (ScUtil.isAllPresent(id)) {

			user = commonService.findById(id, User.class);
			if (!ScUtil.isAllPresent(user)) {
				throw new NotFoundException("User not found");
			}
		}

		user.setEmail(userRequestDTO.getEmail());
		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setLinkedId(userRequestDTO.getLinkedId());
		user.setMobile(userRequestDTO.getMobile());
		user.setStatus(userRequestDTO.getStatus());
		user.setUserType(userRequestDTO.getUserType());

		String roleId = userRequestDTO.getRoleId();
		if (ScUtil.isAllPresent(roleId)) {

			Role role = commonService.findById(roleId, Role.class);
			if (!ScUtil.isAllPresent(role)) {
				throw new NotFoundException("Role not found.");
			}
			user.setRole(role);
		} else {
			user.setRole(null);
		}

		return user;
	}

	public UserResponseDTO findUser(String id) {

		UserResponseDTO res = new UserResponseDTO();

		User user = commonService.findById(id, User.class);

		if (!ScUtil.isAllPresent(user))
			throw new NotFoundException("No users can be found !");
		
		UserDTO userDTO = setUserToDTO(user);

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(userDTO);

		return res;
	}

	public User findUserByName(String username) {

		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("email", Operator.EQUAL, FieldType.STRING, username));
		User user = this.commonService.findOne(filters, User.class);
		return user;
	}

	public ActionResponseDTO createOrUpdateUser(UserRequestDTO userRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		User user = setDTOToUser(userRequestDTO, id);
		user = commonService.save(user);

		if (!ScUtil.isAllPresent(id)) {

			String tempPassword = "test";
			user.setPassword(tempPassword);
			dataGenSmsService.sendSms(user.getMobile(), "Greeting from PESL.\nCredential details:\nUserName: "
					+ user.getEmail() + "\nPassword: " + tempPassword + "\n\nYou can login to portal now.");

			commonService.save(user);
		}

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the user's data";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
		} else {
			message = "Successfully created a user";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public ActionResponseDTO deleteUser(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		User user = commonService.findById(id, User.class);

		if (!ScUtil.isAllPresent(user))
			throw new NotFoundException("No users can be found !");

		commonService.delete(user);

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}

}
