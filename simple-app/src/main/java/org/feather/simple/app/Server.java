package org.feather.simple.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.feather.common.Global;
import org.feather.common.util.FileUtil;
import org.feather.common.util.SocketUtil;
import org.feather.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thetransactioncompany.jsonrpc2.JSONRPC2ParseException;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.Dispatcher;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;

public class Server implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private Thread thread;
	private Dispatcher dispatcher;
	private boolean alive;

	public Server() {
		threadPool = Executors.newCachedThreadPool();
		dispatcher = new Dispatcher();
		thread = new Thread(this);
	}

	public void register(RequestHandler handler) {
		dispatcher.register(handler);
	}

	public void start() {
		alive = true;
		thread = new Thread(this);
		thread.start();
		logger.info("server is running now");
	}

	public void run() {
		try {
			serverSocket = new ServerSocket(Global.serverPort);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		while (!thread.isInterrupted() && alive) {
			try {
				threadPool.execute(new ThreadHandler(serverSocket.accept()));
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		stop();
	}

	public void stop() {
		alive = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		threadPool.shutdown();
		FileUtil.close(serverSocket);
	}

	private class ThreadHandler implements Runnable {
		Socket socket = null;

		public ThreadHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			JSONRPC2Request request = getRequest();
			if (request != null) {
				JSONRPC2Response response = dispatcher.process(request, null);
				logger.info(response.toJSONString());
				SocketUtil.sendMessage(socket, response.toJSONString());
			}
		}

		private JSONRPC2Request getRequest() {
			String message = SocketUtil.getMessage(socket);
			if (StringUtil.isEmpty(message))
				return null;
			try {
				return JSONRPC2Request.parse(message);
			} catch (JSONRPC2ParseException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		}
	}

}
