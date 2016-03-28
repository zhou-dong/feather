package org.feather.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.feather.common.Global;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		for (int i = 0; i < 1000; i++) {

			Socket socket = new Socket("127.0.0.1", Global.SERVER_PORT);

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			String answer = reader.readLine();

			System.out.println(answer);

			socket.close();
		}
	}

}
