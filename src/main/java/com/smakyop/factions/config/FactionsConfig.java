package com.smakyop.factions.config;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.faction.Faction;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FactionsConfig {

    private File file = new File(HardcoreFactions.getInstance().getDataFolder(), "factions.yml");
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

    public void createFaction(String name){
        Faction faction = Faction.getFaction(name);
        try{
            configuration.set("factions." + name + ".owner", faction.getOwnerUUID().toString());
            configuration.set("factions." + name + ".creation-date", faction.getCreationDate());
            configuration.set("factions." + name + ".dtr", faction.getDtr());
            configuration.set("factions." + name + ".max-dtr", faction.getMaxDtr());
            configuration.set("factions." + name + ".dtr-freezed", faction.isDtrFreezed());
            configuration.set("factions." + name + ".kills", faction.getKills());
            configuration.set("factions." + name + ".points", faction.getPoints());
            configuration.set("factions." + name + ".money", faction.getMoney());
            configuration.set("factions." + name + ".description", faction.getDescription());
            configuration.set("factions." + name + ".members", faction.getMembers());
            configuration.save(this.file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public UUID getOwnerUUID(String factionName){
        return UUID.fromString(configuration.getString("factions." + factionName + ".owner"));
    }

    public long getCreationDate(String factionName){
        return configuration.getLong("factions." + factionName + ".creation-date");
    }

    public double getDtr(String factionName){
        return configuration.getDouble("factions." + factionName + ".dtr");
    }

    public double getMaxDtr(String factionName){
        return configuration.getDouble("factions." + factionName + ".max-dtr");
    }

    public boolean isDtrFreezed(String factionName){
        return configuration.getBoolean("factions." + factionName + ".dtr-freezed");
    }

    public int getKills(String factionName){
        return configuration.getInt("factions." + factionName + ".kills");
    }

    public int getPoints(String factionName){
        return configuration.getInt("factions." + factionName + ".points");
    }

    public double getMoney(String factionName){
        return configuration.getDouble("factions." + factionName + ".money");
    }

    public String getDescription(String factionName){
        return configuration.getString("factions." + factionName + ".description");
    }

    public List<String> getMembers(String factionName){
        return configuration.getStringList("factions." + factionName + ".members");
    }

    public void remove(String factionName){

    }
}
