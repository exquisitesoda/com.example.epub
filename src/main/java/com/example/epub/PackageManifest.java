package com.example.epub;

import java.util.*;
import java.util.stream.Collectors;

public class PackageManifest extends AbstractList<PackageItem>
{
	private ArrayList<PackageItem> items = new ArrayList<>();
	
	@Override
	public int size()
	{	
		return items.size();
	}
	
	@Override
	public PackageItem get(int index)
	{
		return items.get(index);
	}
	
	@Override
	public PackageItem set(int index, PackageItem item)
	{
		return items.set(index, item);
	}
	
	@Override
	public void add(int index, PackageItem item)
	{
		items.add(index, item);
	}

	@Override
	public PackageItem remove(int index)
	{
		return items.remove(index);
	}
	
	public String toString()
	{
		String list = items.stream()
			.map(packageItem -> packageItem.toString())
			.collect(Collectors.joining(", "));
		
		return "[" + list + "]";
	}
}
