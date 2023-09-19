package me.cole.managerplugin.commands;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    private final Manager plugin;

    public TeleportCommand(Manager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player){
            String noPerms = plugin.getConfig().getString("no-perms");
            if (player.hasPermission("manager.teleport")){
                if (args.length == 0){

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
