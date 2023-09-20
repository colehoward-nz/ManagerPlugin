package me.cole.managerplugin.commands;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Time;

public class TimeCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;

    public TimeCommand(Manager plugin) {
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player player) {
            World world = player.getWorld();

            if (player.hasPermission("manager.time")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /time <day|night|time>");
                }
                else if (args[0].equalsIgnoreCase("day")){
                    world.setTime(0);
                    player.sendMessage(ChatColor.YELLOW + "Time set to DAY in your world.");
                }
                else if (args[0].equalsIgnoreCase("night")){
                    world.setTime();
                    player.sendMessage(ChatColor.YELLOW + "Time set to DAY in your world.");
                }
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
