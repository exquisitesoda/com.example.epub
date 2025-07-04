package com.example.epub;

import java.util.List;
import java.util.stream.Collectors;

public class PackageDocument
{
	private String title;
	private List<String> authors;
	private String publisher;
	private List<PackageItem> manifest;
	

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getAuthors() {
		return authors;
	}
	
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public List<PackageItem> getManifest()
	{
		return manifest;
	}
	
	public void setManifest(List<PackageItem> manifest)
	{
		this.manifest = manifest;
	}
	
	public String toString()
	{
		return "PackageDocument(title=" + title + ", "
			+ "authors=[" + authors.stream().collect(Collectors.joining(", ")) + "]"
			+ ")";
	}
}
