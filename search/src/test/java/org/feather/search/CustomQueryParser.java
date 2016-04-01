package org.feather.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;

public class CustomQueryParser extends QueryParser {

	public CustomQueryParser(String f, Analyzer a) {
		super(f, a);
	}

	@Override
	protected org.apache.lucene.search.Query getWildcardQuery(String field, String termStr)
			throws ParseException {
		// return super.getWildcardQuery(field, termStr);
		throw new ParseException("由于性能原因，禁止通配符查询");
	}

	@Override
	protected org.apache.lucene.search.Query getFuzzyQuery(String field, String termStr,
			float minSimilarity) throws ParseException {
		// return super.getFuzzyQuery(field, termStr, minSimilarity);
		throw new ParseException("由于性能原因，禁止模糊查询");
	}

}
