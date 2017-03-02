package com.gmail.akenuairu.chatutils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.spongepowered.api.entity.living.player.Player;

public class CUPlayer
{
	private UUID id;
	private Map<OKey<?>, Object> map = new HashMap<>();
	
	public CUPlayer(Player p)
	{
		id = p.getUniqueId();
	}
	
	public CUPlayer(UUID id)
	{
		this.id = id;
	}
	
	public UUID getUUID()
	{
		return id;
	}
}
