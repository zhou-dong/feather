package org.feather.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.feather.common.Global;
import org.feather.common.Module;
import org.feather.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server implements Module, Runnable {

	private static Logger logger = LoggerFactory.getLogger(Server.class);

	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private boolean isAlive;
	private Thread thread;

	public void afterCreate(Object[] params) {
	}

	public boolean init(boolean isReload) {
		try {
			serverSocket = new ServerSocket(Global.SERVER_PORT);
			threadPool = Executors.newCachedThreadPool();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public void start(boolean isReload) {
		isAlive = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		isAlive = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		threadPool.shutdown();
		FileUtil.close(serverSocket);
	}

	public boolean isAlive() {
		return isAlive;
	}

	private class Handler implements Runnable {
		Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			PrintWriter out = null;
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(new Date().toString());
				out.flush();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} finally {
				out.close();
				FileUtil.close(socket);
			}
		}
	}

	public void run() {
		while (!thread.isInterrupted() && isAlive) {
			try {
				threadPool.execute(new Handler(serverSocket.accept()));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		stop();
	}

}
