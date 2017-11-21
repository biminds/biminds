package com.biminds.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biminds.framework.mvc.BaseController;

/**
 * 系统基础信息信息管理
 * 
 * @author bieskei
 * @date 2017年11月1日 下午11:02:02
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sys/web")
public class SysWebController extends BaseController {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SysWebController.class);

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
	@RequestMapping("/user/list")
	public String goUserList(HttpServletRequest request, String menuId) {
		request.setAttribute("menuId", menuId);
		return "sys/user/list";
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
	@RequestMapping("/role/list")
	public String goRoleList(HttpServletRequest request, String menuId) {
		request.setAttribute("menuId", menuId);
		return "sys/role/list";
	}

}
