package org.feather.common;

import org.feather.common.util.StringUtil;

public class Global {

	public static int SERVER_PORT = 9090;
	public static boolean DEVELOP_MODEL = true;
	public static boolean MODULE_NAME_SENSITIVE = false;
	public static String DEVELOP_NAME = "";
	public static String LOCAL_IP = "";

	public static String moduleId(Module module) {
		if (module == null) {
			return "";
		}
		return moduleId(module.getClass().getName());
	}

	public static String moduleId(String id) {
		if (StringUtil.isEmpty(id)) {
			return "";
		}
		if (!MODULE_NAME_SENSITIVE) {
			id = id.toLowerCase();
		}
		if (DEVELOP_MODEL) {
			return id + "@" + DEVELOP_NAME;
		} else {
			return id;
		}
	}

}
