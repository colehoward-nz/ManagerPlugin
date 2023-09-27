package me.cole.managerplugin.commands.user;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ListCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player player) {
            StringBuilder str = new StringBuilder();
            for(Player ps : Bukkit.getOnlinePlayers()){
                if(!str.isEmpty()){
                    str.append(", ");
                }
                if(ps.isOp()) {
                    str.append(ChatColor.RED).append(ps.getName()).append(ChatColor.YELLOW);
                } else {
                    str.append(ChatColor.YELLOW).append(ps.getName()).append(ChatColor.GRAY);
                }
            }
            if (Bukkit.getOnlinePlayers().size() > 1) {
                player.sendMessage(ChatColor.YELLOW + "There are currently " + Bukkit.getOnlinePlayers().size() + " players online.\n" + str);
            }
            else {
                player.sendMessage(ChatColor.YELLOW + "There is currently " + Bukkit.getOnlinePlayers().size() + " player online.\n" + str);
            }
        }
        return false;
    }
}
