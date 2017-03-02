package com.gmail.akenuairu.chatutils;


public class OKey<T>
{
	protected final String id;
	protected final String pluginID;
	protected final Class<T> cast;

	public OKey(String pluginID, String id, Class<T> cast)
	{
		this.pluginID = pluginID;
		this.id = id;
		this.cast = cast;
	}

	public String getId()
	{
		return id;
	}
	
	public String getPluginID()
	{
		return pluginID;
	}
	
	public String getRegisterName()
	{
		return pluginID+":"+id;
	}

	public T cast(Object ob)
	{
		return cast.cast(ob);
	}
	
	
}
