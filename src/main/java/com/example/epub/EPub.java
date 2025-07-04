package com.example.epub;


import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.zip.*;

import com.example.epub.readers.*;

public class EPub
{
	private ZipFile zipFile;
	private ContainerDocument containerDocument;
	private PackageDocument packageDocument;
	
	private Path baseZipPath;
	
	
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
	
	public InputStream getContent(String path)
	{
		Path combinedPath = baseZipPath.resolve(path);
		String resolvedPath = combinedPath.toString().replaceAll("\\\\", "/");
		ZipEntry entry = zipFile.getEntry(resolvedPath);
		try
		{
			InputStream inputStream = zipFile.getInputStream(entry);
			return inputStream;
		}
		catch (Exception exception)
		{
			throw new RuntimeException("could not get " + resolvedPath + " from zipfile of " + zipFile.getName(), exception);
		}
	}
	
	public InputStream getContent(PackageItem packageItem)
	{
		String path = packageItem.getHref();
		return getContent(path);
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
		String packagePath = containerDocument.getRootFiles().get(0);
		baseZipPath = Path.of(packagePath).getParent();
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