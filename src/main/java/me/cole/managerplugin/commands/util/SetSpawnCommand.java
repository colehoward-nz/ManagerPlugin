package me.cole.managerplugin.commands.util;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;

    public SetSpawnCommand(Manager plugin){
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            if (player.hasPermission("manager.setspawn") || player.isOp()){
                Location location = player.getLocation();
                plugin.getConfig().set("spawn", location);
                plugin.saveConfig();
                player.sendMessage(ChatColor.YELLOW + "Set spawn location.");
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
