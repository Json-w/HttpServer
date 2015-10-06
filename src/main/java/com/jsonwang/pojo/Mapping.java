package com.jsonwang.pojo;

public class Mapping {
	private String servletName;
	private String url;

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Mapping [servletName=" + servletName + ", url=" + url + "]";
	}

}
