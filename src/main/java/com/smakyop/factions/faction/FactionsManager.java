package com.smakyop.factions.faction;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionsManager {

    public void sendHelpMessage(Player player){
        player.sendMessage("§b§m-----------------------------------------");
        player.sendMessage("                        §6§lHardcore Faction               ");
        player.sendMessage("§b§m-----------------------------------------");
        player.sendMessage("§6/f create : §bCreate your faction");
        player.sendMessage("§6/f show : §bShow faction's informations");
        player.sendMessage("§6/f tag : §bChange your faction's name");
        player.sendMessage("§6/f join : §bJoin a faction");
        player.sendMessage("§6/f list : §bList of all factions on the server");
        player.sendMessage("§6/f home : §bTeleport to your faction home");
        player.sendMessage("§6/f sethome : §bSet your faction home");
        player.sendMessage("§6/f leave : §bLeave your faction");
        player.sendMessage("§6/f invite : §bInvit a player in your faction");
        player.sendMessage("§6/f revoke : §bCancel an invitation");
        player.sendMessage("§6/f claim : §bClaim a territory for your faction");
        player.sendMessage("§6/f unclaim : §bUnclaim a faction's territory");
        player.sendMessage("§6/f kick : §bKick a player from your faction");
        player.sendMessage("§6/f mod : §bUp a player's permissions in your faction");
        player.sendMessage("§6/f unmod : §bRemove a player's permissions in your faction");
        player.sendMessage("§6/f chat : §bSet your chat to faction chat");
        player.sendMessage("§6/f disband : §bRemove your faction (need confirmation)");
    }

    public boolean factionExists(String name){
        if(HardcoreFactions.getInstance().getGeneralConfig().hasDatabase()){
            return HardcoreFactions.getInstance().getFactionsDatabase().nameExists(name);
        }else{
            if(HardcoreFactions.getInstance().getFactionsConfig().getConfiguration().getConfigurationSection("factions") == null)
                return false;

            for(String factionName : HardcoreFactions.getInstance().getFactionsConfig().getConfiguration().getConfigurationSection("factions").getKeys(false))
                if(factionName.equals(name))
                    return true;
            return false;
        }
    }

    public void loadFactions(){
        if(HardcoreFactions.getInstance().getGeneralConfig().hasDatabase()){
            for(String factionName : HardcoreFactions.getInstance().getFactionsDatabase().getRegisteredFactions()){
                Faction faction = new Faction(factionName);
                faction.setOwnerUUID((UUID)HardcoreFactions.getInstance().getFactionsDatabase().get("owner_uuid", factionName));
                faction.setDtr((double)HardcoreFactions.getInstance().getFactionsDatabase().get("dtr", factionName));
                faction.setMaxDtr((double)HardcoreFactions.getInstance().getFactionsDatabase().get("dtr_max", factionName));
                faction.setMoney((double)HardcoreFactions.getInstance().getFactionsDatabase().get("money", factionName));
                faction.setPoints((int)HardcoreFactions.getInstance().getFactionsDatabase().get("points", factionName));
                faction.setKills((int)HardcoreFactions.getInstance().getFactionsDatabase().get("kills", factionName));
                faction.setDescription((String) HardcoreFactions.getInstance().getFactionsDatabase().get("description", factionName));
                faction.setDtrFreezed((boolean)HardcoreFactions.getInstance().getFactionsDatabase().get("dtr_freezed", factionName));
                faction.setCreationDate((long)HardcoreFactions.getInstance().getFactionsDatabase().get("creation_date", factionName));
            }
        }else{
            if(HardcoreFactions.getInstance().getFactionsConfig().getConfiguration().getConfigurationSection("factions") == null)
                return;

            for(String factionName : HardcoreFactions.getInstance().getFactionsConfig().getConfiguration().getConfigurationSection("factions").getKeys(false)){
                Faction faction = new Faction(factionName);
                faction.setOwnerUUID(HardcoreFactions.getInstance().getFactionsConfig().getOwnerUUID(faction.getName()));
                faction.setDtr(HardcoreFactions.getInstance().getFactionsConfig().getDtr(faction.getName()));
                faction.setMaxDtr(HardcoreFactions.getInstance().getFactionsConfig().getMaxDtr(faction.getName()));
                faction.setMoney(HardcoreFactions.getInstance().getFactionsConfig().getMoney(faction.getName()));
                faction.setPoints(HardcoreFactions.getInstance().getFactionsConfig().getPoints(faction.getName()));
                faction.setKills(HardcoreFactions.getInstance().getFactionsConfig().getKills(faction.getName()));
                faction.setDescription(HardcoreFactions.getInstance().getFactionsConfig().getDescription(faction.getName()));
                faction.setCreationDate(HardcoreFactions.getInstance().getFactionsConfig().getCreationDate(faction.getName()));
            }
        }
    }

    public void sendFactionInformations(FactionPlayer receiver, Faction faction){
        Player player = receiver.getPlayer();
        player.sendMessage("§6§m-----------------------------------------");
        player.sendMessage("§6> " + HardcoreFactions.getInstance().getRelationManager().getColorRelation(receiver.getFaction(), faction) + faction.getName() +
            " §7(§b" + faction.getOnlinePlayers() + "§7/§b" + faction.getMembers().size() + "§7)");
        player.sendMessage("§bDescription: §6" + faction.getDescription());
        player.sendMessage("§bDTR: " + getDtrColor(faction) + faction.getDtr() + " §7(" + faction.getDtrManager().getDtrStatus().getName() + ")");
        player.sendMessage("§bHome location: §6" + getHomeLocationFormat(faction));
        player.sendMessage("§bPoints: §6" + faction.getPoints() + " §7(§6" + faction.getKills() + " §7kills)");
        player.sendMessage("§bBalance: §6" + faction.getMoney() + "$");
        player.sendMessage("§bLeader: §6" + HardcoreFactions.getInstance().getPlayerManager().getPlayerName(faction.getOwnerUUID()));
        player.sendMessage("§bMembers: §6" + faction.getMembers().toString().replace("[", "").replace("]", ""));
        player.sendMessage("§6§m-----------------------------------------");
    }

    private ChatColor getDtrColor(Faction faction){
        return faction.getDtr() > 0 ? ChatColor.GREEN : ChatColor.RED;
    }

    private String getHomeLocationFormat(Faction faction){
        if(faction.getHomeLocation() == null)
            return "None";
        return ("X: " + faction.getHomeLocation().getX() + " Y: " + faction.getHomeLocation().getY() + " Z: " + faction.getHomeLocation().getZ());
    }
}
