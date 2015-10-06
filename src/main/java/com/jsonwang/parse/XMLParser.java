package com.jsonwang.parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.jsonwang.pojo.Mapping;
import com.jsonwang.pojo.ServletEntity;

public class XMLParser {
	private List<Mapping> mappings;
	private List<ServletEntity> entities;

	public XMLParser() {
		mappings = new ArrayList<Mapping>();
		entities = new ArrayList<ServletEntity>();
	}

	public void parse(String resource) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		ServletHandler handler = new ServletHandler();
		saxParser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(resource), handler);
		mappings = handler.getMappings();
		entities = handler.getEntities();
	}

	public List<Mapping> getMappings() {
		return mappings;
	}

	public List<ServletEntity> getEntities() {
		return entities;
	}

}
