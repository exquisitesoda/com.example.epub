package com.example.epub.readers;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import org.junit.jupiter.api.*;


public class ContainerDocumentReaderTests
{
	ContainerDocumentReader reader;
	Path path;
	
	public ContainerDocumentReaderTests()
	{
		path = Paths.get("src/test/resources/container-document-1.xml");
		reader = new ContainerDocumentReader(path.toFile());
		System.out.println(reader.getContainerDocument());
	}
	
	@Test
	public void readRootFiles()
	{
		List<String> rootFiles = reader.getContainerDocument().getRootFiles();
		Assertions.assertEquals(1, rootFiles.size());
	}
	
	@Test
	public void readInputStream() throws Exception
	{
		InputStream inputStream = new FileInputStream(path.toFile());
		reader = new ContainerDocumentReader(inputStream);
		List<String> rootFiles = reader.getContainerDocument().getRootFiles();
		Assertions.assertEquals(1, rootFiles.size());
	}
}
