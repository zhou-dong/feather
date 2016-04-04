package org.feather.implement;

import org.feather.common.app.Client;
import org.feather.common.app.Request;
import org.feather.common.app.Response;
import org.feather.implement.ExampleModule;
import org.junit.Test;

public class ExampleModuleTest {

	@Test
	public void test() throws InterruptedException {
		Request request = new Request();
		request.setClassName(ExampleModule.class.getName());
		request.setMethodName("echo");
		request.setParams(new Object[] { "ni hao a" });
		Response response = Client.request("127.0.0.1", request);
		System.out.println(Response.toString(response));
	}

}
