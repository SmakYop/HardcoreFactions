package com.smakyop.factions.command.player;

import com.smakyop.factions.HardcoreFactions;
import com.smakyop.factions.faction.Faction;
import com.smakyop.factions.faction.FactionRanks;
import com.smakyop.factions.player.FactionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FactionsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(!(commandSender instanceof Player))
            return false;

        Player sender = (Player) commandSender;
        FactionPlayer factionPlayer = FactionPlayer.getPlayer(sender);

        if(args.length == 0){
            HardcoreFactions.getInstance().getFactionsManager().sendHelpMessage(sender);
            return true;
        }

        String firstArg = args[0];

        if(args.length == 1){
            if(! (firstArg.equalsIgnoreCase("list") || firstArg.equalsIgnoreCase("home") || firstArg.equalsIgnoreCase("sethome") || firstArg.equalsIgnoreCase("claim") ||
                    firstArg.equalsIgnoreCase("unclaim") || firstArg.equalsIgnoreCase("leave") || firstArg.equalsIgnoreCase("chat") || firstArg.equalsIgnoreCase("show") || firstArg.equalsIgnoreCase("disband") || firstArg.equalsIgnoreCase("confirm"))){
                HardcoreFactions.getInstance().getFactionsManager().sendHelpMessage(sender);
                return true;
            }

            if(args[0].equalsIgnoreCase("show")){

                if(factionPlayer.getFaction() == null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandPlayerHasNotFaction());
                    return true;
                }

                HardcoreFactions.getInstance().getFactionsManager().sendFactionInformations(factionPlayer, factionPlayer.getFaction());
            }

            if(args[0].equalsIgnoreCase("leave")){
                if(factionPlayer.getFaction() == null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandPlayerHasNotFaction());
                    return true;
                }

                if(factionPlayer.getFactionRanks().getLevel() == 4){
                    sender.sendMessage("");
                    return true;
                }

                factionPlayer.getFaction().sendMessage("§c" + sender.getName() + " §bhas left your faction.");
                factionPlayer.getFaction().removePlayer(sender.getName());
                factionPlayer.setFaction(null);
                factionPlayer.setFactionRanks(FactionRanks.NO_FACTION);
            }

            if(args[0].equalsIgnoreCase("list")){
                
            }
        }

        if(args.length == 2){
            if(! (firstArg.equalsIgnoreCase("show") || firstArg.equalsIgnoreCase("create") || firstArg.equalsIgnoreCase("join") || firstArg.equalsIgnoreCase("invite") ||
                    firstArg.equalsIgnoreCase("revoke") || firstArg.equalsIgnoreCase("kick") || firstArg.equalsIgnoreCase("tag") || firstArg.equalsIgnoreCase("mod") || firstArg.equalsIgnoreCase("unmod"))){
                HardcoreFactions.getInstance().getFactionsManager().sendHelpMessage(sender);
                return true;
            }

            String secondArg = args[1];

            if(firstArg.equalsIgnoreCase("create")){

                if(factionPlayer.getFaction() != null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandPlayerHasAlreadyFaction());
                    return true;
                }

                if(HardcoreFactions.getInstance().getFactionsManager().factionExists(secondArg)){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandFactionNameExists());
                    return true;
                }

                Faction faction = new Faction(secondArg);
                faction.setOwnerUUID(sender.getUniqueId());
                faction.setCreationDate(System.currentTimeMillis());
                faction.setDescription(HardcoreFactions.getInstance().getMessageConfig().getFactionDefaultDescription());
                faction.setKills(0);
                faction.setMoney(0);
                faction.setPoints(0);
                faction.setMaxDtr(HardcoreFactions.getInstance().getGeneralConfig().getMaxDtrUniqueMember());
                faction.setDtr(faction.getMaxDtr());
                faction.setDtrFreezed(false);
                faction.setHomeLocation(null);
                faction.addPlayer(sender.getName());
                factionPlayer.setFaction(faction);
                factionPlayer.setFactionRanks(FactionRanks.LEADER);
                faction.create(factionPlayer);
                return true;
            }

            if(args[0].equalsIgnoreCase("show")){
                Faction faction = Faction.getFaction(secondArg);
                if(faction == null && Bukkit.getPlayer(secondArg) == null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandInvalidPlayerAndFaction());
                    return true;
                }

                if(faction == null){
                    HardcoreFactions.getInstance().getFactionsManager().sendFactionInformations(FactionPlayer.getPlayer(sender), FactionPlayer.getPlayer(Bukkit.getPlayer(secondArg)).getFaction());
                    return true;
                }else{
                    HardcoreFactions.getInstance().getFactionsManager().sendFactionInformations(FactionPlayer.getPlayer(sender), Faction.getFaction(secondArg));
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("join")){
                if(factionPlayer.getFaction() != null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandPlayerHasAlreadyFaction());
                    return true;
                }

                if(factionPlayer.getInvitations().size() == 0){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionNoneInvitation());
                    return true;
                }

                Faction faction = Faction.getFaction(secondArg);
                if(faction == null || !factionPlayer.getInvitations().contains(faction)){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionNotInvit());
                    return true;
                }

                factionPlayer.getInvitations().clear();
                factionPlayer.setFaction(faction);
                sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionSuccessfullyJoinedFaction(faction.getName()));
                faction.addPlayer(sender.getName());
                faction.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionPlayerJoined(sender.getName()));
                return true;
            }

            Player target = Bukkit.getPlayer(secondArg);
            if(target == null){
                sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandPlayerTargetNull());
                return true;
            }

            FactionPlayer factionPlayerTarget = FactionPlayer.getPlayer(target);

            if(args[0].equalsIgnoreCase("invite")){
                if(factionPlayer.getFaction() == null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandPlayerHasNotFaction());
                    return true;
                }

                if(factionPlayer.getFactionRanks().getLevel() < 2){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionPlayerNoPermission());
                    return true;
                }

                if(factionPlayerTarget.getFaction() != null){
                    sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getCommandTargetHasFaction());
                    return true;
                }

                factionPlayerTarget.addInvitations(factionPlayer.getFaction());
                target.sendMessage("");
                target.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionPlayerReceiveInvitation(factionPlayer.getFaction().getName()));
                target.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionPlayerReceiveInvitationClickable());
                target.sendMessage("");
                sender.sendMessage(HardcoreFactions.getInstance().getMessageConfig().getFactionPlayerSentInvitation(target.getName()));
                return true;
            }
        }
        return false;
    }
}