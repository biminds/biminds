package com.biminds.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biminds.framework.mvc.BaseController;

/**
 * 
 * 
 * @author bieskeith.li
 * @date 2017年10月22日 下午8:47:37
 * @since 1.0.0
 */
@Controller
@RequestMapping("/")
public class IndexWebController extends BaseController {

	/**
	 * 登录页跳转
	 * 
	 * @Title: goIndex
	 * @param request
	 * @return 设定文件
	 * @author bieskeith.li
	 * @date 2017年10月22日 下午8:51:38
	 */
	@RequestMapping(value = "")
	public String goIndex(HttpServletRequest request) {
		return "/index";
	}

	/**
	 * 登录成功页面跳转
	 * 
	 * @Title: goMain
	 * @param request
	 * @return 设定文件
	 * @author bieskeith.li
	 * @date 2017年10月29日 下午3:48:27
	 */
	@RequestMapping(value = "main")
	public String goMain(HttpServletRequest request) {
		return "/main";
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @return
	 * @author bieskeith.li
	 * @date 2017年11月13日 下午11:50:11
	 */
	@RequestMapping(value = "/logout")
	public String userLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
}
