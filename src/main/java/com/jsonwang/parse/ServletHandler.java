package com.jsonwang.parse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jsonwang.pojo.Mapping;
import com.jsonwang.pojo.ServletEntity;

public class ServletHandler extends DefaultHandler {
	private List<Mapping> mappings;
	private List<ServletEntity> entities;
	private String tag;
	private ServletEntity entity;
	private Mapping mapping;

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		mappings = new ArrayList<Mapping>();
		entities = new ArrayList<ServletEntity>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		tag = qName;
		if (qName.equals("servlet")) {
			entity = new ServletEntity();
		}
		if (qName.equals("servlet-mapping")) {
			mapping = new Mapping();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		String content = new String(ch, start, length);
		if (null != tag) {
			if (tag.equals("servlet-name")) {
				if (null != entity) {
					entity.setServletName(content);
				} else if (null != mapping) {
					mapping.setServletName(content);
				}
			} else if (tag.equals("servlet-class")) {
				entity.setServletClass(content);
			} else if (tag.equals("url-pattern")) {
				mapping.setUrl(content);
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		if (qName.equals("servlet")) {
			entities.add(entity);
			entity = null;
		}
		if (qName.equals("servlet-mapping")) {
			mappings.add(mapping);
			mapping = null;
		}
		tag = null;
	}

	public List<Mapping> getMappings() {
		return mappings;
	}

	public List<ServletEntity> getEntities() {
		return entities;
	}

}
