package com.smakyop.factions.player;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.faction.FactionRanks;
import org.bukkit.Bukkit;

import java.util.UUID;

public class PlayerManager {

    public void loadPlayer(FactionPlayer factionPlayer){
        if(HardcoreFactions.getInstance().getGeneralConfig().hasDatabase()){

        }else{
            if(HardcoreFactions.getInstance().getPlayersConfig().exists(factionPlayer)){
                factionPlayer.setKills(HardcoreFactions.getInstance().getPlayersConfig().getKills(factionPlayer.getUniqueId()));
                factionPlayer.setDeaths(HardcoreFactions.getInstance().getPlayersConfig().getDeaths(factionPlayer.getUniqueId()));
                factionPlayer.setLives(HardcoreFactions.getInstance().getPlayersConfig().getLives(factionPlayer.getUniqueId()));
                factionPlayer.setPoints(HardcoreFactions.getInstance().getPlayersConfig().getPoints(factionPlayer.getUniqueId()));
                factionPlayer.setMoney(HardcoreFactions.getInstance().getPlayersConfig().getMoney(factionPlayer.getUniqueId()));
                factionPlayer.setFactionRanks(HardcoreFactions.getInstance().getPlayersConfig().getFactionRank(factionPlayer.getUniqueId()));
                factionPlayer.setFaction(HardcoreFactions.getInstance().getPlayersConfig().getFaction(factionPlayer.getUniqueId()));
            }else{
                factionPlayer.setKills(0);
                factionPlayer.setDeaths(0);
                factionPlayer.setLives(0);
                factionPlayer.setMoney(0);
                factionPlayer.setPoints(0);
                factionPlayer.setFactionRanks(FactionRanks.NO_FACTION);
                HardcoreFactions.getInstance().getPlayersConfig().savePlayer(factionPlayer);
            }
        }
    }

    public void disconnectPlayer(FactionPlayer factionPlayer){
        HardcoreFactions.getInstance().getPlayersConfig().savePlayer(factionPlayer);
    }

    public String getPlayerName(UUID uuid){
        return Bukkit.getPlayer(uuid) == null ? Bukkit.getOfflinePlayer(uuid).getName() : Bukkit.getPlayer(uuid).getName();
    }
}
