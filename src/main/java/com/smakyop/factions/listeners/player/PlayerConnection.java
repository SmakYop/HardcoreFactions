package com.smakyop.factions.listeners.player;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnection implements Listener{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        FactionPlayer factionPlayer = new FactionPlayer(player);
        HardcoreFactions.getInstance().getPlayerManager().loadPlayer(factionPlayer);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        FactionPlayer factionPlayer = FactionPlayer.getPlayer(player);
        HardcoreFactions.getInstance().getPlayerManager().disconnectPlayer(factionPlayer);
    }
}
