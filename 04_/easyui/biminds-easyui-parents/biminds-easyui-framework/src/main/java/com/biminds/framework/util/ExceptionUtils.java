package com.biminds.framework.util;

public class ExceptionUtils {

	public static String getExceptionName(Exception ex) {
		return ex.getClass().getSimpleName();
	}

}
