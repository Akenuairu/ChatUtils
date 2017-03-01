package com.gmail.akenuairu.chatutils.manager;

import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import com.gmail.akenuairu.chatutils.ChatUtils;
import com.gmail.akenuairu.chatutils.cmds.CmdNickExecutor;

public class CommandsManager
{
	static CommandSpec cmdNick = CommandSpec.builder()
			.description(Text.of("This command change display name"))
			.arguments(
					GenericArguments.string(Text.of("name")),
					GenericArguments.optional(GenericArguments.string(Text.of("player")))
					)
			.executor(new CmdNickExecutor())
			.build();
	
	public static void load(ChatUtils plugin)
	{
		plugin.getGame().getCommandManager().register(plugin, cmdNick, "nick");
	}

}
