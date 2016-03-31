package org.feather.common.util;

import com.google.gson.Gson;

public class JsonUtil {

	public static <T> T fromJson(String json, Class<T> classOfT) {
		return new Gson().fromJson(json, classOfT);
	}

	public static String toString(Object object) {
		return new Gson().toJson(object);
	}

}
