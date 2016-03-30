package org.feather.search;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.Test;

public class TestAnalyzer {

	String str = "I am from China! My name is Dong Zhou. my QQ number is 1234567 and my email address is 1234567@gmail.com";

	@Test
	public void testToken1() throws IOException {
		displayToken(str, new StandardAnalyzer());
		displayToken(str, new StopAnalyzer());
		displayToken(str, new SimpleAnalyzer());
		displayToken(str, new WhitespaceAnalyzer());
	}

	@Test
	public void testToken2() throws IOException {
		displayAllTokenInfo(str, new StandardAnalyzer());
		displayAllTokenInfo(str, new StopAnalyzer());
		displayAllTokenInfo(str, new SimpleAnalyzer());
		displayAllTokenInfo(str, new WhitespaceAnalyzer());
	}

	public void displayToken(String str, Analyzer analyzer) throws IOException {
		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(str));
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			int startOffset = offsetAttribute.startOffset();
			int endOffset = offsetAttribute.endOffset();
			String term = charTermAttribute.toString();
			System.out.print(startOffset + "-" + endOffset + " " + term + " ++++ ");
		}
		System.out.println();
	}

	public void displayAllTokenInfo(String str, Analyzer analyzer) throws IOException {
		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(str));
		PositionIncrementAttribute positionIncrementAttribute = tokenStream
				.addAttribute(PositionIncrementAttribute.class);
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			StringBuilder builder = new StringBuilder();
			int startOffset = offsetAttribute.startOffset();
			int endOffset = offsetAttribute.endOffset();
			String term = charTermAttribute.toString();
			int positionIncrement = positionIncrementAttribute.getPositionIncrement();
			String type = typeAttribute.type();
			builder.append("term: [").append(term).append("] ");
			builder.append("start: ").append(startOffset).append(" ");
			builder.append("end: ").append(endOffset).append(" ");
			builder.append("positionIncrement: <").append(positionIncrement).append("> ");
			builder.append("type: ").append(type).append(" ");
			builder.append(" | ");
			System.out.print(builder.toString());
		}
		System.out.println();
	}
}
