package com.example.epub.readers;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import com.example.epub.ContainerDocument;

public class ContainerDocumentReader
{
	private ContainerDocument containerDocument = new ContainerDocument();
	
	public ContainerDocumentReader(File file)
	{
		XMLReader xmlReader = getXmlReader();
		try
		{
			xmlReader.parse("file:" + file.getAbsolutePath());
		}
		catch (Exception exception)
		{
			System.out.println("ContainerDocumentReader: " + exception.getMessage());
		}
	}
	
	public ContainerDocumentReader(InputStream inputStream)
	{
		XMLReader xmlReader = getXmlReader();
		try
		{
			xmlReader.parse(new InputSource(inputStream));
		}
		catch (Exception exception)
		{
			System.out.println("ContainerDocumentReader: " + exception.getMessage());
		}
	}
	
	
	public ContainerDocument getContainerDocument()
	{
		return containerDocument;
	}
	
	
	private class ContainerDocumentHandler extends DefaultHandler {
		private List<String> rootFiles = new ArrayList<>();
		
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
		{
			if (localName == "rootfile")
			{
				String path = attributes.getValue("full-path");
				rootFiles.add(path);
				
				System.out.println(path);
			}
		}
		
		public void endElement(String namespaceURI, String localName, String qName) throws SAXException
		{
			if (localName == "rootfiles")
			{
				containerDocument.setRootFiles(rootFiles);
			}
		}
		
	}
	
	private XMLReader getXmlReader()
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		try
		{
			SAXParser saxParser = factory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(new ContainerDocumentHandler());
			return xmlReader;
		}
		catch (Exception exception)
		{
			System.out.println("ContainerDocumentReader: " + exception.getMessage());
		}
		return null;
	}
}
