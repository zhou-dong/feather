package org.feather.simple.app;

import java.io.IOException;
import java.util.List;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class AppStart {

	public static Start start = null;

	public static void main(String[] args) throws IOException {
		start = new Start();
		addEchoHander();
		addPrintHander();
		start.start();
		start.stop();
	}

	private static void addEchoHander() {
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

	private static void addPrintHander() {
		RequestHandler handler = new RequestHandler() {
			public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
				List<Object> params = request.getPositionalParams();
				return new JSONRPC2Response(params.get(0), request.getID());
			}

			public String[] handledRequests() {
				return new String[] { "print" };
			}
		};
		start.register(handler);
	}

}
