package com.smakyop.factions.config;

import com.smakyop.factions.HardcoreFactions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class GeneralConfig {

    private File file = new File(HardcoreFactions.getInstance().getDataFolder(), "config.yml");
    private FileConfiguration configuration = YamlConfiguration.loadConfiguration(this.file);

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public boolean hasDatabase(){
        return this.configuration.getBoolean("database.enable");
    }

    public String getDatabaseUser(){
        return this.configuration.getString("database.user");
    }

    public String getDatabaseUrl(){
        return this.configuration.getString("database.url");
    }

    public String getDatabasePassword(){
        return this.configuration.getString("database.password");
    }

    public double getDtrMax(){
        return this.configuration.getDouble("dtr.dtr-max");
    }

    public double getDtrMin(){
        return this.configuration.getDouble("dtr.dtr-min");
    }

    public double getMaxDtrUniqueMember(){
        return this.configuration.getDouble("dtr.dtr-max-with-one-member");
    }
}
