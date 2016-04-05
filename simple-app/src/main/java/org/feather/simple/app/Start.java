package org.feather.simple.app;

import java.io.IOException;

import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class Start {

	Server server = null;

	public Start() throws IOException {
		server = new Server();
	}

	public void register(RequestHandler handler) {
		server.register(handler);
	}

	public void start() {
		server.start();
	}

	public void stop() {
		server.stop();
	}
}
