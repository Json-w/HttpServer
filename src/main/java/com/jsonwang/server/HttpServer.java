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
	private boolean isShutDown = false;

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
		while (!isShutDown) {
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Thread(new Dispatcher(socket)).start();
		}
	}

	public void stop() {
		// TODO Auto-generated method stub
		try {
			isShutDown = true;
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
