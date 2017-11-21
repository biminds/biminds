package com.biminds.sys.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biminds.entity.SysUserEntity;
import com.biminds.entity.SysUserRoleEntity;
import com.biminds.framework.mvc.form.PageForm;
import com.biminds.framework.util.DateTimeUtils;
import com.biminds.framework.util.HashUtils;
import com.biminds.sys.dao.SysUserDao;
import com.biminds.sys.dao.SysUserRoleDao;
import com.biminds.sys.form.SysUserForm;
import com.biminds.sys.model.SysUserModel;
import com.biminds.sys.service.SysUserService;
import com.github.pagehelper.PageInfo;

/**
 * 系统用户业务逻辑处理实现类
 * 
 * @author bieskei
 * @date 2017年11月12日 上午11:00:10
 * @since 1.0.0
 */
@Service(value = "sysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public PageInfo<SysUserModel> selectUserList(PageForm pageForm,
			SysUserForm sysUserForm) {
		return sysUserDao.getUserList(pageForm, sysUserForm);
	}

	@Override
	public SysUserEntity selectUserByUserName(String userName) {
		return sysUserDao.selectUserByUserName(userName);
	}

	@Override
	public int insertUser(SysUserForm sysUserForm, String roles) {
		int result = 0;
		// 新建系统用户信息
		SysUserEntity user = new SysUserEntity();
		user.setUserId(UUID.randomUUID().toString());
		user.setRealName(sysUserForm.getRealName());
		user.setUserName(sysUserForm.getUserName());
		String saltValue = HashUtils.createRandomSalt();
		user.setPassword(HashUtils.computeSaltedHash(sysUserForm.getPassword(),
				saltValue));
		user.setPasswordSalt(saltValue);
		user.setCreateTime(DateTimeUtils.convertDate(new Date(), 2017));
		user.setEnabled(sysUserForm.getEnabled());
		result = sysUserDao.insertSelective(user);
		// 为用户绑定角色
		if (result != 0) {
			if (StringUtils.isNotBlank(roles) && roles.endsWith(",")) {
				String[] roleArray = roles.split(",");
				if (ArrayUtils.isNotEmpty(roleArray)) {
					for (String roleId : roleArray) {
						SysUserRoleEntity userRole = new SysUserRoleEntity();
						userRole.setUserRoleId(UUID.randomUUID().toString());
						userRole.setRoleId(roleId);
						userRole.setUserId(user.getUserId());
						result = sysUserRoleDao.insertSelective(userRole);
					}
				}
			}
		}
		return result;
	}

	@Override
	public int updateUser(SysUserForm sysUserForm, String roles) {
		int result = 0;
		SysUserEntity user = sysUserDao.selectByPrimaryKey(sysUserForm
				.getUserId());
		if (null != user) {
			user.setRealName(sysUserForm.getRealName());
			user.setEnabled(sysUserForm.getEnabled());
			// 先删除用户之前绑定的角色信息
			sysUserRoleDao.deleteUserRoleByUserId(sysUserForm.getUserId());
			if (StringUtils.isNotBlank(roles) && roles.endsWith(",")) {
				String[] roleArray = roles.split(",");
				if (ArrayUtils.isNotEmpty(roleArray)) {
					for (String roleId : roleArray) {
						SysUserRoleEntity userRole = new SysUserRoleEntity();
						userRole.setUserRoleId(UUID.randomUUID().toString());
						userRole.setRoleId(roleId);
						userRole.setUserId(user.getUserId());
						result = sysUserRoleDao.insertSelective(userRole);
					}
				}
			}
			result = sysUserDao.updateByPrimaryKey(user);
		}
		return result;
	}

	@Override
	public SysUserEntity getUserByUserId(String userId) {
		return sysUserDao.selectByPrimaryKey(userId);
	}

	@Override
	public int updateUser(SysUserEntity sysUserEntity) {
		return sysUserDao.updateByPrimaryKeySelective(sysUserEntity);
	}

}
