package com.biminds.entity;

import java.io.Serializable;

/**
 * 权限角色对象
 * 
 * @author bieskeith.li
 * @date 2017年10月22日 下午8:27:29
 * @since 1.0.0
 */
public class SysRoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6619942440633373534L;

	/**
	 * 主键
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	/**
	 * 角色状态：10|启用/20|停用
	 */
	private Integer enabled;

	public String getRoleId() {
		return roleId == null ? "" : roleId.trim();
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName == null ? "" : roleName.trim();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc == null ? "" : roleDesc.trim();
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}
