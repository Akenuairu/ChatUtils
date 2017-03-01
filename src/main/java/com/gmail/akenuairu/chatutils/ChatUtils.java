package com.gmail.akenuairu.chatutils;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.gmail.akenuairu.chatutils.manager.CommandsManager;
import com.gmail.akenuairu.chatutils.manager.EventsManager;
import com.google.inject.Inject;

@Plugin(authors = { "Akenuairu" }, id = "chatutils", name = "ChatUtils", version = "0.2")
public class ChatUtils
{
	@Inject
	public Logger logger;
	
	@Inject
	@ConfigDir(sharedRoot=false)
	private Path configDir;
	
	private static ChatUtils plugin;
	private Game game = Sponge.getGame();
	EventsManager events;

	@Listener
	public void preInit(GamePreInitializationEvent event)
	{
		plugin = this;
		events = new EventsManager(this);

	}

	@Listener
	public void init(GameInitializationEvent event)
	{
		CommandsManager.load(this);
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event)
	{

	}

	public void registerEvent(Object ob)
	{
		Sponge.getEventManager().registerListeners(this, ob);
	}

	public static ChatUtils getPlugin()
	{
		return plugin;
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public Path getConfigDir()
	{
		return configDir;
	}
}
