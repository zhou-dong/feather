package org.feather.common;

import org.junit.Assert;
import org.junit.Test;

public class ClassTest {

	private class TestClass implements Module {

		public boolean init(boolean isReload) {
			return false;
		}

		public void afterCreate(Object[] params) {
		}

		public void start(boolean isReload) {
		}

		public void stop() {
		}

		public boolean isAlive() {
			return false;
		}

	}

	@Test
	public void testImplement() {
		boolean result = Module.class.isAssignableFrom(TestClass.class);
		Assert.assertTrue(result);
	}

	@Test
	public void testGetName() {
		System.out.println(ClassTest.class.getName());
	}
}
