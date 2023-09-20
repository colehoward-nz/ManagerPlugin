package me.cole.managerplugin.commands.punish;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class MuteCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;
    public MuteCommand(Manager plugin) {
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public void mutePlayer(CommandSender staff, Player player, String reason){
        boolean isMuted = false;
        try {
            isMuted = (boolean) plugin.getConfig().get("players." + player.getUniqueId().toString() + "muted");
        } catch (NullPointerException nullPointerException) {
            System.out.println("[Manager] nullPtrExcept in MuteCommand.addMute");
        }

        if (isMuted) {
            if (reason == null) {
                reason = "Not specified";
            }
            plugin.getConfig().set("players." + player.getUniqueId().toString() + "muted", true);
            plugin.getConfig().set("players." + player.getUniqueId().toString() + "muted.reason", reason);
            plugin.getConfig().set("players." + player.getUniqueId().toString() + "muted.applier", staff);

        }
        else {
            plugin.getConfig().set("players." + player.getUniqueId().toString() + "muted", false);
            plugin.getConfig().set("players." + player.getUniqueId().toString() + "muted.reason", null);
            plugin.getConfig().set("players." + player.getUniqueId().toString() + "muted.applier", null);
        }

    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.mute")){
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /mute <player> [reason]");
                }
                else {
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[0]);
                    if (args.length == 1) {
                        player.sendMessage(ChatColor.YELLOW + "Mute without reason");
                        mutePlayer(player, argumentPlayer, null);
                    }
                    else {
                        player.sendMessage(ChatColor.YELLOW + "Mute with reason:\n" + argumentPlayer +"\n"+ args[1]);
                        mutePlayer(player, argumentPlayer, args[1]);
                    }
                }

            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
