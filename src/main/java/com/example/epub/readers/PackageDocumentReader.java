package com.example.epub.readers;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import com.example.epub.*;

public class PackageDocumentReader 
{
	private PackageDocument packageDocument = new PackageDocument();
	private ArrayList<String> authors = new ArrayList<>();
	private PackageManifest packageManifest = new PackageManifest();
	private ArrayList<PackageItem> spine = new ArrayList<>();
	
	public PackageDocumentReader(File file)
	{
		XMLReader xmlReader = getXmlReader();
		try
		{
			xmlReader.parse("file:" + file.getAbsolutePath());
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}
	
	public PackageDocumentReader(InputStream inputStream)
	{
		XMLReader xmlReader = getXmlReader();
		try
		{
			xmlReader.parse(new InputSource(inputStream));
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}
	
	public PackageDocument getPackageDocument()
	{
		return packageDocument;
	}
	
	
	private class PackageDocumentHandler extends DefaultHandler {
		StringBuffer contentBuffer;
		
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
		{
			contentBuffer = new StringBuffer();
			switch (localName)
			{
				case "item":
					PackageItem packageItem = new PackageItem() {{
						setId(attributes.getValue("id"));
						setHref(attributes.getValue("href"));
						setMediaType(attributes.getValue("media-type"));
					}};
					packageManifest.add(packageItem);
					break;
				case "itemref":
					String idref = attributes.getValue("idref");
					PackageItem pi = packageManifest.stream()
						.filter(item -> item.getId().equals(idref))
						.findFirst().get();
					spine.add(pi);
					break;
				default:
					break;
			}
		}
		
		public void characters(char[] ch, int start, int length) throws SAXException
		{
			contentBuffer.append(ch, start, length);
		}
		
		public void endElement(String namespaceURI, String localName, String qName) throws SAXException
		{
			switch (localName)
			{
				case "metadata":
					packageDocument.setAuthors(authors);
					break;
				case "title":
					packageDocument.setTitle(contentBuffer.toString());
					break;
				case "creator":
					authors.add(contentBuffer.toString());
					break;
				case "publisher":
					packageDocument.setPublisher(contentBuffer.toString());
					break;
				case "manifest":
					packageDocument.setManifest(packageManifest);
					break;
				case "spine":
					packageDocument.setSpine(spine);
					break;
				default:
					// todo
					break;
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
			xmlReader.setContentHandler(new PackageDocumentHandler());
			return xmlReader;
		}
		catch (Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}
}
