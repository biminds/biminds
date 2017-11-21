package com.biminds.sys.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.biminds.entity.SysRoleEntity;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.framework.mvc.mybatis.BaseDao;
import com.biminds.sys.form.SysRoleForm;
import com.github.pagehelper.PageInfo;

/**
 * 权限角色sql
 * 
 * @author bieskei
 * @date 2017年11月11日 下午9:21:51
 * @since 1.0.0
 */
@Repository
public class SysRoleDao extends BaseDao<SysRoleEntity, Serializable> {

	/**
	 * 根据用户ID查询用户角色列表
	 * 
	 * @param userId
	 *            用户ID
	 * @return 角色列表
	 * @author bieskei
	 * @date 2017年11月11日 下午9:32:09
	 */
	public List<SysRoleEntity> getRoleByUserId(String userId) {
		return getSqlSession().selectList("SysRoleDao.getRoleListByUserId",
				userId);
	}

	/**
	 * 根据查询条件查询角色列表
	 * 
	 * @param pageForm
	 *            分页对象
	 * @param sysRoleForm
	 *            查询条件
	 * @return 角色列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午8:58:56
	 */
	public PageInfo<SysRoleEntity> getRoleList(PageForm pageForm,
			SysRoleForm sysRoleForm) {
		return pageFind("SysRoleDao.getRolePage", pageForm, sysRoleForm, true);
	}

	/**
	 * 根据角色名称角色信息
	 * 
	 * @param roleName
	 *            角色名称
	 * @return 角色信息
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午9:24:44
	 */
	public SysRoleEntity getRoleByName(String roleName) {
		return getSqlSession().selectOne("SysRoleDao.getRoleByName", roleName);
	}

	/**
	 * 获取系统所有角色列表
	 * 
	 * @return 角色列表
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午10:32:48
	 */
	public List<SysRoleEntity> getAllRoles() {
		return getSqlSession().selectList("SysRoleDao.getAllRoles");
	}

}
