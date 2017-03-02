package com.gmail.akenuairu.chatutils.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gmail.akenuairu.chatutils.ChatUtils;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class BaseConfig
{
	protected Path configFile;
	protected Path configDir = ChatUtils.getPlugin().getConfigDir();
	protected ConfigurationLoader<CommentedConfigurationNode> configLoader;
	protected CommentedConfigurationNode configNode;

	public BaseConfig(String name)
	{
		this(name, ".conf");
	}

	public BaseConfig(String name, String suffix)
	{
		configFile = Paths.get(configDir + "/" + name + suffix);
		configLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
	}

	public BaseConfig(String name, String suffix, String folder)
	{
		configDir = Paths.get(configDir + "/" + folder);
		configFile = Paths.get(configDir + "/" + name + suffix);
		configLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
	}

	public void setup()
	{
		if (!Files.exists(configDir))
		{
			try
			{
				Files.createDirectories(configDir);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		if (!Files.exists(configFile))
		{
			try
			{
				Files.createFile(configFile);
				load();
				populate();
				save();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			load();
		}
	}

	public void load()
	{
		try
		{
			configNode = configLoader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		init();
	}

	public void init()
	{}

	public void save()
	{
		try
		{
			configLoader.save(configNode);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void populate()
	{}

	public CommentedConfigurationNode get()
	{
		return configNode;
	}
	
	public void put(Object value, Object... args)
	{
		get().getNode(args).setValue(value);
	}
	
	public void put(String coment, Object value, Object... args)
	{
		get().getNode(args).setValue(value).setComment(coment);
	}
	
	public CommentedConfigurationNode get(Object args)
	{
		return get().getNode(args);
	}
}
