package com.jsonwang.server;

public class LoginServlet extends Servlet {

	@Override
	public void doGet(Request req, Response resp) {
		// TODO Auto-generated method stub
		String name = req.getParameter("uname");
		StringBuilder content = new StringBuilder();
		content.append("<html><head><title>jsonwang</title></head><body><h1>hello").append(name)
				.append(" World</h1></body></html>");
		resp.createResponseContent(content.toString());
		
	}

	@Override
	public void doPost(Request req, Response resp) {
		// TODO Auto-generated method stub

	}

}
