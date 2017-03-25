package com.gmail.akeeru.chatutils.manager;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import com.gmail.akeeru.chatutils.ChatAPI;
import com.gmail.akeeru.chatutils.ChatUtils;

public class EventsManager
{
	ChatUtils plugin;

	public EventsManager(ChatUtils origin)
	{
		this.plugin = origin;
		origin.registerEvent(this);
	}

	@Listener(order = Order.PRE)
	public void onPreChat(MessageChannelEvent.Chat event)
	{}

	@Listener(order = Order.AFTER_PRE)
	public void onChat(MessageChannelEvent.Chat event)
	{
		ChatAPI.send(event);
	}

	@Listener
	public void onJoin(ClientConnectionEvent.Join event)
	{
		Player p = event.getTargetEntity();
		this.plugin.getLogger().info("ID: " + p.getIdentifier());
		this.plugin.getLogger().info("UUID: " + p.getUniqueId().toString());
	}

	@Listener
	public void onDisconned(ClientConnectionEvent.Disconnect event)
	{}
}
