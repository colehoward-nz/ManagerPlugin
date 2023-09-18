package me.cole.managerplugin.commands;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {
    private ArrayList<Player> flyingPlayers = new ArrayList<>();
    private Manager plugin;
    public FlyCommand(Manager plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            String noPerms = plugin.getConfig().getString("no-perms");
            String flyOn = plugin.getConfig().getString("fly-on");
            String flyOff = plugin.getConfig().getString("fly-off");
            if (player.hasPermission("manager.fly")){

            }
            else {
                assert noPerms != null;
                player.sendMessage(noPerms);
            }
        }
        return true;
    }
}
