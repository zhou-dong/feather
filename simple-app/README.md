# Socket Server

- Multiple Threads
- [JSON-RPC Protocol](http://www.jsonrpc.org/specification) or https://en.wikipedia.org/wiki/JSON-RPC

## Service register

create handler

```java
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
```

register handler to server

```java
server.register(new EchoHandler());
```

server start

```java
Server server = new Server();
server.register(new EchoHandler());
server.start();
```

## client request

```Java
List<Object> params = new ArrayList<Object>();
params.add("hello world!");
JSONRPC2Response response = Client.request("127.0.0.1", new JSONRPC2Request("echo", params, i));
```
