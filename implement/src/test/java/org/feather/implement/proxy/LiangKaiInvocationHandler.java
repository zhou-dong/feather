package org.feather.implement.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LiangKaiInvocationHandler implements InvocationHandler {

	private Object obj;

	public LiangKaiInvocationHandler(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		try {
			if (method.getName().equals("getName")) {
				System.out.println("prepared transaction!");
				result = (Object) (method.invoke(obj, args).toString() + " ha ha.");
				System.out.println("transaction finished.");
				return result;
			}
			result = method.invoke(obj, args);
		} catch (InvocationTargetException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
