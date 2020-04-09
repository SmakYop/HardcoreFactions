package com.smakyop.factions.listeners.player;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener{

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Player sender = event.getPlayer();
        FactionPlayer factionPlayerSender = FactionPlayer.getPlayer(sender);

        for(Player players : Bukkit.getOnlinePlayers()){
            FactionPlayer factionPlayers = FactionPlayer.getPlayer(players);
            if(factionPlayerSender.getFaction() != null)
                players.sendMessage(HardcoreFactions.getInstance().getRelationManager().getColorRelation(factionPlayerSender.getFaction(), factionPlayers.getFaction()) +
                        factionPlayerSender.getFactionRanks().getName() + factionPlayerSender.getFaction().getName() + " §7" + sender.getDisplayName() + ": §7" + event.getMessage());
            else players.sendMessage(HardcoreFactions.getInstance().getRelationManager().getColorRelation(factionPlayerSender.getFaction(), factionPlayers.getFaction()) +
                    sender.getDisplayName() + ": §7" + event.getMessage());
        }
    }
}
