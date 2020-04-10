package com.smakyop.factions;

import com.smakyop.factions.command.player.FactionsCommand;
import com.smakyop.factions.config.FactionsConfig;
import com.smakyop.factions.config.GeneralConfig;
import com.smakyop.factions.config.MessageConfig;
import com.smakyop.factions.config.PlayersConfig;
import com.smakyop.factions.database.DatabaseManager;
import com.smakyop.factions.database.FactionsDatabase;
import com.smakyop.factions.database.PlayersDatabase;
import com.smakyop.factions.faction.FactionsManager;
import com.smakyop.factions.listeners.FactionsListeners;
import com.smakyop.factions.player.PlayerManager;
import com.smakyop.factions.relation.RelationManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HardcoreFactions extends JavaPlugin{

    private static HardcoreFactions instance;

    private FactionsManager factionsManager;
    private GeneralConfig generalConfig;
    private FactionsConfig factionsConfig;
    private FactionsDatabase factionsDatabase;
    private PlayersDatabase playerDatabase;
    private DatabaseManager databaseManager;
    private MessageConfig messageConfig;
    private PlayerManager playerManager;
    private PlayersConfig playersConfig;
    private RelationManager relationManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        this.factionsManager = new FactionsManager();
        this.generalConfig = new GeneralConfig();
        this.factionsConfig = new FactionsConfig();
        this.factionsDatabase = new FactionsDatabase();
        this.playerDatabase = new PlayersDatabase();
        this.databaseManager = new DatabaseManager();
        this.messageConfig = new MessageConfig();
        this.playerManager = new PlayerManager();
        this.playersConfig = new PlayersConfig();
        this.relationManager = new RelationManager();

        new FactionsListeners(this).registerListeners();

        if(!this.generalConfig.hasDatabase()){
            log("Database: OFF");
            log("Loading factions file...");
            this.factionsConfig.loadFile();
            this.playersConfig.loadFile();
            log("Faction.yml loaded.");

        } else this.databaseManager.connect();

        this.getCommand("faction").setExecutor(new FactionsCommand());
        this.messageConfig.loadFile();
        this.factionsManager.loadFactions();
    }

    @Override
    public void onDisable() {
        this.factionsManager.saveFactions();
        instance = null;
    }

    public static HardcoreFactions getInstance(){
        return instance;
    }

    public FactionsManager getFactionsManager() {
        return factionsManager;
    }

    public GeneralConfig getGeneralConfig() {
        return generalConfig;
    }

    public FactionsConfig getFactionsConfig() {
        return factionsConfig;
    }

    public FactionsDatabase getFactionsDatabase() {
        return factionsDatabase;
    }

    public PlayersDatabase getPlayersDatabase() {
        return playerDatabase;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public PlayersConfig getPlayersConfig() {
        return playersConfig;
    }

    public RelationManager getRelationManager() {
        return relationManager;
    }

    public void log(String message){
        instance.getServer().getLogger().info("[HardcoreFactions] " + message);
    }
}
