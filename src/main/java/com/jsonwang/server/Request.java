package com.jsonwang.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.messaging.saaj.soap.StringDataContentHandler;

public class Request {
	private static final String CRLF = System.lineSeparator();
	private String method;
	private String url;
	private Map<String, List<String>> parameters;
	private InputStream is;

	public Request() {
		// TODO Auto-generated constructor stub
		this.method = "";
		this.url = "";
		this.parameters = new HashMap<String, List<String>>();
	}

	public Request(InputStream is) {
		this();
		this.is = is;
		parseStrToRequest();
	}

	private void parseStrToRequest() {
		int len;
		String request;
		byte[] data = new byte[20480];
		try {
			len = is.read(data);
			request = new String(data, 0, len);
			String firstLine = request.substring(0, request.indexOf(CRLF));
			method = firstLine.substring(0, request.indexOf("/")).trim();
			String urlAndParamStr = firstLine.substring(request.indexOf("/"), request.indexOf("HTTP/"));
			if (method.equalsIgnoreCase("get")) {
				if (urlAndParamStr.contains("?")) {
					String[] strArr = urlAndParamStr.split("\\?");
					this.url = strArr[0].trim();
					String params = strArr[1].trim();
					paramsToMap(params);
				} else {
					this.url = urlAndParamStr.trim();
				}
			} else if (method.equalsIgnoreCase("post")) {
				this.url = urlAndParamStr.trim();
				String postParams = request.substring(request.lastIndexOf(CRLF)).trim();
				paramsToMap(postParams);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(parameters);
	}

	/**
	 * 解决中文乱码问题
	 * 
	 * @param msg
	 * @param code
	 * @return
	 */
	private String decode(String msg, String code) {
		try {
			return URLDecoder.decode(msg, code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 参数转化成map
	 * @param params
	 */
	private void paramsToMap(String params) {
		String[] paramArr = params.split("&");
		for (String param : paramArr) {
			String[] ss = param.split("=");
			if (null == ss[0] || "".equals(ss[0])) {
				continue;
			}
			if (parameters.containsKey(ss[0])) {
				parameters.get(ss[0]).add(decode(ss[1], "utf8"));
			} else {
				List<String> list = new ArrayList<String>();
				list.add(decode(ss[1], "utf8"));
				parameters.put(ss[0], list);
			}
		}
	}
}
