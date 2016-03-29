package org.feather.common.app;

public class Response {

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
