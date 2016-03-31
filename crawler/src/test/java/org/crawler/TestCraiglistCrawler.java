package org.crawler;

import org.feather.crawler.CraiglistCrawler;
import org.junit.Test;

public class TestCraiglistCrawler {

	@Test
	public void test() {
		CraiglistCrawler cc = new CraiglistCrawler();
		System.out.println(cc.getNearCitys("https://montgomery.craigslist.org/"));
	}

	@Test
	public void test1() {
		CraiglistCrawler cc = new CraiglistCrawler();
		System.out.println(cc.getAutoUrls("http://athensga.craigslist.org/search/cta"));
	}

	@Test
	public void testRun() {
		CraiglistCrawler cc = new CraiglistCrawler();
		cc.run(CraiglistCrawler.url);
	}

}
