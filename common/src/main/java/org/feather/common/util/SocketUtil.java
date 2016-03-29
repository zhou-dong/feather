package org.feather.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.feather.common.app.Request;
import org.feather.common.app.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketUtil {

	private static Logger logger = LoggerFactory.getLogger(SocketUtil.class);

	public static void sendRequest(Socket socket, Request request) {
		PrintWriter out = getWriter(socket);
		if (out == null)
			return;
		out.println(Request.toString(request));
		out.flush();
	}

	public static void sendResponse(Socket socket, Response response) {
		PrintWriter out = getWriter(socket);
		if (out == null)
			return;
		out.println(Response.toString(response));
		out.flush();
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

	private static PrintWriter getWriter(Socket socket) {
		try {
			return new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}
