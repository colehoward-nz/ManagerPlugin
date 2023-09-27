package me.cole.managerplugin.commands.staff;

import me.cole.managerplugin.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Objects;

public class GiveCommand implements CommandExecutor {
    private final Manager plugin;
    private final String noPerms;

    public GiveCommand(Manager plugin){
        this.plugin = plugin;
        this.noPerms = plugin.getConfig().getString("no-perms");
    }

    public void givePlayerItem(Player player, Player toGive, String item, int quantity) {
        ItemStack itemToGive = null;
        try {
            itemToGive = new ItemStack(Material.valueOf(item.toUpperCase()), quantity);
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Can't find material " + item);
        }

        if (itemToGive != null) {
            if (toGive == null) {
                PlayerInventory playerInventory = player.getInventory();
                playerInventory.addItem(itemToGive);

                player.sendMessage(ChatColor.YELLOW + "Gave " + itemToGive.getType() +
                        " (" + ChatColor.AQUA + "x" + quantity + ChatColor.YELLOW + ") to " + player.getDisplayName());
            }
            else {
                PlayerInventory playerInventory = toGive.getInventory();
                playerInventory.addItem(itemToGive);

                player.sendMessage(ChatColor.YELLOW + "Gave " + itemToGive.getType() +
                        " (" + ChatColor.AQUA + "x" + quantity + ChatColor.YELLOW + ") to " + toGive.getDisplayName());
            }
        }
    }
    public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("manager.give") || player.isOp()) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Incorrect usage: /give <item> [quantity] [player]");
                    return true;
                }
                else if (args.length == 1) {
                    givePlayerItem(player, null, args[0], 1);
                    return true;
                }

                try {
                    if (args.length == 3) {
                        Player argumentPlayer = Bukkit.getServer().getPlayerExact(args[2]);
                        givePlayerItem(player, argumentPlayer, args[0], Integer.parseInt(args[1]));
                    }
                    else {
                        givePlayerItem(player, null, args[0], Integer.parseInt(args[1]));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("me.cole.managerplugin.commands.staff.givecommand : Cannot covert to integer -> " + args[1]);
                    player.sendMessage(ChatColor.RED + "Can't convert " + args[1] + " to an integer");
                }


            }
            else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerms));
            }
        }
        return true;
    }
}
