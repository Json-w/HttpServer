package com.jsonwang.server;

public interface Server {
	public void start(int port);

	public void receive();

	public void stop();
}
