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
    private final Manager plugin;
    public FlyCommand(Manager plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            String noPerms = plugin.getConfig().getString("no-perms");
            String playerNotFound = plugin.getConfig().getString("player-not-found");
            String flyOn = plugin.getConfig().getString("fly-on");
            String flyOff = plugin.getConfig().getString("fly-off");
            if (player.hasPermission("manager.fly")){
                if (args.length == 0) {
                    if (flyingPlayers.contains(player)) {
                        flyingPlayers.remove(player);
                        player.setAllowFlight(false);
                        assert flyOff != null;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', flyOff));
                    } else {
                        flyingPlayers.add(player);
                        player.setAllowFlight(true);
                        assert flyOn != null;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', flyOn));
                    }
                }
                else{
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer != null){

                    }
                    else{
                        assert playerNotFound != null;
                        player.sendMessage(playerNotFound + args[0]);
                    }
                }
            }
            else {
                assert noPerms != null;
                player.sendMessage(noPerms);
            }
        }
        return true;
    }
}
