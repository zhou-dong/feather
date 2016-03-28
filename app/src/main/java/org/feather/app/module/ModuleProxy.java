package org.feather.app.module;

import org.feather.common.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleProxy extends Proxy {

	static final Logger logger = LoggerFactory.getLogger(ModuleProxy.class);

	public ModuleProxy(Class<?> cls) {
		super(cls);
	}

	public ModuleProxy(Class<?> cls, Object[] params) {
		this(cls);
		this.params = params;
	}

	private Object[] params;

	public Module createModule() {
		try {
			super.createInstance();
			if (params != null) {
				((Module) instance).afterCreate(params);
			}
			return (Module) instance;
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
