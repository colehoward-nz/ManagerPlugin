package me.cole.managerplugin.commands.staff;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    private final Manager plugin;
    private final String playerNotFound;
    private final String gamemodeMessage;
    private final String noPerms;

    public GamemodeCommand(Manager plugin){
        this.plugin = plugin;
        this.playerNotFound = plugin.getConfig().getString("player-not-found");
        this.gamemodeMessage = plugin.getConfig().getString("gamemode-message");
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public void changeGamemode(Player player, String gamemode){
        if (gamemode.equalsIgnoreCase("c") || gamemode.equalsIgnoreCase("creative")){
            player.setGameMode(GameMode.CREATIVE);
        }
        else if (gamemode.equalsIgnoreCase("s") || gamemode.equalsIgnoreCase("survival")){
            player.setGameMode(GameMode.SURVIVAL);
        }
        else if (gamemode.equalsIgnoreCase("a") || gamemode.equalsIgnoreCase("adventure")){
            player.setGameMode(GameMode.ADVENTURE);
        }
        else if (gamemode.equalsIgnoreCase("sp") || gamemode.equalsIgnoreCase("spec") || gamemode.equalsIgnoreCase("spectator")){
            player.setGameMode(GameMode.SPECTATOR);
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', gamemodeMessage) + ChatColor.YELLOW + player.getGameMode().toString().toUpperCase());
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player player){
            if (player.hasPermission("manager.gamemode") || player.isOp()){
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
                        assert playerNotFound != null;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound) + args[1] + ".");
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


