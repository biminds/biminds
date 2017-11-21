package com.biminds.entity;

import java.io.Serializable;

/**
 * 系统角色权限对象
 * 
 * @author bieskeith.li
 * @date 2017年10月22日 下午8:33:48
 * @since 1.0.0
 */
public class SysRoleResourceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5361055274247477253L;

	/**
	 * 系统角色权限ID
	 */
	private String roleResourceId;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 资源ID
	 */
	private String resourceId;

	public String getRoleResourceId() {
		return roleResourceId == null ? "" : roleResourceId.trim();
	}

	public void setRoleResourceId(String roleResourceId) {
		this.roleResourceId = roleResourceId;
	}

	public String getRoleId() {
		return roleId == null ? "" : roleId.trim();
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId == null ? "" : resourceId.trim();
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
