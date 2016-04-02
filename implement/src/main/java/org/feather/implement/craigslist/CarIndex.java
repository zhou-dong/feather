package org.feather.implement.craigslist;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.feather.crawler.Auto;
import org.feather.crawler.CraiglistCrawler;
import org.feather.search.index.LuceneIndex;

public class CarIndex extends LuceneIndex {

	public CarIndex() {
		super("/Users/dongdong/Workspaces/index");
	}

	private int flushIndex = 0;

	private void bulkWrite() throws IOException {
		if (flushIndex >= 50) {
			getWriter().flush();
			getWriter().commit();
			flushIndex = 0;
		}
	}

	public void createIndex() throws IOException, InterruptedException {
		CraiglistCrawler craiglistCrawler = new CraiglistCrawler();
		Thread thread = new Thread(craiglistCrawler);
		thread.start();
		while (craiglistCrawler.isFinish == false) {
			if (craiglistCrawler.autos.isEmpty()) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			getWriter().addDocument(createDocument(craiglistCrawler.autos.poll()));
			bulkWrite();
			System.out.print("--- " + craiglistCrawler.count + " ---");
			if (craiglistCrawler.count >= 10) {
				thread.join();
				craiglistCrawler.isFinish = true;
			}
		}
	}

	private Document createDocument(Auto auto) {
		Document document = new Document();
		document.add(new TextField("title", auto.getTitle(), Store.YES));
		document.add(new TextField("description", auto.getDescription(), Store.YES));
		document.add(new TextField("name", auto.getAutoName(), Store.YES));
		document.add(new LongField("postedTime", auto.getPostedTime(), Store.YES));
		document.add(new LongField("updatedTime", auto.getUpdatedTime(), Store.YES));
		document.add(new DoubleField("price", auto.getPrice(), Store.YES));
		for (Map.Entry<String, String> entry : auto.getAutoInfo().entrySet())
			document.add(new TextField(entry.getKey(), entry.getValue(), Store.YES));
		return document;
	}

}
