package com.gmail.akenuairu.chatutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OKeysList
{
	private static Map<String, OKey<?>> map = new HashMap<>();
	
	/**
	 * @param okey
	 * @throws DuplicateException
	 */
	public static <T> void registerOKey(OKey<T> okey) throws DuplicateException
	{
		registerOKey(okey.getRegisterName(), okey);
	}
	
	/**
	 * @param pluginID
	 * @param name
	 * @param okey
	 * @throws DuplicateException
	 */
	public static <T> void registerOKey(String pluginID, String name, OKey<T> okey) throws DuplicateException
	{
		registerOKey(pluginID+":"+name, okey);
	}
	
	/**
	 * @param registerName = pluginID + ":" + id
	 * @param okey
	 * @throws DuplicateException
	 */
	public static <T> void registerOKey(String registerName, OKey<T> okey) throws DuplicateException
	{
		for (String s : map.keySet())
		{
			System.out.println(s);
		}
		
		if (map.containsKey(registerName))
		{
			throw new DuplicateException(registerName);
		}
		else
		{
			map.put(registerName, okey);
		}
	}
	
	public static boolean hasOKey(String registerName)
	{
		return map.containsKey(registerName);
	}
	
	public static OKey<?> getOKey(String registerName)
	{
		return map.get(registerName);
	}
	
	public static List<String> getOKeysRegisterName()
	{
		List<String> temp = new ArrayList<>();
		
		for (String registerName : map.keySet())
		{
			temp.add(registerName);
		}
		
		return temp;
	}
}
