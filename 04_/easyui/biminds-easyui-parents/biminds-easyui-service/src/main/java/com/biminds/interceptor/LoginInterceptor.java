package com.biminds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.biminds.entity.SysUserEntity;

/**
 * 后台管理系统业务拦截
 * 
 * @author bieskeith.li
 * @date 2017年10月24日 下午8:59:58
 * @since 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 后台管理系统拦截业务逻辑
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 获取当前登录用户系统
		// UC为当前用户登录时存入session中的key
		SysUserEntity user = (SysUserEntity) request.getSession().getAttribute(
				"UC");
		// 如果当前用户未登录
		if (null == user) {
			//
			String url = request.getScheme();
			String serverName = request.getServerName();
			int serverPort = request.getServerPort();
			String contextPath = request.getContextPath();
			String redirectUrl = "";
			if (serverPort == 80) {
				redirectUrl = url + "://" + serverName + "/" + contextPath;
			} else {
				redirectUrl = url + "://" + serverName + ":" + serverPort + "/"
						+ contextPath;
			}
			response.sendRedirect(redirectUrl);
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

}
