package org.feather.search;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum Lucene {

	AUTO("/Users/dongdong/Workspaces/index");

	private static Logger logger = LoggerFactory.getLogger(Lucene.class);

	private Lucene(String indexPath) {
		this.indexPath = indexPath;
		this.analyzer = new StandardAnalyzer();
		init();
	}

	private String indexPath;
	private IndexWriter indexWriter;
	private Directory directory;
	private Analyzer analyzer;
	private SearcherManager searcherManager;

	private void init() {
		try {
			directory = FSDirectory.open(Paths.get(indexPath));
			indexWriter = new IndexWriter(directory, new IndexWriterConfig(analyzer));
			searcherManager = new SearcherManager(indexWriter, new SearcherFactory());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public IndexWriter getWriter() {
		return this.indexWriter;
	}

	public IndexSearcher getSearcher() {
		try {
			return searcherManager.acquire();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public void releaseSearcher(IndexSearcher indexSearcher) {
		if (indexSearcher == null)
			return;
		try {
			searcherManager.release(indexSearcher);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void commit() {
		try {
			indexWriter.commit();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}