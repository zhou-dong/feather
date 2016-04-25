package org.feather.simple.app.example;

import java.util.List;

import org.feather.simple.app.Server;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class AppStart {

	public static void main(String[] args) {
		Server server = new Server();
		createMultiHander(server);
		server.register(new EchoHandler());
		server.register(new AddHandler());
		server.start();
	}

	private static void createMultiHander(Server server) {
		RequestHandler handler = new RequestHandler() {
			public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
				List<Object> params = request.getPositionalParams();
				int p0 = Integer.parseInt(params.get(0) + "");
				int p1 = Integer.parseInt(params.get(1) + "");
				return new JSONRPC2Response(p0 * p1, request.getID());
			}

			public String[] handledRequests() {
				return new String[] { "multiply" };
			}
		};
		server.register(handler);
	}

}
