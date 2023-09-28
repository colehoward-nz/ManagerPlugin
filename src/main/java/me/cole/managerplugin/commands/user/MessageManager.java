package me.cole.managerplugin.commands.user;

import me.cole.managerplugin.Manager;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {

    private final Manager plugin;
    public HashMap<Player, Player> conversations = new HashMap<Player, Player>();

    public MessageManager(Manager plugin) {
        this.plugin = plugin;
    }

    public void setReplyTarget(Player messager, Player reciever) {
        conversations.put(messager, reciever);
        conversations.put(reciever, messager);
    }

    public Player getReplyTarget(Player messager) {
        if (conversations.get(messager)!=null){
            return conversations.get(messager);
        }
        return null;
    }
}
