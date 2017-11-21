package com.biminds.sys.model;

import com.biminds.entity.SysUserEntity;

/**
 * 系统用户查询结果
 * 
 * @author bieskei
 * @date 2017年11月12日 上午10:53:25
 * @since 1.0.0
 */
public class SysUserModel extends SysUserEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8220536669101497208L;

	/**
	 * 用户角色名称
	 */
	private String roleName;

	/**
	 * 用户角色ID
	 */
	private String roleId;

	public String getRoleName() {
		return roleName == null ? "" : roleName.trim();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId == null ? "" : roleId.trim();
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
