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
            configuration.set("command.target-has-faction", "&cThe player is already on a faction.");

            configuration.set("faction.create", "%relation-color%%player-rank%%player-name% &bhas created a new faction: %relation-color%%faction-name%");
            configuration.set("faction.default-description", "Default description");
            configuration.set("faction.player-no-permission", "&cYou haven't the permission.");
            configuration.set("faction.none-invitations", "&cYou haven't any invitations.");
            configuration.set("faction.faction-not-invit", "&cThis faction has not invited you.");
            configuration.set("faction.successfully-join-faction", "&bYou successfully joined the faction: &a%faction-name%");
            configuration.set("faction.player-join-faction", "&a%player-name% &bhas joined your faction.");
            configuration.set("faction.player-receive-faction-invitation", "&bYou received an invitation to join the faction: &c%faction-name%");
            configuration.set("faction.player-receive-faction-invitation-clickable", "&7Click here to join the faction!");
            configuration.set("faction.player-sent-faction-invitation", "&bYou successfully invited: &c%player-name%");
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

    public String getCommandTargetHasFaction(){
        return this.configuration.getString("command.target-has-faction").replace("&", "§");
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

    public String getFactionPlayerNoPermission(){
        return this.configuration.getString("faction.player-no-permission").replace("&", "§");

    }

    public String getFactionNoneInvitation(){
        return this.configuration.getString("faction.none-invitations").replace("&", "§");

    }

    public String getFactionNotInvit(){
        return this.configuration.getString("faction.faction-not-invit").replace("&", "§");

    }

    public String getFactionSuccessfullyJoinedFaction(String factionName){
        return this.configuration.getString("faction.successfully-join-faction").replace("&", "§").replace("%faction-name%", factionName);

    }

    public String getFactionPlayerJoined(String playerName){
        return this.configuration.getString("faction.player-join-faction").replace("&", "§").replace("%player-name%", playerName);

    }

    public String getFactionPlayerReceiveInvitation(String factionName){
        return this.configuration.getString("faction.player-receive-faction-invitation").replace("&", "§").replace("%faction-name%", factionName);

    }

    public String getFactionPlayerSentInvitation(String playerName){
        return this.configuration.getString("faction.player-sent-faction-invitation").replace("&", "§").replace("%player-name%", playerName);

    }

    public String getFactionPlayerReceiveInvitationClickable(){
        return this.configuration.getString("faction.player-receive-faction-invitation-clickable").replace("&", "§");

    }
}
