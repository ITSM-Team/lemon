package com.mossle.auth.support;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Query;

import com.mossle.api.menu.MenuConnector;
import com.mossle.api.menu.MenuDTO;
import com.mossle.api.tenant.TenantHolder;
import com.mossle.api.userauth.UserAuthConnector;
import com.mossle.auth.persistence.domain.AuthUserRole;
import com.mossle.auth.persistence.domain.Menu;
import com.mossle.auth.persistence.domain.UserStatus;
import com.mossle.auth.persistence.manager.MenuManager;

import org.activiti.engine.impl.cmd.GetHistoricIdentityLinksForTaskCmd;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来通过用户权限来动态生成菜单.
 */
public class MenuConnectorImpl implements MenuConnector {
	private static Logger logger = LoggerFactory.getLogger(MenuConnectorImpl.class);
	private MenuManager menuManager;
	private UserAuthConnector userAuthConnector;
	private TenantHolder tenantHolder;
	private MenuCache menuCache;

	/**
	 * systemCode算子系统的标识，比如个人事务子系统，账号子系统.
	 */
	public List<MenuDTO> findMenus(String systemCode, String userId) {
		String tenantId = tenantHolder.getTenantId();
		List<MenuDTO> menuDtos = this.menuCache.findByCode(systemCode);

		if (menuDtos == null) {
			String hql = "from Menu where menu.display='true' and menu.type='system' and menu.code=? order by priority";
			List<Menu> menus = menuManager.find(hql, systemCode);
			menuDtos = this.convertMenuDtos(menus, false);
			this.menuCache.updateByCode(systemCode, menuDtos);
		}

		List<String> permissions = userAuthConnector.findById(userId, tenantId).getPermissions();

		return this.filterMenuDtos(menuDtos, permissions, false);
	}

	/**
	 * 根据权限查询全部的主菜单
	 */
	@SuppressWarnings("unchecked")
	public List<MenuDTO> findMainMenu(String userId) {
		List<MenuDTO> menuDtos = this.menuCache.findEntries();
		List<UserStatus> userStatus = menuManager.find("from UserStatus user where user.ref=?", userId);
		String sql = "from AuthUserRole role where role.userStatusId=? and role.roleId=1";
		List<AuthUserRole> authUserRole = menuManager.find(sql, userStatus.get(0).getId());
		String hql = "from Menu where type<>'index' and display='true' and menu.id is null";
		List<Menu> menus = menuManager.find(hql);
		if (authUserRole.size() > 0) {
			menuDtos = this.convertMainMenuDtos(menus, true);
		} else {
			Menu oneMenu = menus.get(0);
			menus.clear();
			menus.add(0, oneMenu);
			menuDtos = this.convertMainMenuDtos(menus, true);
		}
		this.menuCache.updateEntries(menuDtos);
		return menuDtos;
	}

	/**
	 * 获得所有子系统的入口，比如账号子系统.
	 */
	public List<MenuDTO> findSystemMenus(String userId) {
		String tenantId = tenantHolder.getTenantId();
		List<MenuDTO> menuDtos = this.menuCache.findEntries();

		if (menuDtos == null) {
			List<Menu> menus = menuManager.find("from Menu where type='entry' and display='true'");
			menuDtos = this.convertMenuDtos(menus, true);
			this.menuCache.updateEntries(menuDtos);
		}

		List<String> permissions = userAuthConnector.findById(userId, tenantId).getPermissions();
		return this.filterMenuDtos(menuDtos, permissions, true);
	}

	/**
	 * 将子菜单menu转换为menuDto.
	 */
	public List<MenuDTO> convertMenuDtos(List<Menu> menus, boolean excludeModule) {
		List<MenuDTO> menuDtos = new ArrayList<MenuDTO>();
		for (Menu menu : menus) {
			if (!("true".equals(menu.getDisplay()))) {
				continue;
			}

			if (excludeModule && "module".equals(menu.getType())) {
				continue;
			}
			MenuDTO menuDto = this.convertMenuDto(menu, excludeModule);
			menuDtos.add(menuDto);
		}
		return menuDtos;
	}

	/**
	 * 主菜单menu转换menuDto
	 */

