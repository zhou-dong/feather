package org.feather.simple.app;

import java.util.ArrayList;
import java.util.List;

import org.feather.common.app.Client;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

public class AppTest {

	private static Logger logger = LoggerFactory.getLogger(AppTest.class);

	@Test
	public void testEchoClient() {
		for (int i = 0; i < 100; i++) {
			List<Object> params = new ArrayList<Object>();
			params.add("hello world " + i);
			JSONRPC2Response response = Client.request("127.0.0.1",
					new JSONRPC2Request("echo", params, i));
			logger.info(response.toJSONString());
		}
	}

	@Test
	public void testAddClient() {
		for (int i = 0; i < 100; i++) {
			List<Object> params = new ArrayList<Object>();
			params.add(i);
			params.add(i + 1);
			JSONRPC2Response response = Client.request("127.0.0.1",
					new JSONRPC2Request("add", params, i));
			logger.info(response.toJSONString());
		}
	}

	@Test
	public void testTimeClient() {
		for (int i = 0; i < 100; i++) {
			List<Object> params = new ArrayList<Object>();
			params.add(i);
			params.add(i + 1);
			JSONRPC2Response response = Client.request("127.0.0.1",
					new JSONRPC2Request("multiply", params, i));
			logger.info(response.toJSONString());
		}
	}

}
