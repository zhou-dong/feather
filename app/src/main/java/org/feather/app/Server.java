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

public class Server implements Module {

	private static Logger logger = LoggerFactory.getLogger(Server.class);

	private ServerSocket serverSocket;
	private ExecutorService executorService;
	private boolean isAlive;

	public void afterCreate(Object[] params) {
	}

	public boolean init(boolean isReload) {
		try {
			serverSocket = new ServerSocket(Global.SERVER_PORT);
			executorService = Executors.newCachedThreadPool();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public void start(boolean isReload) {
		isAlive = true;
		while (isAlive) {
			try {
				executorService.execute(new Handler(serverSocket.accept()));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void stop() {
		isAlive = false;
		executorService.shutdown();
		FileUtil.close(serverSocket);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public String getId() {
		return "server";
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

}
