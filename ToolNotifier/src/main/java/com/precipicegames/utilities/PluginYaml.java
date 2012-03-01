package com.precipicegames.utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PluginYaml extends YamlConfiguration {

	HashMap<String, String> cache = new HashMap<String, String>();
	private File config;
	public ChatColor warningColor;
	public ChatColor dangerColor;
	public HashMap<Integer, String> notify = new HashMap<Integer, String>();
	public HashMap<Player, Integer> players = new HashMap<Player, Integer>();

	public PluginYaml(File file) {
		if(file != null && file.exists()) {
			try {
				this.load(file);
			} catch (Exception e) {
				Bukkit.getLogger().warning("[ToolNotifier] Error loading config.yml! Using default values...");
			}
		}
		
		//TODO: Pull hashmap values out of the configuration
		
		this.config = file;
	}

	public void save() { //Persist the data
		Iterator<Entry<String, String>> itcache = this.cache.entrySet().iterator();
		Iterator<Entry<Player, Integer>> itplayers = this.players.entrySet().iterator();
		Iterator<Entry<Integer, String>> itnotify = this.notify.entrySet().iterator();
		//Save the cached data
		while(itcache.hasNext()) {
			Map.Entry<String, String> pair = itcache.next();
			this.set(pair.getKey(), pair.getValue());
		}
		//Save the players
		while(itplayers.hasNext()) {
			Map.Entry<Player, Integer> pair = itplayers.next();
			this.set("notify."+pair.getKey().getName()+".usesleft", pair.getValue());
		}
		//Save the notifications
		while(itnotify.hasNext()) {
			Map.Entry<Integer, String> pair = itnotify.next();
			this.set("notify.message."+pair.getKey(), pair.getValue());
		}
		
		//TODO: Store the Colors
		
		try {
			this.save(config);
		} catch (Exception e) {
			Bukkit.getLogger().warning("[ToolNotifier] Error saving config.yml! Aborting save!");
			Bukkit.getLogger().info(e.getCause().getMessage());
		}
	}
	
	/*
	 * Future proofing
	 */
	public String getString(String key, String value) {
		if(this.cache.containsKey(key)) { //Pulling from the cache is Default
			return this.cache.get(key);
		} else if(this.isString(key)) {
			this.cache.put(key, this.getString(key)); //add it to the cache
			return this.getString(key);
		} else {
			this.cache.put(key, value); //the path doesn't exist, so add it to the cache to be saved later
			return value;
		}
	}

}
