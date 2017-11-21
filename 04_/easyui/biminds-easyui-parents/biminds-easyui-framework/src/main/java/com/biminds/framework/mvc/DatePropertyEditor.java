package com.biminds.framework.mvc;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biminds.framework.util.DateTimeUtils;



public class DatePropertyEditor extends PropertyEditorSupport {

	private static final Logger logger = LoggerFactory.getLogger(DatePropertyEditor.class);

	//	private static Date defaultDate = new Date(0L);

	public String getAsText() {
		return getValue() == null ? "" : DateTimeUtils.formatDateTime((Date) getValue());
	}

	@Override
	public void setAsText(String textValue) {
		if (textValue == null) {
			setValue(null);
			return;
		}
		Date retDate = DateTimeUtils.parseDateWithTry(textValue);
		if (retDate == null) {
			logger.error("Cannot parse {} as date.", textValue);
		} else {
			setValue(retDate);
		}
	}

}
