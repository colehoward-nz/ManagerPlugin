package me.cole.managerplugin.commands.punish;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {
    private final Manager plugin;
    private final String playerNotFound;
    private final String noPerms;

    public MuteCommand(Manager plugin) {
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

    public void mutePlayer(CommandSender staff, Player player, String reason) {
        String isMuted = plugin.getConfig().getString("player." + player.getUniqueId().toString() + ".muted.ismuted");
        String applier = "";
        if (staff instanceof Player staffPlayer) {
            applier = staffPlayer.getDisplayName();
        }

        if (isMuted == null || isMuted.equalsIgnoreCase("false")) {
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".muted.ismuted", "true");
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".muted.reason", reason);
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".muted.applier", applier);

            staff.sendMessage(ChatColor.YELLOW + "Mute applied successfully for " + player.getDisplayName());
            player.sendMessage(ChatColor.RED + "You have been muted by " + applier +
                    "\nYou can no longer type in chat" +
                    "\nReason: " + reason);
        }
        else {
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".muted.ismuted", "false");
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".muted.reason", "squashed ->"+reason);
            plugin.getConfig().set("player." + player.getUniqueId().toString() + ".muted.applier", applier);
            staff.sendMessage(ChatColor.YELLOW + "Mute removed successfully for " + player.getDisplayName());
            player.sendMessage(ChatColor.GREEN + "You have been unmuted by " + applier + ". You can now type in chat again");
        }
        plugin.saveConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.mute") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /mute <player> [reason]");
                }
                else if (args.length == 1) {
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer != null) {
                        mutePlayer(player, argumentPlayer, "No reason");
                    }
                    else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound));
                    }
                }
                else {
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer != null) {
                        mutePlayer(player, argumentPlayer, buildString(args));
                    }
                    else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound));
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
                System.out.println("me.cole.managerplugin.commands.punish.mutecommand : Cannot find player " + args[0]);
            }
            mutePlayer(sender, argumentPlayer, buildString(args));
        }
        return true;
    }
}
