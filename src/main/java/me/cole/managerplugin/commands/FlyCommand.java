package me.cole.managerplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            if (args.length == 0){
                try {
                    if (player.isFlying()) {
                        player.setFlying(false);
                        player.sendMessage(ChatColor.YELLOW + "Flight mode disabled");
                    } else {
                        player.setFlying(true);
                        player.sendMessage(ChatColor.YELLOW + "Flight mode enabled");
                    }
                } catch (Exception e){
                    player.setFlying(true);
                    player.sendMessage(ChatColor.YELLOW + "Flight mode enabled");
                }
            }
            else if (args.length == 1) {
                Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                if (argumentPlayer != null) {
                    try {
                        if (argumentPlayer.isFlying()) {
                            argumentPlayer.setFlying(false);
                            argumentPlayer.sendMessage(ChatColor.YELLOW + "Flight mode enabled");
                            player.sendMessage(ChatColor.YELLOW + "Flight mode disabled for " + argumentPlayer);
                        } else {
                            argumentPlayer.setFlying(true);
                            argumentPlayer.sendMessage(ChatColor.YELLOW + "Flight mode enabled");
                            player.sendMessage(ChatColor.YELLOW + "Flight mode enabled for " + argumentPlayer);
                        }
                    } catch (Exception e) {
                        argumentPlayer.setFlying(true);
                        argumentPlayer.sendMessage(ChatColor.YELLOW + "Flight mode enabled");
                        player.sendMessage(ChatColor.YELLOW + "Flight mode enabled for " + argumentPlayer);
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Cannot find player " + args[0] + ".");
                }
            }
        }
        return true;
    }
}
