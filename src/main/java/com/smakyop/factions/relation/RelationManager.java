package com.smakyop.factions.relation;

import com.smakyop.factions.faction.Faction;
import org.bukkit.ChatColor;

public class RelationManager {

    private Relation getFactionRelation(Faction firstFaction, Faction secondFaction){
        if(firstFaction == null || secondFaction == null) return Relation.NEUTRAL;
        if(firstFaction == secondFaction) return Relation.FACTION;
        if(firstFaction.isAllyWith(secondFaction)) return Relation.ALLY;
        return Relation.NEUTRAL;
    }

    public ChatColor getColorRelation(Faction firstFaction, Faction secondFaction){
        return (getFactionRelation(firstFaction, secondFaction)).getRelationColor();
    }
}
