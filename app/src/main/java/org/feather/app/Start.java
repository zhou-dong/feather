package org.feather.app;

import org.feather.app.module.ModuleFactory;

public class Start {

	public static void registerModules() {
		ModuleFactory.registerModule(Server.class);
	}

	public static void addToStartModules() {
		ModuleFactory.addToStartModule(Server.class.getName());
	}

	public static void startModules() {
		ModuleFactory.startModules();
	}

	public static void main(String[] args) {
		registerModules();
		addToStartModules();
		startModules();
	}

}
