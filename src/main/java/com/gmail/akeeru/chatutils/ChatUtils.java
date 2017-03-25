package com.gmail.akeeru.chatutils;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.service.permission.Subject;

import com.gmail.akeeru.chatutils.config.ConfigFormulas;
import com.gmail.akeeru.chatutils.config.ConfigPlayer;
import com.gmail.akeeru.chatutils.manager.CommandsManager;
import com.gmail.akeeru.chatutils.manager.EventsManager;
import com.google.inject.Inject;

@Plugin(authors = { "Akenuairu" }, id = "chatutils", name = "ChatUtils", version = "0.5.1")
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

	PermissionService permissionService;
	static ConfigPlayer configPlayer;
	static ConfigFormulas configFormulas;

	@Listener
	public void preInit(GamePreInitializationEvent event)
	{
		plugin = this;
		this.events = new EventsManager(this);
		Data.add(Data.defaultChatData);

		configPlayer = new ConfigPlayer("player");
		configPlayer.setup();

		configFormulas = new ConfigFormulas("formulas");
		configFormulas.setup();
	}

	@Listener
	public void init(GameInitializationEvent event)
	{
		CommandsManager.load(this);
		this.permissionService = Sponge.getServiceManager().provide(PermissionService.class).get();
	}

	@Listener
	public void postInit(GamePostInitializationEvent event)
	{
		for (Subject s : this.permissionService.getGroupSubjects().getAllSubjects())
		{
			this.logger.info("Group: " + s.getIdentifier());
		}
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event)
	{
		this.logger.info("This plugin has started!");
	}

	@Listener
	public void onServerStop(GameStoppedServerEvent event)
	{
		this.logger.info("This plugin has stopped!");
	}

	@Listener
	public void onReload(GameReloadEvent event)
	{}

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
		return this.game;
	}

	public Path getConfigDir()
	{
		return this.configDir;
	}

	public Logger getLogger()
	{
		return this.logger;
	}
}
