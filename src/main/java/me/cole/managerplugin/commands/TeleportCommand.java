package me.cole.managerplugin.commands;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    private final Manager plugin;

    String playerNotFound;
    String noPerms;

    public TeleportCommand(Manager plugin) {
        this.plugin = plugin;
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player){

            if (player.hasPermission("manager.teleport")){
                if (args.length == 0){
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /teleport <player> [player]");
                }
                else {
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer != null){
                        if (args.length == 1){
                            player.teleport(argumentPlayer);
                            player.sendMessage(ChatColor.YELLOW + "Teleported to " + argumentPlayer.getDisplayName());
                        }
                        else {
                            Player otherPlayer = Bukkit.getServer().getPlayerExact(args[1]);
                            if (otherPlayer != null) {
                                argumentPlayer.teleport(otherPlayer);
                                player.sendMessage(ChatColor.YELLOW + "Teleported " + argumentPlayer.getDisplayName() + " to " + otherPlayer.getDisplayName());
                            }
                            else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound) + args[1]);
                            }
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound) + args[0]);
                    }
                }
            }
            else{
                assert noPerms != null;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
