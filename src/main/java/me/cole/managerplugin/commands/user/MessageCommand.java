package me.cole.managerplugin.commands.user;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {
    private final Manager plugin;
    private final String playerNotFound;

    public MessageCommand(Manager plugin){
        this.plugin = plugin;
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
    }

    public String buildString(String[] args){
        String returnString = "";
        for (int i = 0; i < args.length; i++) {
            returnString += " " + args[i];
        }
        return returnString;
    }

    public boolean onCommand(CommandSender sender, Command c, String s, String[] args){
        if (sender instanceof Player player) {
            String last = plugin.getConfig().getString("player." + player.getUniqueId() + ".message.last");
            if (args.length == 0 || args.length == 1) {
                player.sendMessage(ChatColor.RED + "Incorrect usage: /message <player> <message>");
            }
            else {
                Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                if (argumentPlayer!=null){
                    String message = buildString(args);
                    player.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + "->" + ChatColor.YELLOW + argumentPlayer.getDisplayName() + ChatColor.GRAY + ") " + ChatColor.WHITE + message);
                    argumentPlayer.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + argumentPlayer.getDisplayName() + ChatColor.GRAY + "->" + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + ") " + ChatColor.WHITE + message);

                }
                else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound));
                }
            }
            
        }
        return true;
    }
}
