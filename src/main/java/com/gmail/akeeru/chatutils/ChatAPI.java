package com.gmail.akeeru.chatutils;

import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.serializer.TextSerializers;

import com.gmail.akeeru.chatutils.config.ConfigPlayer;

public class ChatAPI
{
	static ConfigPlayer configPlayer = ChatUtils.configPlayer;

	public static void send(MessageChannelEvent.Chat event)
	{
		if (event.getMessage().isEmpty())
		{
			return;
		}

		if (event.getCause().first(Player.class).isPresent())
		{
			Player p = (Player) event.getCause().first(Player.class).get();

			String world = "global";
			String formule = Data.formulas.get(world).get("formule").getString();

			if (Data.formulas.containsKey(p.getWorld().getName()))
			{
				world = p.getWorld().getName();
				if (Data.formulas.get(world).containsKey("formule"))
				{
					formule = Data.formulas.get(world).get("formule").getString();
				}
			}

			List<Subject> lSub = p.getParents();
			for (Subject s : lSub)
			{
				String name = s.getIdentifier();
				if (Data.formulas.get("global").containsKey(name))
				{
					formule = Data.formulas.get("global").get("name").getString();
				}
				if (Data.formulas.get(world).containsKey(name))
				{
					formule = Data.formulas.get(world).get(name).getString();
				}
			}

			String output = formule;

			String s = event.getRawMessage().toPlain();
			String s2 = event.getMessage().toPlain().substring(event.getMessage().toPlain().indexOf(" ") + 1);

			Text message = TextSerializers.FORMATTING_CODE.deserialize(event.getRawMessage().toPlain());

			if (s.length() != s2.length())
			{
				String text = event.getMessage().toPlain();
				message = TextSerializers.FORMATTING_CODE.deserialize(text.substring(text.indexOf(" ") + 1));
			}

			for (ChatData cdata : Data.getListChatData())
			{
				cdata.load(event);
				for (Entry<String, String> ent : cdata.getMap().entrySet())
				{
					output = output.replaceAll((String) ent.getKey(), (String) ent.getValue());
				}
			}
			int ms = output.indexOf("%message%");
			int ml = new String("%message%").length();

			output = output.replaceAll("%message%", message.toPlain());

			Text.Builder messageBuild = Text.builder();

			Text text = Text.builder().append(new Text[] { TextSerializers.FORMATTING_CODE.deserialize(output.substring(0, ms)), message, ms + ml > output.length() ? Text.of("") : TextSerializers.FORMATTING_CODE.deserialize(output.substring(ms + ml)) }).build();

			ChatUtils.getPlugin().getLogger().info("OOut: " + text.toPlain());
			event.setChannel(MessageChannel.TO_ALL);
			event.setMessage(messageBuild.append(Text.of(text)).build());
		}
	}

	public static boolean hasDisplayName(Player p)
	{
		boolean has = false;

		String sid = p.getIdentifier();
		if (Data.players.containsKey(sid))
		{
			if (Data.players.get(sid).containsKey("customName"))
			{
				has = true;
			}
		}
		return has;
	}

	public static boolean isVisibleDisplayName(Player p)
	{
		boolean vis = false;

		String sid = p.getIdentifier();
		if (hasDisplayName(p))
		{
			if (Data.players.get(sid).containsKey("visible"))
			{
				vis = Data.players.get(sid).get("visible").getBoolean();
			}
			else
			{
				vis = true;
			}
		}
		return vis;
	}

	public static void setVisibleDisplayName(Player p, boolean visible)
	{
		configPlayer.get().getNode(p.getIdentifier(), "visible").setValue(Boolean.valueOf(visible));
		configPlayer.save();
		configPlayer.loadByPlayer(p);
	}

	public static String getDisplayName(Player p)
	{
		String name = null;
		if (hasDisplayName(p))
		{
			name = Data.players.get(p.getIdentifier()).get("customName").getString();
		}
		return name;
	}

	public static void setDisplayName(Player p, String name)
	{
		configPlayer.get().getNode(p.getIdentifier(), "customName").setValue(name);
		configPlayer.save();
		configPlayer.loadByPlayer(p);
	}

	public static void removeDisplayName(Player p)
	{
		configPlayer.get().getNode(p.getUniqueId()).removeChild("visible");
		configPlayer.get().getNode(p.getUniqueId()).removeChild("customName");
		configPlayer.save();
		configPlayer.loadByPlayer(p);
	}

	public static String getPlayerNameFormUUID(UUID id, Game game) throws NullPointerException
	{
		String name = null;

		Player p = (Player) game.getServer().getPlayer(id).orElse(null);
		if (p != null)
		{
			name = p.getName();
		}
		if (p == null)
		{
			try
			{
				name = game.getServer().getGameProfileManager().get(id, true).get().getName().get();
			}
			catch (InterruptedException | ExecutionException localInterruptedException)
			{}
		}
		return name;
	}
}
