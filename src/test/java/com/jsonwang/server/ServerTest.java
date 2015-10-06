package com.jsonwang.server;

import org.junit.Test;

public class ServerTest {

	@Test
	public void testServerReceive() {
		Server server = new HttpServer();
		server.start(8888);
		server.receive();
	}


}
