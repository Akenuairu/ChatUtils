package com.gmail.akeeru.chatutils.config;

import java.util.Map;

import org.spongepowered.api.entity.living.player.Player;

import com.gmail.akeeru.chatutils.Data;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class ConfigPlayer extends BaseConfig
{
	public ConfigPlayer(String name)
	{
		super(name);
	}

	public void init()
	{
		if (get().hasMapChildren())
		{
			for (Map.Entry<Object, ? extends CommentedConfigurationNode> e : get().getChildrenMap().entrySet())
			{
				Data.players.put(e.getKey().toString(), e.getValue().getChildrenMap());
			}
		}
	}

	public void loadByPlayer(Player p)
	{
		if (get().hasMapChildren())
		{
			String sid = p.getIdentifier();
			if (get().getChildrenMap().containsKey(sid))
			{
				Data.players.remove(sid);
				Data.players.put(sid, get().getNode(sid).getChildrenMap());
			}
			else
			{
				Data.players.remove(sid);
			}
		}
	}
}
