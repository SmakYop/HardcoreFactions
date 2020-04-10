package com.smakyop.factions.player;

import com.smakyop.factions.faction.Faction;
import com.smakyop.factions.faction.FactionRanks;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class FactionPlayer {

    private UUID playerId;
    private Player player;
    private String name;
    private double money;
    private int kills;
    private int deaths;
    private int lives;
    private int points;
    private FactionRanks factionRanks;
    private Faction faction;
    private ArrayList<Faction> invitations;

    private static HashMap<Player, FactionPlayer> players = new HashMap<>();

    public FactionPlayer(Player player){
        this.player = player;
        this.playerId = player.getUniqueId();
        this.name = player.getName();
        this.invitations  = new ArrayList<>();
        players.put(player, this);
    }

    public static FactionPlayer getPlayer(Player player){
        return players.get(player);
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUniqueId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction){
        this.faction = faction;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public FactionRanks getFactionRanks() {
        return factionRanks;
    }

    public void setFactionRanks(FactionRanks factionRanks) {
        this.factionRanks = factionRanks;
    }

    public ArrayList<Faction> getInvitations() {
        return invitations;
    }

    public void addInvitations(Faction faction){
        this.invitations.add(faction);
    }
}
