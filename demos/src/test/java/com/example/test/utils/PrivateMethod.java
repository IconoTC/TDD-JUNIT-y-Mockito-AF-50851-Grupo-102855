package com.example.test.utils;

import java.lang.reflect.InvocationTargetException;

public class PrivateMethod {
	public static Object exec(Object obj, String method, Object... args) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		Class<?> params[] = args.length == 0 ? null : new Class[args.length];
		for(int i = 0; i < args.length; i++)
			params[i] = args[i].getClass();
		var m = obj.getClass().getDeclaredMethod(method, params);
		m.setAccessible(true);		
		return m.invoke(obj, args);
	}
	public static Object exec(Object obj, String method, Class<?> params[], Object... args) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		var m = obj.getClass().getDeclaredMethod(method, params);
		m.setAccessible(true);		
		return m.invoke(obj, args);
	}
}