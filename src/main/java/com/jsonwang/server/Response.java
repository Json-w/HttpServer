package com.jsonwang.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
	private final String CRLF = System.lineSeparator();
	private final String BLANCK = " ";
	private StringBuilder header;
	private String content="";
	private String result;
	private int status;
	private BufferedWriter bw;

	private Response() {
		header = new StringBuilder();
	}

	public Response(Socket socket) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			header = null;
		}
	}

	public void createResponseContent(String content) {
		this.content = content;
	}

	public void pushToClient(int status) {
		try {
			if (null == header) {
				this.status = 500;
			} else {
				this.status = status;
			}
			createResponseHead(content.getBytes().length);
			result = header + content;
			bw.write(result);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createResponseHead(int length) {
		header.append("HTTP/1.1").append(BLANCK);
		switch (this.status) {
		case 200:
			header.append("200").append(BLANCK).append("ok");
			break;
		case 404:
			header.append("404").append(BLANCK).append("not found");
			break;
		case 500:
			header.append("500").append(BLANCK).append("server error");
			break;
		}
		header.append(CRLF);
		header.append("Server:").append(BLANCK).append("jsowang 0.0.1").append(CRLF);
		header.append("Date:").append(BLANCK).append(new Date()).append(CRLF);
		header.append("Content-Type:text/html").append(CRLF);
		header.append("Content-Length:").append(length).append(CRLF);
		header.append(CRLF);
	}
}
