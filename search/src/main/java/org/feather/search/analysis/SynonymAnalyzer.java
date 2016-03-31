package org.feather.search.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;

public class SynonymAnalyzer extends Analyzer {

	public static CharArraySet stopwords = StopAnalyzer.ENGLISH_STOP_WORDS_SET;

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new StandardTokenizer();
		TokenStream filter = new LowerCaseFilter(tokenizer);
		filter = new SynonymTokenFilter(filter);
		filter = new StopFilter(filter, stopwords);
		return new TokenStreamComponents(tokenizer, filter);
	}

}
