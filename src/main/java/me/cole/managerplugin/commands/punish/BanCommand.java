package me.cole.managerplugin.commands.punish;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
    private final Manager plugin;
    private final String playerNotFound;
    private final String noPerms;

    public BanCommand(Manager plugin){
        this.plugin = plugin;
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public String buildString(String[] args){
        String returnString = "";
        for (int i = 1; i < args.length; i++) {
            returnString += " " + args[i];
        }
        return returnString;
    }

    public void banPlayer(CommandSender staff, Player player, String reason) {
        String isBanned = plugin.getConfig().getString("player." + player.getUniqueId() + "banned.isbanned");
        String applier = "";
        if (staff instanceof Player staffPlayer) {
            applier = staffPlayer.getDisplayName();
        }

        if (isBanned == null || isBanned.equalsIgnoreCase("false")) {
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".banned.isbanned", "true");
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".banned.applier", applier);
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".banned.reason", reason);

            staff.sendMessage(ChatColor.YELLOW + "Ban applied successfully for " + player.getDisplayName());
            String kickReason = ChatColor.RED + "You have been banned by " + applier + ".\nReason: " + reason;
            player.kickPlayer(kickReason);
        }
        else {
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".banned.ismuted", "false");
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".banned.applier", applier);
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".banned.reason", "squashed ->"+reason);
            staff.sendMessage(ChatColor.YELLOW + "Ban removed successfully for " + player.getDisplayName());
        }
        plugin.saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.ban") || player.isOp()){
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /ban <player> [reason]");
                }
                else {
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound));
                    }
                    else {
                        if (args.length == 1) {
                            banPlayer(player, argumentPlayer, "No reason");
                        }
                        else {
                            banPlayer(player, argumentPlayer, buildString(args));
                        }
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        else {
            Player argumentPlayer = null;
            try {
                argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("me.cole.managerplugin.commands.punish.bancommand : Cannot find player " + args[0]);
            }
            banPlayer(sender, argumentPlayer, buildString(args));
        }
        return true;
    }
}
