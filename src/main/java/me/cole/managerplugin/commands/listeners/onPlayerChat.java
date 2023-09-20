package me.cole.managerplugin.commands.listeners;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class onPlayerChat implements Listener {

    private final Manager plugin;

    public onPlayerChat(Manager plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player != null) {
            String isMuted;
            isMuted = plugin.getConfig().getString("players" + player.getUniqueId().toString() + "muted");

            if (isMuted.equalsIgnoreCase("true")){
                player.sendMessage(ChatColor.RED + "You are currently muted." +
                        "\nReason: " + plugin.getConfig().getString("players" + player.getUniqueId().toString() + "muted.reason") +
                        "\nApplier: " + plugin.getConfig().getString("players" + player.getUniqueId().toString() + "muted.applier"));
                event.setCancelled(true);
            }
        }
    }
}
