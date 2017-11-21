package com.biminds.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biminds.entity.SysRoleEntity;
import com.biminds.framework.mvc.BaseController;
import com.biminds.sys.service.SysRoleService;

/**
 * 系统基础信息信息管理
 * 
 * @author bieskei
 * @date 2017年11月1日 下午11:02:02
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/sys/web")
public class SysWebController extends BaseController {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SysWebController.class);

	@Autowired
	@Qualifier("sysRoleServiceImpl")
	private SysRoleService sysRoleServiceImpl;

	/**
	 * 用户管理页面跳转
	 * 
	 * @param request
	 *            请求对象
	 * @param menuId
	 *            菜单ID
	 * @return 用户管理
	 * @author bieskei
	 * @date 2017年11月1日 下午11:05:21
	 */
	@RequestMapping(value = "/user/list")
	public String goUserList(HttpServletRequest request, String menuId) {
		logger.debug("用户管理页面跳转");
		request.setAttribute("menuId", menuId);
		return "sys/user/user_list";
	}

	/**
	 * 用户编辑页面跳转
	 * 
	 * @return 用户编辑
	 * @author bieskei
	 * @date 2017年11月11日 下午7:38:33
	 */
	@RequestMapping(value = "/user/update")
	public String goUserUpdate(String operate, HttpServletRequest request) {
		logger.debug("用户编辑页面跳转");
		String roles = "";
		if ("update".equals(operate)) {
			String userId = request.getParameter("userId");
			List<SysRoleEntity> roleList = sysRoleServiceImpl
					.getRoleListByUserId(userId);
			if (CollectionUtils.isNotEmpty(roleList)) {
				for (SysRoleEntity role : roleList) {
					roles += role.getRoleId() + ",";
				}
			}
			request.setAttribute(
					"roles",
					StringUtils.isNotBlank(roles) ? roles.substring(0,
							roles.length() - 1) : "");
		}
		request.setAttribute("operate", operate);
		return "/sys/user/user_update";
	}

	/**
	 * 修改密码页面跳转
	 * 
	 * @return 修改密码页面
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:39:44
	 */
	@RequestMapping(value = "/user/password/update")
	public String goUpdatePassword() {
		logger.debug("修改密码页面跳转");
		return "/sys/user/password_update";
	}

	/**
	 * 角色管理页面跳转
	 * 
	 * @param request
	 *            请求对象
	 * @param menuId
	 *            菜单ID
	 * @return 角色管理
	 * @author bieskei
	 * @date 2017年11月1日 下午11:06:10
	 */
	@RequestMapping(value = "/role/list")
	public String goRoleList(HttpServletRequest request, String menuId) {
		logger.debug("角色管理页面跳转");
		request.setAttribute("menuId", menuId);
		return "sys/role/role_list";
	}

	/**
	 * 角色编辑页面跳转
	 * 
	 * @param request
	 *            请求对象
	 * @param operate
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:12:39
	 */
	@RequestMapping(value = "/role/update")
	public String goRoleUpdate(HttpServletRequest request, String operate) {
		logger.debug("角色编辑页面跳转");
		request.setAttribute("operate", operate);
		return "sys/role/role_update";
	}

	/**
	 * 权限分配页面跳转
	 * 
	 * @param request
	 *            请求对象
	 * @param roleId
	 *            角色ID
	 * @return 权限分配页面跳转
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:45:38
	 */
	@RequestMapping(value = "/resource/allocate")
	public String goAllocateAuth(HttpServletRequest request, String roleId) {
		logger.debug("权限分配页面跳转，待分配角色ID：" + roleId);
		request.setAttribute("roleId", roleId);
		return "sys/resource/resource_update";
	}

}
