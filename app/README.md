# Socket Server

- Multiple Threads
- Proxy
- Java Reflection

```Java
private Object callModule(Module module, String method, Object[] args) {
	return Reflect.on(module).call(method, args);
}
```

Module example

```Java
public class ExampleModule implements Module {
	private boolean alive = false;
	public void afterCreate(Object[] params) {
	}
	public boolean init(boolean isReload) {
		return true;
	}
	public void start(boolean isReload) {
		alive = true;
	}
	public void stop() {
	}
	public boolean isAlive() {
		return alive;
	}
	public String echo(String param) {
		return "Hello: " + param;
	}
}
```

Server start

```Java
public class AppStart extends Start {
	@Override
	public void registerModules() {
	}
	@Override
	public void addToStartModules() {
	}
	public static void main(String[] args) {
		Start start = new AppStart();
		start.start();
	}
}
```