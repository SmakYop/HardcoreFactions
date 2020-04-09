package com.smakyop.factions.config;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.faction.Faction;
import com.smakyop.factions.faction.FactionRanks;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayersConfig {

    private File file = new File(HardcoreFactions.getInstance().getDataFolder(), "players.yml");
    private FileConfiguration configuration = YamlConfiguration.loadConfiguration(this.file);

    public void loadFile(){
        try {
            if (!file.exists())
                file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public void savePlayer(FactionPlayer factionPlayer){
        try{
            configuration.set("players." + factionPlayer.getUniqueId() + ".name", factionPlayer.getName());
            configuration.set("players." + factionPlayer.getUniqueId() + ".kills", factionPlayer.getKills());
            configuration.set("players." + factionPlayer.getUniqueId() + ".deaths", factionPlayer.getDeaths());
            configuration.set("players." + factionPlayer.getUniqueId() + ".lives", factionPlayer.getLives());
            configuration.set("players." + factionPlayer.getUniqueId() + ".points", factionPlayer.getPoints());

            if(factionPlayer.getFaction() != null)
                configuration.set("players." + factionPlayer.getUniqueId() + ".faction.name", factionPlayer.getFaction().getName());
            else configuration.set("players." + factionPlayer.getUniqueId() + ".faction.name", null);

            configuration.set("players." + factionPlayer.getUniqueId() + ".faction.rank", factionPlayer.getFactionRanks().getLevel());
            configuration.set("players." + factionPlayer.getUniqueId() + ".money", factionPlayer.getMoney());
            configuration.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean exists(FactionPlayer factionPlayer){
        return configuration.get("players." + factionPlayer.getUniqueId()) != null;
    }

    public int getKills(UUID playerId){
        return configuration.getInt("players." + playerId + ".kills");
    }

    public int getDeaths(UUID playerId){
        return configuration.getInt("players." + playerId + ".deaths");
    }

    public int getLives(UUID playerId){
        return configuration.getInt("players." + playerId + ".lives");
    }

    public int getPoints(UUID playerId){
        return configuration.getInt("players." + playerId + ".points");
    }

    public double getMoney(UUID playerId){
        return configuration.getDouble("players." + playerId + ".money");
    }

    public Faction getFaction(UUID playerId){
        return Faction.getFaction(configuration.getString("players." + playerId + ".faction.name"));
    }

    public FactionRanks getFactionRank(UUID playerId){
        return FactionRanks.getRankByLevel(configuration.getInt("players." + playerId + ".faction.rank"));
    }
}
