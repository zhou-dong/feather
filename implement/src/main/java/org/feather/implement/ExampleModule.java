package org.feather.implement;

import org.feather.common.Module;

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
