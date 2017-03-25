package com.gmail.akeeru.chatutils;

import java.util.List;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.service.permission.Subject;

public class DefaultChatData extends ChatData
{
	public void load(MessageChannelEvent.Chat event)
	{
		if (event.getCause().first(Player.class).isPresent())
		{
			Player p = (Player) event.getCause().first(Player.class).get();
			if (ChatAPI.isVisibleDisplayName(p))
			{
				add("%player%", ChatAPI.getDisplayName(p));
			}
			else
			{
				add("%player%", p.getName());
			}
			List<Subject> s = p.getParents();

			add("%rank%", ((Subject) s.get(0)).getIdentifier());
		}
	}
}
