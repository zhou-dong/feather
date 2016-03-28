package org.feather.app;

import org.feather.common.Module;
import org.junit.Test;

public class ServerTest {

	private class StopThread implements Runnable {
		Module server = null;

		public StopThread(Module server) {
			this.server = server;
		}

		public void run() {
			System.out.println("server stop");
			server.stop();
		}
	}

	private class StartThread implements Runnable {
		Module server = null;

		public StartThread(Module server) {
			this.server = server;
		}

		public void run() {
			System.out.println("server start");
			server.afterCreate(null);
			server.init(false);
			server.start(false);
		}
	}

	@Test
	public void testStartAndStop() {
		Module server = new Server();
		new StartThread(server).run();
		new StopThread(server).run();
	}

}
