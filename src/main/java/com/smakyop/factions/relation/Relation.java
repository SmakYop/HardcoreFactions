package com.smakyop.factions.relation;

import com.smakyop.factions.HardcoreFactions;
import org.bukkit.ChatColor;

public enum Relation {

    NEUTRAL("Neutral", ChatColor.RED),
    ALLY("Ally", ChatColor.DARK_AQUA),
    FACTION("Faction", ChatColor.GREEN);

    private String relationName;
    private ChatColor relationColor;

    Relation(String relationName, ChatColor relationColor){
        this.relationName = relationName;
        this.relationColor = relationColor;
    }

    public String getRelationName(){
        return this.relationName;
    }

    public ChatColor getRelationColor(){
        return this.relationColor;
    }

    public boolean isAllyActivated(){
        return HardcoreFactions.getInstance().getConfig().getBoolean("allies");
    }
}
