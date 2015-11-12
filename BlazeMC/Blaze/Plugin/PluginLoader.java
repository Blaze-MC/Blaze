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

package info.BlazeMC.Blaze.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Properties;

import info.BlazeMC.Blaze.BlazeServer;
import info.BlazeMC.BlazeAPI.Blaze;
import info.BlazeMC.BlazeAPI.Plugin.Plugin;
import info.BlazeMC.BlazeAPI.Plugin.PluginDescription;

public class PluginLoader
{
	public HashMap<Plugin, PluginDescription> plugins = new HashMap<Plugin, PluginDescription>();
	
	public PluginLoader(BlazeServer server)
	{
		for(File file : server.getPluginDir().listFiles())
		{
			loadPlugin(file);
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public void loadPlugin(File file)
	{
		if(file.getName().endsWith(".jar"))
		{
			try 
			{
				URL pluginYML = new URL("jar:file:" + file.getAbsolutePath() + "!/plugin.yml");
				Properties pluginDesc = new Properties();
				pluginDesc.load(pluginYML.openStream());
				String name = pluginDesc.getProperty("name");
				String main = pluginDesc.getProperty("main");
				String version = pluginDesc.getProperty("version");
			
				URL url2 = file.toURI().toURL();
				URL[] urls = new URL[] {url2};
				ClassLoader cl = new URLClassLoader(urls);
				Class<Plugin> clazz = (Class<Plugin>) cl.loadClass(main);
				
				Plugin pl = (Plugin) clazz.newInstance();
				PluginDescription desc = new PluginDescription(pl, name, main, version);
				
				plugins.put(pl, desc);
				pl.setDescription(desc);
				
				Blaze.getLogger().info(name + " v" + version + " has been loaded!");
				pl.onEnable();
			}catch(Exception e)
			{
				Blaze.getLogger().severe("Could not load the plugin " + file.getName() + "!");
				e.printStackTrace();
			}
		}
	}
}
