package org.feather.simple.app.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

public class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);

	static String host = "127.0.0.1";

	public static void main(String[] args) {
		singleRequest();
		multiRequest();
	}

	private static void multiRequest() {
		for (int i = 0; i < 100; i++) {
			List<Object> params = new ArrayList<Object>();
			params.add("hello world! " + i);
			JSONRPC2Response response = org.feather.common.app.Client.request(host,
					new JSONRPC2Request("echo", params, i));
			logger.info(response.toJSONString());
		}
	}

	private static void singleRequest() {
		String id = UUID.randomUUID().toString();
		List<Object> params = new ArrayList<Object>();
		params.add("hello world!");
		JSONRPC2Response response = org.feather.common.app.Client.request(host,
				new JSONRPC2Request("echo", params, id));
		logger.info(response.toJSONString());
	}

}
