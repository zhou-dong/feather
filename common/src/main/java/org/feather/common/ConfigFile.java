package org.feather.common;

import java.util.ResourceBundle;

import org.feather.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigFile {

	private static Logger logger = LoggerFactory.getLogger(ConfigFile.class);

	ResourceBundle rb = null;

	public ConfigFile(String fileName) {
		this.rb = ResourceBundle.getBundle(fileName);
	}

	public String getItem(String item, String defaultValue) {
		if (rb == null) {
			return defaultValue;
		}
		String value = null;
		try {
			value = rb.getString(item.trim()).trim();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return defaultValue;
		}
		return StringUtil.isEmpty(value) ? defaultValue : value;
	}

}
