package me.cole.managerplugin.commands;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    private Manager plugin;

    public GamemodeCommand(Manager plugin){
        this.plugin = plugin;
    }

    public void changeGamemode(Player player, String gamemode){
        if (gamemode.equalsIgnoreCase("c") || gamemode.equalsIgnoreCase("creative")){
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.YELLOW + "Gamemode set to CREATIVE");
        }
        else if (gamemode.equalsIgnoreCase("s") || gamemode.equalsIgnoreCase("survival")){
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.YELLOW + "Gamemode set to SURVIVAL");
        }
        else if (gamemode.equalsIgnoreCase("a") || gamemode.equalsIgnoreCase("adventure")){
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(ChatColor.YELLOW + "Gamemode set to ADVENTURE");
        }
        else if (gamemode.equalsIgnoreCase("sp") || gamemode.equalsIgnoreCase("spec") || gamemode.equalsIgnoreCase("spectator")){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.YELLOW + "Gamemode set to SPECTATOR");
        }
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            String noPerms = plugin.getConfig().getString("no-perms");
            if (player.hasPermission("manager.gamemode")){
                if (args.length == 0){
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /gamemode <gamemode> [player]");
                }
                else if (args.length == 1){
                    changeGamemode(player, args[0]);
                }
                else if (args.length == 2){
                    Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[1]);
                    if (argumentPlayer != null){
                        changeGamemode(argumentPlayer, args[0]);
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "Could not find player " + args[1] + ".");
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


