package com.gmail.akenuairu.chatutils;


@SuppressWarnings("serial")
public class DuplicateException extends Exception
{
	protected String name;
	
	public DuplicateException(String dupName)
	{
		this.name = dupName;
	}
	
	@Override
	public String getMessage()
	{
		return "This name is repeated '" + name + "'\n";
	}
}
