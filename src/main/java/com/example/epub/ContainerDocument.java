package com.example.epub;

import java.util.List;
import java.util.stream.Collectors;

public class ContainerDocument
{
	private List<String> rootFiles;
	
	
	public List<String> getRootFiles()
	{
		return rootFiles;
	}
	
	public void setRootFiles(List<String> rootFiles)
	{
		this.rootFiles = rootFiles;
	}
	
	public String toString()
	{
		return "ContainerDocument(rootFiles=[" + rootFiles.stream().collect(Collectors.joining(", ")) + "])";
	}
}
