package com.biminds.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.biminds.entity.SysRoleEntity;
import com.biminds.framework.constant.OperateConstant;
import com.biminds.framework.exception.HandlerException;
import com.biminds.framework.mvc.BaseController;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.framework.mvc.model.ResultMessage;
import com.biminds.sys.form.SysRoleForm;
import com.biminds.sys.service.SysRoleService;
import com.github.pagehelper.PageInfo;

/**
 * 角色管理
 * 
 * @author bieskeith.li
 * @date 2017年11月13日 下午8:56:54
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/sys/role")
public class SysRoleController extends BaseController {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SysRoleController.class);

	@Autowired
	@Qualifier(value = "sysRoleServiceImpl")
	private SysRoleService sysRoleServiceImpl;

	/**
	 * 根据查询条件查询角色列表
	 * 
	 * @param pageForm
	 *            分页对象
	 * @param sysRoleForm
	 *            查询条件
	 * @return 角色列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:04:18
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Map<String, Object> getRoleList(PageForm pageForm,
			SysRoleForm sysRoleForm) {
		logger.debug("根据查询条件查询角色列表，查询条件：" + JSON.toJSONString(sysRoleForm));
		// 查询角色列表
		PageInfo<SysRoleEntity> pageInfo = sysRoleServiceImpl.getRoleList(
				pageForm, sysRoleForm);
		// 创建返回信息
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", pageInfo.getList());
		jsonMap.put("total", pageInfo.getTotal());
		return jsonMap;
	}

	/**
	 * 校验角色名称是否已存在
	 * 
	 * @param roleName
	 *            待校验角色名称
	 * @return 角色信息
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:25:29
	 */
	@ResponseBody
	@RequestMapping(value = "/check")
	public Boolean checkRole(String roleName) {
		logger.debug("校验角色名称是否已存在，待校验名称：" + roleName);
		SysRoleEntity role = sysRoleServiceImpl.getRoleByName(roleName);
		return null != role ? true : false;
	}

	/**
	 * 编辑或新建角色信息
	 * 
	 * @param sysRoleForm
	 *            角色信息
	 * @param operate
	 *            update|编辑\create|新建
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:37:54
	 */
	@ResponseBody
	@RequestMapping(value = "/update")
	public ResultMessage doRoleUpdate(SysRoleForm sysRoleForm, String operate) {
		logger.debug("编辑或新建角色信息，角色信息：" + JSON.toJSONString(sysRoleForm));
		int result = 0;
		ResultMessage msg = new ResultMessage();
		if (operate.equals("create")) {
			result = sysRoleServiceImpl.insertRole(sysRoleForm);
		} else {
			result = sysRoleServiceImpl.updateRole(sysRoleForm);
		}
		if (result == 0) {
			throw new HandlerException(OperateConstant.OPERATE_FAILURE);
		}
		return msg;
	}

	/**
	 * 获取全部系统角色
	 * 
	 * @return 角色列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:36:48
	 */
	@ResponseBody
	@RequestMapping(value = "/all")
	public List<SysRoleEntity> doRoleAll() {
		logger.debug("获取全部系统角色");
		return sysRoleServiceImpl.getAllRoles();
	}

}
