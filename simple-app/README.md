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

## Client request

```Java
String host = "127.0.0.1" ;
String method = "echo" ;
List<Object> params = new ArrayList<Object>();
params.add("hello world!");
int id = 0;
JSONRPC2Response response = Client.request(host, new JSONRPC2Request(method, params, id));
```
