package com.smakyop.factions.faction;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.dtr.DTRManager;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Faction {

    private String name;
    private String description;
    private double money;
    private double dtr;
    private double maxDtr;
    private boolean isDtrFreezed;
    private int kills;
    private int points;
    private long creationDate;
    private UUID ownerId;
    private List<Player> members;
    private List<Faction> allies;
    private DTRManager dtrManager;
    private Location homeLocation;

    private static HashMap<String, Faction> factions = new HashMap<>();

    public Faction(String name){
        this.name = name;
        this.members = new ArrayList<>();
        this.allies = new ArrayList<>();
        this.dtrManager = new DTRManager(this);
        factions.put(name, this);
    }

    public static Faction getFaction(String name){
        return factions.get(name);
    }

    public String getName() {
        return name;
    }

    public void setOwnerUUID(UUID ownerId){
        this.ownerId = ownerId;
    }

    public UUID getOwnerUUID(){
        return ownerId;
    }

    public void setCreationDate(long date){
        this.creationDate = date;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getDtr() {
        return dtr;
    }

    public void setDtr(double dtr) {
        this.dtr = dtr;
    }

    public double getMaxDtr() {
        return maxDtr;
    }

    public void setMaxDtr(double maxDtr) {
        this.maxDtr = maxDtr;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isDtrFreezed() {
        return isDtrFreezed;
    }

    public void setDtrFreezed(boolean dtrFreezed) {
        isDtrFreezed = dtrFreezed;
    }

    public List<Faction> getAllies() {
        return allies;
    }

    public void addFactionAlly(Faction faction){
        this.allies.add(faction);
    }

    public List<Player> getMembers() {
        return members;
    }

    public void addPlayer(Player player){
        this.members.add(player);
    }

    public boolean isAllyWith(Faction faction){
        return this.allies.contains(faction);
    }

    public DTRManager getDtrManager() {
        return dtrManager;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
    }

    public void create(FactionPlayer creator){
        if (HardcoreFactions.getInstance().getGeneralConfig().hasDatabase())
            HardcoreFactions.getInstance().getFactionsDatabase().createFaction(this.name);
        else HardcoreFactions.getInstance().getFactionsConfig().createFaction(this.name);

        for(Player player : Bukkit.getOnlinePlayers()){
            FactionPlayer factionPlayer = FactionPlayer.getPlayer(player);
            player.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionCreate(creator, factionPlayer));
        }
    }

    public int getOnlinePlayers(){
        int online = 0;
        for(Player player : members){
            if(player.isOnline()) online++;
        }
        return online;
    }
}
