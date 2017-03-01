package com.gmail.akenuairu.chatutils.manager;

import static org.spongepowered.api.text.Text.of;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;

import com.gmail.akenuairu.chatutils.ChatUtils;

public class EventsManager
{
	ChatUtils plugin;
	
	public EventsManager(ChatUtils origin)
	{
		plugin = origin;
		origin.registerEvent(this);
	}

	@Listener(order=Order.PRE)
	public void onChat(MessageChannelEvent.Chat event)
	{
		if (event.getCause().first(Player.class).isPresent())
		{
			Text omsg = event.getOriginalMessage();
			Text rmsg = event.getRawMessage();
			Text msg = event.getMessage();

			Player p = event.getCause().first(Player.class).get();
			p.sendMessage(of("Original Message || " + omsg.toString()));
			p.sendMessage(of("Raw Message 1 || " + rmsg.toPlain()));
			p.sendMessage(of("Raw Message 2 || " + rmsg.toPlainSingle()));
			p.sendMessage(of("Message || " + msg.toString()));
		}

	}
}
