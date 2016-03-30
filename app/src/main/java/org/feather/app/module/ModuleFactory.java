package org.feather.app.module;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.feather.common.Global;
import org.feather.common.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModuleFactory {

	private static final Logger logger = LoggerFactory.getLogger(ModuleFactory.class);

	private static Set<String> toStartModules = new HashSet<String>();
	private static Map<String, ModuleProxy> moduleProxies = new HashMap<String, ModuleProxy>();

	public static void addToStartModule(String module) {
		toStartModules.add(Global.moduleId(module));
	}

	public static void addToStartModule(String module, Set<String> ips) {
		if (ips.contains(Global.localIP)) {
			addToStartModule(module);
		}
	}

	public static Module module(String id) {
		if (moduleProxies.containsKey(id))
			return (Module) (moduleProxies.get(id).instance);
		return null;
	}

	public static void registerModule(Class<?> moduleClass) {
		moduleProxies.put(Global.moduleId(moduleClass), new ModuleProxy(moduleClass));
	}

	public static void registerModule(Class<?> moduleClass, Object[] params) {
		moduleProxies.put(Global.moduleId(moduleClass), new ModuleProxy(moduleClass, params));
	}

	@SuppressWarnings("unchecked")
	public static void registerModule(String moduleClass) {
		Class<?> cls = null;
		try {
			cls = Class.forName(moduleClass);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			return;
		}
		if (Module.class.isAssignableFrom(cls)) {
			registerModule((Class<Module>) cls);
		} else {
			logger.error(moduleClass + " not implements from module interface");
		}
	}

	public static void startModules() {
		for (String toStartModule : toStartModules) {
			ModuleProxy moduleProxy = moduleProxies.get(toStartModule);
			if (moduleProxy == null)
				logErrorAndExit("module[{}] has not registered.", toStartModule);
			Module module = moduleProxy.createModule();
			if (module == null)
				logErrorAndExit("module[{}] can not be created.", toStartModule);
			if (!module.init(false))
				logErrorAndExit("module[{}]  init fail", toStartModule);
			module.start(false);
			if (!module.isAlive())
				logErrorAndExit("module[{}]  start fail", toStartModule);
			logger.info("===> Load module[{}] Ok.", toStartModule);
		}
	}

	private static void logErrorAndExit(String format, String argument) {
		logger.error(format, argument);
		System.exit(-1);
	}

}
