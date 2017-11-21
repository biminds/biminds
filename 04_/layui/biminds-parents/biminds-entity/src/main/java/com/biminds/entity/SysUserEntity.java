package com.biminds.entity;

import java.io.Serializable;

/**
 * 系统用户对象
 * 
 * @author bieskeith.li
 * @date 2017年10月22日 下午8:35:42
 * @since 1.0.0
 */
public class SysUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2039643462981685385L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 密码盐
	 */
	private String passwordSalt;

	/**
	 * 上次登录IP地址
	 */
	private String lastIp;

	/**
	 * 是否可用：10|启用/20|停用
	 */
	private Integer enabled;

	/**
	 * 创建时间
	 */
	private String createTime;

	public String getUserId() {
		return userId == null ? "" : userId.trim();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName == null ? "" : userName.trim();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName == null ? "" : realName.trim();
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password == null ? "" : password.trim();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt == null ? "" : passwordSalt.trim();
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getLastIp() {
		return lastIp == null ? "" : lastIp.trim();
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getCreateTime() {
		return createTime == null ? "" : createTime.trim();
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
