package org.feather.common.app;

import org.feather.common.Global;
import org.feather.common.util.JsonUtil;
import org.feather.common.util.StringUtil;

public class Request {

	public static Request parse(String message) {
		if (StringUtil.isEmpty(message))
			return null;
		return JsonUtil.fromJson(message, Request.class);
	}

	public static String toString(Request request) {
		if (request == null)
			return "";
		return JsonUtil.toString(request);
	}

	public Request() {
	}

	private String ip;
	private String className;
	private String methodName;
	private Object[] params;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = Global.moduleId(className);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
