package com.biminds.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.biminds.entity.SysRoleEntity;
import com.biminds.entity.SysUserEntity;
import com.biminds.framework.constant.OperateConstant;
import com.biminds.framework.exception.HandlerException;
import com.biminds.framework.mvc.BaseController;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.framework.mvc.model.ResultMessage;
import com.biminds.framework.util.HashUtils;
import com.biminds.sys.form.SysUserForm;
import com.biminds.sys.model.SysUserModel;
import com.biminds.sys.service.SysRoleService;
import com.biminds.sys.service.SysUserService;
import com.github.pagehelper.PageInfo;

/**
 * 系统配置管理业务逻辑管理
 * 
 * @author bieskei
 * @date 2017年11月12日 上午9:36:17
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/sys/user")
public class SysUserController extends BaseController {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SysUserController.class);

	@Autowired
	@Qualifier(value = "sysUserServiceImpl")
	private SysUserService sysUserServiceImpl;

	@Autowired
	@Qualifier(value = "sysRoleServiceImpl")
	private SysRoleService sysRoleServiceImpl;

	/**
	 * 根据查询条件查询系统用户列表
	 * 
	 * @param pageForm
	 *            分页对象
	 * @param sysUserForm
	 *            查询条件
	 * @return 用户列表
	 * @author bieskei
	 * @date 2017年11月12日 上午11:36:25
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public Map<String, Object> doUserList(PageForm pageForm,
			SysUserForm sysUserForm) {
		logger.debug("根据查询条件查询系统用户列表,查询条件：" + JSON.toJSONString(sysUserForm));
		PageInfo<SysUserModel> sysUser = sysUserServiceImpl.selectUserList(
				pageForm, sysUserForm);
		// 获取查询列表
		List<SysUserModel> userList = sysUser.getList();
		// get user's role list
		if (CollectionUtils.isNotEmpty(userList)) {
			for (SysUserModel user : userList) {
				// 角色名称
				String roles = "";
				List<SysRoleEntity> roleList = sysRoleServiceImpl
						.getRoleListByUserId(user.getUserId());
				if (CollectionUtils.isNotEmpty(roleList)) {
					for (SysRoleEntity role : roleList) {
						roles += role.getRoleName() + "|";
					}
				}
				user.setRoleName(StringUtils.isNotBlank(roles) ? roles
						.substring(0, roles.length() - 1) : "");
			}
		}
		// 构建返回信息
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", sysUser.getTotal());
		jsonMap.put("rows", sysUser.getList());
		return jsonMap;
	}

	/**
	 * 根据用户名校验用户是否已存在
	 * 
	 * @param userName
	 *            用户名
	 * @return true 已存在 false 不存在
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:04:11
	 */
	@ResponseBody
	@RequestMapping(value = "/check")
	public Boolean doUserCheck(String userName) {
		logger.debug("根据用户名校验用户是否已存在");
		SysUserEntity user = sysUserServiceImpl.selectUserByUserName(userName);
		return null != user ? true : false;

	}

	/**
	 * 新建或编辑用户信息
	 * 
	 * @param sysUserForm
	 *            用户信息
	 * @param operate
	 *            create|新建\ update|编辑
	 * @param roles
	 *            用户角色
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:04:49
	 */
	@ResponseBody
	@RequestMapping(value = "/update")
	public ResultMessage doUserUpdate(SysUserForm sysUserForm, String operate,
			String roles) {
		logger.debug("新建或编辑用户信息，用户信息：" + JSON.toJSONString(sysUserForm));
		ResultMessage msg = new ResultMessage();
		int result = 0;
		if (operate.equals("create")) {
			result = sysUserServiceImpl.insertUser(sysUserForm, roles);
		} else {
			result = sysUserServiceImpl.updateUser(sysUserForm, roles);
		}
		if (result == 0) {
			throw new HandlerException(OperateConstant.OPERATE_FAILURE);
		}
		return msg;

	}

	/**
	 * 校验用户原始密码是否正确
	 * 
	 * @param request
	 *            请求对象
	 * @param oldPass
	 *            原始密码
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:41:45
	 */
	@ResponseBody
	@RequestMapping(value = "/pass/check")
	public Boolean doPassCheck(HttpServletRequest request, String oldPass) {
		logger.debug("校验用户原始密码是否正确");
		SysUserEntity user = (SysUserEntity) request.getSession().getAttribute(
				"UC");
		String checkPass = HashUtils.computeSaltedHash(oldPass,
				user.getPasswordSalt());
		return checkPass.equals(user.getPassword()) ? true : false;
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param newPass
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:45:57
	 */
	@ResponseBody
	@RequestMapping(value = "/pass/update")
	public ResultMessage doPassUpdate(HttpServletRequest request, String newPass) {
		logger.debug("修改密码");
		int result = 0;
		ResultMessage msg = new ResultMessage();
		SysUserEntity user = (SysUserEntity) request.getSession().getAttribute(
				"UC");
		SysUserEntity userinfo = sysUserServiceImpl.getUserByUserId(user
				.getUserId());
		if (null != userinfo) {
			userinfo.setPassword(HashUtils.computeSaltedHash(newPass,
					userinfo.getPasswordSalt()));
		}
		result = sysUserServiceImpl.updateUser(userinfo);
		if (result == 0) {
			throw new HandlerException(OperateConstant.OPERATE_FAILURE);
		}
		return msg;
	}

}
