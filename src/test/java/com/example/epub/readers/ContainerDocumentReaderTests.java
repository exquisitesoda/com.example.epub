package com.example.epub.readers;

import java.nio.file.*;
import java.util.List;

import org.junit.jupiter.api.*;


public class ContainerDocumentReaderTests
{
	ContainerDocumentReader reader;
	
	
	public ContainerDocumentReaderTests()
	{
		Path path = Paths.get("src/test/resources/container-document-1.xml");
		reader = new ContainerDocumentReader(path.toFile());
		System.out.println(reader.getContainerDocument());
	}
	
	@Test
	public void readRootFiles()
	{
		List<String> rootFiles = reader.getContainerDocument().getRootFiles();
		Assertions.assertEquals(1, rootFiles.size());
	}
}
