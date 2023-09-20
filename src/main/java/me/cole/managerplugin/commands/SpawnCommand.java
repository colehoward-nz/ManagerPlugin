package me.cole.managerplugin.commands;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;

    public SpawnCommand(Manager plugin){
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            Location location = plugin.getConfig().getLocation("spawn");
            if (location != null){
                player.teleport(location);
                player.sendMessage(ChatColor.YELLOW + "Teleported to spawn.");
            }
            else {
                player.sendMessage(ChatColor.YELLOW + "The spawn location has not been set.");
            }
        }
        return true;
    }
}
