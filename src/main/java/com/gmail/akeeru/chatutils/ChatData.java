package com.gmail.akeeru.chatutils;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.api.event.message.MessageChannelEvent;

public abstract class ChatData
{
	private Map<String, String> map = new HashMap<String, String>();

	public void add(String key, String replace)
	{
		if (this.map.containsKey(key))
		{
			this.map.replace(key, replace);
		}
		else
		{
			this.map.put(key, replace);
		}
	}

	public boolean hasKey(String key)
	{
		return this.map.containsKey(key);
	}

	public void remove(String key)
	{
		if (this.map.containsKey(key))
		{
			this.map.remove(key);
		}
	}

	public String get(String key)
	{
		if (this.map.containsKey(key))
		{
			return (String) this.map.get(key);
		}
		return null;
	}

	public Map<String, String> getMap()
	{
		Map<String, String> tempMap = this.map;
		return tempMap;
	}

	public abstract void load(MessageChannelEvent.Chat chat);
}
