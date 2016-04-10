package org.feather.simple.app;

import java.util.ArrayList;
import java.util.List;

import org.feather.common.app.Client;
import org.junit.Test;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

public class AppTest {

	@Test
	public void testClient() {
		for (int i = 0; i < 100; i++) {
			List<Object> params = new ArrayList<Object>();
			params.add("hello world " + i);
			JSONRPC2Response response = Client.request("127.0.0.1",
					new JSONRPC2Request("echo", params, i));
			System.out.println(response.toJSONString());
		}
	}

}
