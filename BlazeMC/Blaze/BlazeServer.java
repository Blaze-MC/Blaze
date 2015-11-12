/*
The MIT License (MIT)

Copyright (c) 2015 BlazeMC

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package info.BlazeMC.Blaze;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import info.BlazeMC.Blaze.Plugin.PluginLoader;
import info.BlazeMC.BlazeAPI.Server;
import info.BlazeMC.BlazeAPI.Entity.Player;
import info.BlazeMC.BlazeAPI.Plugin.Plugin;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class BlazeServer implements Server
{
	private MinecraftServer mcServer;
	private File pluginDir = new File("plugins");
	private PluginLoader pluginLoader;
	private ArrayList<Player> onlinePlayers = new ArrayList<Player>();
	private static BlazeServer server;
	
	public BlazeServer(String[] args, BlazeStart start)
	{
		MinecraftServer.main(args);
		mcServer = MinecraftServer.getServer();
		server = this;
		
		if(!pluginDir.exists())
		{
			pluginDir.mkdir();
		}
		
		pluginLoader = new PluginLoader(this);
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				for(Plugin plugin : pluginLoader.plugins.keySet())
				{
					plugin.onDisable();
				}
			}
		});
	}
	
	public File getPluginDir()
	{
		return pluginDir;
	}
	
	public void addPlayer(Player player, EntityPlayerMP mcPlayer, ServerConfigurationManager manager)
	{
		onlinePlayers.add(player);
	}
	
	public void removePlayer(Player player, EntityPlayerMP mcPlayer, ServerConfigurationManager manager)
	{
		onlinePlayers.remove(player);
	}
	
	public Collection<Player> getOnlinePlayers()
	{
		return Collections.unmodifiableList(onlinePlayers);
	}

	public Player getPlayer(String name)
	{
		for(Player player : onlinePlayers)
		{
			if(player.getName().equals(name))
			{
				return player;
			}
		}
		
		return null;
	}

	public Player getPlayer(UUID uuid)
	{
		for(Player player : onlinePlayers)
		{
			if(player.getUUID().equals(uuid))
			{
				return player;
			}
		}
		
		return null;
	}

	public String getAddress() 
	{
		return mcServer.getServerHostname();
	}

	public int getPort() 
	{
		return mcServer.getPort();
	}

	public String getName() 
	{
		return mcServer.getName();
	}
	
	public void broadcast(String message)
	{
		for(Player player : onlinePlayers)
		{
			player.sendMessage(message);
		}
	}
	
	public static BlazeServer getServer()
	{
		return server;
	}
}
