package me.cole.managerplugin.commands.staff;

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
    private final String noPerms;
    private final String playerNotFound;
    private final String flyOn;
    private final String flyOff;
    public FlyCommand(Manager plugin){
        this.plugin = plugin;
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
        this.noPerms = plugin.getConfig().getString("no-perms");
        this.flyOff = plugin.getConfig().getString("fly-off");
        this.flyOn = plugin.getConfig().getString("fly-on");
    }

    public void toggleFly(Player player) {
        if (flyingPlayers.contains(player)) {
            flyingPlayers.remove(player);
            player.setAllowFlight(false);
            assert flyOff != null;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', flyOff) + player.getDisplayName());
        } else {
            flyingPlayers.add(player);
            player.setAllowFlight(true);
            assert flyOn != null;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', flyOn) + player.getDisplayName());
        }
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            if (player.hasPermission("manager.fly") || player.isOp()){
                if (args.length == 0) {
                    toggleFly(player);
                }
                else{
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (argumentPlayer != null){
                        toggleFly(argumentPlayer);
                    }
                    else{
                        assert playerNotFound != null;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound) + args[0]);
                    }
                }
            }
            else {
                assert noPerms != null;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
