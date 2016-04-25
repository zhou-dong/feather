package org.feather.simple.app.example;

import org.feather.simple.app.Server;

public class AppStart {

	public static void main(String[] args) {
		Server server = new Server();
		server.register(new EchoHandler());
		server.register(new AddHandler());
		server.start();
	}

}
