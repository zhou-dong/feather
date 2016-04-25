package org.feather.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.feather.app.module.ModuleFactory;
import org.feather.common.Global;
import org.feather.common.Module;
import org.feather.common.app.Request;
import org.feather.common.app.Response;
import org.feather.common.reflect.Reflect;
import org.feather.common.util.FileUtil;
import org.feather.common.util.SocketUtil;
import org.feather.common.util.StringUtil;
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
			threadPool = Executors.newCachedThreadPool();
			serverSocket = new ServerSocket(Global.serverPort);
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

		private Response doRequest(Request request) {
			if (request == null) {
				logger.info("no request socket");
				return null;
			}
			Module module = ModuleFactory.module(request.getClassName());
			if (module == null) {
				logger.error("call from [{}] with module [{}] not registered", request.getIp(),
						request.getClassName());
				return new Response(false, request.getClassName() + " module not registered");
			}
			try {
				Object returnValue = callModule(module, request.getMethodName(),
						request.getParams());
				logger.debug("call from [{}] with module [{}] method [{}]", request.getIp(),
						request.getClassName(), request.getMethodName());
				return successResponse(module, returnValue + "");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return new Response(false, e.getMessage());
			}
		}

		private Object callModule(Module module, String method, Object[] args) {
			return Reflect.on(module).call(method, args);
		}

		private Response successResponse(Module module, Object returnValue) {
			if (returnValue == module) {
				return new Response(true, "void");
			} else {
				return new Response(true, returnValue);
			}
		}

		public void run() {
			try {
				String message = SocketUtil.getMessage(socket);
				if (StringUtil.isEmpty(message))
					return;
				Request request = Request.parse(message);
				if (request == null)
					return;
				Response response = doRequest(request);
				if (response == null)
					return;
				SocketUtil.sendMessage(socket, Response.toString(response));
			} finally {
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
