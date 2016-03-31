package org.feather.search.store;

import java.util.HashMap;
import java.util.Map;

public class Thesaurus {

	private static Map<String, String[]> synonymMap = new HashMap<String, String[]>();

	static {
		synonymMap.put("china", new String[] { "dalu", "tianchao" });
		synonymMap.put("i", new String[] { "wo", "zan" });
	}

	public static String[] getSynonyms(String word) {
		return synonymMap.get(word);
	}

}
