package me.cole.managerplugin.commands.user;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {
    private final Manager plugin;
    private final String playerNotFound;
    private final String noReplyTarget;

    public ReplyCommand(Manager plugin) {
        this.plugin = plugin;
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
        this.noReplyTarget = plugin.getConfig().getString("no-reply-target");

    }

    public String buildString(String[] args){
        String returnString = "";
        for (int i = 0; i < args.length; i++) {
            returnString += args[i] + " ";
        }
        return returnString;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player player && args.length >= 1) {
            if (plugin.mm.getReplyTarget(player) == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noReplyTarget));
                return true;
            }
            Player argumentPlayer = plugin.mm.getReplyTarget(player);

            player.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + "<-" + ChatColor.YELLOW + argumentPlayer.getDisplayName() + ChatColor.GRAY + ") " + ChatColor.WHITE + buildString(args));
            argumentPlayer.sendMessage(ChatColor.GRAY + "(" + ChatColor.YELLOW + argumentPlayer.getDisplayName() + ChatColor.GRAY + "<-" + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + ") " + ChatColor.WHITE + buildString(args));
        }
        return false;
    }
}
