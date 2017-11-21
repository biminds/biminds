package com.biminds.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biminds.entity.SysRoleEntity;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.sys.dao.SysRoleDao;
import com.biminds.sys.form.SysRoleForm;
import com.biminds.sys.service.SysRoleService;
import com.github.pagehelper.PageInfo;

/**
 * 角色管理逻辑处理实现类
 * 
 * @author bieskei
 * @date 2017年11月11日 下午7:43:01
 * @since 1.0.0
 */
@Service(value = "sysRoleServiceImpl")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public List<SysRoleEntity> getRoleListByUserId(String userId) {
		return sysRoleDao.getRoleByUserId(userId);
	}

	@Override
	public PageInfo<SysRoleEntity> getRoleList(PageForm pageForm,
			SysRoleForm sysRoleForm) {
		return sysRoleDao.getRoleList(pageForm, sysRoleForm);
	}

	@Override
	public SysRoleEntity getRoleByName(String roleName) {
		return sysRoleDao.getRoleByName(roleName);
	}

	@Override
	public int insertRole(SysRoleForm sysRoleForm) {
		SysRoleEntity role = new SysRoleEntity();
		role.setRoleId(UUID.randomUUID().toString());
		role.setRoleName(sysRoleForm.getRoleName());
		role.setEnabled(10);
		role.setRoleDesc(sysRoleForm.getRoleDesc());
		return sysRoleDao.insert(role);
	}

	@Override
	public int updateRole(SysRoleForm sysRoleForm) {
		SysRoleEntity role = sysRoleDao.selectByPrimaryKey(sysRoleForm
				.getRoleId());
		role.setRoleName(sysRoleForm.getRoleName());
		role.setRoleDesc(sysRoleForm.getRoleDesc());
		role.setEnabled(sysRoleForm.getEnabled());
		return sysRoleDao.updateByPrimaryKey(role);
	}

	@Override
	public List<SysRoleEntity> getAllRoles() {
		return sysRoleDao.getAllRoles();
	}

}
