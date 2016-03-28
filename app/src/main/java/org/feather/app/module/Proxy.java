package org.feather.app.module;

public class Proxy {

	public Class<?> cls = null;
	public Object instance = null;

	public Proxy(Class<?> cls) {
		this.cls = cls;
	}

	public void createInstance() throws InstantiationException, IllegalAccessException {
		instance = cls.newInstance();
	}

}
