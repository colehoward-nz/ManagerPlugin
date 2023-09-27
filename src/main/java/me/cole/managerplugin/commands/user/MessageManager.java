package me.cole.managerplugin.commands.user;

import me.cole.managerplugin.Manager;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {
    public HashMap<Player, Player> conversations = new HashMap<Player, Player>();

    public void setReplyTarget(Player messager, Player reciever) {
        conversations.put(messager, reciever);
        conversations.put(reciever, messager);
    }

    public Player getReplyTarget(Player messager) {
        return conversations.get(messager);
    }
}
