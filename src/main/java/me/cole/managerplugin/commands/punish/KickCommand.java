package me.cole.managerplugin.commands.punish;

import com.sun.jdi.connect.Connector;
import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;
    private final String playerNotFound;

    public KickCommand(Manager plugin) {
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
    }

    public String buildString(String[] args){
        String returnString = "";
        for (int i = 1; i < args.length; i++) {
            returnString += args[i] + " ";
        }
        return returnString;
    }

    public void kickPlayer(CommandSender staff, Player player, String reason){
        String applier = "";
        if (staff instanceof Player staffPlayer) {
            applier = staffPlayer.getDisplayName();
        }

        staff.sendMessage(ChatColor.YELLOW + "Kick applied successfully on " + player.getDisplayName());
        String kickReason = ChatColor.RED + "You have been kicked by " + applier + ".\nReason: " + reason;
        player.kickPlayer(kickReason);
    }

    public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.kick") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /kick <player> [reason]");
                }
                else {
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound));
                    }
                    else {
                        if (args.length == 1) {
                            kickPlayer(player, argumentPlayer, "No reason");
                        }
                        else {
                            kickPlayer(player, argumentPlayer, buildString(args));
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
                System.out.println("me.cole.managerplugin.commands.punish.kickcommand : Cannot find player " + args[0]);
            }
            kickPlayer(sender, argumentPlayer, buildString(args));
        }
        return true;
    }
}
