package me.cole.managerplugin.commands.punish;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
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
        for (int i = 0; i < args.length; i++) {
            returnString += " " + args[i];
        }
        return returnString;
    }

    public void mutePlayer(CommandSender staff, Player player, String reason){

    }

    public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.kick") || player.isOp()) {

            }
        }
        Player argumentPlayer = null;
        try {
            argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("me.cole.managerplugin.commands.punish.kickcommand : Cannot find player " + args[0]);
        }
        mutePlayer(sender, argumentPlayer, buildString(args));
        return true;
    }
}
