package com.school.api.settings.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.api.common.dto.ActionResponseDTO;
import com.school.api.common.dto.ApiUtilDTO;
import com.school.api.common.utils.ScUtil;
import com.school.api.error.exception.NotFoundException;
import com.school.api.settings.dto.MenuDTO;
import com.school.api.settings.dto.MenuResponseDTO;
import com.school.api.settings.dto.MenusResponseDTO;
import com.school.api.settings.dto.SubMenuDTO;
import com.school.api.settings.entity.Menu;
import com.school.api.settings.entity.SubMenu;
import com.school.api.settings.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public MenusResponseDTO findAllMenus(String role) {

		MenusResponseDTO res = new MenusResponseDTO();
		
		List<Menu> menus = new ArrayList<Menu>();

		if(ScUtil.isAllPresent(role)) {
			menus = menuRepository.findByRole(role);
		}else {
			menus = (List<Menu>) menuRepository.findAll();
		}	

		if (!ScUtil.isAllPresent(menus))
			throw new NotFoundException("No menus can be found !");

		List<MenuDTO> menusDTO = new ArrayList<>();

		menus.forEach(menu -> {
			menusDTO.add(setMenuToDTO(menu));
		});

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(menusDTO);
		return res;
	}

	public MenuDTO setMenuToDTO(Menu menu) {

		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setHasSubmenu(menu.getHasSubmenu());
		menuDTO.setIcon(menu.getIcon());
		menuDTO.setId(menu.getId());
		menuDTO.setOrder(menu.getOrder());
		menuDTO.setPath(menu.getPath());
		menuDTO.setTitle(menu.getTitle());

		List<SubMenu> subMenus = menu.getSubMenus();

		if (ScUtil.isAllPresent(subMenus)) {

			List<SubMenuDTO> subMenusDTO = new ArrayList<>();

			subMenus.forEach(subMenu -> {

				SubMenuDTO subMenuDTO = new SubMenuDTO();
				subMenuDTO.setIcon(subMenu.getIcon());
				subMenuDTO.setId(subMenu.getId());
				subMenuDTO.setOrder(subMenu.getOrder());
				subMenuDTO.setPath(subMenu.getPath());
				subMenuDTO.setTitle(subMenu.getPath());
				subMenusDTO.add(subMenuDTO);
			});
			menuDTO.setSubMenus(subMenusDTO);
		}

		return menuDTO;
	}

	public Menu setDTOToMenu(MenuDTO menuDTO, String id) {

		Menu menu = new Menu();
		if (ScUtil.isAllPresent(id)) {

			Optional<Menu> menuOpt = menuRepository.findById(id);
			
			if(menuOpt.isPresent()) {
				menu = menuOpt.get();
			} else {
				throw new NotFoundException("Menu not found");
			}
		}

		menu.setHasSubmenu(menuDTO.getHasSubmenu());
		menu.setIcon(menuDTO.getIcon());
		menu.setOrder(menuDTO.getOrder());
		menu.setPath(menuDTO.getPath());
		menu.setTitle(menuDTO.getTitle());

		List<SubMenuDTO> subMenusDTO = menuDTO.getSubMenus();

		if (ScUtil.isAllPresent(subMenusDTO)) {

			if (ScUtil.isAllPresent(menu.getSubMenus())) {
				menu.getSubMenus().clear();
			}

			List<SubMenu> subMenus = new ArrayList<>();

			subMenusDTO.forEach(subMenuDTO -> {

				SubMenu subMenu = new SubMenu();
				subMenu.setIcon(subMenuDTO.getIcon());
				subMenu.setId(subMenuDTO.getId());
				subMenu.setOrder(subMenuDTO.getOrder());
				subMenu.setPath(subMenuDTO.getPath());
				subMenu.setTitle(subMenuDTO.getPath());
				subMenus.add(subMenu);
			});
			menu.setSubMenus(subMenus);
		}

		return menu;
	}

	public ActionResponseDTO createOrUpdateMenu(MenuDTO menuDTO, String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Menu menu = setDTOToMenu(menuDTO, id);

		menuRepository.save(menu);

		String message = "";
		if (ScUtil.isAllPresent(id)) {
			message = "Successfully updated the menu data";
			res.setApiMessage(ApiUtilDTO.okMessage(message));
		} else {
			message = "Successfully created a menu";
			res.setApiMessage(ApiUtilDTO.createdMessage(message));
			res.setActionMessage(message);
		}

		return res;
	}

	public MenuResponseDTO findMenu(String id) {

		MenuResponseDTO res = new MenuResponseDTO();

		Optional<Menu> menuOpt = menuRepository.findById(id);

		if (!menuOpt.isPresent())
			throw new NotFoundException("No menu can be found !");

		MenuDTO menuDTO = setMenuToDTO(menuOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Success"));
		res.setData(menuDTO);

		return res;
	}
	
	public ActionResponseDTO deleteMenu(String id) {

		ActionResponseDTO res = new ActionResponseDTO();

		Optional<Menu> menuOpt = menuRepository.findById(id);

		if (!menuOpt.isPresent())
			throw new NotFoundException("No menu can be found !");

		menuRepository.delete(menuOpt.get());

		res.setApiMessage(ApiUtilDTO.okMessage("Successfully deleted"));
		return res;
	}
}
