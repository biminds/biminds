package com.biminds.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biminds.entity.SysResourceEntity;
import com.biminds.entity.SysUserEntity;
import com.biminds.framework.mvc.model.ResultMessage;
import com.biminds.framework.util.ExceptionUtils;
import com.biminds.sys.service.LoginService;
import com.biminds.sys.service.SysResourceService;

/**
 * 
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午9:25:12
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sys")
public class LoginController {

	@Autowired
	@Qualifier("loginServiceImpl")
	private LoginService loginServiceImpl;

	@Autowired
	@Qualifier("sysResourceServiceImpl")
	private SysResourceService sysResourceServiceImpl;

	/**
	 * 后台管理系统登录
	 * 
	 * @param request
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午10:23:03
	 */
	@ResponseBody
	@RequestMapping(value = "/login")
	public ResultMessage userLogin(HttpServletRequest request, String username,
			String password) {
		// 创建返回信息对象
		ResultMessage result = null;
		try {
			// 判断用户输入登录信息是否有效
			result = loginServiceImpl.userLogin(username, password);
			// 判断是否登陆成功
			if (result.getStatus() == 200) {
				SysUserEntity user = (SysUserEntity) result.getData();
				// 将登录用户信息放入缓存中
				// 方便其他业务处理时
				// 可以直接从上下文获取用户信息
				// 减少不必要的数据库连接操作
				request.getSession().setAttribute("UC", user);
				Map<String, List<SysResourceEntity>> resourceMap = sysResourceServiceImpl
						.getResourceListByUserId(user.getUserId());
				// 将登录用户资源信息放入缓存中
				request.getSession().setAttribute("resourceMap", resourceMap);
			}
		} catch (Exception e) {
			result = ResultMessage.doHttp(500,
					ExceptionUtils.getExceptionName(e));
		}
		return result;
	}

}
