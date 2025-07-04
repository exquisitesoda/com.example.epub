package com.example.epub;


import java.io.InputStream;
import java.util.List;
import java.util.zip.*;

import com.example.epub.readers.*;

public class EPub
{
	private ZipFile zipFile;
	private ContainerDocument containerDocument;
	private PackageDocument packageDocument;
	
	
	public EPub(ZipFile zipFile)
	{
		this.zipFile = zipFile;
		readContainerDocument();
		readPackageDocument();
	}
	
	public String getTitle()
	{
		return packageDocument.getTitle();
	}
	
	public List<String> getAuthors()
	{
		return packageDocument.getAuthors();
	}
	
	public PackageDocument getPackageDocument()
	{
		return packageDocument;
	}
	
	private void readContainerDocument()
	{
		ZipEntry entry = zipFile.getEntry("META-INF/container.xml");
		
		try (InputStream inputStream = zipFile.getInputStream(entry))
		{
			ContainerDocumentReader reader = new ContainerDocumentReader(inputStream);
			containerDocument = reader.getContainerDocument();
		}
		catch (Exception exception)
		{
			System.out.println("could not open container.xml");
			
		}
	}
	
	private void readPackageDocument()
	{
		String rootFile = containerDocument.getRootFiles().get(0);
		ZipEntry entry = zipFile.getEntry(rootFile);
		
		try (InputStream inputStream = zipFile.getInputStream(entry))
		{
			PackageDocumentReader reader = new PackageDocumentReader(inputStream);
			packageDocument = reader.getPackageDocument();
		}
		catch (Exception exception)
		{
			System.out.println("could not open package document");
		}
	}
}