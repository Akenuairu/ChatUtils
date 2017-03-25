package com.gmail.akeeru.chatutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;

public class Data
{
	public static final ChatData defaultChatData = new DefaultChatData();
	public static Map<String, Map<Object, ? extends CommentedConfigurationNode>> players = new HashMap<String, Map<Object, ? extends CommentedConfigurationNode>>();
	public static Map<String, Map<Object, ? extends CommentedConfigurationNode>> formulas = new HashMap<String, Map<Object, ? extends CommentedConfigurationNode>>();
	private static List<ChatData> listChatData = new ArrayList<ChatData>();

	public static List<ChatData> getListChatData()
	{
		List<ChatData> temp = listChatData;
		return temp;
	}

	public static void add(ChatData cd)
	{
		listChatData.add(cd);
	}
}
