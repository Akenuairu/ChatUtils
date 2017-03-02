package com.gmail.akenuairu.chatutils;

import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.user.UserStorageService;

import com.gmail.akenuairu.chatutils.manager.CommandsManager;
import com.gmail.akenuairu.chatutils.manager.EventsManager;
import com.google.inject.Inject;

@Plugin(authors = { "Akenuairu" }, id = "chatutils", name = "ChatUtils", version = "0.2")
public class ChatUtils
{
	@Inject
	private Logger logger;

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;

	private static ChatUtils plugin;
	private Game game = Sponge.getGame();
	public EventsManager events;

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
		logger.info("This plugin has started!");
	}

	@Listener
	public void onServerStop(GameStoppedServerEvent event)
	{
		logger.info("This plugin has stopped!");
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

	public Logger getLogger()
	{
		return logger;
	}
	
	public String getPlayerNameFromUUID()
	{
		UserStorageService se;
		
		
		return null;
	}

//	public String getPlayerNameFormUUID(UUID id, Game game) throws NullPointerException
//	{
//		String name = null;
//
//		Player p = game.getServer().getPlayer(id).orElse(null);
//
//		if (p != null)
//		{
//			name = p.getName();
//		}
//
//		if (p == null)
//		{
//			try
//			{
//				name = game.getServer().getGameProfileManager().get(id, true).get().getName().get();
//			}
//			catch (InterruptedException | ExecutionException e)
//			{}
//		}
//
//		return name;
//	}
//
//	public UUID getPlayerUUIDFromName(String name, Game game) throws NullPointerException
//	{
//		UUID id = null;
//
//		Player p = game.getServer().getPlayer(name).orElse(null);
//
//		if (p != null)
//		{
//			id = p.getUniqueId();
//		}
//
//		if (id == null)
//		{
//			try
//			{
//				id = game.getServer().getGameProfileManager().get(name, true).get().getUniqueId();
//			}
//			catch (InterruptedException | ExecutionException e)
//			{}
//		}
//
//		return id;
//	}
}
