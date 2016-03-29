package org.feather.common;

import org.feather.common.app.Request;
import org.feather.common.app.Response;
import org.junit.Test;

public class TestRequest {

	@Test
	public void testToJson() {
		Request request = new Request();
		request.setClassName(TestRequest.class.getName());
		request.setIp("127.0.0.1");
		request.setMethodName("testToJson");
		request.setParams(new Integer[] { 1, 3, 5, 7, 8 });
		String result = Request.toString(request);
		System.out.println(result);
		Request request1 = Request.parse(result);
		System.out.println(request1.getClassName());
		System.out.println(request1.getIp());
		System.out.println(request1.getMethodName());
		System.out.println(request1.getParams());
	}

	@Test
	public void testResponse() {
		Response response = new Response();
		response.setContent("great");
		response.setIp("127.0.0.1");
		response.setSuccess(true);
		String result = Response.toString(response);
		System.out.println(result);
		Response response1 = Response.parse(result);
		System.out.println(response1.getContent());
		System.out.println(response1.getIp());
		System.out.println(response1.isSuccess());
	}

}
