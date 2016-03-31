package org.feather.search.analysis;

import java.io.IOException;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;
import org.feather.search.store.Thesaurus;

public class SynonymTokenFilter extends TokenFilter {

	private Stack<String> synonyms = null;
	private AttributeSource.State current = null;
	private CharTermAttribute charTermAttribute = null;
	private PositionIncrementAttribute positionIncrementAttribute = null;

	protected SynonymTokenFilter(TokenStream input) {
		super(input);
		synonyms = new Stack<String>();
		charTermAttribute = this.addAttribute(CharTermAttribute.class);
		positionIncrementAttribute = this.addAttribute(PositionIncrementAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		while (!synonyms.isEmpty()) {
			restoreState(current);
			addSynonym(synonyms.pop());
			return true;
		}
		if (!this.input.incrementToken())
			return false;
		if (setSynonyms(charTermAttribute.toString()))
			current = captureState();
		return true;
	}

	private void addSynonym(String synonym) {
		charTermAttribute.setEmpty();
		charTermAttribute.append(synonym);
		positionIncrementAttribute.setPositionIncrement(0);
	}

	private boolean setSynonyms(String word) {
		String[] words = Thesaurus.getSynonyms(word);
		if (words == null || words.length == 0)
			return false;
		for (String synonym : words)
			synonyms.push(synonym);
		return true;
	}

}
