package org.feather.app;

import org.feather.app.module.ModuleFactory;

public abstract class Start {

	public abstract void registerModules();

	public abstract void addToStartModules();

	public void start() {
		ModuleFactory.registerModule(Server.class);
		ModuleFactory.addToStartModule(Server.class.getName());
		registerModules();
		addToStartModules();
		ModuleFactory.startModules();
	}

}
