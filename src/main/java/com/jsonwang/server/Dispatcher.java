package com.jsonwang.server;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable {
	private Request request;
	private Response response;
	private int code;

	public Dispatcher(Socket socket) {
		try {
			request = new Request(socket.getInputStream());
			response = new Response(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		Servlet servlet = null;
		try {
			servlet = WebApp.getServlet(request.getUrl());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.code = 500;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.code = 500;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.code = 500;
		}
		if (null == servlet) {
			this.code = 404;
		} else {
			servlet.service(request, response);
			this.code = 200;
		}
		response.pushToClient(code);
	}

}
