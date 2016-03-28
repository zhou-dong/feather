package org.feather.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket("127.0.0.1", 9090);

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String answer = reader.readLine();

		System.out.println(answer);

		socket.close();
	}

}
