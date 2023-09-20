package me.cole.managerplugin.commands.util;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                int time = 0;
                if (args.length > 0) {
                    try {
                        time = Integer.parseInt(args[0]);
                    } catch (Exception ignored) {
                    }
                }
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /time <day|night|time>");
                }

                else if (args[0].equals("day")){
                    world.setTime(0);
                    player.sendMessage(ChatColor.YELLOW + "Time set to DAY in your world.");
                }
                else if (args[0].equals("night")){
                    world.setTime(13000);
                    player.sendMessage(ChatColor.YELLOW + "Time set to NIGHT in your world.");
                }
                else if (time <= 20000 && time >= 0) {
                    world.setTime(time);
                    player.sendMessage(ChatColor.YELLOW + "Time set to " + time + " in your world.");
                }
                else {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /time <day|night|time>");
                }
            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
