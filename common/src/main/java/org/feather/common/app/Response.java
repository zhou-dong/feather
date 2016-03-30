package org.feather.common.app;

import org.feather.common.util.JsonUtil;
import org.feather.common.util.StringUtil;

public class Response {

	public static Response parse(String message) {
		if (StringUtil.isEmpty(message))
			return null;
		return JsonUtil.fromJson(message, Response.class);
	}

	public static String toString(Response response) {
		if (response == null)
			return "";
		return JsonUtil.toString(response);
	}

	private boolean success;
	private String ip;
	private Object content;

	public Response() {
	}

	public Response(boolean success, Object content) {
		this.success = success;
		this.content = content;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
