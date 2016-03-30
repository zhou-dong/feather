package org.feather.search.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class SynonymAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer tokenizer = new StandardTokenizer();
		TokenStream filter = new LowerCaseFilter(tokenizer);
		filter = new SynonymTokenFilter(filter);
		return new TokenStreamComponents(tokenizer, filter);
	}

}
