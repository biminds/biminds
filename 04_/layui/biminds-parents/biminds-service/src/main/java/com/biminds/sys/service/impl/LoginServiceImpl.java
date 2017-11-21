package com.biminds.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biminds.entity.SysUserEntity;
import com.biminds.framework.constant.BimindsConstant;
import com.biminds.framework.mvc.model.ResultMessage;
import com.biminds.framework.util.HashUtils;
import com.biminds.sys.dao.SysUserDao;
import com.biminds.sys.service.LoginService;

/**
 * 登录相关业务处理逻辑实现类
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午9:54:48
 * @since 1.0.0
 */
@Service(value="loginServiceImpl")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public ResultMessage userLogin(String username, String password) {

		// 根据用户名查询系统用户是否存在
		SysUserEntity user = sysUserDao.selectUserByUserName(username);
		// 判断用户是否存在
		if (null == user) {
			return ResultMessage.doHttp(400, BimindsConstant.LOGIN_ERROR);
		}
		// 根据密码盐判断用户是否输入正确密码
		// 根据用户密码盐生成用户登录输入密码加密
		String saltPassword = HashUtils.computeSaltedHash(password,
				user.getPasswordSalt());
		// 比较加密后密码是否一致
		if (!user.getPassword().equals(saltPassword)) {
			return ResultMessage.doHttp(400, BimindsConstant.LOGIN_ERROR);
		}
		// 如果用户登录名密码正确
		// 判断用户是否启用
		if (user.getEnabled() != 10) {
			return ResultMessage.doHttp(400, BimindsConstant.LOGIN_DISABLED);
		}
		return ResultMessage.success(user);
	}

}
