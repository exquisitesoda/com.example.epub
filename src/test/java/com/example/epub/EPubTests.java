package com.example.epub;

import java.io.InputStream;
import java.nio.file.*;
import java.util.zip.*;

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
	
	@Test
	public void readContent() throws Exception
	{
		epub.getPackageDocument().getManifest()
			.forEach(packageItem ->
			{
				InputStream inputStream = epub.getContent(packageItem);
				Assertions.assertNotNull(inputStream);
			});
	}
}
