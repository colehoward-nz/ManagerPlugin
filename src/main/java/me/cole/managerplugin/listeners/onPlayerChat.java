package me.cole.managerplugin.listeners;

import me.cole.managerplugin.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onPlayerChat implements Listener {
    private final Manager plugin;

    public onPlayerChat(Manager plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player != null) {
            String isMuted = plugin.getConfig().getString("player." + player.getUniqueId() + ".muted.ismuted");
            String muteReason = plugin.getConfig().getString("player." + player.getUniqueId() + ".muted.reason") + "\n";
            String muteApplier = plugin.getConfig().getString("player." + player.getUniqueId() + ".muted.applier") + "\n";
            if (isMuted.equalsIgnoreCase("true") && !player.isOp()){
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You are currently muted.\n" +
                        "Reason: " + muteReason +
                        "Applier: " + muteApplier);
            }
        }
    }
}
