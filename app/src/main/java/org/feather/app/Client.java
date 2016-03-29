package org.feather.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.feather.common.Global;
import org.feather.common.app.Request;

public class Client {

	private static BufferedReader reader;
	private static PrintWriter out;

	public static void main(String[] args) throws UnknownHostException, IOException {

		for (int i = 0; i < 1000; i++) {

			Socket socket = new Socket("127.0.0.1", Global.SERVER_PORT);

			Request request = new Request();
			request.setClassName(Server.class.getName());
			request.setMethodName("isAlive");
			out = new PrintWriter(socket.getOutputStream(), true);

			out.println(Request.toString(request));
			out.flush();
			// out.close();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String answer = reader.readLine();

			System.out.println(answer);

			reader.close();
			out.close();
			socket.close();
		}
	}

}
