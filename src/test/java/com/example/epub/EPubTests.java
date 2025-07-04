package com.example.epub;

import java.nio.file.*;
import java.util.zip.ZipFile;

import org.junit.jupiter.api.*;

public class EPubTests
{
	EPub epub;
	
	public EPubTests() throws Exception
	{
		Path path = Paths.get("src/test/resources/progit.epub");
		ZipFile zipFile = new ZipFile(path.toFile());
		epub = new EPub(zipFile);
	}
	
	@Test
	public void readTitle()
	{
		String title = epub.getTitle();
		Assertions.assertEquals("Pro Git", title);
	}
}
