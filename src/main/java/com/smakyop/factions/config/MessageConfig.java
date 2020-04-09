package com.smakyop.factions.config;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfig {

    private File file = new File(HardcoreFactions.getInstance().getDataFolder(), "messages.yml");
    private FileConfiguration configuration = YamlConfiguration.loadConfiguration(this.file);

    public void loadFile(){
        try {
            if (!file.exists()) {
                file.createNewFile();
                createDefaultConfig();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createDefaultConfig(){
        try{
            configuration.set("command.player-target-null", "&cInvalid player.");
            configuration.set("command.faction-name-already-exists", "&cThis name is already taken.");
            configuration.set("command.player-has-already-faction", "&cYou are already on a faction.");
            configuration.set("command.player-has-not-faction", "&cYou are not on a faction.");
            configuration.set("command.invalid-player-and-faction", "&cInvalid player or faction.");

            configuration.set("faction.create", "%relation-color%%player-rank%%player-name% &bhas created a new faction: %relation-color%%faction-name%");
            configuration.set("faction.default-description", "Default description");
            configuration.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public String getCommandPlayerTargetNull(){
        return this.configuration.getString("command.player-target-null").replace("&", "§");
    }

    public String getCommandFactionNameExists(){
        return this.configuration.getString("command.faction-name-already-exists").replace("&", "§");
    }

    public String getCommandPlayerHasAlreadyFaction(){
        return this.configuration.getString("command.player-has-already-faction").replace("&", "§");
    }

    public String getCommandPlayerHasNotFaction(){
        return this.configuration.getString("command.player-has-not-faction").replace("&", "§");
    }

    public String getCommandInvalidPlayerAndFaction(){
        return this.configuration.getString("command.invalid-player-and-faction").replace("&", "§");
    }

    public String getFactionCreate(FactionPlayer playerCreator, FactionPlayer receiver){
        return this.configuration.getString("faction.create").replace("&", "§")
                .replace("%player-rank%", playerCreator.getFactionRanks().getName())
                .replace("%player-name%", playerCreator.getName())
                .replace("%faction-name%", playerCreator.getFaction().getName())
                .replace("%relation-color%", HardcoreFactions.getInstance().getRelationManager().getColorRelation(playerCreator.getFaction(), receiver.getFaction()).toString());
    }

    public String getFactionDefaultDescription(){
        return this.configuration.getString("faction.default-description");
    }
}
