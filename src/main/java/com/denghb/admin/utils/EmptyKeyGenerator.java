package com.denghb.admin.utils;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class EmptyKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		return "";
	}

}
