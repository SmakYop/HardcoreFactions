package com.smakyop.factions.listeners;

import com.smakyop.factions.listeners.player.PlayerChat;
import com.smakyop.factions.listeners.player.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class FactionsListeners {

    private Plugin plugin;
    private PluginManager pluginManager;

    public FactionsListeners(Plugin plugin){
        this.plugin = plugin;
        this.pluginManager = Bukkit.getPluginManager();
    }

    public void registerListeners(){
        pluginManager.registerEvents(new PlayerConnection(), plugin);
        pluginManager.registerEvents(new PlayerChat(), plugin);

    }
}
