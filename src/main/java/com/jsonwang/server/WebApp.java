package com.jsonwang.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.jsonwang.parse.XMLParser;
import com.jsonwang.pojo.Mapping;
import com.jsonwang.pojo.ServletEntity;

public class WebApp {
	private static ServletContext context;

	static {
		context = new ServletContext();
		XMLParser parser = new XMLParser();
		try {
			parser.parse("web.xml");
			for (ServletEntity entity : parser.getEntities()) {
				context.getServlet().put(entity.getServletName(), entity.getServletClass());
			}
			for (Mapping mapping : parser.getMappings()) {
				context.getMapping().put(mapping.getUrl(), mapping.getServletName());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Servlet getServlet(String url)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (url.equals("/favicon.ico")) {
			return null;
		}
		try {
			return (Servlet) Class.forName(context.getServlet().get(context.getMapping().get(url))).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}
}
