package com.jsowang.parse;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.jsonwang.parse.XMLParser;

public class XmlParserTest {

	@Test
	public void testParse() throws ParserConfigurationException, SAXException, IOException {
		XMLParser xmlParser = new XMLParser();
		xmlParser.parse("web.xml");
		
	}
}
