package com.smakyop.factions.command.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SecretCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        commandSender.sendMessage("§7This server is running with: §cHardcoreFactions.");
        commandSender.sendMessage("§7Author: §cSmakYop");
        return false;
    }
}
