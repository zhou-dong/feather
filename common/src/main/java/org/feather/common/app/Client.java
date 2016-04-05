package org.feather.common.app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.feather.common.Global;
import org.feather.common.util.FileUtil;
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

	private static JSONRPC2Response request(String host, int port, JSONRPC2Request request) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			SocketUtil.sendMessage(socket, request.toJSONString());
			return JSONRPC2Response.parse(SocketUtil.getMessage(socket));
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (JSONRPC2ParseException e) {
			logger.error(e.getMessage(), e);
		} finally {
			FileUtil.close(socket);
		}
		return null;
	}

	public static Response request(String host, Request request) {
		return request(host, Global.serverPort, request);
	}

	private static Response request(String host, int port, Request request) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			SocketUtil.sendRequest(socket, request);
			return Response.parse(SocketUtil.getMessage(socket));
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			FileUtil.close(socket);
		}
		return null;
	}

}
