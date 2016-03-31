package org.feather.common;

import org.feather.common.util.StringUtil;

public class Global {

	public static int serverPort = 9090;
	public static boolean developMode = true;
	public static boolean moduleNameSensitive = false;
	public static String developName = null;
	public static String localIP = null;
	public static String localName = null;

	static {
		localIP = StringUtil.getLocalIP();
		localName = StringUtil.getLocalName();
		developName = localName;
	}

	public static String moduleId(Class<?> cls) {
		if (cls == null) {
			return "";
		}
		return moduleId(cls.getName());
	}

	public static String moduleId(String id) {
		if (StringUtil.isEmpty(id)) {
			return "";
		}
		if (!moduleNameSensitive) {
			id = id.toLowerCase();
		}
		if (developMode) {
			return id + "@" + developName;
		} else {
			return id;
		}
	}

}
