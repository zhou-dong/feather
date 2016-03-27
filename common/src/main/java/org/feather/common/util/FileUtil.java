package org.feather.common.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				logger.error("close IO error", e);
			}
		}
	}

}
