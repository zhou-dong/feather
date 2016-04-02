package org.feather.search;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

public class TestLucene {

	@Test
	public void testMultiSearch() throws ParseException, IOException {
		for (int i = 0; i < 100; i++) {
			search("title", "dodge");
		}
	}

	private void search(String field, String queryString) throws ParseException, IOException {
		IndexSearcher searcher = Lucene.AUTO.getSearcher();
		QueryParser parser = new QueryParser(field, new StandardAnalyzer());
		Query query = parser.parse(queryString);
		TopDocs topDocs = searcher.search(query, 15);
		displayDocs(topDocs);
		Lucene.AUTO.releaseSearcher(searcher);
	}

	private void displayDocs(TopDocs topDocs) throws IOException {
		for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
			Document document = Lucene.AUTO.getSearcher().doc(scoreDoc.doc);
			System.out.println(document.get("title"));
		}
	}

}
