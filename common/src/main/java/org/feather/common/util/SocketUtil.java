package org.feather.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.feather.common.app.Request;
import org.feather.common.app.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketUtil {

	private static Logger logger = LoggerFactory.getLogger(SocketUtil.class);

	public static void sendRequest(Socket socket, Request request) {
		sendMessage(socket, Request.toString(request));
	}

	public static void sendResponse(Socket socket, Response response) {
		sendMessage(socket, Response.toString(response));
	}

	public static void sendMessage(Socket socket, String message) {
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(message);
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static String getMessage(Socket socket) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			return in.readLine();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}

	public static String request(String host, int port, String request) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			SocketUtil.sendMessage(socket, request);
			return SocketUtil.getMessage(socket);
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			FileUtil.close(socket);
		}
		return "";
	}
}
