package com.denghb.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	public static Date getDate(String source, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
