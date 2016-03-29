package org.feather.app;

import org.feather.common.app.Client;
import org.feather.common.app.Request;
import org.feather.common.app.Response;
import org.junit.Test;

public class ClientTest {

	@Test
	public void testClient() {
		Request request = new Request();
		request.setClassName(Server.class.getName());
		request.setMethodName("isAlive");
		for (int i = 0; i < 100; i++) {
			Response response = Client.request("127.0.0.1", request);
			System.out.println(Response.toString(response));
		}
	}

}
