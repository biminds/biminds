package com.biminds.entity;

import java.io.Serializable;

/**
 * 系统用户角色对象
 * 
 * @author bieskeith.li
 * @date 2017年10月22日 下午8:39:19
 * @since 1.0.0
 */
public class SysUserRoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1305229134910752938L;

	/**
	 * 系统用户角色ID
	 */
	private String userRoleId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 角色ID
	 */
	private String roleId;

	public String getUserRoleId() {
		return userRoleId == null ? "" : userRoleId.trim();
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserId() {
		return userId == null ? "" : userId.trim();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId == null ? "" : roleId.trim();
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
