package me.cole.managerplugin;

import me.cole.managerplugin.commands.punish.BanCommand;
import me.cole.managerplugin.commands.punish.KickCommand;
import me.cole.managerplugin.commands.punish.MuteCommand;
import me.cole.managerplugin.commands.staff.*;
import me.cole.managerplugin.commands.user.ListCommand;
import me.cole.managerplugin.commands.user.MessageCommand;
import me.cole.managerplugin.commands.user.MessageManager;
import me.cole.managerplugin.commands.user.ReplyCommand;
import me.cole.managerplugin.commands.util.SetSpawnCommand;
import me.cole.managerplugin.commands.util.SpawnCommand;
import me.cole.managerplugin.commands.staff.TeleportCommand;
import me.cole.managerplugin.commands.util.TimeCommand;
import me.cole.managerplugin.listeners.onPlayerChat;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Manager extends JavaPlugin {
    Manager plugin;
    public MessageManager mm;

    @Override
    public void onEnable() {
        // Startup logic
        plugin = this;
        mm = new MessageManager(this);
        System.out.println("[Manager] big booty bitches");

        // Register commands
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand(this));
        Objects.requireNonNull(getCommand("teleport")).setExecutor(new TeleportCommand(this));
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand(this));
        Objects.requireNonNull(getCommand("message")).setExecutor(new MessageCommand(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand(this));
        Objects.requireNonNull(getCommand("reply")).setExecutor(new ReplyCommand(this));
        Objects.requireNonNull(getCommand("mute")).setExecutor(new MuteCommand(this));
        Objects.requireNonNull(getCommand("kick")).setExecutor(new KickCommand(this));
        Objects.requireNonNull(getCommand("time")).setExecutor(new TimeCommand(this));
        Objects.requireNonNull(getCommand("give")).setExecutor(new GiveCommand(this));
        Objects.requireNonNull(getCommand("ban")).setExecutor(new BanCommand(this));
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this));
        Objects.requireNonNull(getCommand("list")).setExecutor(new ListCommand());

        // Register listeners
        getServer().getPluginManager().registerEvents(new onPlayerChat(plugin), this);

        // Configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
