package org.feather.simple.app.example;

import java.util.List;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class AddHandler implements RequestHandler {

	// return service name
	public String[] handledRequests() {
		return new String[] { "add" };
	}

	// handle request
	public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
		List<Object> params = request.getPositionalParams();
		int p0 = Integer.parseInt(params.get(0) + "");
		int p1 = Integer.parseInt(params.get(1) + "");
		return new JSONRPC2Response(p0 + p1, request.getID());
	}

}
