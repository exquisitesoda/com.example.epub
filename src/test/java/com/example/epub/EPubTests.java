package com.example.epub;

import java.nio.file.*;
import java.util.zip.ZipFile;

import org.junit.jupiter.api.*;

public class EPubTests
{
	@Test
	public void readTitle() throws Exception
	{
		Path path = Paths.get("src/test/resources/progit.epub");
		ZipFile zipFile = new ZipFile(path.toFile());
		EPub epub = new EPub(zipFile);
		
		Assertions.assertEquals("Pro Git", epub.getTitle());
	}
}
