package com.biminds.sys.service;

import java.util.List;

import com.biminds.entity.SysRoleEntity;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.sys.form.SysRoleForm;
import com.github.pagehelper.PageInfo;

/**
 * 权限角色管理
 * 
 * @author bieskei
 * @date 2017年11月11日 下午7:40:57
 * @since 1.0.0
 */
public interface SysRoleService {

	/**
	 * 根据用户ID查询用户角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户角色列表
	 * @author bieskei
	 * @date 2017年11月11日 下午7:42:02
	 */
	public List<SysRoleEntity> getRoleListByUserId(String userId);

	/**
	 * 根据查询条件查询角色列表
	 * 
	 * @param pageForm
	 *            分页对象
	 * @param sysRoleForm
	 *            查询条件
	 * @return 角色列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:00:14
	 */
	public PageInfo<SysRoleEntity> getRoleList(PageForm pageForm,
			SysRoleForm sysRoleForm);

	/**
	 * 根据角色名称查询角色信息
	 * 
	 * @param roleName
	 *            角色名称
	 * @return 角色信息
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:23:22
	 */
	public SysRoleEntity getRoleByName(String roleName);

	/**
	 * 新增角色
	 * 
	 * @param sysRoleForm
	 *            新建角色信息
	 * @return 角色创建状态
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:30:40
	 */
	public int insertRole(SysRoleForm sysRoleForm);

	/**
	 * 更新角色信息
	 * 
	 * @param sysRoleForm
	 *            角色新信息
	 * @return 角色更新状态
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:32:09
	 */
	public int updateRole(SysRoleForm sysRoleForm);

	/**
	 * 获取系统所有角色
	 * 
	 * @return 角色列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:31:48
	 */
	public List<SysRoleEntity> getAllRoles();
}
