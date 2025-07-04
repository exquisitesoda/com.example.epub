package com.example.epub.readers;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import org.junit.jupiter.api.*;

import com.example.epub.PackageManifest;


public class PackageDocumentReaderTests
{
	PackageDocumentReader reader;
	Path path;
	
	public PackageDocumentReaderTests()
	{
		path = Paths.get("src/test/resources/package-document-1.opf");
		reader = new PackageDocumentReader(path.toFile());
		System.out.println(reader.getPackageDocument());
	}
	
	
	@Test
	public void readTitle()
	{
		String title = reader.getPackageDocument().getTitle();	
		Assertions.assertEquals("Example Book", title);
	}
	
	@Test
	public void readAuthors()
	{
		List<String> authors = reader.getPackageDocument().getAuthors();
		Assertions.assertEquals(2, authors.size());
		Assertions.assertTrue(authors.contains("John Doe"));
		Assertions.assertTrue(authors.contains("Joe Schmoe"));
	}
	
	@Test
	public void readPublisher()
	{
		String publisher = reader.getPackageDocument().getPublisher();
		Assertions.assertEquals("Example Publisher", publisher);
	}
	
	@Test
	public void readInputStream() throws Exception
	{
		InputStream inputStream = new FileInputStream(path.toFile());
		reader = new PackageDocumentReader(inputStream);
		String title = reader.getPackageDocument().getTitle();
		Assertions.assertEquals("Example Book", title);
	}
	
	@Test
	public void readManifest()
	{
		PackageManifest manifest = reader.getPackageDocument().getManifest();
		Assertions.assertEquals(4, manifest.size());
	}
}
