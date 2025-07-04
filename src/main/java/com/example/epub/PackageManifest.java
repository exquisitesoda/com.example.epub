package com.example.epub;

import java.util.*;
import java.util.stream.Collectors;

public class PackageManifest extends AbstractMap<String, String>
{
	Map<String, String> map = new HashMap<>();
	
	@Override
	public Set<Entry<String, String>> entrySet()
	{
		return map.entrySet();	
	}
	
	
	public void putItem(String id, String href)
	{
		map.put(id, href);
	}
	
	public String toString()
	{
		String output = map.entrySet().stream()
			.map(entry -> String.format("\"%s\": \"%s\"", entry.getKey(), entry.getValue()))
			.collect(Collectors.joining(","));
		return "{" + output + "}";
	}
}