	public List<MenuDTO> convertMainMenuDtos(List<Menu> menus, boolean excludeModule) {
		List<MenuDTO> menuDtos = new ArrayList<MenuDTO>();
		if (menus.size() > 0) {
			for (Menu menu : menus) {
				MenuDTO menuDto = this.convertMenuDto(menu, excludeModule);
				menuDtos.add(menuDto);
			}
		}
		return menuDtos;
	}

	/**
	 * 子菜单menu数据复制给MenuDto
	 */
	public List<MenuDTO> convertSubmenuDtos(List<Menu> menus, boolean excludeModule) {
		List<MenuDTO> menuDtos = new ArrayList<MenuDTO>();
		if (menus.size() > 0) {
			for (Menu menu : menus) {
				MenuDTO menuDto = this.convertMenuDto(menu, excludeModule);
				menuDtos.add(menuDto);
			}
		}
		return menuDtos;
	}

	/**
	 * 把menu数据复制给dto.
	 */
	public MenuDTO convertMenuDto(Menu menu, boolean excludeModule) {
		MenuDTO menuDto = new MenuDTO();
		menuDto.setCode(menu.getCode());
		menuDto.setTitle(menu.getTitle());
		// 添加菜单id
		menuDto.setId(menu.getId());
		// 为了jsp里使用方便，要去掉url前面的/
		menuDto.setUrl(this.processUrl(menu.getUrl()));

		// if (menu.getPerm() != null) {
		// menuDto.setPermission(menu.getPerm().getCode());
		// }

		menuDto.setType(menu.getType());

		String hql = "from Menu where menu.display='true' and menu.id=?";
		@SuppressWarnings("unchecked")
		List<Menu> menus = menuManager.find(hql, menu.getId());
		if (menus.size() > 0) {
			List<MenuDTO> menuDtos = this.convertSubmenuDtos(menus, excludeModule);
			menuDto.setChildren(menuDtos);
		}
		return menuDto;
	}

	/**
	 * 按个人权限过滤菜单.
	 */
	public List<MenuDTO> filterMenuDtos(List<MenuDTO> menuDtos, List<String> permissions, boolean excludeModule) {
		List<MenuDTO> result = new ArrayList<MenuDTO>();

		for (MenuDTO menuDto : menuDtos) {
			if (excludeModule && "module".equals(menuDto.getType())) {
				continue;
			}

			if ((!permissions.contains("*")) && (!permissions.contains(menuDto.getPermission()))) {
				logger.debug("permissions : {}", permissions);
				logger.debug("skip : {}", menuDto.getPermission());

				continue;
			}

			MenuDTO item = this.filterMenuDto(menuDto, permissions, excludeModule);
			result.add(item);
		}

		return result;
	}

	/**
	 * 把menu数据复制给dto.
	 */
	public MenuDTO filterMenuDto(MenuDTO menuDto, List<String> permissions, boolean excludeModule) {
		MenuDTO item = new MenuDTO();
		item.setCode(menuDto.getCode());
		item.setTitle(menuDto.getTitle());
		// 添加菜单id
		item.setId(menuDto.getId());
		// 为了jsp里使用方便，要去掉url前面的/
		item.setUrl(this.processUrl(menuDto.getUrl()));
		List<MenuDTO> children = this.filterMenuDtos(menuDto.getChildren(), permissions, excludeModule);
		item.setChildren(children);
		return item;
	}

	/**
	 * 如果url以/开头，要去掉/，这样前端jsp渲染的时候就方便多了.
	 */
	public String processUrl(String url) {
		if (url == null) {
			return "";
		}

		if (url.length() == 0) {
			return "";
		}

		if (url.charAt(0) != '/') {
			return url;
		}

		for (int i = 0; i < url.length(); i++) {
			if (url.charAt(i) != '/') {
				return url.substring(i);
			}
		}

		return "";
	}

	@Resource
	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}

	@Resource
	public void setUserAuthConnector(UserAuthConnector userAuthConnector) {
		this.userAuthConnector = userAuthConnector;
	}

	@Resource
	public void setTenantHolder(TenantHolder tenantHolder) {
		this.tenantHolder = tenantHolder;
	}

	@Resource
	public void setMenuCache(MenuCache menuCache) {
		this.menuCache = menuCache;
	}

}
