package com.biminds.framework.mvc;

import java.beans.PropertyEditorSupport;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.biminds.framework.exception.HandlerException;
import com.biminds.framework.mvc.model.ResultMessage;
import com.biminds.framework.util.ExceptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	/**
	 * 判断是否为Ajax请求
	 * 
	 * @param request
	 *            request
	 * @return true:是; false:否;
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");

		if (logger.isDebugEnabled()) {
			logger.debug(requestType);
		}

		return requestType != null;
	}

	/**
	 * 将系统错误信息转为Json并输出到客户端
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param msg
	 *            ErrorMessage
	 */
	private void printErrorMessage(HttpServletResponse response,
			ResultMessage msg) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");

		try {
			ObjectMapper mapper = new ObjectMapper();
			String msgJson = mapper.writeValueAsString(msg);

			PrintWriter out = response.getWriter();
			out.print(msgJson);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 提供对系统异常的统一处理，URL请求返回异常页面，Ajax请求返回json数据
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param ex
	 *            Exception
	 * @return ModelAndView
	 */
	@ExceptionHandler({ Exception.class })
	protected ModelAndView exception(HttpServletRequest request,
			HttpServletResponse response, Exception ex) {
		if (logger.isWarnEnabled()) {
			logger.warn("异常信息", ex);
		}

		ResultMessage msg = new ResultMessage();
		msg.setSuccess(false);
		msg.setIsException(true);
		msg.setExceptionName(ExceptionUtils.getExceptionName(ex));
		msg.setMessage(ex.getMessage());

		// 设置错误代码
		if (ex instanceof HandlerException) {
			HandlerException exTmp = (HandlerException) ex;
			if (exTmp.getErrorCode() != null) {
				msg.setErrorCode(((HandlerException) ex).getErrorCode());
			}
		}

		if (isAjaxRequest(request)) {
			printErrorMessage(response, msg);
			return null;
		} else {
			ModelAndView model = new ModelAndView();
			model.setViewName("/error");
			model.addObject("errorMsg", msg);
			model.addObject("exception", ex);
			return model;
		}
	}

	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(StringUtils.isBlank(text) ? null
						: htmlSpecialChars(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
	}

	/**
	 * 防止xss
	 * 
	 * @param str
	 * @return
	 * @author bieskeith.li
	 * @date 2017年10月24日 下午9:23:14
	 */
	private String htmlSpecialChars(String str) {
		return str;
	}
}
