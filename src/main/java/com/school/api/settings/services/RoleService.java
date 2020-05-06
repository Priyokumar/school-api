package com.school.api.settings.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.services.CommonService;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;
import com.school.api.settings.dto.MenuDTO;
import com.school.api.settings.dto.RoleDTO;
import com.school.api.settings.dto.RoleRequestDTO;
import com.school.api.settings.dto.RoleResponseDTO;
import com.school.api.settings.dto.RoleShortDTO;
import com.school.api.settings.dto.RolesResponseDTO;
import com.school.api.settings.dto.SubMenuDTO;
import com.school.api.settings.entity.Menu;
import com.school.api.settings.entity.Role;
import com.school.api.settings.entity.SubMenu;

@Service
public class RoleService {

	@Autowired
	private CommonService commonService;

	public RolesResponseDTO findAllRoles() {

		RolesResponseDTO res = new RolesResponseDTO();

		List<Role> roles = commonService.findAll(Role.class);

		if (!ScUtil.isAllPresent(roles))
			throw new NotFoundException("No role can be found !");
		
		List<RoleShortDTO> rolesShortDTO = new ArrayList<>();

		roles.forEach(role -> {
			rolesShortDTO.add(setRoleToDTO(role));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(rolesShortDTO);
		return res;
	}
	
	public RoleShortDTO setRoleToDTO(Role role) {

		RoleShortDTO roleShortDTO = new RoleShortDTO();

		roleShortDTO.setDesc(role.getDesc());
		roleShortDTO.setId(role.getId());
		roleShortDTO.setName(role.getName());

		return roleShortDTO;
	}

	public Role setDTOToRole(RoleRequestDTO roleRequestDTO, String id) {

		Role role = new Role();
		if (ScUtil.isAllPresent(id)) {

			role = commonService.findById(id, Role.class);
			if (!ScUtil.isAllPresent(role)) {
				throw new NotFoundException("Role not found");
			}
		}

		List<String> menuIds = roleRequestDTO.getMenuIDs();
		role.setDesc(roleRequestDTO.getDesc());
		role.setName(roleRequestDTO.getName());

		if (ScUtil.isAllPresent(menuIds)) {
			
			if (ScUtil.isAllPresent(role.getMenus())) {
				role.getMenus().clear();
			}
			List<Menu> menus = new ArrayList<>();

			menuIds.forEach(menuId -> {
					Menu menu = commonService.findById(menuId, Menu.class);
					menus.add(menu);
			});
			role.setMenus(menus);
		}else {
			role.setMenus(new ArrayList<Menu>());
		}

		return role;
	}

	public ActionResponseDTO createOrUpdateRole(RoleRequestDTO roleRequestDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();
		Role role = setDTOToRole(roleRequestDTO, id);
		commonService.save(role);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the role's data";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
		} else {
			message = "Successfully created a role";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public RoleResponseDTO findRole(String id) {

		RoleResponseDTO res = new RoleResponseDTO();

		Role role = commonService.findById(id, Role.class);

		if (!ScUtil.isAllPresent(role))
			throw new NotFoundException("No role can be found !");

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setDesc(role.getDesc());
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		
		
		
		List<Menu> menus = role.getMenus();
		
		if(ScUtil.isAllPresent(menus)) {
			List<MenuDTO> menusDTO = new ArrayList<MenuDTO>();
			menus.forEach(menu->{
				
				MenuDTO menuDTO = new MenuDTO();
				menuDTO.setHasSubmenu(menu.getHasSubmenu());
				menuDTO.setIcon(menu.getIcon());
				menuDTO.setId(menu.getId());
				menuDTO.setOrder(menu.getOrder());
				menuDTO.setPath(menu.getPath());
				menuDTO.setTitle(menu.getTitle());
				
				List<SubMenu> subMenus = menu.getSubMenus();
				
				if(ScUtil.isAllPresent(subMenus)) {
					
					List<SubMenuDTO> subMenusDTO = new ArrayList<>();
					
					subMenus.forEach(subMenu->{
						
						SubMenuDTO subMenuDTO = new SubMenuDTO();
						subMenuDTO.setIcon(subMenu.getIcon());
						subMenuDTO.setId(subMenu.getId());
						subMenuDTO.setOrder(subMenu.getOrder());
						subMenuDTO.setPath(subMenu.getPath());
						subMenuDTO.setTitle(subMenu.getTitle());
						subMenusDTO.add(subMenuDTO);
					});
					menuDTO.setSubMenus(subMenusDTO);
				}
				menusDTO.add(menuDTO);
			});
			roleDTO.setMenus(menusDTO);
		}
		res.setData(roleDTO);
		return res;
	}

	public ActionResponseDTO deleteRole(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Role role = commonService.findById(id, Role.class);

		if (!ScUtil.isAllPresent(role))
			throw new NotFoundException("No role can be found !");

		commonService.delete(role);

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
