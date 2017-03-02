package com.gmail.akenuairu.chatutils;

public class Case
{
	protected Class<?> clazz;
	protected Object ob;
	
	public Case(Class<?> obClazz, Object ob)
	{
		clazz = obClazz;
		this.ob = ob;
	}
	
	public Class<?> getClazz()
	{
		return clazz;
	}
	
	public Object getObject()
	{
		return ob;
	}
}
