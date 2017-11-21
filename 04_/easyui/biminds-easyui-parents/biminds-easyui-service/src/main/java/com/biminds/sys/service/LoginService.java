package com.biminds.sys.service;

import com.biminds.framework.mvc.model.ResultMessage;

/**
 * 登录相关业务处理逻辑
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午9:56:51
 * @since 1.0.0
 */
public interface LoginService {

	/**
	 * 用户登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午9:57:58
	 */
	public ResultMessage userLogin(String username, String password);
}
