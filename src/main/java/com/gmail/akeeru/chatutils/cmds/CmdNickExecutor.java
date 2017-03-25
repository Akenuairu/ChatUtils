package com.gmail.akeeru.chatutils.cmds;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import com.gmail.akeeru.chatutils.ChatUtils;

public class CmdNickExecutor implements CommandExecutor
{
	ChatUtils plugin = ChatUtils.getPlugin();

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
	{
		return null;
	}

}
