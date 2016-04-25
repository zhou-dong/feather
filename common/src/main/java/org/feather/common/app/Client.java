package org.feather.common.app;

import org.feather.common.Global;
import org.feather.common.util.SocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;

public class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);

	public static JSONRPC2Response request(String host, JSONRPC2Request request) {
		return request(host, Global.serverPort, request);
	}

	public static JSONRPC2Response request(String host, int port, JSONRPC2Request request) {
		String response = SocketUtil.request(host, port, request.toJSONString());
		try {
			return JSONRPC2Response.parse(response);
		} catch (JSONRPC2ParseException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static Response request(String host, Request request) {
		return request(host, Global.serverPort, request);
	}

	public static Response request(String host, int port, Request request) {
		String response = SocketUtil.request(host, port, Request.toString(request));
		return Response.parse(response);
	}

}
