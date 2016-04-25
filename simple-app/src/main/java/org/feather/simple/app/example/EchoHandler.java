package org.feather.simple.app.example;

import java.util.List;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class EchoHandler implements RequestHandler {

	// return service name
	public String[] handledRequests() {
		return new String[] { "echo" };
	}

	// handle request
	public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
		// get all parameters
		List<Object> params = request.getPositionalParams();
		return new JSONRPC2Response(params.get(0), request.getID());
	}

}
