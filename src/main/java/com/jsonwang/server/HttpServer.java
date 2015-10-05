package com.jsonwang.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpServer implements Server {
	private ServerSocket serverSocket;
	private final String CRLF = System.lineSeparator();
	private final String BLANCK = " ";

	public void start(int port) {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void receive() {
		// TODO Auto-generated method stub
		Socket socket = null;
		try {
			socket = serverSocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// byte[] data = new byte[2048];
		// accept client request
		// socket.getInputStream().read(data);
		// System.out.println(new String(data));
		// build response content
		try {
			Request request = new Request(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder content = new StringBuilder();
		content.append("<html><head><title>jsonwang</title></head><body><h1>hello World</h1></body></html>");
		Response response = new Response(200, socket);
		response.createResponseContent(content.toString());
		response.pushToClient();

	}

	public void stop() {
		// TODO Auto-generated method stub
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
