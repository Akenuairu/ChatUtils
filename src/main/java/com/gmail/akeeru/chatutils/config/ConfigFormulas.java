package com.gmail.akeeru.chatutils.config;

import java.util.Map;

import com.gmail.akeeru.chatutils.Data;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class ConfigFormulas extends BaseConfig
{
	public ConfigFormulas(String name)
	{
		super(name);
	}

	public void init()
	{
		if (get().hasMapChildren())
		{
			for (Map.Entry<Object, ? extends CommentedConfigurationNode> s : get().getChildrenMap().entrySet())
			{
				Data.formulas.put(s.getKey().toString(), s.getValue().getChildrenMap());
			}
		}
	}

	public void populate()
	{
		get().getNode("global", "formule").setValue("%rank% | %player% >> %message%");
	}
}
