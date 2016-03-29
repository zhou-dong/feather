package org.feather.common.app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.feather.common.Global;
import org.feather.common.util.FileUtil;
import org.feather.common.util.SocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);

	public static Response request(String host, Request request) {
		return request(host, Global.SERVER_PORT, request);
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
