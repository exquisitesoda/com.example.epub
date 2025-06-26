package com.example.epub;

import java.io.File;
import java.nio.file.*;

import org.junit.jupiter.api.*;

public class ResourceTests
{
	@Test
	public void openTestResource()
	{
		Path path = Paths.get("src/test/resources/package-document-1.opf");
		File resource = path.toFile();
		
		Assertions.assertTrue(resource.exists());
	}
}