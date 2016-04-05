package org.feather.simple.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.feather.common.app.Client;
import org.junit.Test;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class AppTest {

	public static Start start = null;

	public static void main(String[] args) throws IOException {
		start = new Start();
		addHander();
		start.start();
		start.stop();
	}

	@Test
	public void startServer() throws IOException {
	}

	private static void addHander() {
		RequestHandler handler = new RequestHandler() {
			public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
				List<Object> params = request.getPositionalParams();
				return new JSONRPC2Response(params.get(0), request.getID());
			}

			public String[] handledRequests() {
				return new String[] { "echo" };
			}
		};
		start.register(handler);
	}

	@Test
	public void testClient() {
		for (int i = 0; i < 100; i++) {
			List<Object> params = new ArrayList<Object>();
			params.add("hello world " + i);
			JSONRPC2Response response = Client.request("127.0.0.1",
					new JSONRPC2Request("echo", params, i));
			System.out.println(response.toJSONString());
		}
	}

	@Test
	public void testCloseServer() throws IOException {
		startServer();
		// start.stop();
	}
}
