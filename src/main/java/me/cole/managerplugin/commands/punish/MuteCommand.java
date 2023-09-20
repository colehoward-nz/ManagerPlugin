package me.cole.managerplugin.commands.punish;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MuteCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;
    public MuteCommand(Manager plugin) {
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public void addMute (Player staff, Player player, String reason){
        List<String> test = plugin.getConfig().getStringList("muted_players)");
        System.out.print(test);
    }

    public void removeMute(Player staff, Player player, String reason) {

    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.mute")){
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /mute <player> [reason]");
                }
                Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                if (args.length == 1) {

                    addMute(player, argumentPlayer, null);
                }
                else {
                    addMute(player, argumentPlayer, args[1]);
                }
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
